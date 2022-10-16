package tech.illuminapps.cookbook.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentMyProfileBinding


class MyProfileFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var loginFragment: LoginFragment


    val binding: FragmentMyProfileBinding by lazy {
        FragmentMyProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginFragment = LoginFragment.newInstance(mainActivity)
        binding.menuBtn.setOnClickListener {
            showMenu(binding.menuBtn, R.menu.profile_menu)
        }
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