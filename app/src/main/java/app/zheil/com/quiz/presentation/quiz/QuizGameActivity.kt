package app.zheil.com.quiz.presentation.quiz

import app.zheil.com.quiz.R
import app.zheil.com.quiz.di.activity.DaggerActivityComponent
import app.zheil.com.quiz.GlobalRouter
import kotlinx.android.synthetic.main.activity_main.*
import app.zheil.com.quiz.presentation.base.baseQuiz.BaseQuiz
import com.arellomobile.mvp.presenter.InjectPresenter
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
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

    private fun init() {
        DaggerActivityComponent.create().inject(this)
        mPresenter.initPresenterStart(this)
        initListeners()
        initFonts()
    }

    private fun initFonts() {
        setFontViews(tvQuestion, tvProgress, tvTitleVictory)
    }

    private fun initListeners() {
        btnNextQuestion.setOnClickListener {
            blockBtn()
            mPresenter.nextQuestion()
        }

        btnPrevQuestion.setOnClickListener {
            mPresenter.prevQuestion()
        }

        btnShowRules.setOnClickListener {
            mRouter.showRulesActivity(this)
        }
    }

    override fun onBackPressed() {
        mPresenter.prevQuestion()
    }

    private fun blockBtn() {
        blockView(btnNextQuestion, "...")
        doAsync {
            Thread.sleep(1000)
            uiThread {
                unblockView(btnNextQuestion, "Следующий вопрос")
            }
        }
    }

    override fun setCurrentNumberQuestion(value: Int, maxQuestion: Int) {
        tvProgress.text = "$value из $maxQuestion"
    }

    override fun nextQuestion(question: String) {
        tvQuestion.text = question
        YoYo.with(Techniques.StandUp)
                .duration(700)
                .playOn(findViewById(R.id.cardView2))
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

}
