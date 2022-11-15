package tech.illuminapps.cookbook.model

data class Post(

    var id:String,
    var name:String,
    var categories: ArrayList<String>,
    var userId:String,
    var mainImage: String
)
