package app.zheil.com.quiz.presentation.finalQuiz

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.zheil.com.quiz.R
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
        val typeface = Typeface.createFromAsset(assets, "fonts/krabuler.ttf")
        tvVictorinaFinish.typeface = typeface
    }

    private fun setListeners() {
        btnFinish.setOnClickListener {
            sendStickyEvent(FinishedQuizEvent())
            finish()
        }
    }
}