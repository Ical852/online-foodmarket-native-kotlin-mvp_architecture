package com.egp.fmkotlin

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.egp.fmkotlin.network.HttpClient

class FoodMarket : MultiDexApplication() {

    companion object {
        lateinit var instance : FoodMarket

        fun getApp() : FoodMarket {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreferences() : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token : String) {
        getPreferences().edit().putString("PREFERENCES_TOKEN", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken() : String? {
        return getPreferences().getString("PREFERENCES_TOKEN", null)
    }

    fun setUser(user : String) {
        getPreferences().edit().putString("PREFERENCES_USER", user).apply()
    }

    fun getUser() : String? {
        return getPreferences().getString("PREFERENCES_USER", null)
    }

}