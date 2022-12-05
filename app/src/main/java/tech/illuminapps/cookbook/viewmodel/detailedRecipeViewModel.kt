package tech.illuminapps.cookbook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import tech.illuminapps.cookbook.model.Post
import tech.illuminapps.cookbook.view.Ingredient
import tech.illuminapps.cookbook.view.Recipe
import tech.illuminapps.cookbook.view.Step

class detailedRecipeViewModel: ViewModel() {


    private val _ingredient = MutableLiveData(Ingredient())
    val ingredient: LiveData<Ingredient> get() = _ingredient

    private val _step = MutableLiveData(Step())
    val step: LiveData<Step> get() = _step


    fun getRecipeInfo(recipeId:String){

        viewModelScope.launch(Dispatchers.IO){

            val result = Firebase.firestore.collection("posts").document(recipeId).get().await()

            var post = result.toObject(Post::class.java)

           // var steps: ArrayList<Step> = arrayListOf()
            val result3 = Firebase.firestore
                .collection("posts").document(post!!.id).collection("steps").get().await()
            for(doc2 in result3.documents){

                val step2 = doc2.toObject(Step::class.java)
                //  Log.e(">>>",step.toString())
                if (step2 != null) {

                    //steps.add(step)
                    _step.postValue(step2)
                }

            }
            //var ingredients: ArrayList<Ingredient> = arrayListOf()
            val result4 = Firebase.firestore
                .collection("posts").document(post.id).collection("ingredients").get().await()
            for(doc3 in result4.documents){

                val ingredient2 = doc3.toObject(Ingredient::class.java)
                // Log.e(">>>",ingredient.toString())
                if (ingredient2 != null) {
                    //ingredients.add(ingredient)
                    _ingredient.postValue(ingredient2)

                }

            }





        }


    }


}