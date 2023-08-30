package com.amaurypm.ifilesdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.amaurypm.ifilesdm.databinding.ActivityMainBinding
import com.amaurypm.ifilesdm.model.Student
import org.simpleframework.xml.core.Persister
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.StringWriter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var serializer: Persister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        serializer = Persister()

        binding.btnSave.setOnClickListener {
            it.hideKeyboard()

            if(!binding.tietText.text.toString().isEmpty()){

                val student = Student(name = binding.tietText.text.toString())
                //val bytesToSave = Gson().toJson(student).encodeToByteArray()

                //Creamos un escritor para el XML
                val writer = StringWriter()

                //Serializamos el objeto en XML
                serializer.write(student, writer)

                //Obtenemos el XML como cadena
                val xmlString = writer.toString()

                val bytesToSave = xmlString.encodeToByteArray()

                try{

                    val file = File(filesDir, "app_data.txt")

                    if(!file.exists()) file.createNewFile()

                    //Sobreescribiendo el archivo
                    file.writeBytes(bytesToSave)

                    binding.tietText.setText("")
                    binding.tvContent.text = ""

                    sbMessage(binding.root, "Información almacenada exitosamente", bgColor = "#50C228")


                }catch(e: IOException){
                    //Manejo de la excepción
                    e.printStackTrace()
                }

            }else{
                sbMessage(binding.root, "Ingrese la información a almacenar")

                binding.tietText.error = "No puede estar vacío"
                binding.tietText.requestFocus()
            }
        }

    }

    fun clickRead(view: View) {
        try{
            val file = File(filesDir,"app_data.txt")

            if(file.exists()){

                val xmlString = file.readBytes().decodeToString()
                val student = serializer.read(Student::class.java, xmlString)
                binding.tvContent.text = "ID: ${student.id}, Nombre: ${student.name}"

            }else{
                sbMessage(binding.clRoot, "No existe información o archivo almacenado")
            }

        }catch(e: Exception){
            //Manejo de la excepción
            e.printStackTrace()
            Log.d("LOGS", "Error: ${e.message.toString()}")
        }
    }
}