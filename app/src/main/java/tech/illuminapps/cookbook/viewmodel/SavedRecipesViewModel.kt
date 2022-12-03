package tech.illuminapps.cookbook.viewmodel

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
import tech.illuminapps.cookbook.view.Recipe

class SavedRecipesViewModel:ViewModel() {

    private val _recipes = MutableLiveData(Recipe())
    val recipes: LiveData<Recipe> get() = _recipes


    fun getSavedRecipes(){

        viewModelScope.launch(Dispatchers.IO){

            val result = Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).collection("savedRecipes").get().await()

            for(doc in result.documents){


                val post = doc.toObject(Post::class.java)

                post.let {

                    
                }

            }



        }



    }

}