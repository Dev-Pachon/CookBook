package tech.illuminapps.cookbook.model

data class Post(

    var id:String = "",
    var name:String = "",
    var categories: ArrayList<String> = arrayListOf(),
    var userId:String = "",
    var mainImage: String = "",
    //var grade: String = "0"
)
