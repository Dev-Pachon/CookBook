package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
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




        //val recipe = Recipe("img1.jps", " ",true,"","","","")
        //adapter.addRecipe(recipe)

       profileViewModel.getUserInfo(userId)

        profileViewModel.user.observe(this){

            binding.nameTV.text = it.name
            binding.descriptionTV.text = it.description
            binding.numFollowersTV.text = it.followerQuantity
            binding.numFollowingTV.text = it.followingQuantity
            binding.numRecipesTV.text = it.postQuantity
        }


        profileViewModel.recipes.observe(this){

            adapter.addRecipe(it)
            Log.e(">>>",it.ownerName)

        }
        /*
        profileViewModel.authState.observe(this){

            adapter.deleteFirst()
        }


         */



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