package tech.illuminapps.cookbook.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.illuminapps.cookbook.databinding.ActivityCreateRecipeBinding
import tech.illuminapps.cookbook.model.Post
import tech.illuminapps.cookbook.util.FCMMessage
import tech.illuminapps.cookbook.util.HTTPSWebUtilDomi
import tech.illuminapps.cookbook.viewmodel.CreateRecipeViewModel
import tech.illuminapps.cookbook.viewmodel.IngredientAdapter
import tech.illuminapps.cookbook.viewmodel.StepAdapter
import tech.illuminapps.cookbook.viewmodel.onGalleryCalled
import java.util.*

class CreateRecipeActivity : AppCompatActivity(), onGalleryCalled {

    val binding: ActivityCreateRecipeBinding by lazy {
        ActivityCreateRecipeBinding.inflate(layoutInflater)
    }
    private lateinit var postrecipe: Post

    private lateinit var layoutMIngredients : LinearLayoutManager
    private lateinit var layoutMSteps:LinearLayoutManager
    private lateinit var adapterIngredient : IngredientAdapter
    private lateinit var adapterStep:StepAdapter
    private var stepCounter = 1
    private val createRecipeViewModel : CreateRecipeViewModel by viewModels()
    private lateinit var mainImageUri: Uri
    private var stepUri:String = ""
    private lateinit var galLauncher2: ActivityResultLauncher<Intent>
    private var listener: onUriReady?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       // var ingredients = createRecipeViewModel.getIngredientslist()

        layoutMIngredients = LinearLayoutManager(binding.root.context)
        layoutMSteps = LinearLayoutManager(binding.root.context)
        adapterIngredient = IngredientAdapter()
        adapterStep = StepAdapter()
        binding.ingredientsRV.layoutManager = layoutMIngredients
        binding.recipeStepsRV.layoutManager = layoutMSteps
        binding.ingredientsRV.adapter = adapterIngredient
        binding.recipeStepsRV.adapter = adapterStep
        adapterStep.listener = this
        listener = adapterStep

        val galLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
            ,::onGalleryResult

        )
        galLauncher2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
            ,::onGalleryResultStep
        )

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        binding.showAddIngredientGroup.setOnClickListener {

                binding.ingredientsAddGroup.isGone = !binding.ingredientsAddGroup.isGone
                val items = arrayOf("Queso", "Jamon", "Carne de Cerdo", "Carne de Res","Aceite de soja"
                ,"Yogur","Mantequilla","Lentejas","Setas","Papa","Berenjena","Brocoli","Calabacin"
                ,"Cebolla","Tomate")
                (binding.menu.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)


        }

        binding.addIngredientBtn.setOnClickListener {

            if(binding.quantityIL.editText?.text!!.isNotEmpty() && binding.quantityIL.editText!!.text.isNotEmpty()) {

                val ingredient = Ingredient(
                    "1",
                    binding.ingredientsDrodownMenu.text.toString(),
                    Integer.parseInt(binding.quantityIL.editText?.text.toString())
                )
                adapterIngredient.addIngredient(ingredient)
                // Toast.makeText(binding.root.context,"??s;lkda;sd",Toast.LENGTH_LONG).show()
                // binding.ingredientsAddGroup.isGone = !binding.ingredientsAddGroup.isGone
                // binding.ingredientsDrodownMenu.text.clear()
                // binding.quantityIL.editText?.text?.clear()
            }
        }

        binding.addStepBtn.setOnClickListener {

           // var uid2 = UUID.randomUUID().toString()

            val step = Step(stepCounter, null, binding.stepIL.editText.toString(),stepCounter.toString())
            adapterStep.addStep(step)
            stepCounter++
        }
        var categories = arrayListOf("Desayuno","Italiana","Pasta","Francesa","Espa??ola","Mexicana","Argentina","Comida Rapida","Hamburguesa","Tipica")

        for (categorie in 0..categories.size-1) {


                val chip = Chip(binding.root.context)
                chip.text = categories.get(categorie)
                binding.chipGroup.addView(chip)
                setContentView(binding.root)
                chip.id = categorie


        }
        binding.finishBtn.setOnClickListener{

            var name = binding.editText.text.toString()
            var selectedCategories = arrayListOf<String>()
            for(position in 0..categories.size-1){


                val chip = binding.chipGroup.findViewById<Chip>(position)
                if(chip.isChecked){

                    selectedCategories.add(chip.text.toString())


                }


            }
            if(this::mainImageUri.isInitialized){
                createRecipeViewModel.addPost(adapterIngredient.returnIngredients(),adapterStep.getSteps(),name,selectedCategories,mainImageUri)
                startActivity(Intent(binding.root.context, MainActivity::class.java))
            }else{
                Toast.makeText(this,"A??ada una Imagen del resultado final de la receta",Toast.LENGTH_LONG)
            }
            //Notificar al contacto
            lifecycleScope.launch(Dispatchers.IO){
                val obj = FCMMessage("/topics/${postrecipe.userId}","Nueva receta")
                val json = Gson().toJson(obj)
                HTTPSWebUtilDomi().POSTtoFCM(json)
            }


        }
        binding.cardView3.setOnClickListener{

            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galLauncher.launch(intent)


        }


    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(binding.root.context)
            .setTitle("Descartar?")
            .setMessage("Est?? seguro de que quiere descartar los cambios?\nPerder?? todo el progreso realizado.")
            .setNeutralButton("Cancelar") { _, _ -> }
            .setNegativeButton("Guardar receta") { _, _ -> }
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
                binding.imageView10.setImageURI(uri)
                mainImageUri = uri
            }





            /*

            uri?.let {
                Firebase.storage.reference.child("profiles").child(UUID.randomUUID().toString()).putFile(uri)

            }
            */

        }
    }
    private fun onGalleryResultStep(activityResult: ActivityResult) {

        if(activityResult.resultCode == RESULT_OK){
            val uri = activityResult.data?.data
            uri.let {
                stepUri = it.toString()
                Log.e(">>>",stepUri)
                listener?.uriReady(stepUri)
            }
            //val name = activityResult.data.data.





            /*

            uri?.let {
                Firebase.storage.reference.child("profiles").child(UUID.randomUUID().toString()).putFile(uri)

            }
            */

        }
    }
    override fun openGallery() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        galLauncher2.launch(intent)



    }




}
interface onUriReady{

    fun uriReady(uri:String)
}