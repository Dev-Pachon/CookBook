package tech.illuminapps.cookbook.viewmodel

import android.content.Context
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ViewHolderStepBinding
import tech.illuminapps.cookbook.view.Comment
import tech.illuminapps.cookbook.view.Step
import tech.illuminapps.cookbook.view.ViewHolderRecipeComment
import tech.illuminapps.cookbook.view.ViewHolderStep
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import tech.illuminapps.cookbook.model.GalleryGetter


class StepAdapter : RecyclerView.Adapter<ViewHolderStep>() {

    private val steps: ArrayList<Step> = ArrayList()

    private lateinit var context: Context

    fun addStep(step: Step) {
        steps.add(step)
        notifyItemInserted(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderStep {
        context = parent.context

        val binding =
            ViewHolderStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = ViewHolderStep(binding.root)

        viewHolder.binding = binding

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderStep, position: Int) {
        // binding.step = step

        val binding = holder.binding!!

        binding.stepIL.hint = "Paso ${position + 1}"

        binding.deleteBtn.setOnClickListener {
            steps.removeAt(position)
            notifyDataSetChanged()
        }

        binding.stepIL.setEndIconOnClickListener {

            if (binding.stepIL.editText?.inputType == InputType.TYPE_NULL) {
                binding.stepIL.editText?.inputType = InputType.TYPE_CLASS_TEXT
                binding.stepIL.requestFocus()
                binding.addImageBtn.visibility = View.VISIBLE
                binding.stepIL.endIconDrawable =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.done)
            } else {
                steps[position].content = binding.stepIL.editText?.text.toString()
                binding.stepIL.editText?.inputType = InputType.TYPE_NULL
                binding.stepIL.clearFocus()
                binding.addImageBtn.visibility = View.INVISIBLE
                binding.stepIL.endIconDrawable =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.edit)
            }
        }

        binding.addImageBtn.setOnClickListener {

            val galery = GalleryGetter()
            galery.getImage(holder)


        }
        binding.deleteBtn.setOnClickListener {

            steps.remove(steps.get(position))

        }
    }

    override fun getItemCount(): Int {
        return steps.size
    }

    fun getSteps(): ArrayList<Step> {
        return steps
    }
}