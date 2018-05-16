package com.rayhahah.designstudy

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_drawer.*

class DrawerActivity : BaseActivity(), DrawerLayout.DrawerListener {
    val mActionBarDrawerToggle by lazy {
        ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.open_drawer, R.string.close_drawer)
    }

    override fun onDrawerStateChanged(newState: Int) {
        when (newState) {
            DrawerLayout.STATE_DRAGGING -> {
                "STATE_DRAGGING".log()
            }
            DrawerLayout.STATE_IDLE -> {
                "STATE_IDLE".log()
            }
            DrawerLayout.STATE_SETTLING -> {
                "STATE_SETTLING".log()
            }
            else -> {
                "UN_KNOWN".log()
            }
        }


    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerOpened(drawerView: View) {
    }


    override fun obtainToolbarMenu(): Int {
        return 0
    }

    override fun getContentView(): Int = R.layout.activity_drawer

    override fun initView(savedInstanceState: Bundle?) {
        val array = theme.obtainStyledAttributes(intArrayOf(android.R.attr.colorPrimary,
                android.R.attr.colorPrimaryDark, android.R.attr.colorAccent))
        val colorPrimary = array.getColor(0, 0xC01E2F)
        val colorPrimaryDark = array.getColor(1, 0xA82828)
        val colorAccent = array.getColor(2, 0xFF4081)
        array.recycle()
        StatusBarUtil.setColorForDrawerLayout(this, mDrawer, colorPrimary, 0)
        toolbar.title = "Hello Drawer"
        setSupportActionBar(toolbar)

        val layoutParams = mNavigationView.layoutParams
        layoutParams.width = (Util.getScreenWidth(this) * 1.0f * 0.7f).toInt()
        mNavigationView.layoutParams = layoutParams
        mNavigationView.itemTextColor = null
        mNavigationView.itemIconTintList = null

        //show back button and make it enabled
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
//        mActionBarDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);//channge the icon,改变图标
//        show the default icon and sync the DrawerToggle state,如果你想改变图标的话，这句话要去掉。这个会使用默认的三杠图标
        mActionBarDrawerToggle.syncState()
        mDrawer.addDrawerListener(this)
        mDrawer.addDrawerListener(mActionBarDrawerToggle)


    }
}
