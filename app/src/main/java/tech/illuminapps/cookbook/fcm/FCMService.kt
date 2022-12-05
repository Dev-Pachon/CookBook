package tech.illuminapps.cookbook.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import org.json.JSONObject
import tech.illuminapps.cookbook.util.NotificationUtil

class FCMService : FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
        val obj = JSONObject(message.data as Map<*, *>)
        val json = obj.toString()
     //   val message = Gson().fromJson(json, Message::class.java)
        NotificationUtil.showNotification(this, "Receta nueva", "Descrubrela!")
    }
}