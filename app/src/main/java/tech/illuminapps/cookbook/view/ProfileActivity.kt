package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ActivityProfileBinding
import tech.illuminapps.cookbook.viewmodel.ExtendedRecipeAdapter
import tech.illuminapps.cookbook.viewmodel.ProfileViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileViewModel: ProfileViewModel

    val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMSaved : LinearLayoutManager

    private lateinit var adapter :ExtendedRecipeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var userId = intent.getSerializableExtra("userId") as String

        layoutMSaved = LinearLayoutManager(binding.root.context)

        binding.recipesRV.layoutManager = layoutMSaved

        binding.recipesRV.setHasFixedSize(true)

        adapter = ExtendedRecipeAdapter()

        binding.recipesRV.adapter = adapter

        profileViewModel = ProfileViewModel()

        binding.followBtn.text = "Seguir"

        Firebase.storage.reference.child("users/${userId}").downloadUrl.addOnSuccessListener{
            Glide.with(this).load(it!!).into(binding.userPfp)

        }

        //val recipe = Recipe("img1.jps", " ",true,"","","","")
        //adapter.addRecipe(recipe)

       profileViewModel.getUserInfo(userId)

        profileViewModel.user.observe(this){

            binding.nameTV.text = it.name
            binding.descriptionTV.text = it.description
            binding.numFollowersTV.text = it.followerQuantity.toString()
            binding.numFollowingTV.text = it.followingQuantity.toString()
            binding.numRecipesTV.text = it.postQuantity
        }


        profileViewModel.recipes.observe(this){

            adapter.addRecipe(it)
           // Log.e(">>>",it.ownerName)

        }
        binding.followBtn.setOnClickListener {

            profileViewModel.addFollower(userId)

        }


        profileViewModel.authState.observe(this){

            binding.followBtn.text = "Siguiendo"
        }






/*
        for (i in 1..5){
            val recipe = Recipe("img1.jps", " ",true,"","","","")

            adapter.addRecipe(recipe)
        }


 */
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}