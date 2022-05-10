package com.hynekbraun.randomanimalscompose1.domain.model

data class AnimalFact(
    val active_time: String,
    val animal_type: String,
    val diet: String,
    val geo_range: String,
    val habitat: String,
    val id: Int,
    val image_link: String,
    val latin_name: String,
    val length_max: String,
    val length_min: String,
    val lifespan: String,
    val name: String,
    val weight_max: String,
    val weight_min: String
){
}

fun AnimalFact.weight(): String{
    return "$weight_min - $weight_max"
}
fun AnimalFact.length(): String{
    return "$length_min - $length_max"
}
