package com.example.upicca_tutorias.ui.home.teacher_registry

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.base.BaseFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.teachers_registry_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileTeacherRegistryFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            ProfileTeacherRegistryFragment()
    }


    private val viewModel: TeachersRegistryViewModel by viewModel()


    override fun getLayoutView(): Int = R.layout.profile_fragment


    override fun initView() {
        initAdapterManager()
    }

    override fun attachObservers() {
        TODO("Not yet implemented")
    }



    private fun initAdapterManager() {
        rv_profile_teachers?.apply {
            layoutManager = teachersLayoutManager
            //addItemDecoration(SpacesItemDecoration(SPACE_ITEM_DECORATION))
            adapter = teachersAdapter
        }
    }

}