package com.example.upicca_tutorias.ui.teacher_registry

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.upicca_tutorias.R

class TeachersRegistryFragment : Fragment() {

    companion object {
        fun newInstance() =
            TeachersRegistryFragment()
    }

    private lateinit var viewModel: TeachersRegistryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.teachers_registry_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeachersRegistryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}