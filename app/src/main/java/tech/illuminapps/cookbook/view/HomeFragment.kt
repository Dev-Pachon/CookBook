package tech.illuminapps.cookbook.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentHomeBinding
import tech.illuminapps.cookbook.viewmodel.ExtendedRecipeAdapter
import tech.illuminapps.cookbook.viewmodel.HomeFragmentViewModel
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
    private lateinit var adapterPopularRecipes: ExtendedRecipeAdapter
    private lateinit var adapterPopularProfile: PopularProfileAdapter
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel


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
        homeFragmentViewModel = HomeFragmentViewModel()
        adapterPopularRecipes = ExtendedRecipeAdapter()

        binding.tendencesRV.adapter = adapterPopularRecipes
        binding.popularProfilesRV.adapter = adapterPopularProfile
        binding.feedRV.adapter = adapterExtendedRecipe

        homeFragmentViewModel.getPopularProfiles()
        homeFragmentViewModel.getTrendingPost()
        homeFragmentViewModel.getFollowedCategoriesPost()
        homeFragmentViewModel.recipes.observe(viewLifecycleOwner){



                adapterExtendedRecipe.addRecipe(it)

           // Log.e(">>>",it.toString())

/*
        for (i in 0..it.size - 1) {

            var recipe2 = it[i] as Recipe

            //Log.e(">>>",it.get(i).toString())
           // Toast.makeText(this,it.get(i).toString(),LONGT)

          //  Toast.makeText(mainActivity,it.get(i).toString(),Toast.LENGTH_LONG).show()


        }

 */
        //Log.e(">>>", it.size.toString())






        }
        //val recipe = Recipe("img1.jps", "",false,"","","","")
        //adapterPopularRecipes.addRecipe(recipe)
        homeFragmentViewModel.recipesTrending.observe(viewLifecycleOwner){

            adapterPopularRecipes.addRecipe(it!!)

        }







        homeFragmentViewModel.popularProfile.observe(viewLifecycleOwner){

            adapterPopularProfile.addProfile(it!!)

        }
        /*
        for (i in 1..5){
            val profile = PopularProfile("$i","Name$i" ,"image$i")
            adapterPopularProfile.addProfile(profile)
        }


         */
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