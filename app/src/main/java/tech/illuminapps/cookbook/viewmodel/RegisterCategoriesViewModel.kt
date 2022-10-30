package tech.illuminapps.cookbook.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.illuminapps.cookbook.model.DataBaseCalls

class RegisterCategoriesViewModel: ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState
    val db = DataBaseCalls()

    fun registerCategories(categories:ArrayList<String>){

        Log.e(">>>","Entra al metodo y el arreglo tiene ${categories.toString()}")

        viewModelScope.launch(Dispatchers.IO){


           var value = db.registerCategories(categories)
            if(value==1){

                _authState.value = AuthState(AuthResult.SUCCESS,"Success")

            }else{

                _authState.value = AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta")


            }

            }



        }



    }
