package app.nationalize.data.remote

import app.nationalize.data.model.NationalizeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NationalizeApi {

    @GET("/")
    suspend fun predictNationality(@Query("name") name: String): NationalizeResponse

}