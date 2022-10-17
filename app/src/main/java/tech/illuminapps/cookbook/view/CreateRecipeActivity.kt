package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.illuminapps.cookbook.databinding.ActivityCreateRecipeBinding

class CreateRecipeActivity : AppCompatActivity() {

    val binding: ActivityCreateRecipeBinding by lazy {
        ActivityCreateRecipeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        for (x in 1..20){
            var chip: Chip = Chip(binding.root.context)
            chip.text = "Holiwi"
            binding.chipGroup.addView(chip)
            setContentView(binding.root)
        }

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed(){
        MaterialAlertDialogBuilder(binding.root.context)
            .setTitle("Descartar?")
            .setMessage("Está seguro de que quiere descartar los cambios?\nPerderá todo el progreso realizado.")
            .setNeutralButton("Cancelar") { _, _ -> }
            .setNegativeButton("Guardar receta") { _, _ -> }
            .setPositiveButton("Descartar") { _, _ ->
                super.onBackPressed()
            }
            .show()
    }
}