package mx.edu.ittepic.tpdm_u2_practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var addtarea : Button?= null
    var addlista : Button?= null
    var vertarea : Button?= null
    var uptarea : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addtarea = findViewById(R.id.ADDTarea)
        addlista=findViewById(R.id.ADDLista)
        vertarea=findViewById(R.id.VerTareas)
        uptarea=findViewById(R.id.ActTarea)

        addlista?.setOnClickListener(){
            val principal = Intent(this,Main2Activity::class.java)
            startActivity(principal)
        }

        addtarea?.setOnClickListener(){
            val principal = Intent(this,Main3Activity::class.java)
            startActivity(principal)
        }
        vertarea?.setOnClickListener(){
            val principal = Intent(this,Main4Activity::class.java)
            startActivity(principal)
        }
    }
}
