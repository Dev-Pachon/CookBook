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
import tech.illuminapps.cookbook.model.Follower
import tech.illuminapps.cookbook.model.Post
import tech.illuminapps.cookbook.model.User
import tech.illuminapps.cookbook.view.Recipe

class ProfileViewModel: ViewModel(){

    private val _recipes = MutableLiveData(Recipe())
    val recipes: LiveData<Recipe> get() = _recipes

    private val _user = MutableLiveData(User())
    val user: LiveData<User> get() = _user

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    fun getUserInfo(userId:String){

        viewModelScope.launch(Dispatchers.IO) {

            val result = Firebase.firestore.collection("users").document(userId).get().await()

            val user2 = result.toObject(tech.illuminapps.cookbook.model.User::class.java)

            val result2 = Firebase.firestore.collection("users").document(userId).collection("followers").get().await()

            val result3 = Firebase.firestore.collection("users").document(userId).collection("following").get().await()

            val result4 = Firebase.firestore.collection("posts").whereEqualTo("userId",
                userId).get().await()

            user2!!.followerQuantity = result2.documents.size.toString()
            user2!!.followingQuantity = result3.documents.size.toString()
            user2!!.postQuantity = result4.documents.size.toString()
            _user.postValue(user2)


            for(doc in result4.documents){

                val post = doc.toObject(Post::class.java)

                post.let {
                    val result2  =  Firebase.firestore.collection("users").document(userId).get().await()

                    val postUser = result2.toObject(tech.illuminapps.cookbook.model.User::class.java)

                    var recipe = Recipe(it!!.name,it.mainImage,true, postUser!!.name,postUser!!.image,postUser!!.id,post!!.id)
                   // Log.e(">>>",recipe.id)
                    _recipes.postValue(recipe)
                }




            }
            _authState.postValue(AuthState(AuthResult.SUCCESS,"Success"))


        }

    }
    fun addFollower(postOwner:String){

        viewModelScope.launch(Dispatchers.IO) {
            val result = Firebase.firestore.collection("users")
                .document(Firebase.auth.currentUser!!.uid).collection("following").document(postOwner).get().await()
            Log.e(">>>","Ya hizo el result")
            if(!result.exists()){
                Log.e(">>>","Hizo la comprobacion")
                Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).collection("following").document(postOwner).set(
                    Follower(postOwner)
                ).addOnSuccessListener(){

                    Firebase.firestore.collection("users").document(postOwner).collection("followers").document(Firebase.auth.currentUser!!.uid).set(
                        Follower(Firebase.auth.currentUser!!.uid)
                    ).addOnSuccessListener(){

                      //  Log.e(">>>","Debio crearse")

                        _authState.postValue(AuthState(AuthResult.SUCCESS,"sucess"))
                    }

                }


            }



        }
    }

}