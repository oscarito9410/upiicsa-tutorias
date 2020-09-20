package com.aboolean.upicca_tutorias.ui.signin.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aboolean.upicca_tutorias.R
import com.aboolean.upicca_tutorias.base.BaseFragment
import com.aboolean.upicca_tutorias.ui.home.HomeActivity
import com.aboolean.upicca_tutorias.ui.model.LoginViewState
import com.aboolean.upicca_tutorias.utils.hideLoadingSpinner
import com.aboolean.upicca_tutorias.utils.showLoadingSpinner
import com.aboolean.upicca_tutorias.utils.toast
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModel()

    private lateinit var mNavController: NavController
    override fun getLayoutView(): Int = R.layout.login_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = Navigation.findNavController(view)
        initView()
        attachObservers()
    }

    override fun initView() {
        tv_login_new_registry.setOnClickListener {
            mNavController.navigate(R.id.action_loginFragment_to_registryStudentFragment)
        }

        btn_login_init.setOnClickListener {
            viewModel.signIn(etLoginUserName.text.toString(),
                etLoginPass.text.toString())
        }
    }

    override fun attachObservers() {
        viewModel.apply {
            loginViewState.observe(viewLifecycleOwner, Observer {
                handleLoginViewState(it)
            })
        }
    }

    private fun handleLoginViewState(viewState: LoginViewState) {
        when (viewState) {
            is LoginViewState.OnLoading -> {
                requireActivity().showLoadingSpinner()
            }
            is LoginViewState.OnFailedSignIn -> {
                requireActivity().hideLoadingSpinner()
                requireContext().toast("an error here")
            }
            is LoginViewState.OnSuccessSignIn -> {
                requireActivity().hideLoadingSpinner()
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObservers()
    }
}