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
import com.example.upicca_tutorias.ui.login.LoginViewModel
import com.example.upicca_tutorias.ui.teacher_registry.adapter.TeacherRegistryAdapter
import kotlinx.android.synthetic.main.teachers_registry_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class TeachersRegistryFragment : BaseFragment() {


    companion object {
        fun newInstance() =
            TeachersRegistryFragment()
    }

    private val viewModel: TeachersRegistryViewModel by viewModel()



    override fun getLayoutView(): Int = R.layout.teachers_registry_fragment


    override fun initView() {
        initAdapterManager()
    }

    override fun attachObservers() {
        viewModel.apply {
            fetchTeachersRegistries()
            teacherRegistries.observe(viewLifecycleOwner, Observer {
                teachersAdapter.addNewTeachers(it)
            })
        }


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObservers()
    }


    private fun initAdapterManager() {
        rv_teachers_registry?.apply {
            layoutManager = teachersLayoutManager
            //addItemDecoration(SpacesItemDecoration(SPACE_ITEM_DECORATION))
            adapter = teachersAdapter
            handleItemClickListener()
        }
    }

    private fun handleItemClickListener() {
        teachersAdapter.apply {
            setItemClickListener { movie ->
                movie.run {
                    notifyDataSetChanged()

                }
            }
        }
    }





}