package first.application.by.androiddefinitivo.model

import java.io.Serializable

data class Person(
    var name: String,
    var age: Int,
    var height: Double
): Serializable
