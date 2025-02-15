package com.betulesen.cryptoapp.service

import com.betulesen.cryptoapp.model.Crypto
import com.betulesen.cryptoapp.model.CryptoList
import retrofit2.http.GET

interface CryptoAPI {

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getCryptoList(): CryptoList

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/crypto.json")
    suspend fun getCrypto(): Crypto


}