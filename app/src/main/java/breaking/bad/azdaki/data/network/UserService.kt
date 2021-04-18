package breaking.bad.azdaki.data.network

import breaking.bad.azdaki.data.models.user.UserProfile
import breaking.bad.azdaki.data.models.user.UserRegistrationRequest
import breaking.bad.azdaki.data.models.user.UserSession
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("/auth/login")
    suspend fun login(
        @Query("username") username:String,
        @Query("password") password: String
    ): UserSession


    @POST("/auth/register")
    suspend fun register(@Body request: UserRegistrationRequest)

    @GET("/auth/user")
    suspend fun getUser() : UserProfile



    @GET("/user/braking-bad/get-my-characters")
    suspend fun getUserCards(): List<String>

    @POST("/user/braking-bad/save-character")
    suspend fun saveUserCards(@Query("cardId") cardId: String)

    @DELETE("/user/braking-bad/delete-my-character")
    suspend fun deleteUserCard(@Query("cardId") cardId: String)




}