package tech.illuminapps.cookbook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ActivityRecipeBinding
import tech.illuminapps.cookbook.viewmodel.ExtendedRecipeAdapter
import tech.illuminapps.cookbook.viewmodel.RecipeCommentAdapter
import tech.illuminapps.cookbook.viewmodel.RecipeViewModel

class RecipeActivity : AppCompatActivity() {

    val binding: ActivityRecipeBinding by lazy {
        ActivityRecipeBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMComments : LinearLayoutManager

    private lateinit var adapterComments : RecipeCommentAdapter

    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        layoutMComments = LinearLayoutManager(binding.root.context)
        binding.commentsRV.layoutManager = layoutMComments
        adapterComments = RecipeCommentAdapter()
        recipeViewModel = RecipeViewModel()
        binding.commentsRV.adapter = adapterComments

        val recipe = intent.extras?.getSerializable("recipe") as? Recipe

            Log.e(">>>", "Id de la receta ${recipe!!.id}")

        binding.nameRecipeTV.text = recipe!!.title
        binding.authorNameTV.text = recipe!!.ownerName
        recipeViewModel.getUserData()

        recipeViewModel.user.observe(this){
            binding.commentName.text = it.name

        }


        for (i in 1..10){
            val comment = Comment("img1.jpg", "Carlos Jimmy","",10,"askldhlkashdas")
            adapterComments.addComment(comment)
        }

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
            val comment= Comment("", "Henry Cavil", "", 50,binding.commentIL.editText?.text.toString())
            adapterComments.addComment(comment)
            layoutMComments.smoothScrollToPosition(binding.commentsRV,null, 0)
            binding.commentIL.editText?.text?.clear()
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

                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}