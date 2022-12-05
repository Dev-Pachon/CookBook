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

    private val _post = MutableLiveData(Post())
    val post: LiveData<Post> get() = _post


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


                    viewModelScope.launch(Dispatchers.IO) {
                        val result2 =
                            Firebase.firestore.collection("users").document(currentUser).get().await()

                        val cU = result2.toObject(User::class.java)
                        cU.let {
                            it!!.followingQuantity = it!!.followingQuantity+1
                        }

                    }



                    Firebase.firestore.collection("users").document(postOwner).collection("followers").document(currentUser).set(Follower(currentUser)).addOnSuccessListener(){

                       // Log.e(">>>","Debio crearse")
                        viewModelScope.launch(Dispatchers.IO) {
                            val result2 =
                                Firebase.firestore.collection("users").document(postOwner).get().await()

                            val cU = result2.toObject(User::class.java)
                            cU.let {
                                it!!.followerQuantity = it!!.followerQuantity+1
                            }

                        }

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
    fun addGrade(recipeId: String,grade:Int){

        viewModelScope.launch(Dispatchers.IO){

            val result = Firebase.firestore.collection("posts").document(recipeId).get().await()

            var post = result.toObject(Post::class.java)

            post.let {
                it!!.grades.add(grade)
                var average: Int = 0
                for(grade in it!!.grades){

                    average+= grade


                }
                average = average/it!!.grades.size
                it.gradeAmount = it.gradeAmount+1
                it.grade = average
            Firebase.firestore.collection("posts").document(recipeId).update("grades",it!!.grades,"gradeAmount",it!!.gradeAmount,"grade",average).await()

                _post.postValue(it!!)
                //post.gr



            }




        }


    }

}