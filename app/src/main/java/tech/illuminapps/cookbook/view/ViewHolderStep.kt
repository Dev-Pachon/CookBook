package tech.illuminapps.cookbook.view

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import tech.illuminapps.cookbook.R
import tech.illuminapps.cookbook.databinding.ViewHolderStepBinding

class ViewHolderStep (itemView: View) : RecyclerView.ViewHolder(itemView){
    var binding: ViewHolderStepBinding?= null
}