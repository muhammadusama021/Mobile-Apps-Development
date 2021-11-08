package com.usamacreation.sqlite_task

import android.provider.BaseColumns

class DBContract {
    /*inner class*/
    class EmpEntry:BaseColumns
    {
        companion object {
            val TABLE_Name = "StudentTable"
            val KEY_ID = "_id"
            val KEY_NAME = "name"
            val KEY_SESSION = "session"
            val KEY_REG = "reg"

        }
    }
}