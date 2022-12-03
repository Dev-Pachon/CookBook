package tech.illuminapps.cookbook.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import tech.illuminapps.cookbook.model.Post
import tech.illuminapps.cookbook.model.SavedRecipe
import tech.illuminapps.cookbook.view.Recipe

class SavedRecipesViewModel:ViewModel() {

    private val _recipes = MutableLiveData(Recipe())
    val recipes: LiveData<Recipe> get() = _recipes


    fun getSavedRecipes(){

        viewModelScope.launch(Dispatchers.IO){

            val result = Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).collection("savedRecipes").get().await()

            for(doc in result.documents){


                val recipeId = doc.toObject(SavedRecipe::class.java)
                Log.e(">>>",recipeId.toString())

                recipeId.let {

                    val resultPost = Firebase.firestore.collection("posts").document(it!!.postId).get().await()

                    val post = resultPost.toObject(Post::class.java)

                    post.let {

                        Log.e(">>>", "El documento ${it!!.userId} tiene un tamaño de ${it!!.userId?.length}")
                        if(!it!!.userId.equals("")){
                            val result2  =  Firebase.firestore.collection("users").document(it!!.userId).get().await()

                            val postUser = result2.toObject(tech.illuminapps.cookbook.model.User::class.java)


                            var recipe = Recipe(it!!.name,it!!.mainImage,false, postUser!!.name,postUser!!.image,postUser!!.id,it!!.id)

                            _recipes.postValue(recipe)
                        }
                }








                }

            }



        }



    }

}