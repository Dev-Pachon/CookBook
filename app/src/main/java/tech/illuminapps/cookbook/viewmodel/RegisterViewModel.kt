package tech.illuminapps.cookbook.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.illuminapps.cookbook.model.DataBaseCalls

class RegisterViewModel:ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    val db = DataBaseCalls()

    fun register(name:String,email:String,password:String){

        Log.e(">>>","Entra al metodo")

        viewModelScope.launch(Dispatchers.IO){

           var value =  db.register(name,email,password)

            if(value==1){

                _authState.value = AuthState(AuthResult.SUCCESS,"Success")

            }else{

                _authState.value = AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta")


            }

        }



    }
}