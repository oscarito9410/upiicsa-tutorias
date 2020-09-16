package com.example.upicca_tutorias.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.base.BaseFragment
import com.example.upicca_tutorias.data.model.AddTeacherRequest
import com.example.upicca_tutorias.domain.model.TeacherRegistry
import com.example.upicca_tutorias.ui.model.TeacherSearchViewState
import com.example.upicca_tutorias.utils.hideLoadingSpinner
import com.example.upicca_tutorias.utils.showLoadingSpinner
import kotlinx.android.synthetic.main.teachers_registry_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class TeachersRegistryFragment : BaseFragment() {

    private val viewModel: TeachersRegistryViewModel by viewModel()

    override fun getLayoutView(): Int = R.layout.teachers_registry_fragment

    override fun initView() {
        initAdapterManager()
        et_teachers_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, count: Int) {
                if (count > 5) {
                    viewModel.getListTeachers(p0.toString())
                }
            }

        })
    }

    override fun attachObservers() {

        viewModel.apply {
            teacherRegistriesViewState.observe(viewLifecycleOwner, Observer {
                handleGetTachersState(it)

            })
        }


    }


    private fun handleGetTachersState(viewState: TeacherSearchViewState) {
        when (viewState) {

            is TeacherSearchViewState.OnLoading -> {
                requireActivity().showLoadingSpinner()
            }
            is TeacherSearchViewState.OnFailedTeacherSearch -> {
                requireActivity().hideLoadingSpinner()
                showDialog("Tu registro ha sido exitoso")
                //requireContext().toast("an error here")
            }
            is TeacherSearchViewState.OnSuccessTeacherSearch -> {
                requireActivity().hideLoadingSpinner()
                showTeachersSearch(viewState.list)
            }
            is TeacherSearchViewState.OnSuccessAddTeachersRegistry -> {
                showDialog("Tu registro ha sido exitoso")

            }

        }
    }


    private fun showTeachersSearch(teacherRegistries: List<TeacherRegistry>) {
        teachersAdapter.updateTeachers(teacherRegistries)

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
            setItemClickListener { teacherRegistry ->
                teacherRegistry.run {
                    val addTeacherRequest = AddTeacherRequest(
                        teacherRegistry.nombre,
                        viewModel.getStringPreferences("idValue")
                    )
                    showDialog(
                        "¿Estás seguro de asignar a este profesor como tu tutor?",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            viewModel.addTeacherRegistry(addTeacherRequest)
                        },
                        true
                    )
                    notifyDataSetChanged()
                }
            }
        }
    }
}


