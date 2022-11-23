package tech.illuminapps.cookbook.view

data class Comment (
    var id:String = "",
    var authorName:String = "",
    var image:String = "",
    var numLikes:Int = 0,
    var content:String = "",
    var authorId:String = ""
)