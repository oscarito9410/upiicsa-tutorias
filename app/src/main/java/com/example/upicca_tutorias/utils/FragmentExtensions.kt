package com.example.upicca_tutorias.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


/**
 * Default short toast
 */
fun Context.toast(any: Any, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, any.toString(), duration).show()
}

/**
 * Default short toast
 */
fun Context.toast(@StringRes resString: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(resString), duration)
}

/**
 * Long duration toast
 */
fun Context.longToast(any: Any) {
    toast(any.toString(), Toast.LENGTH_LONG)
}

fun FragmentActivity.showDialogFragmentOnce(dialogFragment: DialogFragment) {
    supportFragmentManager.findFragmentByTag(dialogFragment::class.java.name).let {
        if (it == null) {
            dialogFragment.show(supportFragmentManager, dialogFragment::class.java.name)
        }
    }
}

fun Fragment.showDialogFragmentOnce(dialogFragment: DialogFragment) {
    activity?.let {
        it.showDialogFragmentOnce(dialogFragment)
    }
}

fun FragmentActivity.showLoadingSpinner() {
    val loadingDialogFragment =
        LoadingDialogFragment()
    supportFragmentManager.beginTransaction().add(
        loadingDialogFragment,
        LoadingDialogFragment::class.java.name
    ).commitAllowingStateLoss()
}

fun FragmentActivity.hideLoadingSpinner() {
    supportFragmentManager.findFragmentByTag(LoadingDialogFragment::class.java.name)?.let {
        supportFragmentManager.beginTransaction().remove(it).commitAllowingStateLoss()
    }
}