package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.databinding.ViewHolderIngredientBinding
import tech.illuminapps.cookbook.databinding.ViewHolderRecipeExpandedBinding
import tech.illuminapps.cookbook.view.Ingredient
import tech.illuminapps.cookbook.view.Recipe
import tech.illuminapps.cookbook.view.ViewHolderExtendedRecipe
import tech.illuminapps.cookbook.view.ViewHolderIngredient

class IngredientAdapter : RecyclerView.Adapter<ViewHolderIngredient>() {
    private lateinit var context: Context

    private lateinit var binding: ViewHolderIngredientBinding

    private var ingredients: ArrayList<Ingredient> = ArrayList()

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
        notifyItemInserted(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderIngredient {
        context = parent.context

        binding = ViewHolderIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolderIngredient(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolderIngredient, position: Int) {
        binding.nameTV.text = ingredients[position].name
        binding.quantityTV.text = ingredients[position].quantity.toString()
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}