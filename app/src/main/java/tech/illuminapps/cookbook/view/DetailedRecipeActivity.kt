package tech.illuminapps.cookbook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import tech.illuminapps.cookbook.databinding.ActivityDetailedRecipeBinding
import tech.illuminapps.cookbook.viewmodel.IngredientAdapter
import tech.illuminapps.cookbook.viewmodel.StepAdapter
import tech.illuminapps.cookbook.viewmodel.detailedRecipeViewModel

class DetailedRecipeActivity : AppCompatActivity() {

    val binding: ActivityDetailedRecipeBinding by lazy {
        ActivityDetailedRecipeBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMIngredients : LinearLayoutManager
    private lateinit var layoutMSteps: LinearLayoutManager
    private lateinit var adapterIngredient : IngredientAdapter
    private lateinit var adapterStep: StepAdapter
    private lateinit var detailedRecipeViewModel: detailedRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        layoutMIngredients = LinearLayoutManager(binding.root.context)
        layoutMSteps = LinearLayoutManager(binding.root.context)
        adapterIngredient = IngredientAdapter()
        adapterStep = StepAdapter()
        binding.ingredientsRV.layoutManager = layoutMIngredients
        binding.recipeStepsRV.layoutManager = layoutMSteps
        binding.ingredientsRV.adapter = adapterIngredient
        binding.recipeStepsRV.adapter = adapterStep
        var recipe = intent.extras?.getSerializable("recipe") as? Recipe

        detailedRecipeViewModel = detailedRecipeViewModel()
        binding.recipeNameTV.text = recipe!!.title
        detailedRecipeViewModel.getRecipeInfo(recipe!!.id)

        detailedRecipeViewModel.step.observe(this){

            adapterStep.addStep(it!!)

        }
        detailedRecipeViewModel.ingredient.observe(this){

            adapterIngredient.addIngredient(it!!)

        }
        Firebase.storage.reference.child("posts/${recipe.id}/${recipe.image}").downloadUrl.addOnSuccessListener {
            Glide.with(this).load(it!!).into(binding.imageView5)

        }

    }
}