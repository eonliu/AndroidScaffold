package com.eonliu.android.scaffold.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.eonliu.android.scaffold.Scaffold
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

const val DEFAULT_DATA_STORE_NAME = "scaffold_data_store"

var dataStoreName = DEFAULT_DATA_STORE_NAME

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = dataStoreName)

suspend inline fun <reified T> datastoreReadContent(key: String): T? {
    val k = when (T::class) {
        String::class -> stringPreferencesKey(key)
        Int::class -> intPreferencesKey(key)
        Boolean::class -> booleanPreferencesKey(key)
        Double::class -> doublePreferencesKey(key)
        Float::class -> floatPreferencesKey(key)
        else -> longPreferencesKey(key)
    }
    return Scaffold.app.dataStore.data.map { it[k] ?: "" }.first() as T?
}

suspend inline fun <reified T> datastoreWriteContent(key: String, value: T) {
    when (T::class) {
        String::class -> {
            Scaffold.app.dataStore.edit { it[stringPreferencesKey(key)] = value as String }
        }
        Int::class -> {
            Scaffold.app.dataStore.edit { it[intPreferencesKey(key)] = value as Int }
        }
        Boolean::class -> {
            Scaffold.app.dataStore.edit { it[booleanPreferencesKey(key)] = value as Boolean }
        }
        Double::class -> {
            Scaffold.app.dataStore.edit { it[doublePreferencesKey(key)] = value as Double }
        }
        Float::class -> {
            Scaffold.app.dataStore.edit { it[floatPreferencesKey(key)] = value as Float }
        }
        else -> {
            Scaffold.app.dataStore.edit { it[longPreferencesKey(key)] = value as Long }
        }
    }
}