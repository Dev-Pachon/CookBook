package tech.devpachon.cookbook.model
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.devpachon.cookbook.model.DataBaseCalls
import tech.illuminapps.cookbook.viewmodel.AuthResult
import tech.illuminapps.cookbook.viewmodel.AuthState

class DataBaseCalls {


    fun login(email:String,password:String):Int{

        var toReturn = 0

        Firebase.auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            Log.e(">>>","Hizo la autorizacion")

            val fbUser = Firebase.auth.currentUser

            toReturn = 1





            // Log.e(">>>","Entro al else")







        }.addOnFailureListener{

            toReturn = -1
        }
    return toReturn

    }

    fun register(name:String,email:String,password:String):Int{

        var toReturn = 0


        Firebase.auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

            // val fbUser = Firebase.auth.currentUser
            Log.e(">>>","Se creo el usuario con id = ${Firebase.auth.currentUser?.uid}")




            var followedCategories = ArrayList<String>()
            var user = User(Firebase.auth.currentUser?.uid.toString(), name,email,followedCategories)

            Firebase.firestore.collection("users").document(user.id).set(user)



            // Log.e(">>>","Entro al else")

            toReturn = 1





        }.addOnFailureListener{
            Log.e(">>>","Fallo la creacion")
            toReturn = -1

        }
        return toReturn
    }


    fun registerCategories(categories:ArrayList<String>):Int{

        var toReturn = 0
        Firebase.firestore.collection("users").document(Firebase.auth.currentUser?.uid.toString()).update("followedCategories",categories).addOnSuccessListener(){
            toReturn = 1
        }.addOnFailureListener(){
            toReturn = -1
        }


        return toReturn

    }


}