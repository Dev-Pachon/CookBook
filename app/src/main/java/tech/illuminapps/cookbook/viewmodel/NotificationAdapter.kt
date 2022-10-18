package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.databinding.ViewHolderNotificationBinding
import tech.illuminapps.cookbook.databinding.ViewHolderRecipeExpandedBinding
import tech.illuminapps.cookbook.view.Notification
import tech.illuminapps.cookbook.view.ViewHolderExtendedRecipe
import tech.illuminapps.cookbook.view.ViewHolderNotification

class NotificationAdapter : RecyclerView.Adapter<ViewHolderNotification>() {

    private val notifications: ArrayList<Notification> = ArrayList()

    private lateinit var context: Context
    private lateinit var binding: ViewHolderNotificationBinding

    fun addComment(notification: Notification) {
        notifications.add(notification)
        notifyItemInserted(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNotification {
        context = parent.context

        binding = ViewHolderNotificationBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolderNotification(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolderNotification, position: Int) {
        binding.titleTV.text = notifications[position].title
        binding.contentTV.text = notifications[position].content
        if (notifications[position].seen)
            binding.seenStateTV.visibility = View.GONE
        else
            binding.seenStateTV.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return notifications.size
    }
}