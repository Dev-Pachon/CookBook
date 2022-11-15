package tech.illuminapps.cookbook.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ViewHolderStepBinding
import tech.illuminapps.cookbook.view.Step
import tech.illuminapps.cookbook.view.ViewHolderStep
import tech.illuminapps.cookbook.view.onUriReady


class StepAdapter : RecyclerView.Adapter<ViewHolderStep>(),onUriReady{

    private val steps: ArrayList<Step> = ArrayList()

    private lateinit var context: Context

    private lateinit var currentBinding: ViewHolderStepBinding

    private var lastStepModified : Int = -1

    var listener: onGalleryCalled?= null

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

    override fun onBindViewHolder(holder: ViewHolderStep, @SuppressLint("RecyclerView") position: Int) {
        // binding.step = step

        val binding = holder.binding!!
        currentBinding = binding

        binding.stepIL.hint = "Paso ${position + 1}"
       // var currentStep = steps.get(position)


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

            lastStepModified = position
            /*
            val galLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
                ,::onGalleryResult

            )

             */
            listener.let {
                it?.openGallery()
               //
                lastStepModified = position

            }

        }
        binding.deleteBtn.setOnClickListener {

            steps.remove(steps.get(position))

                notifyItemRemoved(position);

        }
    }

    override fun getItemCount(): Int {
        return steps.size
    }

    fun getSteps(): ArrayList<Step> {
        return steps
    }

    override fun uriReady(uri: String) {

        Log.e(">>>", "${uri!!} Debiera mostrar algo antes de esto" )

        if(uri.equals("")){

        }else{
            var uriNotString = Uri.parse(uri)
            currentBinding.cardViewStep.isGone = false
            currentBinding.imageViewStep.setImageURI(uriNotString)
            currentBinding.imageViewStep.isGone = false
            steps.get(lastStepModified).image = uri
        }

    }

}
interface onGalleryCalled{

    fun openGallery()

}