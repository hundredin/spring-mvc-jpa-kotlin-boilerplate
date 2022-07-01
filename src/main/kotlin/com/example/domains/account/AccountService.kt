package com.example.domains.account

import com.example.repositories.Account
import com.example.repositories.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {
    fun getAccount(id: Long): Account {
        return accountRepository.findByIdOrNull(id) ?: throw RuntimeException("Account not found.")
    }

    fun getAccount(email: String): Account {
        return accountRepository.findByEmail(email) ?: throw RuntimeException("Account not found.")
    }

    fun getAllAccounts(): List<Account> {
        return accountRepository.findAll()
    }

    fun createAccount(accountData: AccountData): Account {
        accountRepository.findByEmail(accountData.email).also {
            if (it != null) {
                throw java.lang.RuntimeException("Same email account already exist")
            }
        }

        return with(accountData) {
            Account(email, password)
        }.let { accountRepository.save(it) }
    }
}
