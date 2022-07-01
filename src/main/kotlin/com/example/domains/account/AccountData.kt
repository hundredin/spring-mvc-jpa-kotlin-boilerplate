package com.example.domains.account

import io.swagger.v3.oas.annotations.media.Schema

data class AccountData(
    @Schema(description = "이메일", example = "some@example.com")
    var email: String,
    @Schema(description = "패스워드", example = "1234")
    var password: String,
)
