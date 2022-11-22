package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ViewHolderNotificationBinding
import tech.illuminapps.cookbook.databinding.ViewHolderRecipeExpandedBinding
import tech.illuminapps.cookbook.view.ProfileActivity
import tech.illuminapps.cookbook.view.Recipe
import tech.illuminapps.cookbook.view.RecipeActivity
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

        binding = ViewHolderRecipeExpandedBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolderExtendedRecipe(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolderExtendedRecipe, position: Int) {


        binding.textView20.text = recipes[position].title
        binding.author.text = recipes[position].ownerName


        if(recipes[position].isOwner){
            binding.authorGroup.isGone = !binding.authorGroup.isGone
        }

        binding.saveRecipeBtn.setOnClickListener {
            Toast.makeText(binding.root.context,"Receta guardada", Toast.LENGTH_LONG).show()
        }
        binding.RecipeCV.setOnClickListener {
            ContextCompat.startActivity(
                binding.root.context,
                Intent(binding.root.context, RecipeActivity::class.java),
                null
            )
        }

        binding.authorGroup.setOnClickListener{
            ContextCompat.startActivity(
                binding.root.context,
                Intent(binding.root.context, ProfileActivity::class.java),
                null
            )
        }

    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun deleteFirst(){
        recipes.removeAt(0)

        notifyItemRemoved(0);
    }
}