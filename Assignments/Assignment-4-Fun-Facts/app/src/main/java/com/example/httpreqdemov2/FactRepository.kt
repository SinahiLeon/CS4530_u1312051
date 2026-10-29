package com.example.httpreqdemov2

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class FactRepository private constructor(
    private val dao: FactDao,
    private val client: HttpClient
) {
    fun searchFacts(keyword: String): Flow<List<SavedFact>>{
        return dao.searchFacts(keyword)
    }
    suspend fun fetchFact() {
        val fact: FunFact = client
            .get("https://uselessfacts.jsph.pl/random.json?language=en")
            .body()

        dao.insertFact(SavedFact(text = fact.text))
    }

    companion object {
        @Volatile
        private var instance: FactRepository? = null

        fun getInstance(
            dao: FactDao,
            client: HttpClient
        ): FactRepository {
            return instance ?: synchronized(this) {
                instance ?: FactRepository(dao, client).also{
                    instance = it
                }
            }
        }


    }
}