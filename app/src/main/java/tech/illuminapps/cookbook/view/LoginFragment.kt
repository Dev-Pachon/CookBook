package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentLoginBinding
import tech.illuminapps.cookbook.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    val binding: FragmentLoginBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    companion object {
        fun newInstance(mainActivity: MainActivity) = LoginFragment()
    }

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return binding.root
    }

}