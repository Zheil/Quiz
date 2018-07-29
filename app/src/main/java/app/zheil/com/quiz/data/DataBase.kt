package app.zheil.com.quiz.data

import android.content.Context
import app.zheil.com.quiz.Const
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper


class DataBase(context: Context) : SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = Const.Database.DATABASE_NAME
        private const val DATABASE_VERSION = Const.Database.DATABASE_VERSION
    }
}