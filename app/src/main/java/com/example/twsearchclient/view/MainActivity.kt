package com.example.twsearchclient.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.kotlininstitute.injection.ViewModelFactory
import com.example.twsearchclient.BR
import com.example.twsearchclient.common.GenericAdapter
import com.example.twsearchclient.R
import com.example.twsearchclient.entity.TwitterResponseApi
import com.example.twsearchclient.databinding.ActivityMainBinding
import com.example.twsearchclient.entity.TwitterEntity
import com.example.twsearchclient.viewModel.TwitterClientViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var twitterList : List<TwitterEntity> = mutableListOf()

    @Inject
    @JvmField
    var modelFactory: ViewModelFactory? = null
    private lateinit var viewModel: TwitterClientViewModel
    private var adapterTwitterEntity: GenericAdapter<TwitterEntity>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        initListener()
        initViewModel()
        observeLiveData()

    }

    private fun initListener() {
        binding.btnSearch.setOnClickListener(onClick)
    }


    private val onClick =
        View.OnClickListener { view ->
            when (view.id) {
                R.id.btnSearch -> {
                    getSearchedItem(binding.etSeach.text.toString())
                }
            }
        }



    private fun getSearchedItem(string: String) {
        val searchList :  MutableList<TwitterEntity> = arrayListOf()
        for (twitterEntity in twitterList) {
            if((twitterEntity.name?.contains(string,ignoreCase = true)!! || twitterEntity.handle!!.contains(string,ignoreCase = true) || twitterEntity.text!!.contains(string,ignoreCase = true)) && twitterEntity.text!=null){
                searchList.add(twitterEntity)
            }
        }
        if(!searchList.isEmpty()) {
            binding.llError.visibility = View.GONE
            binding.rvRoles.visibility = View.VISIBLE
            setGenericAdapter(searchList)
        }else{
            binding.llError.visibility = View.VISIBLE
            binding.rvRoles.visibility = View.GONE

        }
    }

    private fun observeLiveData() {
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError() })
        viewModel.twitterLiveData.observe(this, Observer { apiresponse -> handleResponse(apiresponse) })
        viewModel.loadingVisibility.observe(this, Observer { isLoading -> handleLoading(isLoading) })
        binding.viewModel = viewModel

    }

    private fun handleLoading(isLoading: Boolean?) {
        if (isLoading!!){
            binding.rlLoader.visibility = View.VISIBLE
        }else{
            binding.rlLoader.visibility = View.GONE

        }

    }

    private fun handleResponse(apiresponse: TwitterResponseApi) {
        if (apiresponse.success!! && apiresponse.data!=null){
            twitterList = emptyList()
            twitterList = apiresponse.data!!
            setGenericAdapter(apiresponse.data)
        }

    }

    private fun setGenericAdapter(twitterList: List<TwitterEntity>?) {
        if (adapterTwitterEntity == null) {
            adapterTwitterEntity =
                GenericAdapter(
                    twitterList,
                    R.layout.adapter_twitter,
                    BR.twitterEntity,
                    this
                )
            binding.genericAdapter = adapterTwitterEntity
            adapterTwitterEntity!!.notifyDataSetChanged()
        } else {
            adapterTwitterEntity!!.setAdapterList(twitterList)
        }
    }

    private fun hideError() {
        binding.llError.visibility = View.GONE

    }

    private fun showError(errorMessage: String) {
        binding.llError.visibility = View.VISIBLE
        binding.tvErrorMsg.text = errorMessage.toString()
    }


    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, modelFactory)[TwitterClientViewModel::class.java]
    }

}