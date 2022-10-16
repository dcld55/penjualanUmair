package id.decloud.common.entity.api.login

data class LoginResponse(
    val error: Boolean,
    val loginResult: LoginResult,
    val message: String
)