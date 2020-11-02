package com.example.twsearchclient.common

import com.example.twsearchclient.entity.TwitterEntity

open class GenericAdapterEntity{

    private var viewPosition : Int = 0

    var onClickInterface: OnClickInterface<TwitterEntity>? = null


    fun getViewPosition(): Int {
        return viewPosition
    }

    fun setViewPosition(viewPosition: Int) {
        this.viewPosition = viewPosition
    }


}
