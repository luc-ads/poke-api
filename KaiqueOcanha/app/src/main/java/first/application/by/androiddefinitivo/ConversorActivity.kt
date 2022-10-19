package first.application.by.androiddefinitivo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import first.application.by.androiddefinitivo.databinding.ActivityConversorBinding
import first.application.by.androiddefinitivo.model.CalculationStrategyHolder
import first.application.by.androiddefinitivo.model.Calculator
import first.application.by.androiddefinitivo.model.strategys.*
import java.lang.NumberFormatException

class ConversorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConversorBinding
    private val supportedCalculationStrategies = arrayOf(
        CalculationStrategyHolder("Quilômetro para centìmetros", KilometerToCentimeterStrategy()),
        CalculationStrategyHolder("Quilômetro para metros", KilometerToMeterStategy()),
        CalculationStrategyHolder("Metros para centìmetros", MeterToCentimeterStrategy()),
        CalculationStrategyHolder("Metros para quilometros", MeterToKilometerStrategy()),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        calculateMeters()

    }

    private fun calculateMeters() {
        val calculationStrategy = KilometerToMeterStategy()

        Calculator.setCalculation(calculationStrategy)

        Log.i("Call CALCULATEMETERS", "${Calculator.calculate(1.toDouble())}")
    }

    private fun initComponents() {
        val spinAdapter = ArrayAdapter(this, R.layout.res_spinner_item, getSpinnerData())
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        setUi()
        setAction()
    }

    private fun getSpinnerData(): MutableList<String> {
        val spinnerData = mutableListOf<String>()
        supportedCalculationStrategies.forEach { spinnerData.add(it.nome) }

        return spinnerData
    }

    private fun setUi() {
        val spinAdapter = ArrayAdapter(this, R.layout.res_spinner_item, getSpinnerData())
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinType.adapter = spinAdapter

    }

    private fun setAction() {
        binding.btnConverter.setOnClickListener {
            try {
                val value = binding.edtValueConverter.text.toString().toDouble()
                Log.i("Valor escolhido no spin", "$value")
                val indexSelectedItem = binding.spinType.selectedItemPosition
                val calculationStrategy = supportedCalculationStrategies[indexSelectedItem].calculationStrategy
                Calculator.setCalculation(calculationStrategy)
                Calculator.calculate(value)
                val resultConvert = Calculator.calculate(value)

                Intent(this@ConversorActivity, ResultConversorActivity::class.java).apply {
                    putExtra("resultConvert", resultConvert)
                    putExtra("strategySelected", calculationStrategy.getResultLabel(resultConvert != 1.0))
                    startActivity(this)
                }
            } catch(e: NumberFormatException) {
                binding.edtValueConverter.error = "Valor inválido"
                binding.edtValueConverter.requestFocus()

            }
        }

        binding.btnLimpar.setOnClickListener {
            binding.edtValueConverter.setText("")
            binding.edtValueConverter.error = null
            binding.spinType.setSelection(0)
        }
    }
}