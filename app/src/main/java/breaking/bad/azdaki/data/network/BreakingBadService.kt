package breaking.bad.azdaki.data.network

import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem
import breaking.bad.azdaki.data.models.breaking.Quotes
import retrofit2.http.GET
import retrofit2.http.Query


interface BreakingBadService {

    @GET("characters")
    suspend fun getChars(
            @Query("limit") limit: Int,
            @Query("offset") offset: Int
    ): List<AllCharactersInfoItem>



    @GET("characters")
    suspend fun getChars(
            @Query("name") name: String? = null
    ): List<AllCharactersInfoItem>


    @GET("quote")
    suspend fun getQuotes(
            @Query("author") author: String? = null
    ):List<Quotes>



}


