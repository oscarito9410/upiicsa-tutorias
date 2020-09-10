package com.example.upicca_tutorias.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.base.BaseFragment
import kotlinx.android.synthetic.main.teachers_registry_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class TeachersRegistryFragment : BaseFragment() {

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