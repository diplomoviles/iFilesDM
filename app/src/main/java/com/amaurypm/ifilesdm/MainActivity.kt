package com.amaurypm.ifilesdm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amaurypm.ifilesdm.databinding.ActivityMainBinding
import com.amaurypm.ifilesdm.model.Student
import com.google.gson.Gson
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        //miToast("Hola!")

        //miToast("Hola 2", Toast.LENGTH_LONG)


    }

    fun clickSave(view: View) {
        //Clic del botón save

        view.hideKeyboard()

        if (binding.tietText.text.toString().isNotEmpty()) {

            val student = Student(name = binding.tietText.text.toString())

            //Pasando el objeto student a un json
            val bytesToSave = Gson().toJson(student).encodeToByteArray()


            try {
                val file = File(filesDir, "mi_archivo.txt")

                if (!file.exists()) {
                    file.createNewFile()
                }

                file.writeBytes(bytesToSave)

                sbMessage(
                    binding.clRoot,
                    getString(R.string.guardado_exitoso),
                    bgColor = "#50C228"
                )

            } catch (e: Exception) {
                //Manejar la excepción
            }


        } else {
            sbMessage(
                binding.clRoot,
                "Ingrese la información a almacenar"
            )

            binding.tietText.error = "No puede estar vacío"
            binding.tietText.requestFocus()
        }

    }

    fun clickRead(view: View) {
        //Clic del botón read

        try {

            val file = File(filesDir, "mi_archivo.txt")

            if (file.exists()) {

                //binding.tvContent.text = file.readBytes().decodeToString()

                val jsonString = file.readBytes().decodeToString()
                val student = Gson().fromJson(jsonString, Student::class.java)

                binding.tvContent.text = getString(R.string.student, student.id, student.name)

            } else {
                sbMessage(
                    binding.clRoot,
                    "No existe información o archivo almacenado en el dispositivo"
                )
            }

        } catch (e: Exception) {
            //Manejo de la excepción
        }
    }
}