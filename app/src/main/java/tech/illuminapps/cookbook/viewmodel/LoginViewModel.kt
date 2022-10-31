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
import tech.illuminapps.cookbook.model.DataBaseCalls

class LoginViewModel:ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState
     //val db = DataBaseCalls()

    fun logIn(email:String,password:String){

      viewModelScope.launch(Dispatchers.IO){
          Firebase.auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
              Log.e(">>>","Hizo la autorizacion")

              val fbUser = Firebase.auth.currentUser






              // Log.e(">>>","Entro al else")



              _authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))




          }.addOnFailureListener{
              _authState.postValue(AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta"))

          }


          // db.login(email,password)

       //if(value==1){

           //authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))

       //}else{

          //_authState.postValue(AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta"))


       //}


   }



    }

}
