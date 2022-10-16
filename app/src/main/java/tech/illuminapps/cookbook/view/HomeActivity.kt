package tech.illuminapps.cookbook.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.illuminapps.cookbook.R

class HomeActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}