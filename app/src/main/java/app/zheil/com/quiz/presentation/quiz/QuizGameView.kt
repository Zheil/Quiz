package app.zheil.com.quiz.presentation.quiz

import app.zheil.com.quiz.presentation.base.baseQuiz.BaseQuizView


interface QuizGameView: BaseQuizView {
    fun nextQuestion(question: String)
    fun finishQuiz()
    fun setCurrentNumberQuestion(current: Int, maxQuestion: Int)
    fun animateBtnClick()

    fun hideWorkPlace()
    fun visibleWorkPlace()
    fun finishAnimation()
}