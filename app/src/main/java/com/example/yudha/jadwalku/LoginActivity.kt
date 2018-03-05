package com.example.yudha.jadwalku

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.yudha.jadwalku.api.ApiService
import com.example.yudha.jadwalku.model.User
import com.example.yudha.jadwalku.tools.ProgressDialogManager

import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    var progressDialog : ProgressDialogManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressDialog = ProgressDialogManager(this)

        if(getSharedPreferences("JADWALKU", Context.MODE_PRIVATE).getBoolean("login", false)){
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        btnLogin.setOnClickListener({
            doLogin()
        })

        btnSignup.setOnClickListener({
            val i = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(i)
        })
    }

    private fun doLogin() {
        if(edtEmail.text.toString() == "" || edtPassword.text.toString() == ""){
            showAlert("Kolom tidak boleh kosong!!!")
        }else{
            login(edtEmail.text.toString(), edtPassword.text.toString())
        }
    }

    private fun login(email : String, password : String) {
            progressDialog?.show()
            val retrofit = Retrofit.Builder()
                    .baseUrl(getString(R.string.BASE_URL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val api = retrofit.create(ApiService::class.java)
            val call = api.login(email, password)
            call.enqueue(object : Callback<User>{
                override fun onFailure(call: Call<User>?, t: Throwable?) {
                    progressDialog?.dismiss()
                    showToast("Terjadi kesalahan pada server")
                    Log.e("ERROR", t.toString())
                }

                override fun onResponse(call: Call<User>?, response: Response<User>?) {
                   if(response!!.body().status){
                       val pref = getSharedPreferences("JADWALKU", Context.MODE_PRIVATE)
                       val editor = pref.edit()
                       editor.putBoolean("login", true)
                       editor.putString("name", response.body().data.name)
                       editor.apply()
                       val i = Intent(this@LoginActivity, MainActivity::class.java)
                       startActivity(i)
                       finish()
                   }else{
                       showToast("Email atau Password salah")
                   }
                    progressDialog?.dismiss()
                }

            })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok, null)
        builder.show()
    }

}
