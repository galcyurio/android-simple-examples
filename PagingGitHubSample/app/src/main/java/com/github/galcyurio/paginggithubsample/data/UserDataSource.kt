package com.github.galcyurio.paginggithubsample.data

import androidx.paging.ItemKeyedDataSource
import com.github.galcyurio.paginggithubsample.domain.User
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author galcyurio
 */
class UserDataSource : ItemKeyedDataSource<Long, User>() {
    private val service: GitHubService

    init {
        val client = OkHttpClient.Builder()
        service = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client.build())
            .build()
            .create()
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User>) {
        service.fetchUsers().enqueue(object : Callback<List<User>?> {
            override fun onResponse(call: Call<List<User>?>, response: Response<List<User>?>) {
                val users = response.body()
                if (response.isSuccessful && users != null) {
                    callback.onResult(users)
                }
            }

            override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User>) {
        service.fetchUsers(params.key).enqueue(object : Callback<List<User>?> {
            override fun onResponse(call: Call<List<User>?>, response: Response<List<User>?>) {
                val users = response.body()
                if (response.isSuccessful && users != null) {
                    callback.onResult(users)
                }
            }

            override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User>) {
        /* Do nothing */
    }

    override fun getKey(item: User): Long = item.id

}