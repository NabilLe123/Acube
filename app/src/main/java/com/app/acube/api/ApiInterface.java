package com.app.acube.api;

import com.app.acube.model.LoginRequestEnvelope;
import com.app.acube.model.LoginResponseEnvelope;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("iaapi/soap")
    Call<LoginResponseEnvelope> authenticateUser(@HeaderMap Map<String, String> headers, @Body LoginRequestEnvelope requestEnvelope);
}
