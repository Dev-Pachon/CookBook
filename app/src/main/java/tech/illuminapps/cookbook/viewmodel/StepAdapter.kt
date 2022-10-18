package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.databinding.ViewHolderStepBinding
import tech.illuminapps.cookbook.view.Comment
import tech.illuminapps.cookbook.view.Step
import tech.illuminapps.cookbook.view.ViewHolderRecipeComment
import tech.illuminapps.cookbook.view.ViewHolderStep

class StepAdapter : RecyclerView.Adapter<ViewHolderStep>() {

    private val steps: ArrayList<Step> = ArrayList()

    private lateinit var context: Context
    private lateinit var binding: ViewHolderStepBinding

    fun addStep(step: Step){
        steps.add(step)
        notifyItemInserted(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderStep {
        context = parent.context

        binding = ViewHolderStepBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolderStep(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolderStep, position: Int) {
        val stepPos = steps[position].position
        binding.contentTL.hint = "Paso ${stepPos}"
    }

    override fun getItemCount(): Int {
        return steps.size
    }
}