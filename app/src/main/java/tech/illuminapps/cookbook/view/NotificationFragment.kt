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
    private lateinit var layoutMPreviousNotification : LinearLayoutManager

    private lateinit var adapterTodayNotification:NotificationAdapter
    private lateinit var adapterPreviousNotification:NotificationAdapter

    val binding: FragmentNotificationBinding by lazy {
        FragmentNotificationBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutMTodayNotification = LinearLayoutManager(binding.root.context)
        layoutMPreviousNotification = LinearLayoutManager(binding.root.context)

        binding.todayNotificationRV.layoutManager = layoutMTodayNotification
        binding.previousNotificationsRV.layoutManager = layoutMPreviousNotification

        adapterTodayNotification = NotificationAdapter()
        adapterPreviousNotification = NotificationAdapter()

        binding.todayNotificationRV.adapter = adapterTodayNotification
        binding.previousNotificationsRV.adapter = adapterPreviousNotification

        for (i in 1..3){
            val notification = Notification(Date.from(Instant.now()), "Carlos Jimmy publicó nuevo contenido $i","Ajiaco Caleño",false)
            adapterTodayNotification.addComment(notification)
        }
        for (i in 1..5){
            val notification = Notification(Date.from(Instant.now()), "Carlos Jimmy añañaí nuevo contenido","Ajiaco del Caribe $i",true)
            adapterPreviousNotification.addComment(notification)
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