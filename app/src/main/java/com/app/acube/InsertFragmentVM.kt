package com.app.acube

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.app.acube.helper.Misc

class InsertFragmentVM internal constructor(
    private val mContext: Context
) {
    var etStockName = ObservableField("")
    var etStockCost = ObservableField("")

    fun onSubmit(view: View) {
        Log.d("rxjava", etStockName.get() + " : " + etStockCost.get())

        when {
            TextUtils.isEmpty(etStockName.get().toString().trim()) ->
                Misc.showAlert(mContext, mContext.getString(R.string.pls_enter_stock_name))

            TextUtils.isEmpty(etStockCost.get().toString().trim()) ->
                Misc.showAlert(mContext, mContext.getString(R.string.pls_enter_stock_cost))

            else -> insertData()
        }
    }

    private fun insertData() {

    }
}