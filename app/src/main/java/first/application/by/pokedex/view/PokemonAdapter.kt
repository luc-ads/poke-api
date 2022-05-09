package first.application.by.pokedex.view

import android.annotation.SuppressLint
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import first.application.by.pokedex.R
import first.application.by.pokedex.domain.Pokemon
import org.w3c.dom.Text

class PokemonAdapter(
    private val items: List<Pokemon>

) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    //forma simplificada de definir esta fun
    override fun getItemCount() = items.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: Pokemon) = with(itemView) {
            val imageViewPokemon = findViewById<ImageView>(R.id.image_view_pokemon)
            val numeration = findViewById<TextView>(R.id.numeration)
            val pokeName = findViewById<TextView>(R.id.poke_name)
            val tvType01 = findViewById<TextView>(R.id.tv_type01)
            val tvType02 = findViewById<TextView>(R.id.tv_type02)

            //TODO: lOAD IMAGE WITH GLIDE
            pokeName.text = item.name
            numeration.text = "Nº ${item.number}"
            tvType01.text = item.types[0].name
            if (item.types[0].name == "Fire") {
                tvType01.setTextColor(ContextCompat.getColor(context, R.color.purple_700))
            }

            if (item.types.size > 1) {
                tvType02.visibility = View.VISIBLE
                tvType02.text = item.types[1].name

            } else {
                tvType02.visibility = View.GONE
            }

        }
    }
}