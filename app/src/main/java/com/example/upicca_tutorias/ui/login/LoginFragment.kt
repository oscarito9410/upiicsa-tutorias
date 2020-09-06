package com.example.upicca_tutorias.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.base.BaseFragment
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModel()

    companion object {
        fun newInstance() = LoginFragment()
    }


    private lateinit var mNavController: NavController
    override fun getLayoutView(): Int = R.layout.login_fragment


    override fun initView() {
    }

    override fun attachObservers() {
        viewModel.apply {  }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObservers()

       // viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = Navigation.findNavController(view)

        tv_login_new_registry.setOnClickListener {
            mNavController.navigate(R.id.registryStudentFragment)
        }

        btn_login_init.setOnClickListener {
            mNavController.navigate(R.id.teachersRegistryFragment)
        }

    }




}