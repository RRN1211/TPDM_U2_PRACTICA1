package mx.edu.ittepic.tpdm_u2_practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import java.sql.SQLException

class Main3Activity : AppCompatActivity() {
    var descripcion : EditText?= null
    var fecha : EditText?= null
    var idlista : EditText?= null
    var insertar : Button?= null
    var regresar : Button?= null

    var bd = Base(this,"practica1",null,1)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        descripcion=findViewById(R.id.IDdescripcion)
        fecha=findViewById(R.id.IDFechaRealizado)
        idlista=findViewById(R.id.IDLista)
        insertar=findViewById(R.id.btnAgregar)
        regresar=findViewById(R.id.btnregresar)

        insertar?.setOnClickListener(){
            insertar(idlista?.text.toString())
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

    fun limpiarCampos(){
        descripcion?.setText("")
        fecha?.setText("")
        idlista?.setText("")
    }

    fun validaCampos(): Boolean{
        if(descripcion?.text!!.toString().isEmpty()||fecha?.text!!.toString().isEmpty()||idlista?.text!!.toString().isEmpty()){
            return false
        }else{
            return true
        }
    }

    fun insertar(idtarea: String) {
        try {
            var trans = bd.writableDatabase
            var con = "INSERT INTO TAREAS VALUES(NULL,'DESCRIPCION','REALIZADO',IDLISTA)"

            if (validaCampos() == false) {
                msj(
                    "Error!","campo vacio"
                )
                return
            }

            con = con.replace("DESCRIPCION", descripcion?.text.toString())
            con = con.replace("REALIZADO", fecha?.text.toString())
            con = con.replace("IDLISTA",idtarea)
            trans.execSQL(con)
            trans.close()
            msj("Registro exitoso!", "Se inserto correctamente")
            limpiarCampos()
        } catch (er: SQLException) {
            msj("Error!", "no se inserto tarea")
        }
    }
}
