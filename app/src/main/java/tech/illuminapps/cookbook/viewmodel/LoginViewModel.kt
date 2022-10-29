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

class LoginViewModel:ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    fun logIn(email:String,password:String){

   viewModelScope.launch(Dispatchers.IO){
       Firebase.auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
           Log.e(">>>","Hizo la autorizacion")

           val fbUser = Firebase.auth.currentUser


                   _authState.value = AuthState(AuthResult.SUCCESS,"Success")




              // Log.e(">>>","Entro al else")







       }.addOnFailureListener{
           _authState.value = AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta")
       }


   }



    }




}
