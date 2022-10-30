package tech.illuminapps.cookbook.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devpachon.cookbook.model.DataBaseCalls
import tech.devpachon.cookbook.model.User

class RegisterViewModel:ViewModel() {
    //private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    //val authState : LiveData<AuthState> get() = _authState

    val db = DataBaseCalls()

    fun register(name:String,email:String,password:String){

        Log.e(">>>","Entra al metodo")

        viewModelScope.launch(Dispatchers.IO){

            db.register(name,email,password)


        }



    }
}