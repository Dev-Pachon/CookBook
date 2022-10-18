package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentHomeBinding
import tech.illuminapps.cookbook.viewmodel.ExtendedRecipeAdapter
import tech.illuminapps.cookbook.viewmodel.PopularProfileAdapter

class HomeFragment : Fragment() {
    private lateinit var mainActivity:MainActivity

    val binding:FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMExtended : LinearLayoutManager
    private lateinit var layoutMFeed : LinearLayoutManager
    private lateinit var layoutMPopularProfiles : LinearLayoutManager

    private lateinit var adapterExtendedRecipe :ExtendedRecipeAdapter
    private lateinit var adapterPopularProfile: PopularProfileAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutMExtended = LinearLayoutManager(binding.root.context)

        layoutMExtended.orientation = LinearLayoutManager.HORIZONTAL

        layoutMPopularProfiles = LinearLayoutManager(binding.root.context)

        layoutMPopularProfiles.orientation = LinearLayoutManager.HORIZONTAL

        layoutMFeed = LinearLayoutManager(binding.root.context)

        binding.tendencesRV.layoutManager = layoutMExtended

        binding.feedRV.layoutManager = layoutMFeed

        binding.popularProfilesRV.layoutManager = layoutMPopularProfiles

        binding.tendencesRV.setHasFixedSize(true)
        binding.feedRV.setHasFixedSize(true)
        binding.popularProfilesRV.setHasFixedSize(true)

        adapterExtendedRecipe = ExtendedRecipeAdapter()
        adapterPopularProfile = PopularProfileAdapter()

        binding.tendencesRV.adapter = adapterExtendedRecipe
        binding.popularProfilesRV.adapter = adapterPopularProfile
        binding.feedRV.adapter = adapterExtendedRecipe

        for (i in 1..10){
            val recipe = Recipe("img1.jps", ContextCompat.getDrawable(binding.root.context, R.drawable.cartoon_gc6b1d9dec_1280_1),null, null)
            adapterExtendedRecipe.addRecipe(recipe)
        }

        for (i in 1..5){
            val profile = PopularProfile("$i","Name$i" ,"image$i")
            adapterPopularProfile.addProfile(profile)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance( mainActivity: MainActivity) =
            HomeFragment().apply {
                this.mainActivity = mainActivity
            }
    }
}