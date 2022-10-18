package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import tech.illuminapps.cookbook.databinding.FragmentNotificationBinding
import tech.illuminapps.cookbook.viewmodel.NotificationAdapter
import java.time.Instant
import java.util.Date

class NotificationFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    private lateinit var layoutMTodayNotification : LinearLayoutManager
    private lateinit var layoutMOldNotification : LinearLayoutManager

    private lateinit var adapterTodayNotification:NotificationAdapter
    private lateinit var adapterOldNotification:NotificationAdapter

    val binding: FragmentNotificationBinding by lazy {
        FragmentNotificationBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutMTodayNotification = LinearLayoutManager(binding.root.context)
        layoutMOldNotification = LinearLayoutManager(binding.root.context)

        binding.todayNotificationRV.layoutManager = layoutMTodayNotification
        binding.oldNotificationsRV.layoutManager = layoutMOldNotification

        adapterTodayNotification = NotificationAdapter()
        adapterOldNotification = NotificationAdapter()

        binding.todayNotificationRV.adapter = adapterTodayNotification
        binding.oldNotificationsRV.adapter = adapterOldNotification

        for (i in 1..3){
            val notification = Notification(Date.from(Instant.now()), "Carlos Jimmy azX nuevo contenido $i","Ajiaco CalezCX",false)
            adapterTodayNotification.addComment(notification)
        }
        for (i in 1..5){
            val notification = Notification(Date.from(Instant.now()), "Carlos Jimmy azadiz nuevo contenido","Ajiaco Calezxcz $i",true)
            adapterOldNotification.addComment(notification)
        }

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