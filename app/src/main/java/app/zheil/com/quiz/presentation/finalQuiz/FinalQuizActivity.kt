package app.zheil.com.quiz.presentation.finalQuiz

import app.zheil.com.quiz.R
import app.zheil.com.quiz.click
import app.zheil.com.quiz.events.FinishedQuizEvent
import app.zheil.com.quiz.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_finish.*

class FinalQuizActivity: BaseActivity() {

    override fun setLayoutView(): Int = R.layout.activity_finish

    override fun onChildCreate() {
        setListeners()
        setFonts()
    }

    private fun setFonts() {
        setFontViews(tvQuizFinish, btnFinish)
    }

    private fun setListeners() {
        btnFinish.click {
            sendStickyEvent(FinishedQuizEvent())
            finish()
        }
    }
}