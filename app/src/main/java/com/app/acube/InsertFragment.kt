package com.app.acube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.app.acube.databinding.FragmentInsertBinding

class InsertFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentInsertBinding: FragmentInsertBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_insert, container, false)
        val insertFragmentVM = InsertFragmentVM(activity!!)
        fragmentInsertBinding.insertFragmentVM = insertFragmentVM

        return fragmentInsertBinding.root
    }
}