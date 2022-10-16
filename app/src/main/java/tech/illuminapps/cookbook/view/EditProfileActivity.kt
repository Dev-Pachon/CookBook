package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.illuminapps.cookbook.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    val binding: ActivityEditProfileBinding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}