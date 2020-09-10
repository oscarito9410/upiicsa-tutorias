package com.example.upicca_tutorias.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.base.BaseFragment
import com.example.upicca_tutorias.ui.model.LoginViewState
import com.example.upicca_tutorias.utils.hideLoadingSpinner
import com.example.upicca_tutorias.utils.showLoadingSpinner
import com.example.upicca_tutorias.utils.toast
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
            viewModel.signIn(et_login_username.text.toString(), et_login_pass.text.toString())
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
                requireContext().toast("on success")
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObservers()
    }
}