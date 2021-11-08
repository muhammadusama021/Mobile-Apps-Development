package com.usamacreation.sqlite_task

import EmpModelClass
import ItemAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.usamacreation.sqlite_task.DatabaseHandler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var  rvItemsList: RecyclerView
        lateinit var tvNoRecordsAvailable: TextView
        lateinit var etUpdateName: EditText
        lateinit var etName: EditText
        lateinit var etSession: EditText
        lateinit var etReg: EditText
        lateinit var etUpdateId: EditText
        lateinit var tvUpdate: TextView
        lateinit var tvCancel: TextView
        lateinit var etUpdateSession: EditText
        lateinit var etUpdateReg: EditText

        val btnAdd=findViewById<Button>(R.id.btnSubmit)
        val btnshow=findViewById<Button>(R.id.btnShow)

        // Click even of the add button.
        btnAdd.setOnClickListener { view ->

            addRecord()
        }
        btnshow.setOnClickListener { view ->

            showRecordActivity()
        }

    }

    private fun showRecordActivity() {
        val intent = Intent(this, ShowRecords::class.java)
        startActivity(intent)
    }
    /**
     * Function is used to show the list of inserted data.
     */

    /**
     * Function is used to get the Items List from the database table.
     */


    //Method for saving the employee records in database
    private fun addRecord() {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val etName: EditText =findViewById(R.id.Name)
        val etSession: EditText =findViewById(R.id.Session)
        val etReg: EditText =findViewById(R.id.Regno)

        val name = etName.text.toString()
        val session = etSession.text.toString()
        val reg = etReg.text.toString().toInt()

        if (!name.isEmpty() && !session.isEmpty() && reg!=0) {
            val status =
                    databaseHandler.addEmployee(EmpModelClass(0, name, session,reg))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                etName.text.clear()
                etSession.text.clear()
                etReg.text.clear()



            }
        } else {
            Toast.makeText(
                    applicationContext,
                    "Name, Session and Reg No cannot be blank",
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Method is used to show the Custom Dialog.
     */

}