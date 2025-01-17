package com.oxford.sean.domain.usecase

import com.oxford.sean.domain.DataGateway
import com.oxford.sean.domain.PresGateway
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CaseToInitActivityTest {

    private lateinit var dataGateway: DataGateway
    private lateinit var presGateway: PresGateway
    private lateinit var caseToInitActivity: CaseToInitActivity

    @Before
    fun setup() {
        dataGateway = mockk()
        presGateway = mockk(relaxed = true)

        caseToInitActivity = CaseToInitActivity(dataGateway, presGateway)
    }

    @Test
    fun launch_userIsLoggedIn_navigateToAppointments() = runTest {
        coEvery { dataGateway.isUserLoggedIn() } returns true

        caseToInitActivity.launch()

        coVerify { presGateway.goToAppointments(clearStack = true) }
        coVerify(exactly = 1) { dataGateway.isUserLoggedIn() }
    }

    @Test
    fun launch_userIsNotLoggedIn_noNavigation() = runTest {
        coEvery { dataGateway.isUserLoggedIn() } returns false

        caseToInitActivity.launch()

        coVerify(exactly = 0) { presGateway.goToAppointments(any()) }
        coVerify(exactly = 1) { dataGateway.isUserLoggedIn() }
    }
}