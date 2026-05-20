package com.app.testapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.view.Window
import java.lang.Exception
import androidx.core.graphics.drawable.toDrawable
import com.app.testapp.R

class MyProgressDialog(context: Context?) : Dialog(
    context!!, R.style.AppTheme_ProgressBarDialog
) {
    init {
        init()
    }

    private fun init() {
        try {
            if (super.isShowing()) {
                super.dismiss()
            }
            super.requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            val mProgressView = layoutInflater.inflate(R.layout.dialog_progressbar, null)
            super.addContentView(
                mProgressView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            super.setCancelable(false)
            super.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun dismiss() {
        try {
            if (this.isShowing) {
                super.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}