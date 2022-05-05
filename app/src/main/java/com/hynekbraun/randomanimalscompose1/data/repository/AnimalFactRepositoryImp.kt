package com.hynekbraun.randomanimalscompose1.data.repository

import com.hynekbraun.randomanimalscompose1.data.local.AnimalFactDatabase
import com.hynekbraun.randomanimalscompose1.data.mapper.toAnimalFact
import com.hynekbraun.randomanimalscompose1.data.mapper.toAnimalFactEntity
import com.hynekbraun.randomanimalscompose1.data.remote.AnimalFactApi
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.domain.repository.AnimalFactRepository
import com.hynekbraun.randomanimalscompose1.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AnimalFactRepositoryImp
@Inject
constructor(
    private val api: AnimalFactApi,
    private val db: AnimalFactDatabase
) : AnimalFactRepository {
    private val dao = db.factDao

    override suspend fun getAnimalFacts(fetchFromRemote: Boolean): Flow<Resource<List<AnimalFact>>> =
        flow {
            emit(Resource.Loading(true))
            val localData = dao.getAnimals()
            emit(Resource.Success(data = localData.map { it.toAnimalFact() }))

            val isDBEmpty = dao.getAnimals().isEmpty()
            val shouldLoadFromCache = !isDBEmpty && !fetchFromRemote
            if (shouldLoadFromCache) {
                emit(Resource.Loading(isLoading = false))
            //if shouldLoadFromCache is true, it loads the data and stops here
                return@flow
            }
            try {
                val response = api.getAnimalList()
                dao.deleteAnimals()
                dao.insertAnimals(response.map { it.toAnimalFact().toAnimalFactEntity() })
                emit(Resource.Success(data = dao.getAnimals().map { it.toAnimalFact() }))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.localizedMessage))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.localizedMessage))
            }
        }
}