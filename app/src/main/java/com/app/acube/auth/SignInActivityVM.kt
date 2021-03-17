package com.app.acube.auth

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import com.app.acube.R
import com.app.acube.api.ApiClient
import com.app.acube.api.ApiInterface
import com.app.acube.helper.Misc
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
        Log.d("rxjava", etUsername.get() + " : " + etPassword.get())

        if (TextUtils.isEmpty(etUsername.get().toString().trim())
            ||
            TextUtils.isEmpty(etPassword.get().toString().trim())
        )
            Misc.showAlert(mContext, mContext.getString(R.string.invalid_credentials))
        else
            loginUser()
    }

    private fun loginUser() {
//        val customLoadingPb = CustomLoadingPb(mContext)
//        customLoadingPb.show()

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
                val movieResponse: LoginResponseEnvelope? = response.body()
                if (movieResponse != null) {
                    Log.d(
                        "now_showing",
                        ": " + movieResponse.loginResponseBody!!.loginResponseModel!!.login!!.isSuccess() +
                                " : " + movieResponse.loginResponseBody!!.loginResponseModel!!.login!!.getEmployeeCode()
                    )
                } else Log.d("now_showing", "loadMovies: null")
            }

            override fun onFailure(call: Call<LoginResponseEnvelope?>, t: Throwable) {
                Log.d("now_showing", "loadMovies: " + t.message)
                Toast.makeText(
                    mContext,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

//    fun onLoginSuccess(login: Login) {
//        AuthPrefReference.getAuthSharedPref(mContext)!!
//            .insertLoginDetails(login.id, login.name, login.email, login.isVerified, login.token)
//
//        if (login.isVerified == "1") {
//            val intent = Intent(mContext, MainActivity::class.java)
//            mContext.startActivity(intent)
//            (mContext as Activity).finish()
//        } else {
//            val intent = Intent(mContext, EnterMobileActivity::class.java)
//            mContext.startActivity(intent)
//            (mContext as Activity).finish()
//        }
//    }
//
//    fun loadingFinish(customLoadingPb: CustomLoadingPb) {
//        customLoadingPb.dismiss()
//    }
}