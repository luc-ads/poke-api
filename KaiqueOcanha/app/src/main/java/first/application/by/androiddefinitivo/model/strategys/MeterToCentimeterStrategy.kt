package first.application.by.androiddefinitivo.model.strategys

import java.lang.IllegalArgumentException

class MeterToCentimeterStrategy: CalculationStrategy {
    override fun calculate(valor: Double): Double {
        if (valor < 0) {
            throw IllegalArgumentException("Argument receive is not valid")
        }

        return valor * 100
    }

    override fun getResultLabel(isPlural: Boolean): String {
        return if (isPlural) {
            "centímetros"
        } else {
            "centímetro"
        }
    }
}