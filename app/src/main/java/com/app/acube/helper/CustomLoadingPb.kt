package com.app.acube.helper

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.app.acube.R

class CustomLoadingPb(mContext: Context) {
    private val context = mContext
    private var dialog: Dialog? = null

    fun show() {
        dialog = Dialog(context)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)
        dialog!!.setContentView(R.layout.custom_loading_pb)
        val width = (context.resources.displayMetrics.widthPixels * 0.70).toInt()
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        if (dialog!!.window != null) {
            dialog!!.window!!.setLayout(width, height)
            dialog!!.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog!!.show()
    }

    fun dismiss() {
        if (dialog != null && dialog!!.isShowing) dialog!!.dismiss()
    }
}