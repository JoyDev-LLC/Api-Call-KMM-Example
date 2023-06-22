package com.example.appcocoapods

import kotlin.coroutines.suspendCoroutine
import cocoapods.AFNetworking.*
import platform.Foundation.NSDictionary
import kotlin.coroutines.resume

actual class MyApi actual constructor() {

    private val manager = AFHTTPSessionManager()

    actual suspend fun getDuckUrl(): String = suspendCoroutine { continuation ->
        manager.requestSerializer = AFJSONRequestSerializer()
        manager.GET(
            URLString = "https://random-d.uk/api/random",
            parameters = null,
            success = { _, response ->
                val json = response as NSDictionary
                val url = json.objectForKey("url").toString()
                continuation.resume(url)
            },
            failure = { _, _, ->
                throw RuntimeException()
            },
            headers = null,
            progress = null
        )
    }

}
