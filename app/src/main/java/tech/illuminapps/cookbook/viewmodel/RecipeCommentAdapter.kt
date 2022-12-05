package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ViewHolderCommentBinding
import tech.illuminapps.cookbook.databinding.ViewHolderPopularProfilesBinding
import tech.illuminapps.cookbook.databinding.ViewHolderRecipeExpandedBinding
import tech.illuminapps.cookbook.view.Comment
import tech.illuminapps.cookbook.view.PopularProfile
import tech.illuminapps.cookbook.view.ViewHolderPopularProfile
import tech.illuminapps.cookbook.view.ViewHolderRecipeComment
import java.util.LinkedList

class RecipeCommentAdapter : RecyclerView.Adapter<ViewHolderRecipeComment>() {

    private val comments: LinkedList<Comment> = LinkedList()

    private lateinit var context: Context
    private lateinit var binding: ViewHolderCommentBinding

    fun addComment(comment: Comment) {
        comments.addFirst(comment)
        notifyItemInserted(0)
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
        Firebase.storage.reference.child("users/${comments[position].authorId}").downloadUrl.addOnSuccessListener {
            //Log.e(">>>","Esto deberia funcionar")
            Glide.with(context).load(it!!).into(binding.imageView14)

        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}