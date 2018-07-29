package app.zheil.com.quiz.di.presenter

import app.zheil.com.quiz.presentation.quiz.QuizGamePresenter
import dagger.Component


@Component(modules = [PresenterModule::class])
interface PresenterComponent {
    fun inject(app: QuizGamePresenter)
}