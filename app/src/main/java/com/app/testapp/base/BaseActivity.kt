package com.app.testapp.base

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.app.testapp.R
import com.app.testapp.utils.MyProgressDialog

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        val TAG = BaseActivity::class.java.simpleName
    }

    fun initToolBarTitle(title: String) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            val tvTitle = toolbar.findViewById<TextView>(R.id.tvTitle)
            tvTitle.text = title
            tvTitle.visibility = View.VISIBLE
        } else {
            Log.e(TAG, "Toolbar not found in layout")
        }
    }

    private var progressDialog: MyProgressDialog? = null

    open fun dismissProgressDialog() {
        hideLoading()
    }

    open fun showProgressDialog() {
        showLoading()
    }

    fun hideLoading() {
        if (!isFinishing) {
            if (progressDialog != null && progressDialog?.isShowing == true) {
                progressDialog?.dismiss()
            }
        }
    }

    fun showLoading() {
        if (!isFinishing) {
            if (progressDialog != null && progressDialog?.isShowing == true) {
                progressDialog?.dismiss()
            }
            progressDialog = MyProgressDialog(this)
            progressDialog!!.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (progressDialog != null && progressDialog?.isShowing == true) {
            progressDialog?.dismiss()
        }
    }

    open fun onSystemBackClickEvent() {
        finish()
    }

    fun navigateToActivity(intent: Intent) {
        startActivity(intent)
    }
}