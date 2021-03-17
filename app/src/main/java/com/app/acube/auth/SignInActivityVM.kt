package com.app.acube.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.app.acube.MainActivity
import com.app.acube.R
import com.app.acube.api.ApiClient
import com.app.acube.api.ApiInterface
import com.app.acube.helper.AuthSharedPref
import com.app.acube.helper.CustomLoadingPb
import com.app.acube.helper.Misc
import com.app.acube.model.Login
import com.app.acube.model.LoginRequestEnvelope
import com.app.acube.model.LoginRequestModel
import com.app.acube.model.LoginResponseEnvelope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivityVM internal constructor(
    private val mContext: Context
) {
    var etUsername = ObservableField("")
    var etPassword = ObservableField("")

    fun onSubmit(view: View) {
        Log.d("acube", etUsername.get() + " : " + etPassword.get())

        if (TextUtils.isEmpty(etUsername.get().toString().trim())
            ||
            TextUtils.isEmpty(etPassword.get().toString().trim())
        )
            Misc.showAlert(mContext, mContext.getString(R.string.invalid_credentials))
        else
            loginUser()
    }

    private fun loginUser() {
        val customLoadingPb = CustomLoadingPb(mContext)
        customLoadingPb.show()

        val loginParams = LoginRequestModel.LoginParams()
        loginParams.username = etUsername.get()!!.trim()
        loginParams.password = etPassword.get()!!.trim()

        val loginRequestModel = LoginRequestModel()
        loginRequestModel.loginParams = loginParams

        val loginRequestBody: LoginRequestEnvelope.LoginRequestBody =
            LoginRequestEnvelope.LoginRequestBody()
        loginRequestBody.loginRequestModel = loginRequestModel

        val loginRequestEnvelope = LoginRequestEnvelope(loginRequestBody)
        val header = HashMap<String, String>()
        header["Content-Type"] = "text/xml;charset=UTF-8"
        header["SOAPAction"] = "http://services.acubeinfotech/IAcubeDataExchange/Login"

        val apiInterface: ApiInterface = ApiClient.getApiInterface()
        val call: Call<LoginResponseEnvelope> =
            apiInterface.authenticateUser(header, loginRequestEnvelope)
        call.enqueue(object : Callback<LoginResponseEnvelope?> {
            override fun onResponse(
                call: Call<LoginResponseEnvelope?>,
                response: Response<LoginResponseEnvelope?>
            ) {
                loadingFinish(customLoadingPb)

                val loginResponseEnvelope: LoginResponseEnvelope? = response.body()
                if (loginResponseEnvelope != null) {
                    onLoginSuccess(loginResponseEnvelope.loginResponseBody!!.loginResponseModel!!.login!!)

                } else {
                    Misc.showAlert(mContext, mContext.getString(R.string.server_error_retry))
                }
            }

            override fun onFailure(call: Call<LoginResponseEnvelope?>, t: Throwable) {
                Log.d("acube", "onFailure: " + t.message)
                loadingFinish(customLoadingPb)
                Misc.showAlert(mContext, mContext.getString(R.string.server_error_retry))
            }
        })
    }

    fun onLoginSuccess(login: Login) {
        Log.d("acube", ": " + login.success + " : " + login.employeeCode)

        if (login.success == true) {
            val authPrefReference = AuthSharedPref(mContext)
            authPrefReference.insertLoginDetails(login.employeeCode!!, "")

            val intent = Intent(mContext, MainActivity::class.java)
            mContext.startActivity(intent)
            (mContext as Activity).finish()
        } else if (!TextUtils.isEmpty(login.errorMessage)) {
            Misc.showAlert(mContext, login.errorMessage!!)
        }
    }

    fun loadingFinish(customLoadingPb: CustomLoadingPb) {
        customLoadingPb.dismiss()
    }
}