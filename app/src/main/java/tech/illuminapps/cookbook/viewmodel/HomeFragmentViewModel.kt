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
import tech.illuminapps.cookbook.model.Follower
import tech.illuminapps.cookbook.model.Post
import tech.illuminapps.cookbook.model.User
import tech.illuminapps.cookbook.view.Ingredient
import tech.illuminapps.cookbook.view.PopularProfile
import tech.illuminapps.cookbook.view.Recipe
import tech.illuminapps.cookbook.view.Step
import kotlin.math.log

class HomeFragmentViewModel: ViewModel()  {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    private val _recipes = MutableLiveData(Recipe())
    val recipes: LiveData<Recipe> get() = _recipes

    private val _recipesTrending = MutableLiveData(Recipe())
    val recipesTrending: LiveData<Recipe> get() = _recipesTrending

    private val _popularProfile = MutableLiveData(PopularProfile())
    val popularProfile: LiveData<PopularProfile> get() = _popularProfile

   // var recipes: ArrayList<Recipe> = arrayListOf()

    fun getFollowedCategoriesPost(){

       // var recipes2: ArrayList<Recipe> = arrayListOf()
        viewModelScope.launch(Dispatchers.IO) {


            val result  =  Firebase.firestore.collection("users")
                .document(Firebase.auth.currentUser!!.uid).get().await()

            val currentUser = result.toObject(tech.illuminapps.cookbook.model.User::class.java)
           //Log.e(">>>",currentUser.toString())

            var posts: ArrayList<Post> = arrayListOf()

            for(categorie in 0..currentUser!!.followedCategories.size-1){

                val result2 = Firebase.firestore.collection("posts").whereArrayContains("categories",currentUser.followedCategories.get(categorie)).get().await()

                for(doc in result2.documents){
                    val post = doc.toObject(Post::class.java)
                    //Log.e(">>>",post.toString())
                    post.let {

                        if(!posts.contains(post) and !(it!!.userId.equals(Firebase.auth.currentUser!!.uid))){
                            posts.add(post!!)
                            /*
                            var steps: ArrayList<Step> = arrayListOf()
                            val result3 = Firebase.firestore
                                .collection("posts").document(post.id).collection("steps").get().await()
                            for(doc2 in result3.documents){

                                val step = doc2.toObject(Step::class.java)
                            //  Log.e(">>>",step.toString())
                                if (step != null) {
                                    steps.add(step)
                                }

                            }
                            var ingredients: ArrayList<Ingredient> = arrayListOf()
                            val result4 = Firebase.firestore
                                .collection("posts").document(post.id).collection("ingredients").get().await()
                            for(doc3 in result4.documents){

                                val ingredient = doc3.toObject(Ingredient::class.java)
                              // Log.e(">>>",ingredient.toString())
                                if (ingredient != null) {
                                    ingredients.add(ingredient)
                                }

                            }

                             */
                            // Log.e(">>>","${post.name} fue hecho por ${post.userId} con las categorias ${post.categories}")
                            //var userId: String = post.userId.toString()
                            //val userIdTest = "${post.userId}"
                            val result2  =  Firebase.firestore.collection("users").document(post.userId).get().await()

                            val postUser = result2.toObject(tech.illuminapps.cookbook.model.User::class.java)


                            var recipe = Recipe(post!!.name,post!!.mainImage,false, postUser!!.name,postUser!!.image,postUser!!.id,post!!.id)
                            //Log.e(">>>",recipe.toString())
                            // recipes2.add(recipe)
                            _recipes.postValue(recipe)

                        }
                    }




                    }


                }

                val result2 = Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).collection("following").get().await()

                for(doc in result2.documents){

                    var following = doc.toObject(Follower::class.java)

                    following.let {

                        val result3 = Firebase.firestore.collection("posts").whereEqualTo("userdId",it!!.id).get().await()

                        for(doc2 in result3.documents){

                            var post = doc2.toObject(Post::class.java)

                            if(!posts.contains(post)){

                                val result4  =  Firebase.firestore.collection("users").document(post!!.userId).get().await()

                                val postUser = result4.toObject(tech.illuminapps.cookbook.model.User::class.java)


                                var recipe = Recipe(post!!.name,post!!.mainImage,false, postUser!!.name,postUser!!.image,postUser!!.id,post!!.id)
                                //Log.e(">>>",recipe.toString())
                                // recipes2.add(recipe)
                                _recipes.postValue(recipe)

                            }


                        }



                    }


                }


            }

           // Log.e(">>>",recipes.toString())
          //  recipes = recipes2

           // Log.e(">>>",recipes2.toString())
          // _authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))


        }
    fun getTrendingPost(){

        viewModelScope.launch(Dispatchers.IO){

            val result = Firebase.firestore.collection("posts").orderBy("grade").limit(10).get().await()

            for(doc in result.documents){
                val post = doc.toObject(Post::class.java)

                 post.let {

                     val result2  =  Firebase.firestore.collection("users").document(it!!.userId).get().await()

                     val postUser = result2.toObject(tech.illuminapps.cookbook.model.User::class.java)


                     var recipe = Recipe(post!!.name,post!!.mainImage,false, postUser!!.name,postUser!!.image,postUser!!.id,post!!.id)
                     //Log.e(">>>",recipe.toString())
                     // recipes2.add(recipe)
                     _recipesTrending.postValue(recipe)

                 }

                }

        }




    }
    fun getPopularProfiles(){

        viewModelScope.launch(Dispatchers.IO){

            val result = Firebase.firestore.collection("users").orderBy("followerQuantity").limit(10).get().await()

            for(doc in result.documents){

                val user = doc.toObject(User::class.java)
                user.let {
                    Log.e(">>>", "Usuario ${it!!.name}")
                    val popularProfile = PopularProfile(it!!.id,it!!.name,it!!.image)
                    _popularProfile.postValue(popularProfile)
                }



            }



        }


    }



    }

