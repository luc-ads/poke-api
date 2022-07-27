package first.application.by.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import first.application.by.pokedex.databinding.ActivityMainBinding
import first.application.by.pokedex.domain.Pokemon
import first.application.by.pokedex.viewModel.PokemonViewModel
import first.application.by.pokedex.viewModel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    // lateinit é uma inicialização para variáveis que podem sofrer mutações, by lazy é para variáveis imutáveis
    private val viewModel by lazy{
        ViewModelProvider(this, PokemonViewModelFactory()).get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel.pokemons.observe(this){
            loadRecycler(it)
        }
    }

    private fun loadRecycler(pokemons: List<Pokemon?>) {
        with(binding){
            rvPokemons.layoutManager= LinearLayoutManager(this@MainActivity)
            rvPokemons.adapter= PokemonAdapter(pokemons)
        }
    }
}