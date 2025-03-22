package com.amaurypm.ifilesdm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.amaurypm.ifilesdm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.simpleframework.xml.core.Persister
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.StringWriter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var serializer: Persister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Instanciamos el serializer
        serializer = Persister()

        //toast("hola")
        //toast("Amaury", Toast.LENGTH_LONG)

        /*Snackbar.make(
            binding.main,
            "Ingrese la info a almacenar",
            Snackbar.LENGTH_SHORT
        )
            .setTextColor(getColor(R.color.white))
            .setBackgroundTint(getColor(R.color.snackbar_red))
            .show()*/

        //sbMessage(binding.main, "Hola!!!!!")

        binding.btnSave.setOnClickListener { view ->

            view.hideKeyboard()

            if(binding.tietText.text.toString().isNotEmpty()){

                //Pasamos la cadena a un arreglo de bytes
                //val bytesToSave = binding.tietText.text.toString().encodeToByteArray()

                val student = Student(name = binding.tietText.text.toString())

                //Creamos un escritor para el xml
                val writer = StringWriter()

                //Serializamos el objeto en xml
                serializer.write(student, writer)

                //Obtenemos el xml como cadena
                val xmlString = writer.toString()

                val bytesToSave = xmlString.encodeToByteArray()



                try{

                    val file = File(filesDir, "app_data.txt")

                    //Si no existe el archivo, lo creamos
                    if(!file.exists()) file.createNewFile()

                    //Para sobrescribir el archivo
                    /*val fos = FileOutputStream(file)
                    fos.write(bytesToSave)
                    fos.close()*/

                    //Para agregar al archivo
                    /*val fos = FileOutputStream(file,true)
                    fos.write(bytesToSave)
                    fos.close()*/

                    //Sobrescribe el archivo
                    file.writeBytes(bytesToSave)

                    //Agrega al archivo
                    //file.appendBytes(bytesToSave)

                    sbMessage(
                        binding.main,
                        "Información almacenada exitosamente",
                        bgColor = R.color.snackbar_green
                    )

                    binding.tietText.setText("")
                    binding.tvContent.text = ""

                }catch (e: IOException){
                    //Manejamos la excepción
                    e.printStackTrace()
                }

            }else{

                sbMessage(
                    binding.main,
                    getString(R.string.empty_tiet)
                )

                binding.tietText.error = "No puede estar vacío"
                binding.tietText.requestFocus()

            }

        }

        binding.btnRead.setOnClickListener {
            try{

                val file = File(filesDir, "app_data.txt")

                if(file.exists()){

                    /*val fis = FileInputStream(file)
                    val content = fis.readBytes()
                    binding.tvContent.text = content.decodeToString()
                    fis.close()*/

                    //binding.tvContent.text = file.readText()


                    val xmlString = file.readText()
                    val student = serializer.read(Student::class.java, xmlString)
                    binding.tvContent.text = "Nombre: ${student.name}\nId: ${student.id}"

                }else{
                    sbMessage(
                        binding.main,
                        "No existe información o archivo almacenado en el dipositivo"
                    )
                }

            }catch (e: IOException){
                //Manejamos la excepción
                e.printStackTrace()
            }
        }

    }
}