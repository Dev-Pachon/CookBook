package tech.illuminapps.cookbook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val _authState = MutableLiveData(
        AuthState(AuthResult.IDLE, "Starting...")
    )

    val authState : LiveData<AuthState> get() = _authState

    private val _firstInit = MutableLiveData(
        FirstInit.FIRST_INIT
    )

    val firstInit : LiveData<FirstInit> get() = _firstInit

    fun testing(){
        _authState.value = AuthState(AuthResult.FAIL,"")
    }


}
data class AuthState(val result:AuthResult, val message:String)
enum class AuthResult{ IDLE, FAIL, SUCCESS }
enum class FirstInit{ FIRST_INIT, NORMAL_INIT}