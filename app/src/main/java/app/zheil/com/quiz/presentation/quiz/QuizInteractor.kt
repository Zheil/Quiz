package app.zheil.com.quiz.presentation.quiz

import android.content.Context
import app.zheil.com.quiz.data.DataBase
import java.util.concurrent.TimeUnit


const val TABLE_NAME_QUESTION = "Questions"
const val COLUMN_NAME_QUESTION = "Question"

class QuizInteractor(val mContext: Context) {

    fun getAllQuestionsFromAssetsDataBase(): MutableList<String> {
        val listQuestions = mutableListOf<String>()

        val db = DataBase(mContext).readableDatabase
        val cur = db.query(TABLE_NAME_QUESTION,null, null, null, null, null, null)
        if (cur.moveToFirst()) {
            do {
                listQuestions.add(cur.getString(cur.getColumnIndex(COLUMN_NAME_QUESTION)))
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