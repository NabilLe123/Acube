package com.app.acube.api;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ApiClient {
    private static final String BASE_URL_ = "http://acuberfid.fortiddns.com:8001/";
    private static Retrofit retrofit = null;
    private static ApiInterface apiInterface = null;

    public static ApiInterface getApiInterface() {
        if (apiInterface == null) {
            apiInterface = getClient().create(ApiInterface.class);
        }
        return apiInterface;
    }

    private static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor1 = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
            HttpLoggingInterceptor interceptor2 = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.HEADERS);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(interceptor1)
                    .addInterceptor(interceptor2);
            OkHttpClient OkHttpClient = httpClient.build();

            Strategy strategy = new AnnotationStrategy();
            Serializer serializer = new Persister(strategy);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_)
                    .addConverterFactory(SimpleXmlConverterFactory.create(serializer))//To get result as XML
                    .client(OkHttpClient)
                    .build();
        }
        return retrofit;
    }
}
