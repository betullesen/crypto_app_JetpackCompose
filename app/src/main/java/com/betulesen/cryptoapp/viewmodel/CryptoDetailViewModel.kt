package com.betulesen.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.betulesen.cryptoapp.model.Crypto
import com.betulesen.cryptoapp.repository.CryptoRepository
import com.betulesen.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository : CryptoRepository
): ViewModel() {

    suspend fun getCrypto(id: String) : Resource<Crypto>{
        return repository.getCrypto(id)
    }
}