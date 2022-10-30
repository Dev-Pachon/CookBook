package tech.illuminapps.cookbook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.illuminapps.cookbook.model.DataBaseCalls

class LoginViewModel:ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState
     val db = DataBaseCalls()

    fun logIn(email:String,password:String){

      viewModelScope.launch(Dispatchers.IO){

       var value =  db.login(email,password)

       if(value==1){

           _authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))

       }else{

           _authState.postValue(AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta"))


       }


   }



    }

}
