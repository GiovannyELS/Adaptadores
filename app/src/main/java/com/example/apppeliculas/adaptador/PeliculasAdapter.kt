package com.example.apppeliculas.adaptador

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.apppeliculas.databinding.PeliculasItemBinding
import com.example.apppeliculas.entidades.Peliculas
private val TAG = PeliculasAdapter::class.java.simpleName

class PeliculasAdapter: RecyclerView.Adapter<PeliculasAdapter.PeliculasViewHolder>(){

    lateinit var onItemClickListener: (Peliculas) -> Unit

    var peliculasList = mutableListOf<Peliculas>()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeliculasAdapter.PeliculasViewHolder {
       val bindingItem =
           PeliculasItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PeliculasViewHolder(bindingItem)
    }


    override fun onBindViewHolder(holder: PeliculasAdapter.PeliculasViewHolder, position: Int) {

        val peliculas: Peliculas = peliculasList [position]
        holder.bind(peliculas)
    }

    override fun getItemCount(): Int {
        return peliculasList.size
    }


    inner class PeliculasViewHolder(private var bindingItem:PeliculasItemBinding):
        RecyclerView.ViewHolder(bindingItem.root) {

        fun bind(peliculas: Peliculas) {
            with(peliculas) {
                bindingItem.aOtxt.text = peliculas.añoDeEstreno.toString()
                bindingItem.generotxt.text = peliculas.genero
                bindingItem.peliculatxt.text = peliculas.nombrePelicula

            }

            bindingItem.root.setOnClickListener {
                if (::onItemClickListener.isInitialized)
                    onItemClickListener(peliculas)
                else
                    Log.e("TAG", "Listener no initializer")
            }

        }
    }
}