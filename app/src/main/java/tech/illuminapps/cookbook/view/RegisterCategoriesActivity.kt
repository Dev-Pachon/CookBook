package tech.illuminapps.cookbook.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        for (x in 1..20){
            val chip = Chip(binding.root.context)
            chip.text = "Holiwi"
            binding.chipGroup.addView(chip)
            setContentView(binding.root)
        }
    }

    override fun onBackPressed(){
        MaterialAlertDialogBuilder(binding.root.context)
            .setTitle("Descartar?")
            .setMessage("Está seguro de que quiere descartar los cambios?\nPerderá todo el progreso realizado.")
            .setNeutralButton("Cancelar") { _, _ -> }
            .setPositiveButton("Descartar") { _, _ ->
                super.onBackPressed()
            }
            .show()
    }
}