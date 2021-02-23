package com.hikingman.mvp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.hikingman.mvp.v4.AutoPresenter
import com.hikingman.mvp.v4.BaseActivity
import com.hikingman.mvp.x.BaseMVPActivity
import com.hikingman.mvp.x.IUserContact
import com.hikingman.mvp.x.MVP

class MainActivity : BaseMVPActivity<IUserContact.IUserPresenter>(), IUserContact.IUserView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.loadUser()
    }

    fun login(v: View) {
        presenter.loadUser()
    }

    override fun showUser(name: String?) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }
}