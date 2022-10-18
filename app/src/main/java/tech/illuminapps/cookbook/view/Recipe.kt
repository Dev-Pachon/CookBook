package tech.illuminapps.cookbook.view

import android.graphics.drawable.Drawable

class Recipe(
    var title:String,
    var image:Drawable?,
    var ingredients: ArrayList<Ingredient>?,
    var steps: ArrayList<Step>?,
    var isOwner:Boolean = false
) {
}

data class Ingredient(
    var name: String,
    var quantity: Int
)

data class Step(
    var position:Int,
    var image: String?,
    var content:String
)