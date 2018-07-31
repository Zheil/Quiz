package app.zheil.com.quiz.di.activity

import app.zheil.com.quiz.presentation.main.MainActivity
import app.zheil.com.quiz.presentation.quiz.QuizGameActivity
import dagger.Component


@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(app: MainActivity)
    fun inject(app: QuizGameActivity)
}