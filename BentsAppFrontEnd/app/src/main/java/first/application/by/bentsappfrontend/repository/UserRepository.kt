package first.application.by.bentsappfrontend.repository

import first.application.by.bentsappfrontend.activities.LoginRequest
import first.application.by.bentsappfrontend.models.User
import first.application.by.bentsappfrontend.rest.RetrofitService

class UserRepository constructor(private val retrofitService: RetrofitService) {

    fun saveUser(user: User) = retrofitService.saveUser(user)

    fun login(loginRequest: LoginRequest) = retrofitService.login(loginRequest)

}