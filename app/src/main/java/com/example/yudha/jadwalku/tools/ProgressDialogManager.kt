package com.example.yudha.jadwalku.tools

import android.app.Activity
import android.app.ProgressDialog

/**
 * Created by yudha on 3/4/2018.
 */
class ProgressDialogManager(act : Activity) : ProgressDialog(act) {

    private val MESSAGE = "Memuat Data..."

    override fun show() {
        setProgressStyle(ProgressDialog.STYLE_SPINNER)
        setCancelable(false)
        setMessage(MESSAGE)
        super.show()
    }

}