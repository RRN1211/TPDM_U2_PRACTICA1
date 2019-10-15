package mx.edu.ittepic.tpdm_u2_practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main4.*

class Main4Activity : AppCompatActivity() {
    var bd = Base(this,"practica1",null,1)
    var ver : Button?= null
    var mostrar:TextView?=null
    var regresar: Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        mostrar = findViewById(R.id.mostrar)
        ver=findViewById(R.id.ver)
        regresar=findViewById(R.id.regresar)

        ver?.setOnClickListener(){
            mostrar()
        }
        regresar?.setOnClickListener(){
            val principal = Intent(this,MainActivity::class.java)
            startActivity(principal)
        }

    }
    fun msj(t: String, m: String){
        AlertDialog.Builder(this)
            .setTitle(t)
            .setMessage(m)
            .setPositiveButton("OK")
            { dialogInterface, i ->}.show()
    }
    fun mostrar(){
        var sel = ""
        try {
            var trans = bd.readableDatabase
            var con = "SELECT * FROM TAREAS"
            var c = trans.rawQuery(con,null)
            if(c != null) {
                if (c.moveToFirst() == true) {
                    do{
                        sel +="ID: ${c.getString(0)}\nDescripción: " +
                                "${c.getString(1)}\nFecha de creación: " +
                                "${c.getString(2)}\nID Lista: " +
                                "${c.getString(3)}\n"+
                                "_________________________________\n"
                    }while (c.moveToNext())
                    mostrar?.setText(sel)
                }else{
                    msj("Advertencia!","No existen tareas")
                }
            }
            c.close()
        }
        catch (er: android.database.SQLException){
            msj("Error!","No se encuentran registros en la BD")
        }
    }


}
