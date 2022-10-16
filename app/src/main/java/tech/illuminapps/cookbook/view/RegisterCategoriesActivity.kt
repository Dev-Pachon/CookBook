package tech.illuminapps.cookbook.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import com.google.android.material.chip.Chip
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ActivityRegisterBinding
import tech.illuminapps.cookbook.databinding.ActivityRegisterCategoriesBinding

class RegisterCategoriesActivity : AppCompatActivity() {

    val binding: ActivityRegisterCategoriesBinding by lazy {
        ActivityRegisterCategoriesBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.finishBtn.setOnClickListener {
            finish()
        }

        var chip: Chip = Chip(binding.root.context)
        chip.text = "Holiwi"
        binding.chipGroup.addView(chip)
        setContentView(binding.root)



    }
}