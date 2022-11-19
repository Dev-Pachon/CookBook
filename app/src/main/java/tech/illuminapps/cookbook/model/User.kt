package tech.illuminapps.cookbook.model

data class User(
   var id:String = "",
   var name: String = "",
   var email: String = "",
   var followedCategories: ArrayList<String> = arrayListOf()

)
