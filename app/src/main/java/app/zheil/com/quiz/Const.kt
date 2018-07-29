package app.zheil.com.quiz

object Const {
    object Database {
        const val DATABASE_NAME = "GameQuestions.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME_QUESTION = "Questions"
        const val COLUMN_NAME_QUESTION = "Question"
    }

    object Settings {
        const val BUTTON_NEXT_QUESTION_SLEEP = 1000L
        const val FORCE_SLEEP_LOADING_DATABASE = 1L
    }

    object Animation {
        const val DURATION_ANIMATION_OF_BUTTON_NEXT_QUESTION = 700L
        const val DURATION_ANIMATION_OF_CARD_VIEW = 700L
    }

    const val CURRENT_FONT_TYPEFACE = "fonts/krabuler.ttf"
}