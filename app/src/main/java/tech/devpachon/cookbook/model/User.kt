package tech.devpachon.cookbook.model

data class User(
   var name: String = "",
   var email: String = "",
   var followedCategories: ArrayList<String>
)