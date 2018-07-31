package app.zheil.com.quiz.presentation.main

import app.zheil.com.quiz.*
import app.zheil.com.quiz.di.activity.DaggerActivityComponent
import app.zheil.com.quiz.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject

class MenuActivity: BaseActivity() {

    @Inject
    lateinit var mRouter: GlobalRouter

    override fun setLayoutView(): Int = R.layout.activity_menu

    override fun onChildCreate() {
        DaggerActivityComponent.create().inject(this)
        initView()
        initListeners()
        initTypeFont()
        initVersion()
    }

    private fun initView() {
        checkAnimationStatus.isChecked = MemoryApp.ANIMATION.ANIMATION_STATUS
    }

    private fun initVersion() {
        tvVersion.text = BuildConfig.VERSION_NAME
    }

    private fun initListeners() {
        btnStart.click {
            mRouter.showQuizActivity(this)
        }

        checkAnimationStatus.setOnCheckedChangeListener { buttonView, isChecked ->
            MemoryApp.ANIMATION.ANIMATION_STATUS = isChecked
        }
    }

    private fun initTypeFont() {
        setFontViews(tvInfoQuiz, tvVersion)
    }
}