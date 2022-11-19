package tech.illuminapps.cookbook.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentMyProfileBinding
import tech.illuminapps.cookbook.viewmodel.ExtendedRecipeAdapter
import tech.illuminapps.cookbook.viewmodel.UserViewModel


class MyProfileFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var loginFragment: LoginFragment
    private val userViewModel: UserViewModel by viewModels()


    val binding: FragmentMyProfileBinding by lazy {
        FragmentMyProfileBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMSaved : LinearLayoutManager

    private lateinit var adapter :ExtendedRecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginFragment = LoginFragment.newInstance(mainActivity)
        binding.menuBtn.setOnClickListener {
            showMenu(binding.menuBtn, R.menu.profile_menu)
        }


        layoutMSaved = LinearLayoutManager(binding.root.context)

        binding.myRecipesRV.layoutManager = layoutMSaved

        binding.myRecipesRV.setHasFixedSize(true)

        adapter = ExtendedRecipeAdapter()


        binding.myRecipesRV.adapter = adapter
        /*
        for (i in 1..5){
            val recipe = Recipe("img1.jps","imagen ", null,null,null,true)

            adapter.addRecipe(recipe)
        }

         */

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(mainActivity: MainActivity) =
            MyProfileFragment().apply {
                this.mainActivity = mainActivity
            }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(binding.root.context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.logout_option -> {
                    userViewModel.logOut(requireActivity())
                    mainActivity.showFragment(loginFragment)
                    true
                }
                R.id.edit_profile_option -> {
                    startActivity(Intent(binding.root.context, EditProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}