
package com.example.ioshacketonapp.shared
import com.example.ioshacketonapp.shared.entity.RocketLaunch
import com.example.ioshacketonapp.shared.network.SpaceXApi

class SpaceXSDK () {
    private val api = SpaceXApi()

    @Throws(Exception::class) suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
            api.getAllLaunches().also {
                return  it
            }
    }
}