package com.oxford.sean.dataia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiLoginResponse(@SerialName("token") val token: String)