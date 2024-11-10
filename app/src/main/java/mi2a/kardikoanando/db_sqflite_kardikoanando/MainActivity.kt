package mi2a.kardikoanando.db_sqflite_kardikoanando

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mi2a.kardikoanando.db_sqflite_kardikoanando.Helper.DbHelper

class MainActivity : AppCompatActivity() {
    private lateinit var etNama :EditText
    private lateinit var etNamaKampus :EditText
    private lateinit var etEmail:EditText
    private lateinit var etHP :EditText
    private lateinit var btnSubmit :Button
    private lateinit var btnShowBase :Button
    private lateinit var  txtNama:TextView
    private  lateinit var txtkampus:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        etNama= findViewById(R.id.etNama)
        etNamaKampus=findViewById(R.id.etNamaKampus)
        etNama=findViewById(R.id.etNama)
        etHP=findViewById(R.id.etHP)
        etEmail=findViewById(R.id.etEmail)
        btnSubmit=findViewById(R.id.btnSubmit)
        btnShowBase=findViewById(R.id.btnShowBase)
        txtNama = findViewById(R.id.txtNama)
        txtkampus = findViewById(R.id.txtkampus)
        btnSubmit.setOnClickListener(){
            val dbHelper= DbHelper(this,null)
            val name=etNama.text.toString()
            val kampus=etNamaKampus.text.toString()
            val email =etEmail.text.toString()
            val phone=etHP.text.toString()

            dbHelper.addName(
                name,
                kampus,
                email,
                phone

            )

            //Ketika Berhasil Input, kita clear semua isian di widget
            etNama.text.clear()
            etNamaKampus.text.clear()
            etEmail.text.clear()
            etHP.text.clear()
        }

        btnShowBase.setOnClickListener(){
            val dbHelper = DbHelper(this,null)

            val cursor = dbHelper.getName()
            cursor!!.moveToFirst() //mengambilndata yang pertama atau terbaru
            txtNama.append(cursor.getString(cursor.getColumnIndex(DbHelper.NAMA_LENGKAP)) +"\n")
            txtkampus.append(cursor.getString(cursor.getColumnIndex(DbHelper.NAMA_KAMPUS)) +"\n")
            while (cursor.moveToNext()){
                txtNama.append(cursor.getString(cursor.getColumnIndex(DbHelper.NAMA_LENGKAP)) +"\n")
                txtkampus.append(cursor.getString(cursor.getColumnIndex(DbHelper.NAMA_KAMPUS)) +"\n")
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}