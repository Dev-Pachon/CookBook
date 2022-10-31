package tech.illuminapps.cookbook.model
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tech.illuminapps.cookbook.viewmodel.AuthResult
import tech.illuminapps.cookbook.viewmodel.AuthState
import kotlin.math.log

class DataBaseCalls {


    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    fun login(email:String,password:String){


        Firebase.auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            Log.e(">>>","Hizo la autorizacion")

            val fbUser = Firebase.auth.currentUser






            // Log.e(">>>","Entro al else")



            _authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))




        }.addOnFailureListener{
            _authState.postValue(AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta"))

        }


    }

    fun register(name:String,email:String,password:String){



        Firebase.auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

            // val fbUser = Firebase.auth.currentUser
            Log.e(">>>","Se creo el usuario con id = ${Firebase.auth.currentUser?.uid}")




            var followedCategories = ArrayList<String>()
            var user = User(Firebase.auth.currentUser?.uid.toString(), name,email,followedCategories)

            Firebase.firestore.collection("users").document(user.id).set(user)



            // Log.e(">>>","Entro al else")


            _authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))




        }.addOnFailureListener{
            Log.e(">>>","Fallo la creacion")
            _authState.postValue(AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta"))

        }

    }


    fun registerCategories(categories:ArrayList<String>){

        Firebase.firestore.collection("users").document(Firebase.auth.currentUser?.uid.toString()).update("followedCategories",categories).addOnSuccessListener(){
            _authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))

        }.addOnFailureListener(){
            _authState.postValue(AuthState(AuthResult.FAIL,"Correo y/o Contraseña Incorrecta"))

        }




    }


}