package com.rayhahah.designstudy

import android.graphics.Color
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_app_bar.*

class AppBarActivity : BaseActivity() {

    override fun obtainToolbarMenu(): Int {
        return R.menu.menu_toolbar
    }

    override fun initView(savedInstanceState: Bundle?) {
        initToolbar(toolbar, "This is App Bar", navigationIcon = R.drawable.ic_svg_arrow_left_color_primary_24)
        /**
         * layout_scrollFlags:
         * scroll:设为Scroll会跟随滑动事件一起华东
         * scroll|enterAlways：当ScrollView往下滚动时，该View会直接往下滚动。而不用考虑ScrollView是否在滚动。
         * scroll|enterAlways|enterAlwaysCollapsed : 拉到最顶上就会全部显示，平时拉一下只会显示状态栏
         * scroll|exitUntilCollapsed : 和上面的区别就是状态栏会一直显示，最顶上才显示全部内容
         */

        mColToolbar.setCollapsedTitleTextColor(Color.WHITE)
        mColToolbar.setExpandedTitleColor(Color.WHITE)



    }

    override fun getContentView(): Int = R.layout.activity_app_bar

}
