package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.illuminapps.cookbook.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    val binding: ActivityEditProfileBinding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        for (x in 1..20) {
            val chip: Chip = Chip(binding.root.context)
            chip.text = "Categoría $x"
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