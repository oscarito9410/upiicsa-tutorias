package com.aboolean.tutorias.ui.home.teacher_registry

import androidx.lifecycle.Observer
import com.aboolean.tutorias.R
import com.aboolean.tutorias.base.BaseFragment
import com.aboolean.tutorias.domain.model.TeacherRegistry
import com.aboolean.tutorias.ui.model.TeacherSearchViewState
import com.aboolean.tutorias.utils.hideLoadingSpinner
import com.aboolean.tutorias.utils.showLoadingSpinner
import kotlinx.android.synthetic.main.profile_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileTeacherRegistryFragment : BaseFragment() {


    private val viewModel: TeachersRegistryViewModel by viewModel()
    override fun getLayoutView(): Int = R.layout.profile_fragment


    override fun initView() {
        initAdapterManager()
        viewModel.apply {
            teacherRegistriesViewState.observe(viewLifecycleOwner, Observer {
                handleGetTutorsState(it)
            })
        }
    }

    override fun attachObservers() {
    }

    private fun initAdapterManager() {
        rv_profile_teachers?.apply {
            layoutManager = teachersLayoutManager
            adapter = teachersAdapter
        }

        teachersAdapter.apply { notifyDataSetChanged() }

        viewModel.getStringPreferences("idValue")?.let { viewModel.getListTutors(it) }

    }

    private fun showTutors(tutorsRegistries: List<TeacherRegistry>) {
        teachersAdapter.deleteIconAddd()
        teachersAdapter.updateTeachers(tutorsRegistries)

    }


    private fun handleGetTutorsState(viewState: TeacherSearchViewState) {
        when (viewState) {

            is TeacherSearchViewState.OnLoading -> {
                requireActivity().showLoadingSpinner()
            }
            is TeacherSearchViewState.OnFailedTeacherSearch -> {
                requireActivity().hideLoadingSpinner()
                showDialog(viewState.toString())
            }
            is TeacherSearchViewState.OnSuccessTeacherSearch -> {
                requireActivity().hideLoadingSpinner()
                showTutors(viewState.list)
            }

        }
    }


}