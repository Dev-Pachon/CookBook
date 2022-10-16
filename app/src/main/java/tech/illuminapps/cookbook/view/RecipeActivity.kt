package tech.illuminapps.cookbook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.illuminapps.cookbook.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {

    val binding: ActivityRecipeBinding by lazy {
        ActivityRecipeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.startRecipeBtn.setOnClickListener {
            startActivity(Intent(binding.root.context, DetailedRecipeActivity::class.java))
        }
    }
}