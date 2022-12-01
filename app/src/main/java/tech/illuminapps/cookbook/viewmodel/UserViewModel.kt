package tech.illuminapps.cookbook.viewmodel

import android.app.Activity
import android.content.Context
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
import kotlinx.coroutines.tasks.await
import tech.illuminapps.cookbook.model.Post
import tech.illuminapps.cookbook.model.SharedPreferences
import tech.illuminapps.cookbook.view.Recipe
import java.io.File

class UserViewModel : ViewModel() {

    private val _recipes = MutableLiveData(Recipe())
    val recipes: LiveData<Recipe> get() = _recipes

    private val _authState = MutableLiveData(
        AuthState(AuthResult.IDLE, "Starting...")
    )

    val authState: LiveData<AuthState> get() = _authState

    private val _firstInit = MutableLiveData(
        FirstInit.FIRST_INIT
    )

    val firstInit: LiveData<FirstInit> get() = _firstInit

    fun logOut(activity: Activity) {
        _authState.value = AuthState(AuthResult.FAIL, "")
        SharedPreferences.saveAuthState(authState.value?.result!!, activity)
    }

    fun logIn(activity: Activity) {
        _authState.value = AuthState(AuthResult.SUCCESS, "")
        SharedPreferences.saveAuthState(authState.value?.result!!, activity)
    }

    fun turnOffWelcomeFlow(activity: Activity) {
        _firstInit.value = FirstInit.NORMAL_INIT
        SharedPreferences.saveInitState(firstInit.value!!, activity)
    }

    fun setPreferences(activity: Activity){
        _firstInit.value = SharedPreferences.InitState(activity)
        _authState.value = AuthState(SharedPreferences.AuthState(activity),"")
    }
    fun getUserPost(){

        Log.e(">>>","Esta entrando al metodo")

        viewModelScope.launch(Dispatchers.IO) {

            Log.e(">>>","Deberia mostrar el uid ${Firebase.auth.currentUser!!.uid.toString()}")
            val result = Firebase.firestore.collection("posts").whereEqualTo("userId",
                Firebase.auth.currentUser!!.uid.toString()).get().await()


            for(doc in result.documents){

                val post = doc.toObject(Post::class.java)

                post.let {
                    val result2  =  Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).get().await()

                    val postUser = result2.toObject(tech.illuminapps.cookbook.model.User::class.java)

                    var recipe = Recipe(it!!.name,it.mainImage,true, postUser!!.name,postUser!!.image,postUser!!.id,post!!.id)
                    Log.e(">>>",recipe.id)
                    _recipes.postValue(recipe)
                }



            }

        }

    }

}

data class AuthState(val result: AuthResult, val message: String)
enum class AuthResult { IDLE, FAIL, SUCCESS }
enum class FirstInit { FIRST_INIT, NORMAL_INIT }