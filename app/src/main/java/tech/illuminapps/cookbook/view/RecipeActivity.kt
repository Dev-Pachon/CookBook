package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.illuminapps.cookbook.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {

    val binding: ActivityRecipeBinding by lazy {
        ActivityRecipeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.firstStar.background.level = 400
        setContentView(binding.root)
    }
}