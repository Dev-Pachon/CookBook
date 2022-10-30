package tech.illuminapps.cookbook.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.devpachon.cookbook.model.DataBaseCalls

class LoginViewModel:ViewModel() {

    //private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    //val authState : LiveData<AuthState> get() = _authState
     val db = DataBaseCalls()

    fun logIn(email:String,password:String){

      viewModelScope.launch(Dispatchers.IO){

        db.login(email,password)



   }



    }

}
