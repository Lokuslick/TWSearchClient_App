package com.example.twsearchclient

import com.example.twsearchclient.entity.TwitterResponseApi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {

    @GET
    fun getApi(@Url url: String?): Observable<TwitterResponseApi>


}