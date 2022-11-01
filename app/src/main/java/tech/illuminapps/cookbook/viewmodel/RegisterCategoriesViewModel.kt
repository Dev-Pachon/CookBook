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

class RegisterCategoriesViewModel: ViewModel() {

  private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState
    //val db = DataBaseCalls()

    fun registerCategories(categories:ArrayList<String>){

        Log.e(">>>","Entra al metodo y el arreglo tiene ${categories.toString()}")

        viewModelScope.launch(Dispatchers.IO){

            Firebase.firestore.collection("users").document(Firebase.auth.currentUser?.uid.toString()).update("followedCategories",categories).addOnSuccessListener(){
                _authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))

            }.addOnFailureListener(){
                _authState.postValue(AuthState(AuthResult.FAIL,it.localizedMessage.toString()))

            }

          // db.registerCategories(categories)


           //     _authState.value = AuthState(AuthResult.SUCCESS,"Success")

            //}else{

               // _authState.value = AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta")


            //}

            }



        }



    }
