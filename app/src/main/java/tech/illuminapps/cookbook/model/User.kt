package tech.illuminapps.cookbook.model

import java.io.Serializable

class User:Serializable{
   var id:String = ""
   var name: String = ""
   var email: String = ""
   var followedCategories: ArrayList<String> = arrayListOf()
   var image:String = ""
   var description: String = ""
   var followerQuantity: String = ""
   var followingQuantity: String = ""
   var postQuantity: String = ""

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
      image: String,
      description: String
   ) {
      this.id = id
      this.name = name
      this.email = email
      this.followedCategories = followedCategories
      this.image = image
      this.description = description
   }

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


