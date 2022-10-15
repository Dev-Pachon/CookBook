package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.illuminapps.cookbook.databinding.FragmentFirstInitBinding


class FirstInitFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    lateinit var secondInitFragment: SecondInitFragment


    val binding: FragmentFirstInitBinding by lazy {
        FragmentFirstInitBinding.inflate(layoutInflater)
    }

    companion object {
        fun newInstance(mainActivity: MainActivity) = FirstInitFragment().apply {
            this.mainActivity = mainActivity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        secondInitFragment = SecondInitFragment.newInstance(mainActivity)

        binding.nextBtn.setOnClickListener {
            mainActivity.showFragment(secondInitFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}