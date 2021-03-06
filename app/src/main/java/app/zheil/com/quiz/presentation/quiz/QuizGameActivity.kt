package app.zheil.com.quiz.presentation.quiz

import app.zheil.com.quiz.Const
import app.zheil.com.quiz.R
import app.zheil.com.quiz.di.activity.DaggerActivityComponent
import app.zheil.com.quiz.GlobalRouter
import app.zheil.com.quiz.click
import app.zheil.com.quiz.events.FinishedQuizEvent
import kotlinx.android.synthetic.main.activity_main.*
import app.zheil.com.quiz.presentation.base.baseQuiz.BaseQuiz
import com.arellomobile.mvp.presenter.InjectPresenter
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


class QuizGameActivity : BaseQuiz(), QuizGameView {

    @InjectPresenter
    lateinit var mPresenter: QuizGamePresenter

    @Inject
    lateinit var mRouter: GlobalRouter

    override fun setLayoutView(): Int = R.layout.activity_main

    override fun onChildCreate() {
        init()
    }

    override fun onPause() {
        super.onPause()
        unregisterEventBus()
    }

    override fun onStart() {
        super.onStart()
        registerEventBus()
    }

    private fun init() {
        DaggerActivityComponent.create().inject(this)
        mPresenter.initPresenterStart(this)
        initListeners()
        initFonts()
    }

    private fun initFonts() {
        setFontViews(tvQuestion, tvProgress, tvTitleQuiz)
    }

    private fun initListeners() {
        btnNextQuestion.click {
            mPresenter.nextQuestion()
        }

        btnPrevQuestion.click {
            mPresenter.prevQuestion()
        }

        btnShowRules.click {
            mRouter.showRulesActivity(this)
        }

        btnBack.click {
            mPresenter.closeCurrentActivity()
        }
    }

    override fun animateBtnClick() {
        YoYo.with(Techniques.Swing)
                .duration(Const.Animation.DURATION_ANIMATION_OF_BUTTON_NEXT_QUESTION)
                .playOn(btnNextQuestion)
    }

    override fun finishAnimation() {

    }

    override fun onBackPressed() {
        mPresenter.prevQuestion()
    }

    override fun blockBtn() {
        blockView(btnNextQuestion, "...")
    }

    override fun unblockBtn() {
        unblockView(btnNextQuestion, getString(R.string.btn_next_question))
    }

    override fun setCurrentNumberQuestion(current: Int, maxQuestion: Int) {
        tvProgress.text = String.format(getString(R.string.welcome_messages), current, maxQuestion)
    }

    override fun nextQuestion(question: String) {
        tvQuestion.text = question
    }

    override fun finishQuiz() {
        mRouter.showFinishActivity(this)
    }

    override fun visibleWorkPlace() {
        visibleViews(workPlace)
    }

    override fun hideWorkPlace() {
        hideViews(workPlace)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun eventFinishedQuiz(event: FinishedQuizEvent) {
        EventBus.getDefault().removeStickyEvent(event)
        finish()
    }

    override fun animateCard() {
        YoYo.with(Techniques.StandUp)
                .duration(Const.Animation.DURATION_ANIMATION_OF_CARD_VIEW)
                .playOn(findViewById(R.id.cardQuestionQuiz))
    }

}
