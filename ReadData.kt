package com.example.realtimedatabasekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabasekotlin.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadData : AppCompatActivity() {

        private lateinit var binding : ActivityReadDataBinding
        private lateinit var database : DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityReadDataBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.readdataBtn.setOnClickListener {

                val nomeReceita : String = binding.pesquisarReceita.text.toString()
                if  (nomeReceita.isNotEmpty()){

                    readData(nomeReceita)

                }else{

                    Toast.makeText(this,"PLease enter the Username",Toast.LENGTH_SHORT).show()

                }

            }

        }

        private fun readData(nomeReceita: String) {

            database = FirebaseDatabase.getInstance().getReference("Receita")
            database.child(nomeReceita).get().addOnSuccessListener {

                if (it.exists()){

                    val nomeReceita = it.child("nomeReceita").value
                    val ingrediente = it.child("ingrediente").value
                    val descricao = it.child("descricao").value
                    Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                    binding.pesquisarReceita.text.clear()
                    binding.rdNomeReceita.text = nomeReceita.toString()
                    binding.rdIngrediente.text = ingrediente.toString()
                    binding.rdDescricao.text = descricao.toString()

                }else{

                    Toast.makeText(this,"User Doesn't Exist",Toast.LENGTH_SHORT).show()


                }

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


            }



        }
    }
