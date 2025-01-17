package com.oxford.sean.domain.usecase

import com.oxford.sean.domain.DataGateway
import com.oxford.sean.domain.PresGateway
import com.oxford.sean.domain.entity.ToastDurationEntityType
import com.oxford.sean.util.NoInternetException
import com.oxford.sean.util.UnauthorizedRequestException
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CaseToLoginTest {

    companion object {
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
    }

    private lateinit var dataGateway: DataGateway
    private lateinit var presGateway: PresGateway
    private lateinit var caseToLogin: CaseToLogin

    @Before
    fun setup() {
        dataGateway = mockk()
        presGateway = mockk(relaxed = true)
        caseToLogin = CaseToLogin(dataGateway, presGateway)
    }

    @Test
    fun launch_validCredentials_navigateToAppointments() = runTest {
        coEvery { dataGateway.login(USERNAME, PASSWORD) } just Runs

        caseToLogin.launch(USERNAME, PASSWORD)

        coVerifyOrder {
            presGateway.showLoadingDialog()
            dataGateway.login(USERNAME, PASSWORD)
            presGateway.hideLoadingDialog()
            presGateway.showToast("Login Successful!", ToastDurationEntityType.SHORT)
            presGateway.goToAppointments(clearStack = true)
        }
    }

    @Test
    fun launch_invalidCredentials_showInvalidLoginToast() = runTest {
        coEvery { dataGateway.login(USERNAME, PASSWORD) } throws UnauthorizedRequestException()

        caseToLogin.launch(USERNAME, PASSWORD)

        coVerifyOrder {
            presGateway.showLoadingDialog()
            dataGateway.login(USERNAME, PASSWORD)
            presGateway.hideLoadingDialog()
            presGateway.showToast("Invalid Login", ToastDurationEntityType.SHORT)
        }
        coVerify(exactly = 0) { presGateway.goToAppointments(any()) }
    }

    @Test
    fun launch_noInternet_showNoInternetToast() = runTest {
        coEvery { dataGateway.login(USERNAME, PASSWORD) } throws NoInternetException()

        caseToLogin.launch(USERNAME, PASSWORD)

        coVerifyOrder {
            presGateway.showLoadingDialog()
            dataGateway.login(USERNAME, PASSWORD)
            presGateway.hideLoadingDialog()
            presGateway.showToast("Unable to connect", ToastDurationEntityType.SHORT)
        }
        coVerify(exactly = 0) { presGateway.goToAppointments(any()) }
    }

    @Test
    fun launch_unknownError_showUnknownErrorToast() = runTest {
        coEvery { dataGateway.login(USERNAME, PASSWORD) } throws RuntimeException("Unexpected error")

        caseToLogin.launch(USERNAME, PASSWORD)

        coVerifyOrder {
            presGateway.showLoadingDialog()
            dataGateway.login(USERNAME, PASSWORD)
            presGateway.hideLoadingDialog()
            presGateway.showToast("Unknown error occurred", ToastDurationEntityType.SHORT)
        }
        coVerify(exactly = 0) { presGateway.goToAppointments(any()) }
    }

    @Test
    fun launch_loginThrowsException_hideLoadingDialog() = runTest {
        coEvery { dataGateway.login(USERNAME, PASSWORD) } throws RuntimeException("Unexpected error")

        caseToLogin.launch(USERNAME, PASSWORD)

        coVerify(exactly = 1) { presGateway.hideLoadingDialog() }
    }
}