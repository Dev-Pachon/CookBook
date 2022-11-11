package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import tech.illuminapps.cookbook.databinding.ActivityCreateRecipeBinding
import tech.illuminapps.cookbook.viewmodel.CreateRecipeViewModel
import tech.illuminapps.cookbook.viewmodel.IngredientAdapter
import tech.illuminapps.cookbook.viewmodel.StepAdapter

class CreateRecipeActivity : AppCompatActivity() {

    val binding: ActivityCreateRecipeBinding by lazy {
        ActivityCreateRecipeBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMIngredients : LinearLayoutManager
    private lateinit var layoutMSteps:LinearLayoutManager
    private lateinit var adapterIngredient : IngredientAdapter
    private lateinit var adapterStep:StepAdapter
    private var stepCounter = 1
    private val createRecipeViewModel : CreateRecipeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       // var ingredients = createRecipeViewModel.getIngredientslist()

        layoutMIngredients = LinearLayoutManager(binding.root.context)
        layoutMSteps = LinearLayoutManager(binding.root.context)
        adapterIngredient = IngredientAdapter()
        adapterStep = StepAdapter()
        binding.ingredientsRV.layoutManager = layoutMIngredients
        binding.recipeStepsRV.layoutManager = layoutMSteps
        binding.ingredientsRV.adapter = adapterIngredient
        binding.recipeStepsRV.adapter = adapterStep

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        binding.showAddIngredientGroup.setOnClickListener {

                binding.ingredientsAddGroup.isGone = !binding.ingredientsAddGroup.isGone
                val items = arrayOf("Queso", "Jamon", "Carne de Cerdo", "Carne de Res","Aceite de soja"
                ,"Yogur","Mantequilla","Lentejas","Setas","Papa","Berenjena","Brocoli","Calabacin"
                ,"Cebolla","Tomate")
                (binding.menu.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)


        }

        binding.addIngredientBtn.setOnClickListener {
            val ingredient = Ingredient("1",binding.ingredientsDrodownMenu.text.toString(),Integer.parseInt(binding.quantityIL.editText?.text.toString()),"")
            adapterIngredient.addIngredient(ingredient)
           // Toast.makeText(binding.root.context,"äs;lkda;sd",Toast.LENGTH_LONG).show()
           // binding.ingredientsAddGroup.isGone = !binding.ingredientsAddGroup.isGone
           // binding.ingredientsDrodownMenu.text.clear()
           // binding.quantityIL.editText?.text?.clear()
        }

        binding.addStepBtn.setOnClickListener {
            val step = Step(stepCounter, null, "")
            adapterStep.addStep(step)
            stepCounter++
        }
        var categories = arrayListOf("Desayuno","Italiana","Pasta","Francesa","Española","Mexicana","Argentina","Comida Rapida","Hamburguesa","Tipica")

        for (categorie in 0..categories.size-1) {


                val chip = Chip(binding.root.context)
                chip.text = categories.get(categorie)
                binding.chipGroup.addView(chip)
                setContentView(binding.root)
                chip.id = categorie


        }
        binding.finishBtn.setOnClickListener{

            var name = binding.editTextRecipeName.text.toString()
            createRecipeViewModel.addPost(adapterIngredient.returnIngredients(),adapterStep.getSteps(),name)



        }

    }

    override fun onBackPressed() {
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