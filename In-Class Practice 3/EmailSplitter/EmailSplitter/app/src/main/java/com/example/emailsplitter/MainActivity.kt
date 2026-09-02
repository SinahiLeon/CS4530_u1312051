package com.example.emailsplitter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val inputFragment = InputFragment()

            val fTrans = supportFragmentManager.beginTransaction()
            fTrans.replace(
                R.id.fl_frag_container,
                inputFragment,
                "InputFrag_tag"
            )
            fTrans.commit()
        }
    }
}