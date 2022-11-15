package tech.illuminapps.cookbook.model

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import tech.illuminapps.cookbook.view.ViewHolderStep

class GalleryGetter: AppCompatActivity() {

    var bind: ViewHolderStep?=null

    fun getImage(binding: ViewHolderStep) {

        bind = binding

        val galLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), ::onGalleryResult

        )
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        galLauncher.launch(intent)

    }

    private fun onGalleryResult(activityResult: ActivityResult) {

        if (activityResult.resultCode == RESULT_OK) {

            val uri = activityResult.data?.data

            bind!!.binding!!.imageViewStep.setImageURI(uri)
            
        }


    }
}