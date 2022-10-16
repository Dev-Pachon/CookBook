package tech.illuminapps.cookbook.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.material.navigation.NavigationBarView
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentMainBinding
import tech.illuminapps.cookbook.viewmodel.UserViewModel

class MainFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    private var userViewModel: UserViewModel = UserViewModel()

    val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private lateinit var homeFragment: HomeFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragment = HomeFragment.newInstance(mainActivity)
        mainActivity.showFragment(homeFragment, binding.fragmentContainerView.id)

        binding.floatingActionButton.setOnClickListener{
            startActivity(Intent(binding.root.context, DetailedRecipeActivity::class.java))
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    mainActivity.showFragment(homeFragment, binding.fragmentContainerView.id)
                    true
                }
                R.id.page_2 -> {
                    mainActivity.showFragment(homeFragment, binding.fragmentContainerView.id)
                    true
                }
                R.id.page_3 -> {
                    startActivity(Intent(binding.root.context, RecipeActivity::class.java))
                    true
                }
                R.id.page_4 -> {
                    Toast.makeText(binding.root.context,"Aquí",Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(mainActivity: MainActivity) = MainFragment().apply {
            this.mainActivity = mainActivity
        }
    }
}