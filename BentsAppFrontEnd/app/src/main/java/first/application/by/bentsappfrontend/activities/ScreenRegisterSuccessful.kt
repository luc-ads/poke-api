package first.application.by.bentsappfrontend.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import first.application.by.bentsappfrontend.R
import first.application.by.bentsappfrontend.databinding.ActivityScreenRegisterSuccessfulBinding

class ScreenRegisterSuccessful : AppCompatActivity() {

    private lateinit var binding: ActivityScreenRegisterSuccessfulBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenRegisterSuccessfulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}