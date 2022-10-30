package tech.illuminapps.cookbook.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ActivityRegisterBinding
import tech.illuminapps.cookbook.databinding.ActivityRegisterCategoriesBinding
import tech.illuminapps.cookbook.viewmodel.AuthResult
import tech.illuminapps.cookbook.viewmodel.RegisterCategoriesViewModel
import tech.illuminapps.cookbook.viewmodel.RegisterViewModel

class RegisterCategoriesActivity : AppCompatActivity() {

    val binding: ActivityRegisterCategoriesBinding by lazy {
        ActivityRegisterCategoriesBinding.inflate(layoutInflater)
    }
    private val registerCategoriesViewModel: RegisterCategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var categories = arrayListOf("Desayuno","Italiana","Pasta","Francesa","Española","Mexicana","Argentina","Comida Rapida","Hamburguesa","Tipica")
        //var categoriesSize = categories.size

        for (categorie in 0..categories.size){
            val chip = Chip(binding.root.context)
            chip.text = categories.get(categorie)
            binding.chipGroup.addView(chip)
            setContentView(binding.root)
            chip.id = categorie
        }

        binding.finishBtn.setOnClickListener {

            var selectedCategories = arrayListOf<String>()

            for(position in 0..categories.size){


                val chip = binding.chipGroup.findViewById<Chip>(position)
                if(chip.isChecked){

                    selectedCategories.add(chip.text.toString())


                }


            }

            registerCategoriesViewModel.registerCategories(selectedCategories)


            registerCategoriesViewModel.authState.observe(this){


                when(it.result){

                    AuthResult.SUCCESS->{

                        Log.e(">>>","Deberia cambiar de pantalla")

                        // startActivity(Intent(binding.root.context, RegisterCategoriesActivity::class.java))
                    }
                    AuthResult.IDLE->{




                    }
                    AuthResult.FAIL->{

                        //Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()


                    }
                }



            }



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