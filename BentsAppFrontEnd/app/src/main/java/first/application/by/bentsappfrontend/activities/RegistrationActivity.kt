package first.application.by.bentsappfrontend.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import first.application.by.bentsappfrontend.R
import first.application.by.bentsappfrontend.databinding.ActivityRegistrationBinding
import first.application.by.bentsappfrontend.models.User
import first.application.by.bentsappfrontend.repository.UserRepository
import first.application.by.bentsappfrontend.rest.RetrofitService
import first.application.by.bentsappfrontend.viewmodel.RegisterViewModel
import first.application.by.bentsappfrontend.viewmodel.RegisterViewModelFactory

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var viewModel: RegisterViewModel
    private val retrofitService = RetrofitService.getInstance()
    private var typeLoginSelected: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, RegisterViewModelFactory(UserRepository(retrofitService))).get(
            RegisterViewModel::class.java
        )

        binding.toolbar.setOnClickListener {
            onBackPressed()
        }

        initListener()
    }

    override fun onBackPressed() {
        when (binding.sliderPassos.currentStep) {
            1 -> finish()
            2 -> {
                TransitionManager.beginDelayedTransition(binding.root, AutoTransition())
                binding.sliderPassos.updateSteps(1)
                binding.layoutPasso2.root.visibility = View.GONE
                binding.layoutPasso1.root.visibility = View.VISIBLE

            }
            3 -> {
                TransitionManager.beginDelayedTransition(binding.root, AutoTransition())
                binding.sliderPassos.updateSteps(2)
                binding.layoutPasso3.root.visibility = View.GONE
                binding.layoutPasso2.root.visibility = View.VISIBLE
            }
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.status.observe(this, Observer {
            if (it) {
                startActivity(Intent(this@RegistrationActivity, ScreenRegisterSuccessful::class.java))
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Erro ao registrar ao usuário",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun initListener() {
        with(binding) {
            sliderPassos.startStepper(3, 1)
            layoutPasso1.cardviewUser.setBackgroundResource(R.drawable.background_cardview)
            layoutPasso1.cardviewRestaurant.setBackgroundResource(R.drawable.background_cardview)

            layoutPasso1.cardviewUser.setOnClickListener { validationCards(1) }
            layoutPasso1.cardviewRestaurant.setOnClickListener { validationCards(2) }
            layoutPasso1.btnAvancar.setOnClickListener { avancarPasso1() }
            layoutPasso2.edtSenha.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                override fun afterTextChanged(editable: Editable) {
                    if (layoutPasso2.edtSenha.text.length >= 6) layoutPasso2.btnAvancar.setBackgroundColor(ContextCompat.getColor(this@RegistrationActivity,
                        R.color.azul_principal
                    ))

                }
            })
            layoutPasso2.btnAvancar.setOnClickListener { avancarPasso2() }
            layoutPasso3.btnAvancar.setOnClickListener { finalizarRegistro() }
        }
    }

    private fun validationCards(option: Int) {
        with(binding) {
            typeLoginSelected = if (option == 1) {
                viewModel.typeSelected = "user"
                layoutPasso1.cardviewUser.setBackgroundResource(R.drawable.background_actived_cardview)
                layoutPasso1.txtCardviewUser.setTextColor(ContextCompat.getColor(this@RegistrationActivity,
                    R.color.white
                ))
                layoutPasso1.cardviewRestaurant.setBackgroundResource(R.drawable.background_cardview)
                layoutPasso1.txtCardviewRestaurant.setTextColor(ContextCompat.getColor(this@RegistrationActivity,
                    R.color.gray_light
                ))
                layoutPasso1.btnAvancar.setBackgroundColor(ContextCompat.getColor(this@RegistrationActivity,
                    R.color.azul_principal
                ))
                1
            } else {
                viewModel.typeSelected = "restaurant"
                layoutPasso1.cardviewRestaurant.setBackgroundResource(R.drawable.background_actived_cardview)
                layoutPasso1.txtCardviewRestaurant.setTextColor(ContextCompat.getColor(this@RegistrationActivity,
                    R.color.white
                ))
                layoutPasso1.cardviewUser.setBackgroundResource(R.drawable.background_cardview)
                layoutPasso1.txtCardviewUser.setTextColor(ContextCompat.getColor(this@RegistrationActivity,
                    R.color.gray_light
                ))
                layoutPasso1.btnAvancar.setBackgroundColor(ContextCompat.getColor(this@RegistrationActivity,
                    R.color.azul_principal
                ))
                2
            }
        }
    }

    private fun avancarPasso1() {
        with(binding) {
            if (typeLoginSelected != 0) {
                TransitionManager.beginDelayedTransition(root, AutoTransition())
                sliderPassos.updateSteps(2)
                layoutPasso1.root.visibility = View.GONE
                layoutPasso2.root.visibility = View.VISIBLE

            } else {
                Toast.makeText(this@RegistrationActivity, "Selecione uma opção de usuário", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun avancarPasso2() {
        with(binding) {
            if (layoutPasso2.edtNome.text.isEmpty()) {
                layoutPasso2.edtNome.error = "Preencha corretamente"

            } else if (layoutPasso2.edtTelefone.text.isEmpty()) {
                layoutPasso2.edtTelefone.error = "Preencha corretamente"

            } else if (layoutPasso2.edtEmail.text.isEmpty()) {
                layoutPasso2.edtEmail.error = "Preencha corretamente"

            } else if (layoutPasso2.edtSenha.text.isEmpty()) {
                layoutPasso2.edtSenha.error = "Preencha corretamente"

            } else {
                layoutPasso2.btnAvancar.setBackgroundColor(ContextCompat.getColor(this@RegistrationActivity,
                    R.color.azul_principal
                ))
                TransitionManager.beginDelayedTransition(root, AutoTransition())
                sliderPassos.updateSteps(3)
                layoutPasso1.root.visibility = View.GONE
                layoutPasso2.root.visibility = View.GONE
                layoutPasso3.root.visibility = View.VISIBLE
            }
        }
    }

    private fun finalizarRegistro() {
        with(binding) {
            if (layoutPasso3.edtCep.text.isEmpty()) {
                layoutPasso3.edtCep.error = "Preencha corretamente"

            } else if (layoutPasso3.edtEstado.text.isEmpty()) {
                layoutPasso3.edtEstado.error = "Preencha corretamente"

            } else if (layoutPasso3.edtEndereco.text.isEmpty()) {
                layoutPasso3.edtEndereco.error = "Preencha corretamente"

            } else if (layoutPasso3.edtNumero.text.isEmpty()) {
                layoutPasso3.edtNumero.error = "Preencha corretamente"

            } else if (layoutPasso3.edtBairro.text.isEmpty()) {
                layoutPasso3.edtNumero.error = "Preencha corretamente"

            } else if (layoutPasso3.edtCidade.text.isEmpty()) {
                layoutPasso3.edtNumero.error = "Preencha corretamente"

            } else {
                layoutPasso3.btnAvancar.setBackgroundColor(ContextCompat.getColor(this@RegistrationActivity,
                    R.color.azul_principal
                ))
                viewModel.register(User(
                    layoutPasso2.edtNome.text.toString(),
                    layoutPasso2.edtSenha.text.toString(),
                    layoutPasso2.edtEmail.text.toString(),
                    layoutPasso2.edtTelefone.text.toString(),
                    layoutPasso3.edtCep.text.toString(),
                    layoutPasso3.edtEstado.text.toString(),
                    layoutPasso3.edtCidade.text.toString(),
                    layoutPasso3.edtBairro.text.toString(),
                    layoutPasso3.edtEndereco.text.toString(),
                    layoutPasso3.edtNumero.text.toString(),
                    "-23.4888",
                    "-46.67051",
                    viewModel.typeSelected
                ))
            }
        }
    }
}