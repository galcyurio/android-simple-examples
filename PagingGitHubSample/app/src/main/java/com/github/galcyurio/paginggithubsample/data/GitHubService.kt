package com.github.galcyurio.paginggithubsample.data

import com.github.galcyurio.paginggithubsample.domain.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author galcyurio
 */
interface GitHubService {
    /**
     * @param since 요청했던 리스트의 마지막 유저 ID
     */
    @GET("users")
    fun fetchUsers(
        @Query("since") since: Long? = null
    ): Call<List<User>>
}