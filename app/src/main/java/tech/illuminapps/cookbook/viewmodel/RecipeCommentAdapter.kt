package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ViewHolderCommentBinding
import tech.illuminapps.cookbook.databinding.ViewHolderPopularProfilesBinding
import tech.illuminapps.cookbook.databinding.ViewHolderRecipeExpandedBinding
import tech.illuminapps.cookbook.view.Comment
import tech.illuminapps.cookbook.view.PopularProfile
import tech.illuminapps.cookbook.view.ViewHolderPopularProfile
import tech.illuminapps.cookbook.view.ViewHolderRecipeComment

class RecipeCommentAdapter : RecyclerView.Adapter<ViewHolderRecipeComment>() {

    private val comments: ArrayList<Comment> = ArrayList()

    private lateinit var context: Context
    private lateinit var binding: ViewHolderCommentBinding

    fun addComment(comment: Comment) {
        comments.add(comment)
        notifyItemInserted(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecipeComment {
        context = parent.context

        binding = ViewHolderCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolderRecipeComment(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolderRecipeComment, position: Int) {
        binding.authorNameTV.text = comments[position].authorName
        binding.commentTV.text = comments[position].content
        val numLikes = comments[position].numLikes
        "$numLikes me gustas".also { binding.numLikesTV.text = it }
        binding.imageView14.setImageDrawable(ContextCompat.getDrawable(binding.root.context, R.drawable.daily_activities_g698a2baf4_1920_1))
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}