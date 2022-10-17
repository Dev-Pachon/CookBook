package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.databinding.ViewHolderPopularProfilesBinding
import tech.illuminapps.cookbook.databinding.ViewHolderRecipeExpandedBinding
import tech.illuminapps.cookbook.view.PopularProfile
import tech.illuminapps.cookbook.view.Recipe
import tech.illuminapps.cookbook.view.ViewHolderExtendedRecipe

class PopularProfileAdapter : RecyclerView.Adapter<ViewHolderExtendedRecipe>() {

    private val profiles: ArrayList<PopularProfile> = ArrayList()

    private lateinit var context: Context
    private lateinit var binding: ViewHolderPopularProfilesBinding

    fun addRecipe(profile: PopularProfile) {
        profiles.add(profile)
        notifyItemInserted(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExtendedRecipe {
        context = parent.context

        binding = ViewHolderPopularProfilesBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolderExtendedRecipe(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolderExtendedRecipe, position: Int) {
        binding.nameProfileTV.text = profiles[position].name
    }

    override fun getItemCount(): Int {
        return profiles.size
    }
}