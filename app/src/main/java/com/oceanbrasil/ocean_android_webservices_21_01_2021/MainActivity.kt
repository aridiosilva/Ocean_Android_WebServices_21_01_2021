package com.oceanbrasil.ocean_android_webservices_21_01_2021

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        tvResultado.text = "Carregando lista de pokémons..."

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PokemonService::class.java)

        val call = service.listPokemons()


        call.enqueue(object : Callback<PokemonApiResult> {
            override fun onResponse(
                call: Call<PokemonApiResult>,
                response: Response<PokemonApiResult>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()

                    body?.results?.let { pokemons ->
                        tvResultado.text = ""

                        pokemons.forEach {
                            tvResultado.append(it.name)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PokemonApiResult>, t: Throwable) {
                tvResultado.text = "Erro ao carregar a lista de pokémons."
                Log.e("MAIN_ACTIVITY", "Erro ao executar a API", t)
            }
        })
    }
}
