package app.zheil.com.quiz.presentation.main

import android.graphics.drawable.BitmapDrawable
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import app.zheil.com.quiz.*
import app.zheil.com.quiz.di.activity.DaggerActivityComponent
import app.zheil.com.quiz.presentation.base.BaseActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.popwind.view.*
import javax.inject.Inject
import com.michaelflisar.changelog.ChangelogBuilder


class MainActivity: BaseActivity(), MainView {

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    @Inject
    lateinit var mRouter: GlobalRouter

    private lateinit var popupWindow: PopupWindow

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
            showPopWindow()
        }

        btnInfoApp.click {
            mPresenter.showAboutApp()
        }

        checkAnimationStatus.setOnCheckedChangeListener { buttonView, isChecked ->
            MemoryApp.ANIMATION.ANIMATION_STATUS = isChecked
        }
    }


    private fun initTypeFont() {
        setFontViews(tvInfoQuiz, tvVersion, tvTitleQuiz)
    }

    override fun showAboutApp() {
        ChangelogBuilder().buildAndShowDialog(this, false)
    }

    override fun showQuizActivity() {
        mRouter.showQuizActivity(this)
    }

    private fun showPopWindow() {
        activitiesPopWindView.setBackgroundColor(ContextCompat.getColor(this, R.color.background_pop_wind))
        activitiesPopWindView.visible()
        val view = layoutInflater.inflate(R.layout.popwind, null, false)
        popupWindow = PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popupWindow.setBackgroundDrawable(BitmapDrawable())
        popupWindow.setOutsideTouchable(true)
        popupWindow.setOnDismissListener(PopupWindow.OnDismissListener {  activitiesPopWindView.setVisibility(View.GONE) })
        popupWindow.showAtLocation(activitiesPopWindView, Gravity.BOTTOM, 0, 0)
        initDetailsPopupWindow(view)
    }

    private fun initDetailsPopupWindow(view: View?) {
        view?.setOnClickListener {
            activitiesPopWindView!!.hide()
            popupWindow.dismiss()
        }

        view?.btnStartGame?.click {
            mPresenter.showQuizActivity()
        }
    }

}