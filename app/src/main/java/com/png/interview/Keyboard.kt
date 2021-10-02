package com.png.interview

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.FragmentActivity

class Keyboard {

    companion object {
        fun dismiss(activity: FragmentActivity?) {
            if (activity != null) {
                val view = activity.window.decorView.findViewById<View>(android.R.id.content)
                dismissKeyboard(view)
            }
        }

        fun dismiss(view: View?) {
            dismissKeyboard(view)
        }

        private fun dismissKeyboard(view: View?) {
            if (view != null) {
                val context = view.context

                if (view is EditText) {
                    val imm =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                } else if (view is ViewGroup) {
                    val children = view.childCount

                    for (i in 0 until children) {
                        val child = view.getChildAt(i)
                        dismissKeyboard(child)
                    }
                }
            }
        }
    }

}