package app.zheil.com.quiz.presentation.quiz

import app.zheil.com.quiz.R
import app.zheil.com.quiz.presentation.GlobalRouter
import kotlinx.android.synthetic.main.activity_main.*
import app.zheil.com.quiz.presentation.base.baseQuiz.BaseQuiz
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bigkoo.svprogresshud.SVProgressHUD
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class QuizGameActivity : BaseQuiz(), QuizGameView {

    @InjectPresenter
    lateinit var mPresenter: QuizGamePresenter

    private val mRouter = GlobalRouter(this)

    private lateinit var mLoadingBar: SVProgressHUD

    override fun setLayoutView(): Int = R.layout.activity_main


    override fun onChildCreate() {
        init()
    }

    private fun init() {
        mPresenter.initPresenterStart(this)
        mLoadingBar = SVProgressHUD(this)
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
            mRouter.showRulesActivity()
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

    override fun finishVictorina() {
        mRouter.showFinishActivity()
    }

    override fun visibleWorkPlace() {
        visibleViews(workPlace)
    }

    override fun hideWorkPlace() {
        hideViews(workPlace)
    }

}
