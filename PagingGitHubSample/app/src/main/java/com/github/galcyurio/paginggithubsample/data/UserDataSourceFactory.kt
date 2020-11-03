package com.github.galcyurio.paginggithubsample.data

import androidx.paging.DataSource
import com.github.galcyurio.paginggithubsample.domain.User

/**
 * @author galcyurio
 */
class UserDataSourceFactory : DataSource.Factory<Long, User>() {
    override fun create(): DataSource<Long, User> {
        return UserDataSource()
    }
}