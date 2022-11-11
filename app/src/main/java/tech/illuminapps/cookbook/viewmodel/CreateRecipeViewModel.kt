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
import kotlinx.coroutines.tasks.await
import tech.illuminapps.cookbook.view.Ingredient
import tech.illuminapps.cookbook.view.Step
import java.util.*
import kotlin.collections.ArrayList

class CreateRecipeViewModel: ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    fun addPost(ingredients:ArrayList<Ingredient>, steps:ArrayList<Step>,name:String){

        viewModelScope.launch(Dispatchers.IO){

        var uid = UUID.randomUUID().toString()
        for(ingredient in 0..ingredients.size-1){

            var uid2 = UUID.randomUUID().toString()
            Firebase.firestore.collection("posts").document(uid).collection("ingredientds").document(uid2).set(ingredient)


            }
        for(step in 0..steps.size-1){

            var uid2 = UUID.randomUUID().toString()
            Firebase.firestore.collection("posts").document(uid).collection("steps").document(uid2).set(step)


        }

        }



        }





    }



