package com.aboolean.tutorias.ui.signin.signup.matters_completion

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aboolean.tutorias.R
import com.aboolean.tutorias.domain.model.Matter
import com.tokenautocomplete.TokenCompleteTextView


class MattersCompletionView(context: Context, attribute: AttributeSet) : TokenCompleteTextView<String>(context, attribute) {


    override fun getViewForObject(matter: String?): View {
        val l = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = l.inflate(R.layout.layout_matters, parent as ViewGroup, false) as TextView
        view.setText(matter)

        return view    }

    override fun defaultObject(completionText: String?): String {
        return completionText.toString()
    }
}