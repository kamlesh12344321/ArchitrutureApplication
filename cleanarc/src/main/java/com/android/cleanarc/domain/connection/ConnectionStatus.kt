package com.android.cleanarc.domain.connection

 sealed class ConnectionStatus {
   object Available : ConnectionStatus()
     object Unavailable: ConnectionStatus()
}