package tech.illuminapps.cookbook.model

data class Post(

    var id:String = "",
    var name:String = "",
    var categories: ArrayList<String> = arrayListOf(),
    var userId:String = "",
    var mainImage: String = "",
    var grades: ArrayList<Int> = arrayListOf(),
    var gradeAmount: Int = 0,
    var grade: Int = 0
)
