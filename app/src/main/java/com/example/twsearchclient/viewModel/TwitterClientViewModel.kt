package com.example.twsearchclient.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.twsearchclient.*
import com.example.twsearchclient.entity.TwitterResponseApi
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TwitterClientViewModel  : BaseViewModel() {

    @Inject
    lateinit var api: Api

    private lateinit var subscription: Disposable

    var loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    var twitterLiveData: MutableLiveData<TwitterResponseApi> = MutableLiveData()


    init {
        getTweets()
    }

    private fun getTweets() {
        val url: String? = UrlBuilder.getTweets()
        subscription = api.getApi(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { t: Throwable? -> onRetrievePostListError(t) }
            )!!

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = true
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = false
    }

    private fun onRetrievePostListSuccess(apiResponse: TwitterResponseApi) {
        if (apiResponse.success!!) {
            val mapper = ObjectMapper()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            val twitterEntity: TwitterResponseApi = mapper.convertValue(apiResponse, object : TypeReference<TwitterResponseApi>() {})
            twitterLiveData.value = twitterEntity
        }
    }


    private fun onRetrievePostListError(t: Throwable?) {
        if (t != null) {
            errorMessage.value = t.message
        }
    }

}

