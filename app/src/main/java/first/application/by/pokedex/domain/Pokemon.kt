package first.application.by.pokedex.domain

class Pokemon (
    val imagemUrl: String? = null,
    val number: Int? = null,
    val name: String,
    val url: String,
    val types: List<PokemonType>? = null

) {

    val formattedNumber = number.toString().padStart(3, '0')
}

