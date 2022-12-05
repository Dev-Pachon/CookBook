package tech.illuminapps.cookbook.viewmodel

import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import tech.illuminapps.cookbook.model.Post
import tech.illuminapps.cookbook.view.Ingredient
import tech.illuminapps.cookbook.view.Step
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class CreateRecipeViewModel: ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    fun addPost(ingredients:ArrayList<Ingredient>, steps:ArrayList<Step>,name:String, categories: ArrayList<String>,mainImage:Uri){

        viewModelScope.launch(Dispatchers.IO){

        var uid = UUID.randomUUID().toString()


         var file:File = File(mainImage.path)
         var post = Post(uid,name,categories,Firebase.auth.currentUser?.uid.toString(),file.name)

         Firebase.firestore.collection("posts").document(uid).set(post)

            //Firebase.storage.reference.child("posts").child(uid).child(file.name).putFile(mainImage)
            Firebase.storage.reference.child("posts").child(uid).putFile(mainImage)


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
            stepToAdd.id = uid2
           // Log.e(">>>",stepToAdd.toString())
            if(stepToAdd.image==null){

            }else{
                var file:File = File(stepToAdd.image)
                var uri = Uri.parse(stepToAdd.image)
                stepToAdd.image = file.name
                Firebase.storage.reference.child("posts").child(uid).child(file.name).putFile(uri)
            }

            Firebase.firestore.collection("posts").document(uid).collection("steps").document(uid2).set(stepToAdd)




        }



        }



        }





    }



