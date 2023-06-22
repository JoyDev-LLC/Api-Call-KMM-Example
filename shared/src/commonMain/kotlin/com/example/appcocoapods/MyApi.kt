package com.example.appcocoapods

expect class MyApi() {
    suspend fun getDuckUrl(): String
}
