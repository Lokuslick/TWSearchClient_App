package com.example.twsearchclient.injection.module

import android.util.Base64
import okhttp3.Credentials
import okhttp3.Interceptor

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)
    var creds = String.format("%s:%s", "Logesh", "123456")
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "Basic " + Base64.encodeToString(creds.toByteArray(), Base64.NO_WRAP))
            .header("userId", "63748jdfbjd")
            .build()
        return chain.proceed(request)
    }
}