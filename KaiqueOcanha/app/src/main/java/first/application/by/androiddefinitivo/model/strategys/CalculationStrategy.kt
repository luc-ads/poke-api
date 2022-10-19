package first.application.by.androiddefinitivo.model.strategys

interface CalculationStrategy {

    fun calculate(valor: Double): Double

    fun getResultLabel(isPlural: Boolean): String
}