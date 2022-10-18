package tech.illuminapps.cookbook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class RecipeActivity : AppCompatActivity() {

    val binding: ActivityRecipeBinding by lazy {
        ActivityRecipeBinding.inflate(layoutInflater)
    }

    private lateinit var layoutMComments : LinearLayoutManager

    private lateinit var adapterComments : RecipeCommentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        layoutMComments = LinearLayoutManager(binding.root.context)
        binding.commentsRV.layoutManager = layoutMComments
        binding.commentsRV.setHasFixedSize(true)
        adapterComments = RecipeCommentAdapter()
        binding.commentsRV.adapter = adapterComments

        for (i in 1..10){
            val comment = Comment("img1.jps", "Carlos Jimmy","",10,"askldhlkashdas")
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