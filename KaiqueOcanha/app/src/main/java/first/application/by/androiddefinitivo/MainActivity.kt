package first.application.by.androiddefinitivo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import first.application.by.androiddefinitivo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result ->

            if (result.data != null) {
                val nome = result!!.data?.getStringExtra("NOME")

                Log.i("Lucas", nome!!)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val person = first.application.by.androiddefinitivo.model.Person(
            name = "Lucas Pereira da Silva",
            age =  21,
            height = 1.69
        )


        binding.toggleButton.setOnCheckedChangeListener { _, state ->
            binding.toggleButton.isActivated = true
            if (state) Toast.makeText(this,"Habilitado", Toast.LENGTH_SHORT).show() else Toast.makeText(this,"Desabilitado", Toast.LENGTH_SHORT).show()
        }

        binding.switch1.setOnCheckedChangeListener { _, state ->
            if (state) Toast.makeText(this,"Habilitado", Toast.LENGTH_SHORT).show() else Toast.makeText(this,"Desabilitado", Toast.LENGTH_SHORT).show()
        }

        binding.floatingActionButton.setOnClickListener {
            Toast.makeText(this,"Habilitado", Toast.LENGTH_SHORT).show()
        }

        binding.btnRegister.setOnClickListener {

            val intent = Intent(this, TestScreen::class.java).apply {
                putExtra("person", person)
            }
            getResult.launch(intent)

//            Intent(this, TestScreen::class.java).apply {
//                if (binding.toggleButton.isActivated) {
//                    putExtra("help", testando)
//
//                }
//                putExtra("person", person)
//                startActivity(this)
//            }


            Toast.makeText(
                this,
                "DEU BOM SAFADO",
                Toast.LENGTH_SHORT
            ).show()

            // OU

            Snackbar.make(
                it,
            "DEU BOM SAFADO",
            Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("onStart", "onStart foi chamado")
    }

    override fun onResume() {
        super.onResume()
        Log.i("onResume", "onResume foi chamado")
    }

    override fun onPause() {
        super.onPause()
        Log.i("onPause", "onPause foi chamado")
    }

    override fun onStop() {
        super.onStop()
        Log.i("onStop", "onStop foi chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy", "onDestroy foi chamado")
    }
}