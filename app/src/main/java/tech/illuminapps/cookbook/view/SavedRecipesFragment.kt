package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentSavedRecipesBinding
import tech.illuminapps.cookbook.viewmodel.ExtendedRecipeAdapter


class SavedRecipesFragment : Fragment() {
    private lateinit var mainActivity: MainActivity

    val binding: FragmentSavedRecipesBinding by lazy {
        FragmentSavedRecipesBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMSaved : LinearLayoutManager

    private lateinit var adapter :ExtendedRecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        layoutMSaved = LinearLayoutManager(binding.root.context)

        binding.savedRecipesRV.layoutManager = layoutMSaved

        binding.savedRecipesRV.setHasFixedSize(true)

        adapter = ExtendedRecipeAdapter()


        binding.savedRecipesRV.adapter = adapter

        for (i in 1..10){
            val recipe = Recipe("img1.jps", ContextCompat.getDrawable(binding.root.context, R.drawable.cartoon_gc6b1d9dec_1280_1),null,null)

            adapter.addRecipe(recipe)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(mainActivity: MainActivity) =
            SavedRecipesFragment().apply {
                this.mainActivity = mainActivity
            }
    }
}