package com.usamacreation.sqlite_task

import EmpModelClass
import ItemAdapter
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

class ShowRecords : AppCompatActivity() {

    lateinit var  rvItemsList: RecyclerView
    lateinit var tvNoRecordsAvailable: TextView
    lateinit var etUpdateName: EditText

    lateinit var etUpdateSession: EditText
    lateinit var etUpdateReg: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_records)

        val btnAdd=findViewById<Button>(R.id.search)
        setupListofDataIntoRecyclerView()
        btnAdd.setOnClickListener()
        {
            val reg:EditText=findViewById(R.id.Regno)
            val search=reg.text.toString().toInt()
            setupListofDataIntoRecyclerView(search)
        }
    }


    private fun setupListofDataIntoRecyclerView(reg:Int) {
        val  rvItemsList: RecyclerView =findViewById(R.id.rvItemsList)
        val tvNoRecordsAvailable: TextView=findViewById(R.id.tvNoRecordsAvailable)

        if (getItemsList(reg).size > 0) {

            rvItemsList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE

            // Set the LayoutManager that this RecyclerView will use.
            rvItemsList.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = ItemAdapter(this, getItemsList(reg))
            // adapter instance is set to the recyclerview to inflate the items.
            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }
    private fun getItemsList(reg:Int): ArrayList<EmpModelClass> {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val empList: ArrayList<EmpModelClass> = databaseHandler.viewEmployee(reg)

        return empList
    }

    private fun setupListofDataIntoRecyclerView() {
        val  rvItemsList: RecyclerView =findViewById(R.id.rvItemsList)
        val tvNoRecordsAvailable: TextView=findViewById(R.id.tvNoRecordsAvailable)

        if (getItemsList().size > 0) {

            rvItemsList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE

            // Set the LayoutManager that this RecyclerView will use.
            rvItemsList.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = ItemAdapter(this, getItemsList())
            // adapter instance is set to the recyclerview to inflate the items.
            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }
    private fun getItemsList(): ArrayList<EmpModelClass> {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val empList: ArrayList<EmpModelClass> = databaseHandler.viewEmployee()

        return empList
    }
    fun updateRecordDialog(empModelClass: EmpModelClass) {
        val dialog= AlertDialog.Builder(this)
        val inflator=layoutInflater



        val updateDialog=dialog.setTitle("Update Record")
                .setView(inflator.inflate(R.layout.dialog_update,null))
                .setPositiveButton("Update",){di,i->

                    val name = etUpdateName.text.toString()
                    val session = etUpdateSession.text.toString()
                    val reg = etUpdateReg.text.toString().toInt()


                    val databaseHandler: DatabaseHandler = DatabaseHandler(this)

                    if (!name.isEmpty() && !session.isEmpty() && reg!=0) {
                        val status =
                                databaseHandler.updateEmployee(EmpModelClass(empModelClass.id, name, session,reg))
                        if (status > -1) {
                            Toast.makeText(applicationContext, "Record Updated.", Toast.LENGTH_LONG).show()


                            di.dismiss()
                        }
                    } else {
                        Toast.makeText(
                                applicationContext,
                                "Name or Email cannot be blank",
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }
                .setNegativeButton("Cancel"){di,i->
                    di.dismiss()
                }
                .create()

        updateDialog.show()
        etUpdateName= updateDialog.findViewById(R.id.etUpdateName)!!
        etUpdateSession= updateDialog.findViewById(R.id.etUpdateSession)!!
        etUpdateReg= updateDialog.findViewById(R.id.etUpdateReg)!!
        etUpdateName.setText(empModelClass.name)
        etUpdateSession.setText(empModelClass.session)
        etUpdateReg.setText(empModelClass.reg)


    }

    /**
     * Method is used to show the Alert Dialog.
     */
    fun deleteRecordAlertDialog(empModelClass: EmpModelClass) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Record")
        builder.setMessage("Are you sure you wants to delete ${empModelClass.name}.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->

            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            //calling the deleteEmployee method of DatabaseHandler class to delete record
            val status = databaseHandler.deleteEmployee(EmpModelClass(empModelClass.id, "", "",0))
            if (status > -1) {
                Toast.makeText(
                        applicationContext,
                        "Record deleted successfully.",
                        Toast.LENGTH_LONG
                ).show()

            }

            dialogInterface.dismiss() // Dialog will be dismissed
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI
    }

}