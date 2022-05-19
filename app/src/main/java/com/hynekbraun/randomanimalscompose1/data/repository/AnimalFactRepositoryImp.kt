package com.hynekbraun.randomanimalscompose1.data.repository

import android.util.Log
import com.hynekbraun.randomanimalscompose1.data.local.AnimalFactDatabase
import com.hynekbraun.randomanimalscompose1.data.local.AnimalFactEntity
import com.hynekbraun.randomanimalscompose1.data.mapper.toAnimalFact
import com.hynekbraun.randomanimalscompose1.data.mapper.toAnimalFactEntity
import com.hynekbraun.randomanimalscompose1.data.remote.AnimalFactApi
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.domain.repository.AnimalFactRepository
import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.ErrorState
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
    db: AnimalFactDatabase
) : AnimalFactRepository {
    private val dao = db.factDao

    override suspend fun getAnimalFacts(fetchFromRemote: Boolean): Flow<Resource<List<AnimalFact>>> =
        flow {
            Log.d("TAG", "AnimalFactRepository - getAnimalFactList trigered")
            emit(Resource.Loading(true))
            Log.d("TAG", "AnimalFactRepository - emit isLoading = true")
            val localData = dao.getAnimals()
            emit(Resource.Success(data = localData.map { it.toAnimalFact() }))
            Log.d("TAG", "AnimalFactRepository - emit success")
            val isDBEmpty = dao.getAnimals().isEmpty()
            val shouldLoadFromCache = !isDBEmpty && !fetchFromRemote
            if (shouldLoadFromCache) {
                emit(Resource.Loading(isLoading = false))
                Log.d("TAG", "AnimalFactRepository - emit isLoading = false")
                //if shouldLoadFromCache is true, it loads the data and stops here
                return@flow
            }
            try {
                val response = api.getAnimalList()
                dao.deleteAnimals()
                dao.insertAnimals(response.map { it.toAnimalFact().toAnimalFactEntity() })
                emit(Resource.Success(data = dao.getAnimals().map { it.toAnimalFact() }))
                Log.d("TAG", "AnimalFactRepository - emit Success")
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(error = ErrorState.IOError))
                Log.d("TAG", "AnimalFactRepository - emit Error - IO Error")
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(error = ErrorState.HttpError))
                Log.d("TAG", "AnimalFactRepository - emit Error - Http Error")
            }
        }

    override suspend fun getAnimaFactById(id: Int): AnimalFact {
        return dao.getAnimalById(id).toAnimalFact()
    }
}