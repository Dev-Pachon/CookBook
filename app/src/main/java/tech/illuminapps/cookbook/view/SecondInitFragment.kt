package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentSecondInitBinding


class SecondInitFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var mainActivity: MainActivity
    private lateinit var thirdInitFragment: ThirdInitFragment
    val binding: FragmentSecondInitBinding by lazy {
        FragmentSecondInitBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        thirdInitFragment = ThirdInitFragment.newInstance(mainActivity)

        binding.nextBtn.setOnClickListener {
            mainActivity.showFragment(thirdInitFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(mainActivity: MainActivity) =
            SecondInitFragment().apply {
                this.mainActivity = mainActivity
            }
    }
}