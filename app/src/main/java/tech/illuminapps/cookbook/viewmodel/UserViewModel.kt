package tech.illuminapps.cookbook.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.illuminapps.cookbook.model.SharedPreferences
import java.io.File

class UserViewModel : ViewModel() {

    private val _authState = MutableLiveData(
        AuthState(AuthResult.IDLE, "Starting...")
    )

    val authState: LiveData<AuthState> get() = _authState

    private val _firstInit = MutableLiveData(
        FirstInit.FIRST_INIT
    )

    val firstInit: LiveData<FirstInit> get() = _firstInit

    fun logOut(activity: Activity) {
        _authState.value = AuthState(AuthResult.FAIL, "")
        SharedPreferences.saveAuthState(authState.value?.result!!, activity)
    }

    fun logIn(activity: Activity) {
        _authState.value = AuthState(AuthResult.SUCCESS, "")
        SharedPreferences.saveAuthState(authState.value?.result!!, activity)
    }

    fun turnOffWelcomeFlow(activity: Activity) {
        _firstInit.value = FirstInit.NORMAL_INIT
        SharedPreferences.saveInitState(firstInit.value!!, activity)
    }

    fun setPreferences(activity: Activity){
        _firstInit.value = SharedPreferences.InitState(activity)
        _authState.value = AuthState(SharedPreferences.AuthState(activity),"")
    }

}

data class AuthState(val result: AuthResult, val message: String)
enum class AuthResult { IDLE, FAIL, SUCCESS }
enum class FirstInit { FIRST_INIT, NORMAL_INIT }