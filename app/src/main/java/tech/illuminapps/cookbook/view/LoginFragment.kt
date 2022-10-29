package tech.illuminapps.cookbook.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.FragmentLoginBinding
import tech.illuminapps.cookbook.viewmodel.AuthResult
import tech.illuminapps.cookbook.viewmodel.LoginViewModel
import tech.illuminapps.cookbook.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private val binding: FragmentLoginBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    private lateinit var mainFragment: MainFragment
    private lateinit var mainActivity:MainActivity

    private val userViewModel: UserViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    companion object {
        fun newInstance(mainActivity: MainActivity) = LoginFragment().apply {
            this.mainActivity = mainActivity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragment = MainFragment.newInstance(mainActivity)

        binding.loginBtn.setOnClickListener {

            val email = binding.emailText.text.toString()
            val password = binding.editText.text.toString()

            Log.e(">>>>>", " Email: ${email} y contrasena ${password}" )

            loginViewModel.logIn(email,password);

            loginViewModel.authState.observe(viewLifecycleOwner){

                when(it.result){

                    AuthResult.SUCCESS->{
                        userViewModel.logIn(requireActivity())
                        mainActivity.showFragment(mainFragment)

                    }
                    AuthResult.IDLE->{




                    }
                    AuthResult.FAIL->{

                        //Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()


                    }
                }


            }








        }

        binding.registerBtn.setOnClickListener{
            startActivity(Intent(binding.root.context, RegisterActivity::class.java))
        }




        return binding.root
    }

}