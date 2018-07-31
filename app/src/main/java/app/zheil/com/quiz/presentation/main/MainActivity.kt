package app.zheil.com.quiz.presentation.main

import app.zheil.com.quiz.*
import app.zheil.com.quiz.di.activity.DaggerActivityComponent
import app.zheil.com.quiz.presentation.base.BaseActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject
import com.michaelflisar.changelog.ChangelogBuilder



class MainActivity: BaseActivity(), MainView {

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    @Inject
    lateinit var mRouter: GlobalRouter

    override fun setLayoutView(): Int = R.layout.activity_menu

    override fun onChildCreate() {
        DaggerActivityComponent.create().inject(this)
        mPresenter.initView()
    }

    override fun initView() {
        checkAnimationStatus.isChecked = MemoryApp.ANIMATION.ANIMATION_STATUS
        initListeners()
        initTypeFont()
        initVersion()
    }

    private fun initVersion() {
        tvVersion.text = BuildConfig.VERSION_NAME
    }

    private fun initListeners() {
        btnStart.click {
            mPresenter.showQuizActivity()
        }

        btnInfoApp.click {
            mPresenter.showAboutApp()
        }

        checkAnimationStatus.setOnCheckedChangeListener { buttonView, isChecked ->
            MemoryApp.ANIMATION.ANIMATION_STATUS = isChecked
        }
    }

    private fun initTypeFont() {
        setFontViews(tvInfoQuiz, tvVersion)
    }

    override fun showAboutApp() {
        val builder = ChangelogBuilder()
                .buildAndShowDialog(this, false)
    }

    override fun showQuizActivity() {
        mRouter.showQuizActivity(this)
    }

}