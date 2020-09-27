package com.aboolean.tutorias.ui.signin.signup

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aboolean.tutorias.R
import com.aboolean.tutorias.base.BaseFragment
import com.aboolean.tutorias.data.model.SignUpRequest
import com.aboolean.tutorias.domain.model.Matter
import com.aboolean.tutorias.ui.home.HomeActivity
import com.aboolean.tutorias.ui.model.RegistryViewState
import com.aboolean.tutorias.ui.signin.SiginInActivity
import com.aboolean.tutorias.utils.hideLoadingSpinner
import com.aboolean.tutorias.utils.showLoadingSpinner
import com.aboolean.tutorias.utils.toast
import kotlinx.android.synthetic.main.registry_student_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegistryStudentFragment : BaseFragment() {


    private val viewModel: RegistryStudentViewModel by viewModel()
    private var mIsNextScreen: Boolean = false
    private var mIsFinalScreen: Boolean = false

    private lateinit var mNavController: NavController

    override fun getLayoutView(): Int = R.layout.registry_student_fragment

    override fun initView() {

    }

    override fun attachObservers() {
        viewModel.apply {
            registryViewState.observe(viewLifecycleOwner, Observer {
                handleRegistryViewState(it)
            })
        }
    }


    private fun handleRegistryViewState(viewState: RegistryViewState) {
        when (viewState) {
            is RegistryViewState.OnLoading -> {
                requireActivity().showLoadingSpinner()
            }
            is RegistryViewState.OnFailedSignUp -> {
                requireActivity().hideLoadingSpinner()
                showDialog(viewState.errorResponseRegistry.Message.toString())
            }
            is RegistryViewState.OnSuccessSignUp -> {
                requireActivity().hideLoadingSpinner()
                // mNavController.navigate(R.id.teachersRegistryFragment)
                // mNavController.navigate(R.id.action_loginFragment_to_teachersRegistryFragment)
                viewModel.saveStringPreferences(et_registry_id_student.text.toString())
                context!!.startActivity(Intent(context, HomeActivity::class.java))
                activity!!.finish()

            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO eliminar este codigo
        val listMatters = listOf("Fisica", "Matematicas","Materias","Estadistica","Matematicas","Materias","Matematicas","Materias","Matematicas","Materias")
        val arrayMatterAdapter= ArrayAdapter<String>(context!!,android.R.layout.simple_list_item_1,listMatters)

        mcv_matters_owned_name.setAdapter(arrayMatterAdapter)

        mNavController = Navigation.findNavController(view)
        tv_registry_footer.text = String.format(getString(R.string.text_registry_footer, "1"))
        iv_registry_footer.setOnClickListener {

            if (!mIsNextScreen) {

                val isNotEmptyFirstScreen =
                    !et_registry_first_last_name.text.isEmpty() && !et_registry_second_last_name.text.isEmpty() && !et_registry_names.text.isEmpty()
                            && !et_registry_id_student.text.isEmpty() && !et_registry_semester.text.isEmpty() && !et_registry_init_average.text.isEmpty()

                if (isNotEmptyFirstScreen) {
                    mIsNextScreen = true
                    tv_registry_footer.text =
                        String.format(getString(R.string.text_registry_footer, "2"))
                    cly_container_registry_step_1.visibility = View.GONE
                    cly_container_registry_step_2.visibility = View.VISIBLE
                } else {
                    showDialog(getString(R.string.text_edits_no_empty))
                }


            } else if (!mIsFinalScreen) {

                val isNotEmptySecondScreen =
                    !et_registry_mail.text.isEmpty() && !et_registry_phone.text.isEmpty() && !et_registry_matters_owed.text.isEmpty()

                if (isNotEmptySecondScreen) {
                    mIsFinalScreen = true
                    tv_registry_footer.text = String.format(getString(R.string.text_registry_footer, "3"))
                    cly_container_registry_step_1.visibility = View.GONE
                    cly_container_registry_step_2.visibility = View.GONE
                    cly_container_registry_step_3.visibility = View.VISIBLE
                } else {
                    showDialog(getString(R.string.text_edits_no_empty))
                }


            } else {

                val isCorrectPassword = et_registry_confirm_password.text.toString()
                    .equals(et_registry_password.text.toString()) && !et_registry_confirm_password.text.isEmpty() && !et_registry_password.text.isEmpty()

                if (isCorrectPassword) {
                    val signUpRequest = SignUpRequest(
                        et_registry_id_student.text.toString(),
                        et_registry_names.text.toString(),
                        et_registry_confirm_password.text.toString(),
                        et_registry_first_last_name.text.toString(),
                        et_registry_second_last_name.text.toString(),
                        sp_registry_academic_program.selectedItem.toString(),
                        et_registry_matters_owed.text.toString().toInt(),
                        et_registry_matters_owed_name.text.toString(),
                        if (rb_registry_student_scholarship_yes.isChecked) true else if (rb_registry_student_scholarship_no.isChecked) false else false,
                        rb_registry_student_scholarship_asp.isChecked,
                        et_registry_init_average.text.toString().toFloat(),
                        sp_registry_academic_student.selectedItem.toString(),
                        sp_registry_turn.selectedItem.toString()
                    )
                    viewModel.signUp(signUpRequest)
                } else {
                    showDialog(getString(R.string.text_pass_no_equals))
                }
            }
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (cly_container_registry_step_1.visibility == View.VISIBLE) {
                    requireActivity().startActivity(Intent(context, SiginInActivity::class.java))
                } else if (cly_container_registry_step_2.visibility == View.VISIBLE) {
                    mIsNextScreen = false
                    tv_registry_footer.text = String.format(getString(R.string.text_registry_footer, "1"))
                    cly_container_registry_step_2.visibility = View.GONE
                    cly_container_registry_step_1.visibility = View.VISIBLE
                } else if (cly_container_registry_step_3.visibility == View.VISIBLE) {
                    mIsFinalScreen = false
                    tv_registry_footer.text = String.format(getString(R.string.text_registry_footer, "2"))
                    cly_container_registry_step_3.visibility = View.GONE
                    cly_container_registry_step_2.visibility = View.VISIBLE
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)


    }


}