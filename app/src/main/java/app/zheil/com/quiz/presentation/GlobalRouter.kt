package app.zheil.com.quiz.presentation

import android.content.Context
import android.content.Intent
import app.zheil.com.quiz.presentation.finalQuiz.FinalQuizActivity
import app.zheil.com.quiz.presentation.rules.RulesActivity
import app.zheil.com.quiz.presentation.quiz.QuizGameActivity


class GlobalRouter(private val mContext: Context) {

    fun showFinishActivity() {
       mContext.startActivity(Intent(mContext, FinalQuizActivity::class.java))
    }

    fun showVictorinaActivity() {
        mContext.startActivity(Intent(mContext, QuizGameActivity::class.java))
    }

    fun showRulesActivity() {
        mContext.startActivity(Intent(mContext, RulesActivity::class.java))
    }
}