package first.application.by.whatsappcloneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import first.application.by.whatsappcloneapp.databinding.LoginMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()

    }

    private fun initListeners() {
       with(binding) {

           buttonCadastrar.setOnClickListener {
               abrirCadastroActivity()
           }
       }
    }

    private fun abrirCadastroActivity() {
        startActivity(Intent(this, VerifyEmailActivity::class.java))
        finish()

    }
}