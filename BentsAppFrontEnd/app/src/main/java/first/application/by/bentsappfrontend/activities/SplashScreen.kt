package first.application.by.bentsappfrontend.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import first.application.by.bentsappfrontend.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            Intent(this, LoginActivity::class.java).apply {
                startActivity(this)
                Log.i("Status", "Trocando de Splash para Main Screen")
            }
        }, 2500)
    }
}