package tech.illuminapps.cookbook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import tech.illuminapps.cookbook.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val launcher = registerForActivityResult(StartActivityForResult(), ::onResult)

        binding.loginBtn.setOnClickListener {
            finish()
        }

        binding.registerBtn.setOnClickListener {
            Toast.makeText(this, "Registrado exitosamente", Toast.LENGTH_LONG).show()
            startActivity(Intent(binding.root.context, RegisterCategoriesActivity::class.java))
        }

    }

    private fun onResult(result:ActivityResult){

    }
}