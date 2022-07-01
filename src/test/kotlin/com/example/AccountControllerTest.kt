package com.example

import com.example.domains.account.AccountController
import com.example.domains.account.AccountService
import com.example.repositories.Account
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@WebMvcTest(AccountController::class)
class AccountControllerTest {
    @TestConfiguration
    class AccountControllerTestConfig {
        @Bean
        fun accountService() = mockk<AccountService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var accountService: AccountService

    @Test
    fun `get account`() {
        val id = 1L
        every { accountService.getAccount(id) } returns Account(
            "some@example.com",
            "password",
            LocalDateTime.now(),
            LocalDateTime.now(),
            1
        )

        val result = mockMvc.perform(get("/accounts/$id"))
            .andExpect(status().isOk)
            .andReturn()

        println(result.response.contentAsString)

        verify { accountService.getAccount(id) }
    }
}
