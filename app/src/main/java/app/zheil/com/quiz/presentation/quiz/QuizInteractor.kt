package app.zheil.com.quiz.presentation.quiz

import android.content.Context
import app.zheil.com.quiz.Const
import app.zheil.com.quiz.data.DataBase
import java.util.concurrent.TimeUnit


class QuizInteractor(private val mContext: Context) {

    fun getAllQuestionsFromAssetsDataBase(): MutableList<String> {
        val listQuestions = mutableListOf<String>()

        val db = DataBase(mContext).readableDatabase
        val cur = db.query(Const.Database.TABLE_NAME_QUESTION,null, null, null, null, null, null)
        if (cur.moveToFirst()) {
            do {
                listQuestions.add(cur.getString(cur.getColumnIndex(Const.Database.COLUMN_NAME_QUESTION)))
            } while (cur.moveToNext())
        }
        cur.close()

        try {
            TimeUnit.SECONDS.sleep(1)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return listQuestions
    }
}