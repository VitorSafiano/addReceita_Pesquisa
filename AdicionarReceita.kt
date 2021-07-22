package com.example.realtimedatabasekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.realtimedatabasekotlin.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdicionarReceita : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var telaReadData: Button

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.adicionarBtn.setOnClickListener {

            val nomeReceita: String = binding.nomeReceita.text.toString()
            val ingrediente = binding.ingrediente.text.toString()
            val descricao = binding.descricao.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Receita")
            val Receita = Receita(nomeReceita,ingrediente,descricao)
            database.child(nomeReceita).setValue(Receita).addOnSuccessListener {

                binding.nomeReceita.text.clear()
                binding.ingrediente.text.clear()
                binding.descricao.text.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


            }

            telaReadData = findViewById(R.id.bt_telaReadData)
            telaReadData.setOnClickListener{
                val intent: Intent = Intent(applicationContext, ReadData::class.java)
                startActivity(intent)
            }

        }

    }
}