package com.example.twsearchclient.common

import android.view.View

interface OnClickInterface<T> {
    fun onClick(view: View?, t: T)
}