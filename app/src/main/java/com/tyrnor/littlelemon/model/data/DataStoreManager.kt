package com.tyrnor.littlelemon.model.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tyrnor.littlelemon.model.OnboardingModel
import kotlinx.coroutines.flow.map

const val USER_DATASTORE = "user_datastore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name= USER_DATASTORE)


class DataStoreManager (private val context: Context){

    companion object{
        val FIRST_NAME = stringPreferencesKey("FIRST_NAME")
        val LAST_NAME = stringPreferencesKey("LAST_NAME")
        val EMAIL = stringPreferencesKey("EMAIL")
    }

    suspend fun saveToDataStore(onboardingModel: OnboardingModel){
        context.dataStore.edit {
            it[FIRST_NAME] = onboardingModel.firstName
            it[LAST_NAME] = onboardingModel.lastName
            it[EMAIL] = onboardingModel.email
        }
    }

    fun getFromDataStore() = context.dataStore.data.map {
        OnboardingModel(
            firstName = it[FIRST_NAME]?:"",
            lastName = it[LAST_NAME]?:"",
            email = it[EMAIL]?:"",

        )
    }

    suspend fun clearDataSotre() = context.dataStore.edit {
        it.clear()
    }

}