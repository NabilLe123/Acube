package com.app.acube

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

class DisplayFragmentVM internal constructor(mContext: Context) {

    var showSpinner = ObservableBoolean(false)
    var tvStock = ObservableField(mContext.getString(R.string.select_stock))
}