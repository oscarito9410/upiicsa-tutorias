package com.example.upicca_tutorias.ui.signin.signup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.base.BaseFragment
import com.example.upicca_tutorias.data.model.SignUpRequest
import com.example.upicca_tutorias.ui.home.HomeActivity
import com.example.upicca_tutorias.ui.model.LoginViewState
import com.example.upicca_tutorias.ui.model.RegistryViewState
import com.example.upicca_tutorias.ui.signin.login.LoginViewModel
import com.example.upicca_tutorias.utils.hideLoadingSpinner
import com.example.upicca_tutorias.utils.showLoadingSpinner
import com.example.upicca_tutorias.utils.toast
import com.squareup.moshi.Json
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.registry_student_fragment.*
import org.koin.android.ext.koin.androidContext
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
                showDialog("Error de conexión")
            }
            is RegistryViewState.OnSuccessSignUp -> {
                requireActivity().hideLoadingSpinner()
                mNavController.navigate(R.id.teachersRegistryFragment)
                viewModel.saveStringPreferences(et_registry_id_student.text.toString())
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = Navigation.findNavController(view)
        tv_registry_footer.text = String.format(getString(R.string.text_registry_footer, "1"))
        iv_registry_footer.setOnClickListener {
            if (!mIsNextScreen) {
                mIsNextScreen = true
                tv_registry_footer.text =
                    String.format(getString(R.string.text_registry_footer, "2"))
                cly_container_registry_step_1.visibility = View.GONE
                cly_container_registry_step_2.visibility = View.VISIBLE
            } else if (!mIsFinalScreen) {
                mIsFinalScreen = true
                tv_registry_footer.text =
                    String.format(getString(R.string.text_registry_footer, "3"))
                cly_container_registry_step_1.visibility = View.GONE
                cly_container_registry_step_2.visibility = View.GONE
                cly_container_registry_step_3.visibility = View.VISIBLE

            } else {

                val isCorrectPassword = et_registry_confirm_password.text.toString()
                    .equals(et_registry_password.text.toString())

                if (isCorrectPassword) {
                    val signUpRequest = SignUpRequest(
                        et_registry_id_student.text.toString(),
                        et_registry_names.text.toString(),
                        et_registry_confirm_password.text.toString(),
                        et_registry_first_last_name.text.toString(),
                        et_registry_second_last_name.text.toString(),
                        sp_registry_academic_program.selectedItem.toString(),
                        et_registry_matters_owed.text.toString().toInt(),
                        if (rb_registry_student_scholarship_yes.isActivated) true else if (rb_registry_student_scholarship_no.isActivated) false else false,
                        et_registry_init_average.text.toString().toFloat(),
                        sp_registry_academic_student.selectedItem.toString(),
                        sp_registry_turn.selectedItem.toString()
                    )
                    viewModel.signUp(signUpRequest)
                } else {
                    viewModel.saveStringPreferences(et_registry_id_student.text.toString())

                val sharedPreferences  = context?.getSharedPreferences(
                    getString(R.string.prefs_name_tutorias),
                    Context.MODE_PRIVATE
                )
                    showDialog(sharedPreferences?.getString("idValue","").toString())
                    //showDialog(getString(R.string.text_pass_no_equals))
                }

            }
        }
    }
}