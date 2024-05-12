package com.example.apppeliculas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apppeliculas.adaptador.PeliculasAdapter
import com.example.apppeliculas.databinding.ActivityMainBinding
import com.example.apppeliculas.entidades.Peliculas
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerMovies.layoutManager = LinearLayoutManager(this)
        initAdapter()

    }

    private fun initAdapter() {
        val moviesAdaptador = PeliculasAdapter()
        binding.recyclerMovies.adapter = moviesAdaptador
        moviesAdaptador.peliculasList = Peliculas.dataPeliculas
        moviesAdaptador.onItemClickListener = { peliculas ->
            Toast.makeText(this, peliculas.nombrePelicula, Toast.LENGTH_LONG).show()
            enviarCorreoElectronico(peliculas)
            //enviarWsp(peliculas)
        }
        //moviesAdaptador.peliculasList.isEmpty()
        if (moviesAdaptador.peliculasList.isEmpty())
            binding.empty.visibility = View.VISIBLE
        else
            binding.empty.visibility = View.GONE

    }

    private fun enviarCorreoElectronico(peliculas: Peliculas){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("glira27@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "envio de Peliculas")
        intent.putExtra(Intent.EXTRA_TEXT, "La pelicula descargada es: " +
                " ${peliculas.nombrePelicula}")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent,"Enviar pelicula"))
        } else
            Toast.makeText(
                this,"Correo no disponible",
                Toast.LENGTH_LONG
            ).show()
    }

    fun enviarWsp(peliculas: Peliculas) {
        val telefonoWsp = "+56972353732"
        val mensaje = "Descargaste ${peliculas.nombrePelicula} "
        val uri = Uri.parse(
            "https://api.whatsapp.com/send?phone=$telefonoWsp&text=${
                URLEncoder.encode(
                    mensaje,
                    "UTF-8"
                )
            }"
        )
        val intent = Intent(Intent.ACTION_VIEW,uri)


        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Enviar por mensaje pelicula"))
        } else
            Toast.makeText(
                this, "Wsp no disponible",
                Toast.LENGTH_LONG
            ).show()

    }




}