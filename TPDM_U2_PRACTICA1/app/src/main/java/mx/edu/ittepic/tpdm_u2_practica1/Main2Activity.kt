package mx.edu.ittepic.tpdm_u2_practica1

import android.content.Intent
import android.database.SQLException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class Main2Activity : AppCompatActivity() {
    var descripcion: EditText?= null
    var fechacreacion : EditText?= null
    var insertar : Button?= null
    var regresar : Button?= null
    var bd = Base(this,"practica1",null,1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        descripcion = findViewById(R.id.IDdescripcion)
        fechacreacion = findViewById(R.id.IDfecha)
        insertar = findViewById(R.id.btnAgregar)
        regresar= findViewById(R.id.btnregresar)

        insertar?.setOnClickListener(){
            insertar()
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
        fechacreacion?.setText("")
    }

    fun validaCampos(): Boolean{
        if(descripcion?.text!!.toString().isEmpty()||fechacreacion?.text!!.isEmpty()){
            return false
        }else{
            return true
        }
    }

    fun insertar(){
        try {
            var trans = bd.writableDatabase
            var con = "INSERT INTO LISTA VALUES(NULL,'DESC','FECHACREA')"
            if (validaCampos() == false) {
                msj("Error!", "Existe algun campo vacio (\"Descripción\" y/o \"Fecha de creación\")")
                return
            }

            con = con.replace("DESC", descripcion?.text.toString())
            con = con.replace("FECHACREA", fechacreacion?.text.toString())
            trans.execSQL(con)
            trans.close()
            msj("Registro exitoso!", "Se inserto correctamente")
            limpiarCampos()
        }
        catch (er: SQLException) {
            msj("Error!","No se pudo insertar el registro, verifique sus datos!")
        }
    }

}
