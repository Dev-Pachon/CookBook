package tech.illuminapps.cookbook.model

import android.app.Activity
import android.content.Context
import tech.illuminapps.cookbook.viewmodel.AuthResult
import tech.illuminapps.cookbook.viewmodel.FirstInit

object SharedPreferences {

    fun InitState(activity: Activity):FirstInit {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return FirstInit.valueOf(sharedPref.getString("FIRST_INIT", FirstInit.FIRST_INIT.toString())!!)
    }

    fun AuthState(activity: Activity):AuthResult{
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return AuthResult.valueOf(sharedPref.getString("AUTH_RESULT", AuthResult.FAIL.toString())!!)
    }

    fun saveInitState(firstInit: FirstInit, activity: Activity){
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("FIRST_INIT", firstInit.toString())
            apply()
        }
    }

    fun saveAuthState(authResult: AuthResult, activity: Activity){
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("AUTH_RESULT", authResult.toString())
            apply()
        }
    }

}