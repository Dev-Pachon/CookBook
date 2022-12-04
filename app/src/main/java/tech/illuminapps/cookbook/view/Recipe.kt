package tech.illuminapps.cookbook.view

import java.io.Serializable


class Recipe:Serializable{
    var title:String=""
    var image:String=""
    var ingredients: ArrayList<Ingredient>? = arrayListOf()
    var steps: ArrayList<Step>? = arrayListOf()
    var isOwner:Boolean = false
    var ownerName: String = ""
    var ownerImage: String = ""
    var ownerId: String = ""
    var id: String = ""
    var grades: ArrayList<Int> = arrayListOf()
    var gradeAmount: String = ""
    var grade: Int = 0

    constructor()

    constructor(title: String,image: String,isOwner: Boolean,ownerName: String,ownerImage: String,ownerId:String,id: String){
        this.title = title
        this.image = image
        this.ownerImage = ownerImage
        this.isOwner = isOwner
        this.ownerName = ownerName
        this.ownerId = ownerId
        this.id = id
    }

    constructor(
        title: String,
        image: String,
        ingredients: ArrayList<Ingredient>?,
        steps: ArrayList<Step>?,
        isOwner: Boolean,
        ownerName: String,
        ownerImage: String,
        ownerId: String,
        id: String,
        grades: ArrayList<Int>,
        gradesAmount: String,
        grade: Int
    ) {
        this.title = title
        this.image = image
        this.ingredients = ingredients
        this.steps = steps
        this.isOwner = isOwner
        this.ownerName = ownerName
        this.ownerImage = ownerImage
        this.ownerId = ownerId
        this.id = id
        this.grades = grades
        this.gradeAmount = gradesAmount
        this.grade = grade
    }


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