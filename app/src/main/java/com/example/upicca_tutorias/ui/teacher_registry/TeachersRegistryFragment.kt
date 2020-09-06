package com.example.upicca_tutorias.ui.teacher_registry

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.base.BaseFragment
import com.example.upicca_tutorias.ui.teacher_registry.adapter.TeacherRegistryAdapter

class TeachersRegistryFragment : BaseFragment() {


    companion object {
        fun newInstance() =
            TeachersRegistryFragment()
    }

    private lateinit var viewModel: TeachersRegistryViewModel


    override fun getLayoutView(): Int = R.layout.teachers_registry_fragment


    override fun initView() {
    }

    override fun attachObservers() {
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObservers()
        viewModel = ViewModelProviders.of(this).get(TeachersRegistryViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            fetchTeachersRegistries()
            teacherRegistries.observe(viewLifecycleOwner, Observer {
                teachersAdapter.addNewTeachers(it)
            })
        }

    }




}