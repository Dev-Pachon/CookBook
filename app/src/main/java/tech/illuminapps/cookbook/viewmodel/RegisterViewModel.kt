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

class RegisterViewModel:ViewModel() {
    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    fun register(name:String,email:String,password:String){

        Log.e(">>>","Entra al metodo")

        viewModelScope.launch(Dispatchers.IO){
            Firebase.auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

                val fbUser = Firebase.auth.currentUser
                Log.e(">>>","Se creo el usuario con id = ${Firebase.auth.currentUser?.uid}")


                _authState.value = AuthState(AuthResult.SUCCESS,"Success")




                // Log.e(">>>","Entro al else")







            }.addOnFailureListener{
                Log.e(">>>","Fallo la creacion")
                _authState.value = AuthState(AuthResult.FAIL,it.toString())
            }


        }



    }
}