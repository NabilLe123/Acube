package com.app.acube

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.app.acube.database.RoomDbInstance
import com.app.acube.databinding.FragmentDisplayBinding
import com.app.acube.model.Stock


class DisplayFragment : Fragment() {
    private lateinit var displayFragmentVM: DisplayFragmentVM
    private var stocks: List<Stock> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentDisplayBinding: FragmentDisplayBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_display, container, false)
        displayFragmentVM = DisplayFragmentVM(activity!!)
        fragmentDisplayBinding.displayFragmentVM = displayFragmentVM

        RoomDbInstance.getAppDb(activity).stockDao().fetchAllStocks().observe(this) { stocks1 ->
            if (stocks1 != null && stocks1.isNotEmpty()) {
                stocks = stocks1
                displayFragmentVM.showSpinner.set(true)
            }

            Log.d("acube", "stocks1: $stocks1")
        }

        fragmentDisplayBinding.btnSelect.setOnClickListener {
            showSpinnerDialog()
        }

        return fragmentDisplayBinding.root
    }

    private fun showSpinnerDialog() {
        val items: ArrayList<String> = ArrayList()
        for (stock in stocks) {
            items.add(stock.stockName)
        }

        val spinnerDialog = SpinnerDialog(activity, items, getString(R.string.select_stock), "OK")
        spinnerDialog.setCancellable(true)
        spinnerDialog.showSpinerDialog()

        spinnerDialog.bindOnSpinerListener { item, _ ->
            displayFragmentVM.tvStock.set(item)
        }
    }
}