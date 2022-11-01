package tech.illuminapps.cookbook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.illuminapps.cookbook.databinding.ActivityRegisterBinding
import tech.illuminapps.cookbook.viewmodel.AuthResult
import tech.illuminapps.cookbook.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val launcher = registerForActivityResult(StartActivityForResult(), ::onResult)

        binding.loginBtn.setOnClickListener {
            finish()
        }

        binding.registerBtn.setOnClickListener {

            var email = binding.emailTxt.text.toString()
            var name = binding.nameTxt.text.toString()
            var password = binding.passwordTxt.text.toString()

            registerViewModel.register(name,email,password)
            registerViewModel.authState.observe(this){


                when(it.result){

                    AuthResult.SUCCESS->{

                        Log.e(">>>","Deberia cambiar de pantalla")

                        startActivity(Intent(binding.root.context, RegisterCategoriesActivity::class.java))
                    }
                    AuthResult.IDLE->{




                    }
                    AuthResult.FAIL->{

                        Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()


                    }
                }



            }



        }

    }

    private fun onResult(result:ActivityResult){

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
}