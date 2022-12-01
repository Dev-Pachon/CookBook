package tech.illuminapps.cookbook.viewmodel

import android.net.Uri
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

class EditProfileViewModel:ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE,""))
    val authState : LiveData<AuthState> get() = _authState

    fun updateUserData(name:String,description:String,followedCategories: ArrayList<String>,pfp: Uri){

        viewModelScope.launch(Dispatchers.IO) {

            if(pfp !=null) {
                Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).update("name",name)
               // Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).update("followedCategories",followedCategories)


              //  Firebase.storage.reference.child("users").child(Firebase.auth.currentUser!!.uid)
                //    .child(pfp.toString()).putFile(pfp)


            }


        }
    }


}