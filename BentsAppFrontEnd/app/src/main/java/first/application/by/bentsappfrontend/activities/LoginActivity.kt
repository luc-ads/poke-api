package first.application.by.bentsappfrontend.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import first.application.by.bentsappfrontend.R
import first.application.by.bentsappfrontend.databinding.ActivityLoginBinding
import first.application.by.bentsappfrontend.models.UserSession
import first.application.by.bentsappfrontend.repository.UserRepository
import first.application.by.bentsappfrontend.rest.RetrofitService
import first.application.by.bentsappfrontend.viewmodel.LoginViewModel
import first.application.by.bentsappfrontend.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, LoginViewModelFactory(UserRepository(retrofitService))).get(
            LoginViewModel::class.java
        )

        initListeners()
    }

    override fun onStart() {
        super.onStart()

        viewModel.success.observe(this, Observer {
            UserSession.setToken(it.token)
            startActivity(Intent(this@LoginActivity, UserAreaActivity::class.java))
            finish()

        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this@LoginActivity, "Deu erro ao fazer o login", Toast.LENGTH_LONG).show()
        })
    }

    private fun initListeners() {
        val spanRegister = SpannableString("Não é membro? Registre-se agora.")
        spanRegister.setSpan(ForegroundColorSpan(ContextCompat.getColor(this@LoginActivity,
            R.color.azul_principal
        )),14, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val spanWebView = SpannableString("Ainda não conhece a Bent Eats? Veja sobre nós e o que oferecemos aos nossos usuários clicando aqui.")
        spanWebView.setSpan(ForegroundColorSpan(ContextCompat.getColor(this@LoginActivity,
            R.color.azul_principal
        )),85, 98, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        with(binding) {
            txtRegister.text = setCustomFontTypeSpan(spanRegister, 14, 32)
            txtRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
            }
            txtWebview.setOnClickListener {
                startActivity(Intent(this@LoginActivity, WebViewAboutUsActivity::class.java))
            }
            txtWebview.text = setCustomFontTypeSpan(spanWebView, 85, 91)
            edtPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                override fun afterTextChanged(editable: Editable) {
                    if (edtPassword.text.length >= 6) validationButton(true)
                }
            })
            edtPassword.setOnClickListener {
                if (edtPassword.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    edtPassword.inputType = InputType.TYPE_CLASS_TEXT
                } else {
                    edtPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                }
            }
            btnEntrar.setOnClickListener {
                if (edtEmail.text.isEmpty()) {
                    edtEmail.error = "Preencha corretamente"
                } else if (edtPassword.text.isEmpty()) {
                    edtPassword.error = "Preencha corretamente"
                } else {
                    viewModel.login(
                        LoginRequest(
                        binding.edtEmail.text.toString(),
                        binding.edtPassword.text.toString()
                        )
                    )
                }
            }
        }
    }

    private fun validationButton(validation: Boolean) {
        if (validation) {
            binding.btnEntrar.setBackgroundColor(ContextCompat.getColor(this,
                R.color.azul_principal
            ))
        }
    }

    private fun setCustomFontTypeSpan(
        source: SpannableString,
        startIndex: Int,
        endIndex: Int,
    ): SpannableString {
        val spannableString = SpannableString(source)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }
}