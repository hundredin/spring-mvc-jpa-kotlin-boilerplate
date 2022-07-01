package com.example.domains.account

import com.example.repositories.Account
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "계정")
@RestController
@RequestMapping("/accounts")
class AccountController(private val accountService: AccountService) {
    @Operation(description = "모든 계정 조회")
    @GetMapping
    fun getAllAccounts(): ResponseEntity<List<Account>> {
        val allAccounts = accountService.getAllAccounts()
        return ResponseEntity.ok(allAccounts)
    }

    @Operation(description = "계정 정보 조회")
    @GetMapping("/{id}")
    fun getAccount(@Parameter(name = "account id") @PathVariable id: Long): ResponseEntity<Account> {
        val account = accountService.getAccount(id)

        return ResponseEntity.ok(account)
    }

    @Operation(description = "계정 생성")
    @PostMapping
    fun createAccount(@RequestBody accountData: AccountData): ResponseEntity<Account> {
        val account = accountService.createAccount(accountData)

        return ResponseEntity.ok(account)
    }
}
