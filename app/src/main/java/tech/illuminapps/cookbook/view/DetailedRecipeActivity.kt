package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.illuminapps.cookbook.databinding.ActivityDetailedRecipeBinding

class DetailedRecipeActivity : AppCompatActivity() {

    val binding: ActivityDetailedRecipeBinding by lazy {
        ActivityDetailedRecipeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}