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
import tech.illuminapps.cookbook.model.User

class RegisterViewModel:ViewModel() {

   private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    //val db = DataBaseCalls()

    fun register(name:String,email:String,password:String){

        Log.e(">>>","Entra al metodo")

        viewModelScope.launch(Dispatchers.IO){
            Firebase.auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

                // val fbUser = Firebase.auth.currentUser
                Log.e(">>>","Se creo el usuario con id = ${Firebase.auth.currentUser?.uid}")




                var followedCategories = ArrayList<String>()
                var user = User(Firebase.auth.currentUser?.uid.toString(), name,email,followedCategories)

               // User.instance = user
                Firebase.firestore.collection("users").document(user.id).set(user)



                // Log.e(">>>","Entro al else")


                _authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))




            }.addOnFailureListener{
                _authState.postValue(AuthState(AuthResult.FAIL,it.localizedMessage.toString()))

            }
          // db.register(name,email,password)
/*
            if(value==1){

                _authState.value = AuthState(AuthResult.SUCCESS,"Success")

            }else{

                _authState.value = AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta")


            }

 */

        }



    }
}