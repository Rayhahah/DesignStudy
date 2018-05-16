package com.rayhahah.designstudy

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jaeger.library.StatusBarUtil


/**
 * ┌───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│ │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│ ┌┐    ┌┐    ┌┐
 * └───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘ └┘    └┘    └┘
 * ┌──┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐┌───┬───┬───┐┌───┬───┬───┬───┐
 * │~`│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp ││Ins│Hom│PUp││N L│ / │ * │ - │
 * ├──┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤├───┼───┼───┤├───┼───┼───┼───┤
 * │Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ ││Del│End│PDn││ 7 │ 8 │ 9 │   │
 * ├────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤└───┴───┴───┘├───┼───┼───┤ + │
 * │Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │             │ 4 │ 5 │ 6 │   │
 * ├─────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤    ┌───┐    ├───┼───┼───┼───┤
 * │Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │    │ ↑ │    │ 1 │ 2 │ 3 │   │
 * ├────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤┌───┼───┼───┐├───┴───┼───┤ E││
 * │Ctrl│Ray │Alt │         Space         │ Alt│code│fuck│Ctrl││ ← │ ↓ │ → ││   0   │ . │←─┘│
 * └────┴────┴────┴───────────────────────┴────┴────┴────┴────┘└───┴───┴───┘└───────┴───┴───┘
 *
 * @author Rayhahah
 * @blog http://rayhahah.com
 * @time 2018/5/15
 * @tips 这个类是Object的子类
 * @fuction
 */
abstract class BaseActivity : AppCompatActivity() {
    init {
        //设置VectorDrawable兼容支持，否则会闪退
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getContentView()
        if (layoutId != 0) {
            setContentView(layoutId)
        }
        setStatusBar()
        initView(savedInstanceState)
    }


    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun getContentView(): Int

    /**
     * Toolbar的menu资源
     */
    abstract fun obtainToolbarMenu(): Int

    protected fun setStatusBar() {
        val array = theme.obtainStyledAttributes(intArrayOf(android.R.attr.colorPrimary,
                android.R.attr.colorPrimaryDark, android.R.attr.colorAccent))
        val colorPrimary = array.getColor(0, 0xC01E2F)
        val colorPrimaryDark = array.getColor(1, 0xA82828)
        val colorAccent = array.getColor(2, 0xFF4081)
        array.recycle()
        StatusBarUtil.setColor(this,colorPrimaryDark,0)
    }

    protected fun initToolbar(toolbar: Toolbar,
                              titleRes: String = "",
                              @DrawableRes logo: Int = 0,
                              @DrawableRes navigationIcon: Int = 0,
                              menuClick: Toolbar.OnMenuItemClickListener = object : Toolbar.OnMenuItemClickListener {
                                  override fun onMenuItemClick(item: MenuItem?): Boolean {
                                      return false
                                  }
                              }) {
        toolbar.apply {
            title = titleRes
            if (logo != 0) {
                setLogo(logo)
            }
            setSupportActionBar(toolbar)
            if (navigationIcon != 0) {
                setNavigationIcon(navigationIcon)
            }
            setOnMenuItemClickListener(menuClick)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 绑定toobar跟menu
        return if (obtainToolbarMenu() != 0) {
            menuInflater.inflate(obtainToolbarMenu(), menu);
            true
        } else {
            super.onCreateOptionsMenu(menu)
        }
    }

    fun getAttrColor(context: Context, attrRes: Int): Int {
        val typedValue = TypedValue()
        context.getTheme().resolveAttribute(attrRes, typedValue, true)
        return typedValue.data
    }


    /**
     * 为了让Menu图标可以正常显示
     */
    override fun onPrepareOptionsPanel(view: View?, menu: Menu?): Boolean {
        /**
         * View必须加上 ? 否则，kotlin判空检查会崩溃
         */
        //让在overflow中的menuitem的icon显示
        setIconEnable(menu, true)
        return super.onPrepareOptionsPanel(view, menu)
    }

    /**
     * 利用反射机制调用MenuBuilder中的setOptionIconsVisable（），
     * 如果是集成自AppCompatActivity则不行,需要在onPreareOptionPanel（）中调用该方法
     * @param menu   该menu实质为MenuBuilder，该类实现了Menu接口
     * @param enable enable为true时，菜单添加图标有效，enable为false时无效。因为4.0系统之后默认无效
     */
    private fun setIconEnable(menu: Menu?, enable: Boolean) {
        if (menu != null) {
            try {
                val clazz = menu.javaClass
                if (clazz.simpleName == "MenuBuilder") {
                    val m = clazz.getDeclaredMethod("setOptionalIconsVisible", java.lang.Boolean.TYPE)
                    m.isAccessible = true

                    //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
                    m.invoke(menu, enable)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}