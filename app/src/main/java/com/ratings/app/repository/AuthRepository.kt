package com.ratings.app.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.ratings.app.api.RatingsApiClient
import com.ratings.app.helper.KEY_ACCESS_TOKEN
import com.ratings.app.type.LoginInput
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AuthRepository @Inject constructor(private val preferences: SharedPreferences,private val apiClient: RatingsApiClient) {
    private lateinit var authNetworkSource: AuthNetworkSource

    fun authenticateUser(loginInput: LoginInput, compositeDisposable: CompositeDisposable): LiveData<String> {
        authNetworkSource = AuthNetworkSource(apiClient, compositeDisposable)
        authNetworkSource.fetchAccessToken(loginInput)
        return authNetworkSource.userToken
    }

    fun saveAccessToken(accessToken: String) {
        preferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).commit()
    }

    fun getAuthUserNetworkState(): LiveData<NetworkState> {
        return authNetworkSource.networkState
    }
}