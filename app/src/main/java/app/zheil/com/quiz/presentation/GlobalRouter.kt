package app.zheil.com.quiz.presentation

import android.content.Context
import android.content.Intent
import app.zheil.com.quiz.presentation.finalQuiz.FinalQuizActivity
import app.zheil.com.quiz.presentation.rules.RulesActivity
import app.zheil.com.quiz.presentation.quiz.QuizGameActivity


class GlobalRouter {

    fun showFinishActivity(context: Context) {
        context.startActivity(Intent(context, FinalQuizActivity::class.java))
    }

    fun showVictorinaActivity(context: Context) {
        context.startActivity(Intent(context, QuizGameActivity::class.java))
    }

    fun showRulesActivity(context: Context) {
        context.startActivity(Intent(context, RulesActivity::class.java))
    }
}