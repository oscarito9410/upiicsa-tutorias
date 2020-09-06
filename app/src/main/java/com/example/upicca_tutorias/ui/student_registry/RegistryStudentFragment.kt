package com.example.upicca_tutorias.ui.student_registry

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import com.example.upicca_tutorias.R
import kotlinx.android.synthetic.main.registry_student_fragment.*

class RegistryStudentFragment : Fragment() {

    companion object {
        fun newInstance() =
            RegistryStudentFragment()
    }

    private lateinit var viewModel: RegistryStudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registry_student_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegistryStudentViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_registry_footer.setText(String.format(getString(R.string.text_registry_footer,"1")))

        iv_registry_footer.setOnClickListener {
            tv_registry_footer.setText(String.format(getString(R.string.text_registry_footer,"2")))
            cly_container_registry_step_1.visibility = View.GONE
            cly_container_registry_step_2.visibility = View.VISIBLE
        }


    }


}