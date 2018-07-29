package app.zheil.com.quiz.data

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper


class DataBase(context: Context) : SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "GameQuestions.db"
        private const val DATABASE_VERSION = 1
    }
}