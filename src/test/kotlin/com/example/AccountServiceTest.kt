package com.example

import com.example.domains.account.AccountData
import com.example.domains.account.AccountService
import com.example.repositories.Account
import com.example.repositories.AccountRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime

internal class AccountServiceTest : BehaviorSpec({
    val accountRepository: AccountRepository = mockk()
    val accountService = AccountService(accountRepository)

    Given("계정이 있는 상태에서") {
        val accountId = 1L
        every { accountRepository.findByIdOrNull(accountId) } returns Account(
            "some@example.com",
            "password",
            LocalDateTime.now(),
            LocalDateTime.now(),
            1
        )

        When("계정을 accountId 로 가져오면") {
            val account = accountService.getAccount(accountId)

            then("해당 accountId 의 계정이 리턴된다") {
                account.email shouldBe "some@example.com"
                account.password shouldBe "password"

                verify { accountRepository.findByIdOrNull(accountId) }
            }
        }
    }

    Given("계정 정보를 받아온 후에") {
        val email = "some@example.com"
        val password = "1234"
        val accountData = AccountData(email, password)

        every { accountRepository.findByEmail(email) } returns null
        every { accountRepository.save(any()) } returns Account(email, password, id = 1L)

        When("계정을 생성하면") {
            val account = accountService.createAccount(accountData)

            Then("계정이 생성된다.") {
                account.email shouldBe "some@example.com"
                account.password shouldBe "1234"
                account.id shouldNotBe null

                verify { accountRepository.findByEmail(email) }
            }
        }
    }

    Given("이미 존재하는 email 주소의 계정 정보가 넘어오면") {
        val email = "some@example.com"
        val password = "1234"
        val accountData = AccountData(email, password)

        every { accountRepository.findByEmail(email) } returns Account(email, password, id = 1L)

        When("계정을 생성하면") {
            Then("예외가 발생해야 한다.") {
                shouldThrow<RuntimeException> {
                    accountService.createAccount(accountData)
                }

                verify { accountRepository.findByEmail(email) }
            }
        }
    }
})
