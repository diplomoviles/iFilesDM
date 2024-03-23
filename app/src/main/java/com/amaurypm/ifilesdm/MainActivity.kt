package com.amaurypm.ifilesdm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amaurypm.ifilesdm.databinding.ActivityMainBinding
import java.io.File
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

        if(binding.tietText.text.toString().isNotEmpty()){

            //Codificando a bytes la cadena de texto a almacenar
            val bytesToSave = binding.tietText.text.toString().encodeToByteArray()

            try{
                val file = File(filesDir,"mi_archivo.txt")

                if(!file.exists()){
                    file.createNewFile()
                }

                val fos = FileOutputStream(file)
                fos.write(bytesToSave)
                fos.close()

            }catch(e: Exception){
                //Manejar la excepción
            }


        }else{
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

        view.hideKeyboard()
    }
}