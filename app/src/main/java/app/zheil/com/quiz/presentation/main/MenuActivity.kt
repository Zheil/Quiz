package app.zheil.com.quiz.presentation.main

import app.zheil.com.quiz.*
import app.zheil.com.quiz.di.activity.DaggerActivityComponent
import app.zheil.com.quiz.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject
import com.michaelflisar.changelog.classes.ChangelogFilter
import com.michaelflisar.changelog.ChangelogBuilder



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

    private fun initAbout() {

        val builder = ChangelogBuilder()
                //.withUseBulletList(bulletList) // true if you want to show bullets before each changelog row, false otherwise
                //.withMinVersionToShow(110)     // provide a number and the log will only show changelog rows for versions equal or higher than this number
                //.withFilter(ChangelogFilter(ChangelogFilter.Mode.Exact, "somefilterstring", true)) // this will filter out all tags, that do not have the provided filter attribute
                //.withManagedShowOnStart(true)  // library will take care to show activity/dialog only if the changelog has new infos and will only show this new infos
                //.withRateButton(true) // enable this to show a "rate app" button in the dialog => clicking it will open the play store; the parent activity or target fragment can also implement IChangelogRateHandler to handle the button click
                .buildAndShowDialog(this, false) // second parameter defines, if the dialog has a dark or light theme
    }

    private fun initListeners() {
        btnStart.click {
            mRouter.showQuizActivity(this)
        }

        btnInfoApp.click {
            initAbout()
        }

        checkAnimationStatus.setOnCheckedChangeListener { buttonView, isChecked ->
            MemoryApp.ANIMATION.ANIMATION_STATUS = isChecked
        }
    }

    private fun initTypeFont() {
        setFontViews(tvInfoQuiz, tvVersion)
    }
}