package com.example.upicca_tutorias.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.ui.home.adapter.TeacherRegistryAdapter

abstract class BaseFragment : Fragment() {
    //Methods will be used for each fragment
    abstract fun getLayoutView(): Int
    abstract fun initView()
    abstract fun attachObservers()



    protected val teachersAdapter = TeacherRegistryAdapter(mutableListOf())
    protected val teachersLayoutManager by lazy {
        LinearLayoutManager(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected fun showDialog(message: String) {
        val listener = DialogInterface.OnClickListener{dialogInterface, i ->  }
        showDialog(message, listener, false )
    }


    protected fun showDialog(message: String, listener: DialogInterface.OnClickListener, isCompleteAlert:Boolean ) {

        val builder = AlertDialog.Builder(context)
        with(builder)
        {
            setTitle(R.string.app_name)
            setMessage(message)
            if (isCompleteAlert) {
                setPositiveButton(R.string.text_accept, listener)
                setNegativeButton(R.string.text_cancel, { dialogInterface, i -> dialogInterface.dismiss() })

            } else {
                setNeutralButton(
                    R.string.text_accept,
                    { dialogInterface, i -> dialogInterface.dismiss() })
            }
            show()
        }


    }


}