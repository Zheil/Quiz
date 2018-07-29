package app.zheil.com.quiz.presentation.main

import app.zheil.com.quiz.BuildConfig
import app.zheil.com.quiz.R
import app.zheil.com.quiz.di.activity.DaggerActivityComponent
import app.zheil.com.quiz.GlobalRouter
import app.zheil.com.quiz.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject

class MenuActivity: BaseActivity() {

    @Inject
    lateinit var mRouter: GlobalRouter

    override fun setLayoutView(): Int = R.layout.activity_menu

    override fun onChildCreate() {
        DaggerActivityComponent.create().inject(this)
        initListeners()
        initTypeFont()
        initVersion()
    }

    private fun initVersion() {
        tvVersion.text = BuildConfig.VERSION_NAME
    }

    private fun initListeners() {
        btnStart.setOnClickListener {
            mRouter.showQuizActivity(this)
        }
    }

    private fun initTypeFont() {
        setFontViews(tvInfoQuiz, tvVersion)
    }
}