
package com.example.ioshacketonapp.shared
import com.example.ioshacketonapp.shared.cache.Database
import com.example.ioshacketonapp.shared.cache.DatabaseDriverFactory
import com.example.ioshacketonapp.shared.entity.RocketLaunch
import com.example.ioshacketonapp.shared.network.SpaceXApi

class SpaceXSDK (databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    @Throws(Exception::class) suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}