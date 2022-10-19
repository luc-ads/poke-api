package first.application.by.bentsappfrontend.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import first.application.by.bentsappfrontend.models.User
import first.application.by.bentsappfrontend.repository.UserRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class RegisterViewModel constructor(private val repository: UserRepository): ViewModel() {

    val status = MutableLiveData<Boolean>()

    var typeSelected: String = "user"

    fun register(user: User) {
        val request = repository.saveUser(user)
        request.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    status.postValue(true)
                } else {
                    status.postValue(false)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                status.postValue(false)
                Log.e("ERROR", "Erro no serviço de registro")
            }
        })
    }
}