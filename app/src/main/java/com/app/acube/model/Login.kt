package com.app.acube.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "LoginResult", strict = false)
class Login {
    @field:Element(name = "EmployeeCode", required = false)
    private var employeeCode: Int? = 0

    @field:Element(name = "Success", required = false)
    private var success: Boolean? = null

    fun getEmployeeCode(): Int? {
        return employeeCode
    }

    fun setEmployeeCode(employeeCode: Int?) {
        this.employeeCode = employeeCode
    }

    fun isSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }
}