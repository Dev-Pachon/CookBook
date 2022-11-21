package tech.illuminapps.cookbook.model

class User{
   var id:String = ""
   var name: String = ""
   var email: String = ""
   var followedCategories: ArrayList<String> = arrayListOf()
   var image:String = ""

   constructor(id: String, name: String, email: String, followedCategories: ArrayList<String>) {
      this.id = id
      this.name = name
      this.email = email
      this.followedCategories = followedCategories
   }

   constructor()

   constructor(
      id: String,
      name: String,
      email: String,
      followedCategories: ArrayList<String>,
      image: String
   ) {
      this.id = id
      this.name = name
      this.email = email
      this.followedCategories = followedCategories
      this.image = image
   }


}


