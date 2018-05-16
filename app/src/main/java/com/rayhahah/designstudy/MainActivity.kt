package com.rayhahah.designstudy

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), View.OnClickListener {


    override fun obtainToolbarMenu(): Int {
        return 0
    }

    override fun initView(savedInstanceState: Bundle?) {
        doClick(btn_toolbar, btn_appbar, btn_draw)
    }


    override fun getContentView(): Int = R.layout.activity_main

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_toolbar -> {
                startActivity<ToolbarActivity>()
            }
            R.id.btn_appbar -> {
                startActivity<AppBarActivity>()
            }
            R.id.btn_draw -> {
                startActivity<DrawerActivity>()
            }
            else -> {
                toast("Nothing")
            }
        }


    }
}
