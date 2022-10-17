package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentSecondInitBinding
import tech.illuminapps.cookbook.databinding.FragmentThirdInitBinding
import tech.illuminapps.cookbook.viewmodel.UserViewModel


class ThirdInitFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var loginFragment: LoginFragment
    private val userViewModel: UserViewModel by viewModels()

    val binding: FragmentThirdInitBinding by lazy {
        FragmentThirdInitBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginFragment = LoginFragment.newInstance(mainActivity)

        binding.nextBtn.setOnClickListener {
            mainActivity.showFragment(loginFragment)
            userViewModel.turnOffWelcomeFlow(requireActivity())
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(mainActivity: MainActivity) =
            ThirdInitFragment().apply {
                this.mainActivity = mainActivity
            }
    }
}