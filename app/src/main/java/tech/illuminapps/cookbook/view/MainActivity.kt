package tech.illuminapps.cookbook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ActivityMainBinding
import tech.illuminapps.cookbook.viewmodel.AuthResult
import tech.illuminapps.cookbook.viewmodel.AuthState
import tech.illuminapps.cookbook.viewmodel.FirstInit
import tech.illuminapps.cookbook.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()

    lateinit var loginFragment: LoginFragment
    lateinit var firstInitFragment: FirstInitFragment
    lateinit var mainFragment: MainFragment

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userViewModel.setPreferences(this)

        loginFragment = LoginFragment.newInstance(this)
        firstInitFragment = FirstInitFragment.newInstance(this)
        mainFragment = MainFragment.newInstance(this)

        userViewModel.authState.observe(this) { authState ->
            when (authState.result) {
                AuthResult.IDLE -> {

                }
                AuthResult.SUCCESS -> {
                    showFragment(mainFragment)
                }
                AuthResult.FAIL -> {
                    userViewModel.firstInit.observe(this) {
                        when (it) {
                            FirstInit.FIRST_INIT -> {
                                showFragment(firstInitFragment)
                            }
                            FirstInit.NORMAL_INIT -> {
                                showFragment(loginFragment)
                            }
                            else -> {
                                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }

    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentCV.id, fragment)
        transaction.commit()
    }
    fun showFragment(fragment: Fragment, fragmentContainerID:Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentContainerID, fragment)
        transaction.commit()
    }
}