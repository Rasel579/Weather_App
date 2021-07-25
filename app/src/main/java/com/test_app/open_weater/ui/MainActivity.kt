package com.test_app.open_weater.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test_app.open_weater.R
import com.test_app.open_weater.ui.main.MainFragment
import com.test_app.open_weater.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}