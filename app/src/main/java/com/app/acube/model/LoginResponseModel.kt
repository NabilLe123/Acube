package com.app.acube.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList
import org.simpleframework.xml.Root


@Root(name = "LoginResponse", strict = false)
@Namespace(reference = "http://services.acubeinfotech")
class LoginResponseModel {
    @field:Element(name = "LoginResult", required = false)
    @NamespaceList(
        Namespace(
            prefix = "a",
            reference = "http://schemas.datacontract.org/2004/07/ai.webinterface.authentication"
        ),
        Namespace(
            prefix = "i",
            reference = "http://www.w3.org/2001/XMLSchema-instance"
        )
    )
    var login: Login? = null
}