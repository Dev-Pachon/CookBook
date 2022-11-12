package tech.illuminapps.cookbook.view

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import tech.illuminapps.cookbook.R

class ViewHolderStep (itemView: View) : RecyclerView.ViewHolder(itemView){

    var step:Step?=null

    var editText: EditText = itemView.findViewById(R.id.editText)
    var deleteBtn: MaterialButton = itemView.findViewById(R.id.deleteBtn)
    var addImageButton: MaterialButton = itemView.findViewById(R.id.addImageBtn)
    var stepIl: TextInputLayout = itemView.findViewById(R.id.stepIL)
    var cardViewStep: CardView = itemView.findViewById(R.id.cardViewStep)
    var imageViewStep: ImageView = itemView.findViewById(R.id.imageViewStep)

    init {

        addImageButton.setOnClickListener{

        }
        deleteBtn.setOnClickListener {

        }

    }

}