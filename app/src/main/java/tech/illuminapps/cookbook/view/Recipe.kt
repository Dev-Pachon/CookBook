package tech.illuminapps.cookbook.view

import android.graphics.drawable.Drawable

class Recipe(
    var title:String="",
    var image:String="",
    var ingredients: ArrayList<Ingredient>? = arrayListOf(),
    var steps: ArrayList<Step>? = arrayListOf(),
    var isOwner:Boolean = false
) {
}

data class Ingredient(
    var id:String = "",
    var nombre: String = "",
    var quantity: Int = 0,
    //var imagen:String
)

data class Step(
    var position:Int = 0,
    var image: String? = "",
    var content:String = "",
    var id:String = ""
)