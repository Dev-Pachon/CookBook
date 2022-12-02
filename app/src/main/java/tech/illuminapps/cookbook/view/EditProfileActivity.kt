package tech.illuminapps.cookbook.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.illuminapps.cookbook.databinding.ActivityEditProfileBinding
import tech.illuminapps.cookbook.model.User
import tech.illuminapps.cookbook.viewmodel.EditProfileViewModel

class EditProfileActivity : AppCompatActivity() {

    val binding: ActivityEditProfileBinding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }
    private lateinit var mainImageUri: Uri
    private lateinit var editProfileViewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val user = intent.getSerializableExtra("user") as? User

        val galLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
            ,::onGalleryResult

        )
        editProfileViewModel = EditProfileViewModel()
        binding.editText.setText(user!!.name)
        binding.descriptionET.setText(user!!.description)

        var categories = arrayListOf("Desayuno","Italiana","Pasta","Francesa","Española","Mexicana","Argentina","Comida Rapida","Hamburguesa","Tipica")

        for (categorie in 0..categories.size-1){
            val chip = Chip(binding.root.context)
            chip.text = categories.get(categorie)
            binding.chipGroup.addView(chip)
            setContentView(binding.root)
            chip.id = categorie
            if(user!!.followedCategories.contains(categories.get(categorie))){
                binding.chipGroup.check(categorie)
            }
        }

        binding.nextBtn.setOnClickListener{

            var name = binding.editText.text.toString()
            var description = binding.descriptionET.text.toString()
            var selectedCategories = arrayListOf<String>()
            for(position in 0..categories.size-1){


                val chip = binding.chipGroup.findViewById<Chip>(position)
                if(chip.isChecked){

                    selectedCategories.add(chip.text.toString())


                }

                if(::mainImageUri.isInitialized) {
                    editProfileViewModel.updateUserData(name,description,selectedCategories,mainImageUri!!)


                }else{

                    editProfileViewModel.updateDataNopfp(name,description,selectedCategories)

                }

            }
        editProfileViewModel.authState.observe(this){

            startActivity(Intent(binding.root.context, MainActivity::class.java))

        }

        }
        binding.profilePicCV.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galLauncher.launch(intent)
        }
    }

    override fun onBackPressed(){
        MaterialAlertDialogBuilder(binding.root.context)
            .setTitle("Descartar?")
            .setMessage("Está seguro de que quiere descartar los cambios?\nPerderá todo el progreso realizado.")
            .setNeutralButton("Cancelar") { _, _ -> }
            .setPositiveButton("Descartar") { _, _ ->
                super.onBackPressed()
            }
            .show()
    }
    private fun onGalleryResult(activityResult: ActivityResult) {

        if(activityResult.resultCode == RESULT_OK){
            val uri = activityResult.data?.data
            mainImageUri = uri!!
            //val name = activityResult.data.data.
            uri?.let {
                Log.e(">>>", it.toString() )
                binding.pfpIV.setImageURI(uri)
                mainImageUri = uri
            }




            /*

            uri?.let {
                Firebase.storage.reference.child("profiles").child(UUID.randomUUID().toString()).putFile(uri)

            }
            */

        }
    }

}