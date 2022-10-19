package first.application.by.androiddefinitivo.model.strategys

import java.lang.IllegalArgumentException

class KilometerToCentimeterStrategy: CalculationStrategy {
    override fun calculate(valor: Double): Double {
        if (valor < 0) {
            throw IllegalArgumentException("Argument receive is not valid")
        }

        return valor * 100000
    }

    override fun getResultLabel(isPlural: Boolean): String {
        return if (isPlural) {
            "centímetros"
        } else {
            "centímetro"
        }
    }
}