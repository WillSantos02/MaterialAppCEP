package br.com.aulaspuc.materialappcep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import br.com.aulaspuc.materialappcep.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflar a activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Buscar por CEP via JSON*/
        binding.btnBuscar.setOnClickListener {

            // TestControl
            var input = binding.etInputCep.text.toString()
            Log.i("VARinputTest", "onCreate: $input")
            // TestControl

            /* Realizar a chamada da API passando o caminho do EditText */
            val call = RetrofitInitializer().apiRetrofitServiceJSON().getEnderecoByJSON(binding.etInputCep.unMasked.toString())

            /* A chamada deve implementar dois metodos: onResponse e onFailure */
            call.enqueue(object : Callback<CEP> {

                /* Caso a resposta seja positiva recebemos o objeto da resposta e exibimos o resultado na tela */
                override fun onResponse(call: Call<CEP>, response: Response<CEP>) {

                    response.let {
                        val CEPs: CEP? = it.body()

                        // TestControl
                        var output = CEPs
                        Log.i("VARoutputTest", "onCreate: $output")
                        // TestControl

                        if (CEPs == null) {
                            binding.tvResult.text = "Cep inválido!"
                            binding.card.visibility = View.VISIBLE

                        } else if(CEPs.cep == null){
                            binding.tvResult.text = "Cep não existe"
                            binding.card.visibility = View.VISIBLE
                        } else{
                            binding.tvResult.text =
                                "Cep: " + CEPs.cep + "\n" +
                                        "Logradouro: " + CEPs.logradouro + "\n" +
                                        "Bairro: " + CEPs.bairro + "\n" +
                                        "Cidade: " + CEPs.localidade + "\n" +
                                        "DDD: " + CEPs.ddd + "\n" +
                                        "UF: " + CEPs.uf
                            binding.card.visibility = View.VISIBLE
                        }
                    }
                }

                /* Caso ocorra uma falha na resposta lançamos um erro no log */
                override fun onFailure(call: Call<CEP>?, t: Throwable?) {
                    t?.message?.let { it1 -> Log.e("Erro", it1) }
                }
            })
        }

    }

}
