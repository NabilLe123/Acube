package com.app.acube.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Login")
class LoginRequestModel {
    @field:Element(name = "ser:loginParam", required = false)
    var loginParams: LoginParams? = null

    class LoginParams {
        @field:Element(name = "ai:UserName", required = false)
        var username: String? = null

        @field:Element(name = "ai:Password", required = false)
        var password: String? = null
    }
}