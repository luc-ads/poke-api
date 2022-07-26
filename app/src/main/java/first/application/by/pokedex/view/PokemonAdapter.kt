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
import first.application.by.pokedex.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon?>

) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: Pokemon?) = with(itemView) {
            val imageViewPokemon = findViewById<ImageView>(R.id.image_view_pokemon)
            val numeration = findViewById<TextView>(R.id.numeration)
            val pokeName = findViewById<TextView>(R.id.poke_name)
            val tvType01 = findViewById<TextView>(R.id.tv_type01)
            val tvType02 = findViewById<TextView>(R.id.tv_type02)
            val container = findViewById<ConstraintLayout>(R.id.container_geral)

            item?.let {
                Glide.with(itemView.context).load(it.imagemUrl).into(imageViewPokemon)

                pokeName.text = item.formattedName
                numeration.text = "Nº ${item.formattedNumber}"
                tvType01.text = item.types[0].name.capitalize()
                if (item.types[0].name == "fire") {
                    tvType01.setTextColor(ContextCompat.getColor(context, R.color.purple_700))
                }
                if (item.types.size > 1) {
                    tvType02.visibility = View.VISIBLE
                    tvType02.text = item.types[1].name.capitalize()

                } else {
                    tvType02.visibility = View.GONE
                }
            }
        }
    }
}