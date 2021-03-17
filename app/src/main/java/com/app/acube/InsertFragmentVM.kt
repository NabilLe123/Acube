package com.app.acube

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.app.acube.database.RoomDbInstance
import com.app.acube.helper.Misc
import com.app.acube.model.Stock

class InsertFragmentVM internal constructor(
    private val mContext: Context
) {
    var etStockName = ObservableField("")
    var etStockCost = ObservableField("")

    fun logout(view: View) {
        Misc.reLogin(mContext.getString(R.string.logout_successful), mContext)
    }

    fun onSubmit(view: View) {
        Log.d("acube", etStockName.get() + " : " + etStockCost.get())

        when {
            TextUtils.isEmpty(etStockName.get().toString().trim()) ->
                Misc.showAlert(mContext, mContext.getString(R.string.pls_enter_stock_name))

            TextUtils.isEmpty(etStockCost.get().toString().trim()) ->
                Misc.showAlert(mContext, mContext.getString(R.string.pls_enter_stock_cost))

            else -> insertData(etStockName.get().toString(), etStockCost.get().toString().trim())
        }
    }

    private fun insertData(stockName: String, stockCost: String) {
        Thread {
            val stock = Stock(stockName, stockCost)
            RoomDbInstance.getAppDb(mContext).stockDao().insertStock(stock)
        }.start()

        etStockName.set("")
        etStockCost.set("")
        Misc.showAlert(mContext, mContext.getString(R.string.stock_added))
    }
}