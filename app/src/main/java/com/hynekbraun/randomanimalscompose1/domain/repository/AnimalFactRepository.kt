package com.hynekbraun.randomanimalscompose1.domain.repository

import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.util.Resource
import kotlinx.coroutines.flow.Flow

interface AnimalFactRepository {

    suspend fun getAnimalFacts(fetchFromRemote: Boolean): Flow<Resource<List<AnimalFact>>>
}