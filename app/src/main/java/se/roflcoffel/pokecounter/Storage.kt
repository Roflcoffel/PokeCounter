package se.roflcoffel.pokecounter

import android.content.SharedPreferences
import com.google.gson.Gson

class Storage(private val shared : SharedPreferences) {

    fun <T> Submit(key: String, data: T?) {
        val json = Gson().toJson(data);
        with(shared.edit()) {
            putString(key, json)
            apply()
        }
    }

    fun <T> Retrieve(key: String, clazz: Class<T>) : T {
        val json = shared.getString(key, null)
        return Gson().fromJson<T>(json, clazz)
    }

    fun KeyExist(key: String) : Boolean {
        return shared.getString(key, null) != null
    }

    fun Clear() {
        with(shared.edit()) {
            clear()
            apply()
        }
    }
}