package com.aboolean.tutorias.ui.home.teacher_registry

import android.animation.Animator
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.aboolean.tutorias.R
import com.aboolean.tutorias.base.BaseFragment
import com.aboolean.tutorias.data.model.AddTeacherRequest
import com.aboolean.tutorias.domain.model.TeacherRegistry
import com.aboolean.tutorias.ui.model.TeacherSearchViewState
import com.aboolean.tutorias.utils.hideLoadingSpinner
import com.aboolean.tutorias.utils.showLoadingSpinner
import kotlinx.android.synthetic.main.lottie_success_custom_dialog.view.*
import kotlinx.android.synthetic.main.teachers_registry_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class TeachersRegistryFragment : BaseFragment() {

    private val viewModel: TeachersRegistryViewModel by viewModel()

   lateinit var teacherRegistryIndex: TeacherRegistry


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
                showDialog("Error de conexión")
                //requireContext().toast("an error here")
            }
            is TeacherSearchViewState.OnSuccessTeacherSearch -> {
                requireActivity().hideLoadingSpinner()
                showTeachersSearch(viewState.list)
            }
            is TeacherSearchViewState.OnSuccessAddTeachersRegistry -> {
                requireActivity().hideLoadingSpinner()
                initAnimationSuccess()
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


    private fun initAnimationSuccess(){
        teachersAdapter.deleteTeachers(teacherRegistryIndex)

        val builder = AlertDialog.Builder(context,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        val customLayout = getLayoutInflater().inflate(R.layout.lottie_success_custom_dialog, null);
        builder.setView(customLayout);
        val dialog = builder.create()
        dialog.show()
        customLayout.lav_success_process.playAnimation()
        customLayout.lav_success_process.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(p0: Animator?) {
            }
            override fun onAnimationEnd(p0: Animator?) {
                dialog.dismiss()
            }
            override fun onAnimationCancel(p0: Animator?) {
            }
            override fun onAnimationStart(p0: Animator?) {
            }
        })




    }

    private fun handleItemClickListener() {
        teachersAdapter.apply {
            setItemClickListener { teacherRegistry ->
                teacherRegistry.run {
                    val addTeacherRequest = AddTeacherRequest(
                        viewModel.getStringPreferences("idValue"),
                        teacherRegistry.id
                    )
                    showDialog(
                        "¿Estás seguro de asignar a este profesor como tu tutor?",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            viewModel.addTeacherRegistry(addTeacherRequest)
                        },
                        true
                    )
                    teacherRegistryIndex = teacherRegistry
                    notifyDataSetChanged()
                }
            }
        }
    }





}


