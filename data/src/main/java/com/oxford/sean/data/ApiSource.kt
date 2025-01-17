package com.oxford.sean.data

import android.net.Uri
import com.oxford.sean.dataia.model.ApiAppointmentsResponse
import com.oxford.sean.dataia.model.ApiLoginResponse
import com.oxford.sean.util.UnauthorizedRequestException
import com.oxford.sean.util.UnknownException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiSource @Inject constructor(private val httpClient: HttpClient) {

    var token = ""

    private val baseUrl: String =
        Uri.Builder().scheme("https").authority("node-api-for-candidates.onrender.com").build().toString()

    suspend fun login(username: String, password: String): ApiLoginResponse {
        // I tend not to use constants unless certain values need to be synced in multiple places, otherwise it
        // becomes overly-scoped noise imo
        val endpoint = "/signin"

        val body = Json.encodeToString(mapOf("username" to username, "password" to password))

        val response = httpClient.post(baseUrl + endpoint) {
            header(HttpHeaders.ContentType, "application/json")
            setBody(body)
        }

        when {
            response.status.isSuccess() -> return response.body()
            response.status.value == 401 -> throw UnauthorizedRequestException()
            else -> throw UnknownException("${response.status} - ${response.body<String>()}")
        }
    }

    suspend fun getAppointments(): ApiAppointmentsResponse {
        val endpoint = "/appointments"

        val response = httpClient.get(baseUrl + endpoint) {
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(body)
        }

        when {
            response.status.isSuccess() -> return response.body()
            response.status.value == 401 -> throw UnauthorizedRequestException()
            else -> throw UnknownException("${response.status} - ${response.body<String>()}")
        }
    }

}