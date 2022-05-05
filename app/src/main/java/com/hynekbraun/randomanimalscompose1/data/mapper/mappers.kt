package com.hynekbraun.randomanimalscompose1.data.mapper

import com.hynekbraun.randomanimalscompose1.data.local.AnimalFactEntity
import com.hynekbraun.randomanimalscompose1.data.remote.dto.AnimalFactDto
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact

fun AnimalFactDto.toAnimalFact(): AnimalFact {
    return AnimalFact(
        active_time = active_time,
        animal_type = animal_type,
        diet = diet,
        geo_range = geo_range,
        habitat = habitat,
        id = id,
        image_link = image_link,
        latin_name = latin_name,
        length_max = length_max,
        length_min = length_min,
        lifespan = lifespan,
        name = name,
        weight_max = weight_max,
        weight_min = weight_min
    )
}

fun AnimalFact.toAnimalFactEntity(): AnimalFactEntity {
    return AnimalFactEntity(
        active_time = active_time,
        animal_type = animal_type,
        diet = diet,
        geo_range = geo_range,
        habitat = habitat,
        id = id,
        image_link = image_link,
        latin_name = latin_name,
        length_max = length_max,
        length_min = length_min,
        lifespan = lifespan,
        name = name,
        weight_max = weight_max,
        weight_min = weight_min
    )
}

fun AnimalFactEntity.toAnimalFact(): AnimalFact {
    return AnimalFact(
        active_time = active_time,
        animal_type = animal_type,
        diet = diet,
        geo_range = geo_range,
        habitat = habitat,
        id = id,
        image_link = image_link,
        latin_name = latin_name,
        length_max = length_max,
        length_min = length_min,
        lifespan = lifespan,
        name = name,
        weight_max = weight_max,
        weight_min = weight_min
    )
}
