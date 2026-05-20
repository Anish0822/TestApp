package com.app.testapp.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<N> : ViewModel() {

    var navigator: N? = null

    @JvmName("assignNavigator")
    fun setNavigator(navigator: N) {
        this.navigator = navigator
    }
}