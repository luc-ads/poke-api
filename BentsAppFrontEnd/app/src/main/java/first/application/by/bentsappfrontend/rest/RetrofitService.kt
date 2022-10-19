package first.application.by.bentsappfrontend.rest

import android.annotation.SuppressLint
import android.util.Log
import first.application.by.bentsappfrontend.activities.LoginRequest
import first.application.by.bentsappfrontend.activities.LoginResponse
import first.application.by.bentsappfrontend.models.User
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

interface RetrofitService {

    private val interceptor: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor()

    @POST("users/registerUser")
    fun saveUser(@Body user: User): Call<ResponseBody>

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    companion object {

        private val retrofitService: RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://backend-bents.hopto.org/")
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)

        }

        val loggingInterceptor by lazy {
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.i("RETROFIT", message)
                }
            })
        }

        @Suppress("DEPRECATION")
        @SuppressLint("TrustAllX509TrustManager", "CustomX509TrustManager")
        private fun createOkHttpClient(): OkHttpClient? {
                return OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }
}