package com.betulesen.cryptoapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.betulesen.cryptoapp.model.Crypto
import com.betulesen.cryptoapp.util.Resource
import com.betulesen.cryptoapp.viewmodel.CryptoDetailViewModel

@Composable
fun CryptoDetailScreen(
    id: String,
    price: String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {
    val cryptoItem = produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto(id)
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when (cryptoItem) {
                is Resource.Success -> {
                    val selectedCrypto = cryptoItem.data!![0]


                    Text(
                        text = selectedCrypto.name,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    // 📷 Coin Logosu
                    Image(
                        painter = rememberImagePainter(data = selectedCrypto.logo_url),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier
                            .size(220.dp)
                            .clip(CircleShape)
                            .border(4.dp, Color.Gray, CircleShape)
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    val priceColor = if (price.toDoubleOrNull() ?: 0.0 >= 0) Color.Green else Color.Red
                    Text(
                        text = "$price USD",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = priceColor,
                        textAlign = TextAlign.Center
                    )
                }

                is Resource.Error -> {
                    Text(
                        text = "⚠️ ${cryptoItem.message}",
                        color = Color.Red,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }
    }
}
