package first.application.by.androiddefinitivo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import first.application.by.androiddefinitivo.databinding.ActivityResultConversorBinding

class ResultConversorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultConversorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultConversorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
    }

    private fun initComponents() {
        with(binding) {

            if (intent.hasExtra("resultConvert")) {
                txtResult.text = intent.getDoubleExtra("resultConvert", 0.0).toString()
                txtUnidadeMedida.text = intent.getStringExtra("strategySelected")
            }

            btnVoltar.setOnClickListener {
                finish()
            }
        }
    }
}