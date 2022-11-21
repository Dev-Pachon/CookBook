package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ActivityProfileBinding
import tech.illuminapps.cookbook.viewmodel.ExtendedRecipeAdapter

class ProfileActivity : AppCompatActivity() {

    val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMSaved : LinearLayoutManager

    private lateinit var adapter :ExtendedRecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        layoutMSaved = LinearLayoutManager(binding.root.context)

        binding.recipesRV.layoutManager = layoutMSaved

        binding.recipesRV.setHasFixedSize(true)

        adapter = ExtendedRecipeAdapter()


        binding.recipesRV.adapter = adapter

        for (i in 1..5){
            val recipe = Recipe("img1.jps", " ",true,"","","")

            adapter.addRecipe(recipe)
        }

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}