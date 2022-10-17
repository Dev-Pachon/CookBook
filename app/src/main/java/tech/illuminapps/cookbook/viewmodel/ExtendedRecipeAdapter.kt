package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ViewHolderRecipeExpandedBinding
import tech.illuminapps.cookbook.view.Recipe
import tech.illuminapps.cookbook.view.ViewHolderExtendedRecipe

class ExtendedRecipeAdapter : RecyclerView.Adapter<ViewHolderExtendedRecipe>() {
    private lateinit var context: Context

    lateinit var binding: ViewHolderRecipeExpandedBinding

    private val recipes: ArrayList<Recipe> = ArrayList()

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
        notifyItemInserted(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExtendedRecipe {
        context = parent.context

        binding = ViewHolderRecipeExpandedBinding.inflate(LayoutInflater.from(parent.context))

        handleSave()

        return ViewHolderExtendedRecipe(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolderExtendedRecipe, position: Int) {
        binding.saveRecipeBtn.setOnClickListener {
            handleSave()
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun handleSave() {
        if (false) {
            binding.saveRecipeBtn.setBackgroundColor(
                ContextCompat.getColor(
                    context, R.color.red_700
                )
            )
            binding.saveRecipeBtn.setTextColor(
                ContextCompat.getColor(
                    context, R.color.white
                )
            )
        } else {
            binding.saveRecipeBtn.setBackgroundColor(
                ContextCompat.getColor(
                    context, R.color.transparent
                )
            )
            binding.saveRecipeBtn.setTextColor(
                ContextCompat.getColor(
                    context, R.color.red_700
                )
            )
        }
    }
}