package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
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
        binding.nameTV.text = ingredients[position].nombre
        binding.quantityTV.text = ingredients[position].quantity.toString()
      //  binding.ingredientView.isGone
      //  binding.ingredientCV.isGone = true

        Firebase.storage.reference.child("ingredients/${ingredients[position].nombre}").downloadUrl.addOnSuccessListener {
            Glide.with(context).load(it!!).into(binding.ingredientView)

        }
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    fun returnIngredients(): ArrayList<Ingredient>{
        return ingredients
    }

}