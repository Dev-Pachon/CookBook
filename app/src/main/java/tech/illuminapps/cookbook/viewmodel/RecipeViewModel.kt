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
import tech.illuminapps.cookbook.model.SavedRecipe
import tech.illuminapps.cookbook.model.User
import tech.illuminapps.cookbook.view.Comment

class RecipeViewModel: ViewModel() {

    private val _user = MutableLiveData(User())
    val user: LiveData<User> get() = _user

    private val _comment = MutableLiveData(Comment())
    val comment: LiveData<Comment> get() = _comment

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    fun getUserData(){

        viewModelScope.launch(Dispatchers.IO) {


            val result = Firebase.firestore.collection("users")
                .document(Firebase.auth.currentUser!!.uid).get().await()

            val currentUser = result.toObject(tech.illuminapps.cookbook.model.User::class.java)
            _user.postValue(currentUser)

        }
    }
    fun addComment(comment:Comment,postId:String){

        viewModelScope.launch(Dispatchers.IO) {

            Firebase.firestore.collection("posts").document(postId).collection("comments").document(comment.id).set(comment).addOnSuccessListener {

                Log.e(">>>", "Comentario a;adido en el post ${postId}")
            }

        }

    }
    fun getComments(postId: String){

        viewModelScope.launch(Dispatchers.IO) {

            Log.e(">>>",postId)
            val result = Firebase.firestore.collection("posts")
                .document(postId).collection("comments").get().await()
            for(doc in result.documents){

                val com = doc.toObject(Comment::class.java)
                com?.let {
                    Log.e(">>>","${it.content} Aqui deberia salir algo" )
                    _comment.postValue(com)
                }

            }

        }
    }
    fun addFollower(postOwner:String,currentUser:String){

        viewModelScope.launch(Dispatchers.IO) {
            val result = Firebase.firestore.collection("users")
                .document(Firebase.auth.currentUser!!.uid).collection("following").document(postOwner).get().await()
           // Log.e(">>>","Ya hizo el result")
            if(!result.exists()){
              //  Log.e(">>>","Hizo la comprobacion")
                Firebase.firestore.collection("users").document(currentUser).collection("following").document(postOwner).set(Follower(postOwner)).addOnSuccessListener(){

                    Firebase.firestore.collection("users").document(postOwner).collection("followers").document(currentUser).set(Follower(currentUser)).addOnSuccessListener(){

                       // Log.e(">>>","Debio crearse")

                        _authState.postValue(AuthState(AuthResult.SUCCESS,"Seguidor"))
                    }

                }


            }



        }
    }
    fun saveRecipe(recipeId:String){

        viewModelScope.launch(Dispatchers.IO){

            Log.e(">>>",Firebase.auth.currentUser!!.uid)
            Log.e(">>>","Hasta aqui llega")
            Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).collection("savedRecipes").document(recipeId).set(SavedRecipe(recipeId)).addOnSuccessListener{
                _authState.postValue(AuthState(AuthResult.SUCCESS,"Guardado"))
                Log.e(">>>","Hizo la solicitud")
            }.await()



        }

    }

}