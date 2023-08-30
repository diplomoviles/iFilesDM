package com.amaurypm.ifilesdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.amaurypm.ifilesdm.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //toast("Hola", Toast.LENGTH_LONG)
        //sbMessage(binding.clRoot, "Hola")
        //Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()

        binding.btnSave.setOnClickListener {
            it.hideKeyboard()

            if(!binding.tietText.text.toString().isEmpty()){
                //El usuario escribió algo

                val bytesToSave = binding.tietText.text.toString().encodeToByteArray()

                try{

                    val file = File(filesDir, "app_data.txt")

                    if(!file.exists()) file.createNewFile()

                    /*val fos = FileOutputStream(file)
                    fos.write(bytesToSave)
                    fos.close()*/

                    //file.appendBytes(bytesToSave)  //Escribe datos adjuntando, sin sobreescribir
                    file.writeBytes(bytesToSave)  //escribe los datos sobreescribiéndolos

                    binding.tietText.setText("")
                    binding.tvContent.text = ""

                    sbMessage(
                        binding.clRoot,
                        "Información almacenada exitosamente",
                        bgColor = "#50C228"
                    )




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
        try {
            val file = File(filesDir, "app_data.txt")

            if (file.exists()) {


                //Más como en Java:
                /*val fis = FileInputStream(file)
                val content = fis.readBytes()
                binding.tvContent.text = content.decodeToString()
                fis.close()*/

                //En Kotlin
                binding.tvContent.text = file.readBytes().decodeToString()

            } else {
                sbMessage(
                    binding.clRoot,
                    "No existe información o archivo almacenado en el dispositivo"
                )
            }
        } catch (e: Exception) {
            //Manejar la excepción
        }
    }
}