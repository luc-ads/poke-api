package first.application.by.bentsappfrontend.models

data class User(
    val name: String,
    val password: String,
    val email: String,
    val phone: String,
    val cep: String,
    val state: String,
    val city: String,
    val district: String,
    val address: String,
    val addressNumber: String,
    val lat: String,
    val lng: String,
    val userType: String
)
