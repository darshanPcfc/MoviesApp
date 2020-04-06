package com.example.moviesapp.ui.moduleone.activities

import android.os.Bundle
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.base.BaseActivity
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.ui.moduleone.activities.viewmodel.IMainActivityNavigator
import com.example.moviesapp.ui.moduleone.activities.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Darshan Patel
 * Usage: launcher activity
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(),
    IMainActivityNavigator {


    override val viewModel: MainActivityViewModel by viewModel()
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = this.viewDataBinding!!
        viewModel.setNavigator(this)

    }
}
