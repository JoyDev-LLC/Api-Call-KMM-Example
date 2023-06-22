package com.example.appcocoapods

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DuckViewModel : CommonViewModel() {

    private val api = MyApi()

    init { updateDuckAndroid() }

    private val _duckUrl = MutableStateFlow("")
    val duckUrl = _duckUrl.asStateFlow()

    fun updateDuckAndroid() {
        CoroutineScope(Dispatchers.Default).launch {
            val result = api.getDuckUrl()
            _duckUrl.emit(result)
        }
    }

    suspend fun updateDuckIOS(): String = api.getDuckUrl()

}
