package com.aboolean.upicca_tutorias.ui.home.teacher_registry

import androidx.lifecycle.Observer
import com.aboolean.upicca_tutorias.R
import com.aboolean.upicca_tutorias.base.BaseFragment
import com.aboolean.upicca_tutorias.domain.model.TeacherRegistry
import com.aboolean.upicca_tutorias.ui.model.TeacherSearchViewState
import com.aboolean.upicca_tutorias.utils.hideLoadingSpinner
import com.aboolean.upicca_tutorias.utils.showLoadingSpinner
import kotlinx.android.synthetic.main.profile_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileTeacherRegistryFragment : BaseFragment() {



    private val viewModel: TeachersRegistryViewModel by viewModel()


    override fun getLayoutView(): Int = R.layout.profile_fragment


    override fun initView() {
        initAdapterManager()
        viewModel.getStringPreferences("idValue")?.let { viewModel.getListTutors(it) }

    }

    override fun attachObservers() {
        viewModel.apply {
            teacherRegistriesViewState.observe(viewLifecycleOwner, Observer {
                handleGetTutorsState(it)
            })
        }    }



    private fun initAdapterManager() {
        rv_profile_teachers?.apply {
            layoutManager = teachersLayoutManager
            //addItemDecoration(SpacesItemDecoration(SPACE_ITEM_DECORATION))
            adapter = teachersAdapter
        }
    }

    private fun showTutors(tutorsRegistries: List<TeacherRegistry>) {
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
                //requireContext().toast("an error here")
            }
            is TeacherSearchViewState.OnSuccessTeacherSearch -> {
                requireActivity().hideLoadingSpinner()
                showTutors(viewState.list)
            }

        }
    }



}