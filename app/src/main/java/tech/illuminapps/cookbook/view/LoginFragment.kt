package tech.illuminapps.cookbook.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentLoginBinding
import tech.illuminapps.cookbook.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private val binding: FragmentLoginBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    private lateinit var mainFragment: MainFragment
    private lateinit var mainActivity:MainActivity

    private val userViewModel: UserViewModel by viewModels()

    companion object {
        fun newInstance(mainActivity: MainActivity) = LoginFragment().apply {
            this.mainActivity = mainActivity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragment = MainFragment.newInstance(mainActivity)

        binding.loginBtn.setOnClickListener {
            userViewModel.logIn(requireActivity())
            mainActivity.showFragment(mainFragment)
        }

        binding.registerBtn.setOnClickListener{
            startActivity(Intent(binding.root.context, RegisterActivity::class.java))
        }

        return binding.root
    }

}