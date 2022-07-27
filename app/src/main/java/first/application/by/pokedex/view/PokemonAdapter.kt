package first.application.by.pokedex.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import first.application.by.pokedex.R
import first.application.by.pokedex.databinding.PokemonItemBinding
import first.application.by.pokedex.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon?>

) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    lateinit var binding: PokemonItemBinding

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
            item?.let{
                Glide.with(itemView.context).load(it.imagemUrl).into(binding.imageViewPokemon)
                with(binding){
                    pokeName.text= item.formattedName
                    numeration.text= "Nº ${item.formattedNumber}"
                    tvType01.text= item.types[0].name.capitalize()
                    if (item.types[0].name == "fire") {
                        tvType01.setTextColor(ContextCompat.getColor(context, R.color.purple_700))
                    }
                    if (item.types.size > 1) {
                        tvType02.visibility= View.VISIBLE
                        tvType02.text= item.types[1].name.capitalize()

                    } else {
                        tvType02.visibility= View.GONE
                    }
                }
            }
        }
    }
}