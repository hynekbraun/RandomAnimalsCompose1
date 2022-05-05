package com.hynekbraun.randomanimalscompose1.data.remote

import com.hynekbraun.randomanimalscompose1.data.remote.dto.AnimalFactDto
import retrofit2.http.GET
import java.util.concurrent.Flow

interface AnimalFactApi {

    @GET("animals/rand/10")
    suspend fun getAnimalList(): List<AnimalFactDto>

    companion object {
        val BASE_URL = "https://zoo-animal-api.herokuapp.com"
    }
}