package first.application.by.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import first.application.by.pokedex.R
import first.application.by.pokedex.domain.Pokemon
import first.application.by.pokedex.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemons = listOf(
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
                1,
                "Charmander",
                listOf(
                    PokemonType("Fire")
                )
            ),
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
                2,
                "Charmander",
                listOf(
                    PokemonType("Poison")
                )
            ),
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
                3,
                "Charmander",
                listOf(
                    PokemonType("Fire")
                )
            ),
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
                4,
                "Charmander",
                listOf(
                    PokemonType("Fire")
                )
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_pokemons)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}