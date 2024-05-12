package com.example.apppeliculas.entidades

data class Peliculas(
    val añoDeEstreno: Int,
    val genero: String,
    val nombrePelicula: String,

    ){

    companion object {

    val dataPeliculas = mutableListOf(

        Peliculas(2004, "Drama", "La Pasion de Cristo"),
        Peliculas(2010, "Biopic", "La Red Social"),
        Peliculas(2010, "Animacion", "Colorful"),
        Peliculas(2011, "Drama", "Intocable"),
        Peliculas(2012, "Superheroes", "Avengers"),
        Peliculas(2013, "Accion", "Plan de Escape"),
        Peliculas(2014, "Animacion", "Big Hero"),
        Peliculas(2014, "Bélico", "Francotirador"),
        Peliculas(2015, "Accion", "Furia en la carretera"),
        Peliculas(2015, "Terror", "Bone Tomahawk"),
        Peliculas(2016, "Comedia", "Ave Cesar"),
        Peliculas(2017, "Suspence", "La cura del bienestar"),
        Peliculas(2017, "Suspence", "La forma del agua"),
        Peliculas(2018, "Ciencia ficcion", "Ready Player one"),





    )
    }
}