package com.hynekbraun.randomanimalscompose1.domain.repository

import com.hynekbraun.randomanimalscompose1.data.remote.AnimalFactApi
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.resource.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AnimalFactRepository {

    suspend fun getAnimalFacts(fetchFromRemote: Boolean): Flow<Resource<List<AnimalFact>>>
}