package com.aboolean.upicca_tutorias.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.aboolean.upicca_tutorias.R

class LoadingDialogFragment : DialogFragment() {

    override fun onStart() {
        super.onStart()
        setupWindow()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.loader_layout, container)
    }

    private fun setupWindow() {
        dialog?.window?.apply {
            setGravity(Gravity.CENTER)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setStyle(STYLE_NO_TITLE, R.style.CustomDialogTranslucent)
        }
    }
}