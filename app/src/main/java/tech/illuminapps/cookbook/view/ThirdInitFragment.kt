package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentSecondInitBinding
import tech.illuminapps.cookbook.databinding.FragmentThirdInitBinding


class ThirdInitFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var mainActivity: MainActivity
    private lateinit var loginFragment: LoginFragment
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