package com.app.acube.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList
import org.simpleframework.xml.Root

@Root(name = "soapenv:Envelope")
@NamespaceList(
    Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
    Namespace(prefix = "ser", reference = "http://services.acubeinfotech"),
    Namespace(
        prefix = "ai",
        reference = "http://schemas.datacontract.org/2004/07/ai.webinterface.authentication"
    )
)
class LoginRequestEnvelope(
    @field:Element(name = "soapenv:Body", required = false)
    var loginRequestBody: LoginRequestBody?
) {

    class LoginRequestBody {
        @field:Element(name = "ser:Login", required = false)
        var loginRequestModel: LoginRequestModel? = null
    }
}