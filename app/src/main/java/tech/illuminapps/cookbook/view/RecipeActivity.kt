package tech.illuminapps.cookbook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ActivityRecipeBinding
import tech.illuminapps.cookbook.model.User
import tech.illuminapps.cookbook.viewmodel.ExtendedRecipeAdapter
import tech.illuminapps.cookbook.viewmodel.RecipeCommentAdapter
import tech.illuminapps.cookbook.viewmodel.RecipeViewModel
import java.util.*

class RecipeActivity : AppCompatActivity() {

    val binding: ActivityRecipeBinding by lazy {
        ActivityRecipeBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMComments : LinearLayoutManager

    private lateinit var adapterComments : RecipeCommentAdapter

    private lateinit var recipeViewModel: RecipeViewModel

    private lateinit var recipeGlobal: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        layoutMComments = LinearLayoutManager(binding.root.context)
        binding.commentsRV.layoutManager = layoutMComments
        adapterComments = RecipeCommentAdapter()
        recipeViewModel = RecipeViewModel()
        binding.commentsRV.adapter = adapterComments

        val recipe = intent.extras?.getSerializable("recipe") as? Recipe
        recipeGlobal = recipe!!
        var gradeDone = false

        binding.nameRecipeTV.text = recipe!!.title
        binding.authorNameTV.text = recipe!!.ownerName
        binding.follow.text = "Seguir"
        binding.scoreTV.text = recipe!!.grade.toString()
        binding.numReviewsTV.text = "(${recipe!!.gradeAmount}) Reseñas"

        Log.e(">>>",recipe!!.ownerName)
        recipeViewModel.getUserData()
        recipeViewModel.getComments(recipe.id)

        var currentUser: User = User()
        recipeViewModel.user.observe(this){
            binding.commentName.text = it.name
            currentUser = it
        }
        recipeViewModel.comment.observe(this){

            adapterComments.addComment(it)
        }
        recipeViewModel.authState.observe(this){


            when(it.message){

                "Seguir" ->     binding.follow.text = "Siguiendo"
                "Guardar" -> Toast.makeText(this,"Se ha guardado correctamente",Toast.LENGTH_LONG)

            }

        }
        recipeViewModel.post.observe(this){

            binding.scoreTV.text = it!!.grade.toString()
            binding.numReviewsTV.text = "(${it!!.gradeAmount}) Reseñas"

        }

        /*
        for (i in 1..10){
            val comment = Comment("img1.jpg", "Carlos Jimmy","",10,"askldhlkashdas","")
            adapterComments.addComment(comment)
        }
        */

        binding.startRecipeBtn.setOnClickListener {
            startActivity(Intent(binding.root.context, DetailedRecipeActivity::class.java))
        }

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        binding.menuBtn.setOnClickListener {
            showMenu(binding.menuBtn, R.menu.recipe_menu)
        }

        binding.commentIL.setEndIconOnClickListener {
            val comment= Comment(UUID.randomUUID().toString(), currentUser.name,"" ,0,binding.commentIL.editText?.text.toString(),currentUser.id)
            adapterComments.addComment(comment)
            layoutMComments.smoothScrollToPosition(binding.commentsRV,null, 0)
            binding.commentIL.editText?.text?.clear()
            recipeViewModel.addComment(comment, recipe.id)
        }
        binding.follow.setOnClickListener {

            recipeViewModel.addFollower(recipe!!.ownerId,currentUser.id)

        }
        binding.firstStar.setOnClickListener{

            if(!gradeDone){
                gradeDone = true
                recipeViewModel.addGrade(recipeGlobal.id,1)
                binding.firstStar2.isGone
                binding.firstStar3.isGone
                binding.firstStar4.isGone
                binding.firstStar5.isGone
            }

        }
        binding.firstStar2.setOnClickListener{


            if(!gradeDone){
            gradeDone = true
                recipeViewModel.addGrade(recipeGlobal.id,2)
                binding.firstStar3.isGone
                binding.firstStar4.isGone
                binding.firstStar5.isGone
            }
        }
        binding.firstStar3.setOnClickListener{

            if(!gradeDone){
                gradeDone = true
                recipeViewModel.addGrade(recipeGlobal.id,3)
                binding.firstStar4.isGone
                binding.firstStar5.isGone
            }

        }
        binding.firstStar4.setOnClickListener{


            if(!gradeDone){
                gradeDone = true
                recipeViewModel.addGrade(recipeGlobal.id,4)
                binding.firstStar5.isGone
            }
        }
        binding.firstStar5.setOnClickListener{

            if(!gradeDone){
                gradeDone = true
                recipeViewModel.addGrade(recipeGlobal.id,5)

            }

        }


    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(binding.root.context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.share_recipe_option -> {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "https://www.example.com/cookbook/recipe")
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                    true
                }
                R.id.save_recipe_option -> {
                    recipeViewModel.saveRecipe(recipeGlobal.id)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}