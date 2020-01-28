package com.github.galcyurio.paginggithubsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.galcyurio.paginggithubsample.data.UserDataSourceFactory
import com.github.galcyurio.paginggithubsample.domain.User

/**
 * @author galcyurio
 */
class MainViewModel : ViewModel() {
    val users: LiveData<PagedList<User>>

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .build()
        users = LivePagedListBuilder(UserDataSourceFactory(), config).build()
    }

}