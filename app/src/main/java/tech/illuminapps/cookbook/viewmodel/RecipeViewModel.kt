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
import tech.illuminapps.cookbook.model.User

class RecipeViewModel: ViewModel() {

    private val _user = MutableLiveData(User())
    val user: LiveData<User> get() = _user

    fun getUserData(){

        viewModelScope.launch(Dispatchers.IO) {


            val result = Firebase.firestore.collection("users")
                .document(Firebase.auth.currentUser!!.uid).get().await()

            val currentUser = result.toObject(tech.illuminapps.cookbook.model.User::class.java)
            _user.postValue(currentUser)

        }
    }

}