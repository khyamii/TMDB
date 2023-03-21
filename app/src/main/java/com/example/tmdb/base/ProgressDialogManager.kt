package com.example.tmdb.base

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.example.tmdb.R

class ProgressDialogManager {
    private var progressDialog: AlertDialog? = null
    fun showProgressDialog(context: Context) {
        val builder = AlertDialog.Builder(context)

        val dialogView = View.inflate(context, R.layout.dialog_progress, null)
        builder.setView(dialogView)
        builder.setCancelable(true)
        progressDialog = builder.create()
        progressDialog?.apply {
            show()
        }
    }

    fun hideProgressDialog() {
        progressDialog?.apply {
            if (isShowing)
                dismiss()
        }
    }


}

