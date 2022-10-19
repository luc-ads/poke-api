package first.application.by.pokedex.view

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import first.application.by.pokedex.R
import first.application.by.pokedex.databinding.PokemonItemBinding
import first.application.by.pokedex.domain.Pokemon
import java.util.*

class PokemonAdapter(
    private val items: List<Pokemon?>

) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private lateinit var binding: PokemonItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val binding: PokemonItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: Pokemon?) = with(itemView){
            item?.let{ it ->
                Glide.with(itemView.context).load(it.imagemUrl).into(binding.imageViewPokemon)
                with(binding) {
                    pokeName.text = item.formattedName
                    numeration.text = "Nº ${item.formattedNumber}"
                    tvType01.text = item.types[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    }
                    if (item.types[0].name == "fire") {
                        tvType01.setTextColor(ContextCompat.getColor(context, R.color.purple_700))
                    }
                    if (item.types.size > 1) {
                        tvType02.visibility = View.VISIBLE
                        tvType02.text = item.types[1].name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                        }

                    } else {
                        tvType02.visibility = View.GONE
                    }
                }
            }
        }
    }
}