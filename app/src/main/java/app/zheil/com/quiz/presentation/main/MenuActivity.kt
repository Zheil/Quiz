package app.zheil.com.quiz.presentation.main


import app.zheil.com.quiz.R
import app.zheil.com.quiz.presentation.GlobalRouter
import app.zheil.com.quiz.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity: BaseActivity() {
    private val mRouter = GlobalRouter(this)

    override fun setLayoutView(): Int = R.layout.activity_menu

    override fun onChildCreate() {
        initListeners()
        initTypeFont()
    }

    private fun initListeners() {
        btnStart.setOnClickListener {
            mRouter.showVictorinaActivity()
        }
    }

    private fun initTypeFont() {
        setFontViews(tvVict)
    }

}