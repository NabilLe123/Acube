package com.app.acube.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "LoginResult", strict = false)
class Login {
    @field:Element(name = "EmployeeCode", required = false)
    var employeeCode: Int? = 0

    @field:Element(name = "Success", required = false)
    var success: Boolean? = null

    @field:Element(name = "ErrorMessage", required = false)
    var errorMessage: String? = null
}