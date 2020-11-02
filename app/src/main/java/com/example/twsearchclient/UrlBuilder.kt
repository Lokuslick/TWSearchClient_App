package com.example.twsearchclient

class UrlBuilder {
    companion object UrlBuilderFactory{
        fun getTweets(): String{
            return BASE_URL+"tweets"
        }
    }
}