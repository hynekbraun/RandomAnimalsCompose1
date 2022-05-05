package com.hynekbraun.randomanimalscompose1.data.repository

import com.hynekbraun.randomanimalscompose1.data.local.AnimalFactDatabase
import com.hynekbraun.randomanimalscompose1.data.mapper.toAnimalFact
import com.hynekbraun.randomanimalscompose1.data.mapper.toAnimalFactEntity
import com.hynekbraun.randomanimalscompose1.data.remote.AnimalFactApi
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.domain.repository.AnimalFactRepository
import com.hynekbraun.randomanimalscompose1.resource.Resource
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

            val animalFacts = dao.getAnimals()

            if (animalFacts.isNotEmpty() && !fetchFromRemote) {
                emit(Resource.Success(animalFacts.map { it.toAnimalFact() }))
            } else {
                try {
                    val response = api.getAnimalList()
                    dao.deleteAnimals()
                    dao.insertAnimals(response.map {
                        it.toAnimalFact().toAnimalFactEntity()
                    })
                    emit(Resource.Success(dao.getAnimals().map { it.toAnimalFact() }))
                } catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Error(message = e.message.toString()))
                } catch (e: HttpException) {
                    e.printStackTrace()
                    emit(Resource.Error(message = e.message.toString()))
                }
            }
        }

}