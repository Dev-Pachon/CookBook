package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.databinding.ViewHolderPopularProfilesBinding
import tech.illuminapps.cookbook.databinding.ViewHolderRecipeExpandedBinding
import tech.illuminapps.cookbook.view.*

class PopularProfileAdapter : RecyclerView.Adapter<ViewHolderPopularProfile>() {

    private val profiles: ArrayList<PopularProfile> = ArrayList()

    private lateinit var context: Context
    private lateinit var binding: ViewHolderPopularProfilesBinding

    fun addProfile(profile: PopularProfile) {
        profiles.add(profile)
        notifyItemInserted(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPopularProfile {
        context = parent.context

        binding = ViewHolderPopularProfilesBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolderPopularProfile(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolderPopularProfile, position: Int) {
        binding.nameProfileTV.text = profiles[position].name

        binding.root.setOnClickListener {
            ContextCompat.startActivity(
                binding.root.context,
                Intent(binding.root.context, ProfileActivity::class.java),
                null
            )
        }
    }

    override fun getItemCount(): Int {
        return profiles.size
    }
}