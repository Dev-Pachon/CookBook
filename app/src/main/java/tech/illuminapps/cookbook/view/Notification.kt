package tech.illuminapps.cookbook.view

import java.util.Date

class Notification(
    var date: Date,
    var title:String,
    var content:String,
    var seen: Boolean
) {
}