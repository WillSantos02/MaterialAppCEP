package br.com.aulaspuc.materialappcep

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RetrofitInitializer {

    /* Consumir JSON da API e converter (parser) */
    val retrofitJSON = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Implementar a interface
    fun apiRetrofitServiceJSON(): APIRetrofitService {
        return retrofitJSON.create(APIRetrofitService::class.java)
    }

    /* Consumir XML da API e converter (parser) */
    val retrofitXML = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    // Implementar a interface
    fun apiRetrofitServiceXML(): APIRetrofitService {
        return retrofitXML.create(APIRetrofitService::class.java)
    }

}
