package app.zheil.com.quiz.di.activity

import app.zheil.com.quiz.presentation.main.MenuActivity
import app.zheil.com.quiz.presentation.quiz.QuizGameActivity
import dagger.Component


@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(app: MenuActivity)
    fun inject(app: QuizGameActivity)
}