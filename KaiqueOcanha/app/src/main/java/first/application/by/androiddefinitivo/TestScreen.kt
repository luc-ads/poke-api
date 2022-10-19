package first.application.by.androiddefinitivo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.TypedValue
import android.widget.Toast
import androidx.core.content.ContextCompat
import first.application.by.androiddefinitivo.databinding.ActivityTestScreenBinding
import first.application.by.androiddefinitivo.model.Person

class TestScreen : AppCompatActivity() {

    private lateinit var binding: ActivityTestScreenBinding
    private var countLap = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            countLap = savedInstanceState.getInt("counter", -1)
        }

        binding.txt.text = "Contador de voltas: $countLap"
        val pessoa = intent.getSerializableExtra("person") as Person

        if (intent.hasExtra("help")) {
            Toast.makeText(this, intent.getStringExtra("help"), Toast.LENGTH_LONG).show()
        }

        binding.txtPerson.text = pessoa.name
        binding.txtAge.text = pessoa.age.toString()
        binding.txtHeigth.text = pessoa.height.toString()

        binding.button.setOnClickListener {
            countLap++
                if (countLap % 2 == 0) {
                binding.txt.setTextColor(ContextCompat.getColor(this, R.color.purple_200))
            } else {
                binding.txt.setTextColor(ContextCompat.getColor(this, R.color.black))
            }
            binding.txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_OK)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle, persistableBundle: PersistableBundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", countLap)
    }
}