package com.app.acube.helper

import android.content.Context

class AuthSharedPref(context: Context) {
    private val employeeCode = "employeeCode"
    private val uName = "uName"
    private val pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    fun insertLoginDetails(
        employeeCode1: Int,
        uName1: String
    ) {
        val editor = pref.edit()
        editor.putInt(employeeCode, employeeCode1)
        editor.putString(uName, uName1)
        editor.apply()
    }

    fun getEmployeeCode(): Int {
        return pref.getInt(employeeCode, -1)
    }
}