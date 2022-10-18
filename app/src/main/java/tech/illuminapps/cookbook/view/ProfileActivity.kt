package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.illuminapps.cookbook.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}