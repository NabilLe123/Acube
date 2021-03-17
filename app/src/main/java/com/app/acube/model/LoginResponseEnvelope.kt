package com.app.acube.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "s:Envelope")
@Namespace(
    prefix = "s",
    reference = "http://schemas.xmlsoap.org/soap/envelope/"
)
class LoginResponseEnvelope {
    @field:Element(name = "Body")
    @Namespace(prefix = "s")
    var loginResponseBody: LoginResponseBody? = null

    class LoginResponseBody {
        @field:Element(name = "LoginResponse", required = false)
        @Namespace(reference = "http://services.acubeinfotech")
        var loginResponseModel: LoginResponseModel? = null
    }
}