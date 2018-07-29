package app.zheil.com.quiz

import android.content.Context
import app.zheil.com.quiz.presentation.finalQuiz.FinalQuizActivity
import app.zheil.com.quiz.presentation.rules.RulesActivity
import app.zheil.com.quiz.presentation.quiz.QuizGameActivity
import org.jetbrains.anko.startActivity


class GlobalRouter {

    fun showFinishActivity(context: Context) {
        context.startActivity<FinalQuizActivity>()
    }

    fun showQuizActivity(context: Context) {
        context.startActivity<QuizGameActivity>()
    }

    fun showRulesActivity(context: Context) {
        context.startActivity<RulesActivity>()
    }
}