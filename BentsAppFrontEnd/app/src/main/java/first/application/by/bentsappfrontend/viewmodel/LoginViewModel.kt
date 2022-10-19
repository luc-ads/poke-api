package first.application.by.bentsappfrontend.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import first.application.by.bentsappfrontend.activities.LoginRequest
import first.application.by.bentsappfrontend.activities.LoginResponse
import first.application.by.bentsappfrontend.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class LoginViewModel constructor(private val repository: UserRepository): ViewModel() {

    val success = MutableLiveData<LoginResponse>()
    val errorMessage = MutableLiveData<String>()


    fun login(loginRequest: LoginRequest) {
        val request = repository.login(loginRequest)
        request.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                println(response.code())
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    success.postValue(response.body())
                } else {
                    errorMessage.postValue("Não foi possível entrar. Verifique os dados e tente novamente")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}