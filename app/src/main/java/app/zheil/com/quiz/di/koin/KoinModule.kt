package app.zheil.com.quiz.di.koin

import app.zheil.com.quiz.GlobalRouter
import app.zheil.com.quiz.data.DataQuestion
import org.koin.dsl.module.applicationContext


val koinModule = applicationContext {
    bean { GlobalRouter() }
    bean { DataQuestion() }

}