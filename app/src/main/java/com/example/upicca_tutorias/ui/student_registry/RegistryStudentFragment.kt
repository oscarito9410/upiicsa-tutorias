package com.example.upicca_tutorias.ui.student_registry

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.upicca_tutorias.R

class RegistryStudentFragment : Fragment() {

    companion object {
        fun newInstance() =
            RegistryStudentFragment()
    }

    private lateinit var viewModel: RegistryStudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registry_student_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegistryStudentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}