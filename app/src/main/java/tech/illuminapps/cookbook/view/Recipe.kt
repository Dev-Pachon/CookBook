package tech.illuminapps.cookbook.view


class Recipe{
    var title:String=""
    var image:String=""
    var ingredients: ArrayList<Ingredient>? = arrayListOf()
    var steps: ArrayList<Step>? = arrayListOf()
    var isOwner:Boolean = false
    var ownerName: String = ""
    var ownerImage: String = ""
    var ownerId: String = ""


    constructor()
    constructor(
        title: String,
        image: String,
        ingredients: ArrayList<Ingredient>?,
        steps: ArrayList<Step>?,
        isOwner: Boolean,
        ownerName: String,
        ownerImage: String
    ) {
        this.title = title
        this.image = image
        this.ingredients = ingredients
        this.steps = steps
        this.isOwner = isOwner
        this.ownerName = ownerName
        this.ownerImage = ownerImage
    }
    constructor(title: String,image: String,isOwner: Boolean,ownerName: String,ownerImage: String,ownerId:String){
        this.title = title
        this.image = image
        this.ownerImage = ownerImage
        this.isOwner = isOwner
        this.ownerName = ownerName
        this.ownerId = ownerId
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