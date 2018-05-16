package com.rayhahah.designstudy

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_toolbar.*

class ToolbarActivity : BaseActivity(), Toolbar.OnMenuItemClickListener {
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return true

    }

    override fun obtainToolbarMenu(): Int {
        return R.menu.menu_toolbar
    }



    override fun initView(savedInstanceState: Bundle?) {
        iv_cover.setImageResource(R.mipmap.ic_logo)
//        initToolbar(toolbar,"Toolbar Activity",R.drawable.ic_logo,R.drawable.ic_back)
        // Logo
        tl.setLogo(R.drawable.ic_svg_logo_color_accent_24)

        // 主标题
        tl.setTitle("Title")

        //设置toolbar
        setSupportActionBar(tl)

        //左边的小箭头（注意需要在setSupportActionBar(toolbar)之后才有效果）
        tl.setNavigationIcon(R.drawable.ic_svg_arrow_left_color_primary_24)

        //菜单点击事件（注意需要在setSupportActionBar(toolbar)之后才有效果）
        tl.setOnMenuItemClickListener(this);

        tl.setNavigationOnClickListener {
            finish()

        }

    }

    override fun getContentView(): Int = R.layout.activity_toolbar
}
