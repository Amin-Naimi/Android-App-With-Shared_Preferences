package com.example.emptyproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var login_btn: Button
    lateinit var name: EditText
    lateinit var password: EditText
    lateinit var remember_check: CheckBox
    lateinit var btnClear: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_btn = findViewById(R.id.login)
        name = findViewById(R.id.text_name)
        password = findViewById(R.id.text_password)
        btnClear = findViewById(R.id.clear_text)
        remember_check = findViewById(R.id.remeber_me)



        val sharedPreference: SharedPreference = SharedPreference(this)
        val o_name = sharedPreference.getValueString("name")
        val o_pass = sharedPreference.getValueString("password")

        if (o_name != null && o_pass != null) {
            val intent = Intent(this, login::class.java)
            intent.putExtra("name", o_name)
            startActivity(intent)
        }

        login_btn.setOnClickListener {
            if (!name.text.isEmpty() && !password.text.isEmpty()) {

                if (remember_check.isChecked) {
                    sharedPreference.save("name", name.text.toString())
                    sharedPreference.save("password", password.text.toString())
                }

                val intent = Intent(this, login::class.java)
                intent.putExtra("name", name.text.toString())
                startActivity(intent)
            }
        }

        btnClear.setOnClickListener {
            sharedPreference.clearSharedPreference()
            Toast.makeText(this@MainActivity,"Data Cleared from shared preferences",Toast.LENGTH_SHORT).show()
        }




    }


}