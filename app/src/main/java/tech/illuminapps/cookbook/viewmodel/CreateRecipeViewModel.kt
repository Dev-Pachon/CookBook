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
import tech.illuminapps.cookbook.model.Post
import tech.illuminapps.cookbook.view.Ingredient
import tech.illuminapps.cookbook.view.Step
import java.util.*
import kotlin.collections.ArrayList

class CreateRecipeViewModel: ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    fun addPost(ingredients:ArrayList<Ingredient>, steps:ArrayList<Step>,name:String, categories: ArrayList<String>){

        viewModelScope.launch(Dispatchers.IO){

        var uid = UUID.randomUUID().toString()


         var post = Post(uid,name,categories,Firebase.auth.currentUser?.uid.toString())

         Firebase.firestore.collection("posts").document(uid).set(post)

         Log.e(">>>",ingredients.toString())
         Log.e(">>>",steps.toString())

        for(ingredient in 0..ingredients.size-1){

            var uid2 = UUID.randomUUID().toString()
            var ingredientToadd = ingredients.get(ingredient)
            ingredientToadd.id = uid2
            Firebase.firestore.collection("posts").document(uid).collection("ingredients").document(uid2).set(ingredientToadd)


        }

        for(step in 0..steps.size-1){

           var uid2 = UUID.randomUUID().toString()
            var stepToAdd = steps.get(step)
           // Log.e(">>>",stepToAdd.toString())
            Firebase.firestore.collection("posts").document(uid).collection("steps").document(uid2).set(stepToAdd)


        }



        }



        }





    }



