package com.app.acube.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.app.acube.auth.SignInActivity

object Misc {

    private fun showToast(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showAlert(context: Context, message: String) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    fun reLogin(showMsg: String?, context: Context) {
        showToast(context, showMsg)

        //clear db of auth credentials
        val authSharedPref = AuthSharedPref(context)
        authSharedPref.insertLoginDetails(-1, "")

        val intent = Intent(context, SignInActivity::class.java)
        (context as Activity).finishAffinity()
        context.startActivity(intent)
        context.overridePendingTransition(0, 0)
    }
}