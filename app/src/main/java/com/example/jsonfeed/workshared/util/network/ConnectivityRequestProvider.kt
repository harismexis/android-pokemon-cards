package com.example.jsonfeed.workshared.util.network

import android.net.NetworkCapabilities
import android.net.NetworkRequest

class ConnectivityRequestProvider {

    fun provideNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }

}

