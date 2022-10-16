package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    val binding: FragmentNotificationBinding by lazy {
        FragmentNotificationBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(mainActivity: MainActivity) =
            NotificationFragment().apply {
                this.mainActivity = mainActivity
            }
    }
}