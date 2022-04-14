package br.com.aulaspuc.materialappcep

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface APIRetrofitService {

    /* Mapeando o ENDEREÇO HTTP para recebimento dos valores por JSON */

    @GET("{CEP}/json/")
    fun getEnderecoByJSON(@Path("CEP") CEP : String) : Call<CEP>
}
