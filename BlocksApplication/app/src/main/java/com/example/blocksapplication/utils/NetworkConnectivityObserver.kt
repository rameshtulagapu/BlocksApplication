package com.example.blocksapplication.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class NetworkConnectivityObserver(
    application: Application
) : IConnectivityObserver {

    private val connectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<IConnectivityObserver.Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(IConnectivityObserver.Status.Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(IConnectivityObserver.Status.Losing) }
                }
                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(IConnectivityObserver.Status.Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(IConnectivityObserver.Status.Unavailable) }

                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }

    override fun isConnected(): IConnectivityObserver.Status {
        return if (connectivityManager.activeNetwork != null &&
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null)
            IConnectivityObserver.Status.Available
        else
            IConnectivityObserver.Status.Unavailable
    }
}