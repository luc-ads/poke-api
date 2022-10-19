package first.application.by.androiddefinitivo.model

import first.application.by.androiddefinitivo.model.strategys.CalculationStrategy
import java.lang.IllegalArgumentException

object Calculator {

    private var calculationStrategy: CalculationStrategy? = null

    fun setCalculation(calculationStrategy: CalculationStrategy) {
        this.calculationStrategy = calculationStrategy
    }

    fun calculate(value: Double): Double{

        if (calculationStrategy == null) {
            throw IllegalArgumentException("Calculation Strategy is not set")
        }
        return calculationStrategy!!.calculate(value)
    }

}