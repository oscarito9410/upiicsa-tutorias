package com.example.upicca_tutorias.ui.student_registry

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.base.BaseFragment
import kotlinx.android.synthetic.main.registry_student_fragment.*

class RegistryStudentFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            RegistryStudentFragment()
    }

    private lateinit var viewModel: RegistryStudentViewModel
    private var mIsNextScreen:Boolean = false
    private lateinit var mNavController: NavController

    override fun getLayoutView(): Int = R.layout.registry_student_fragment

    override fun initView() {

    }

    override fun attachObservers() {

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObservers()
        viewModel = ViewModelProviders.of(this).get(RegistryStudentViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = Navigation.findNavController(view)

        tv_registry_footer.setText(String.format(getString(R.string.text_registry_footer,"1")))

        iv_registry_footer.setOnClickListener {

            if(!mIsNextScreen){
                mIsNextScreen = true
                tv_registry_footer.setText(String.format(getString(R.string.text_registry_footer,"2")))
                cly_container_registry_step_1.visibility = View.GONE
                cly_container_registry_step_2.visibility = View.VISIBLE
            }else{
                mNavController.navigate(R.id.teachersRegistryFragment)
            }

        }


    }


}