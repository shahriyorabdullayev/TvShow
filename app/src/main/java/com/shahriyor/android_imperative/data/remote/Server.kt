package com.shahriyor.android_imperative.data.remote

object Server {

    val IS_TESTER = true

    init{
        System.loadLibrary("keys")
    }

    external fun getServerDevelopment(): String
    external fun getServerProduction(): String

}