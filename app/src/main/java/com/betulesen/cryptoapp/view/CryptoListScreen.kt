package com.betulesen.cryptoapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.betulesen.cryptoapp.model.CryptoListItem
import com.betulesen.cryptoapp.viewmodel.CryptoListViewModel

// ANA EKRAN
@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            Text(
                text = "Crypto Tracker 🚀",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Gray,
                        blurRadius = 4f
                    )
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            SearchBar(
                hint = "🔍 Search for a cryptocurrency...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                viewModel.searchCryptoList(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            CryptoList(navController = navController)
        }
    }
}

// ARAMA ÇUBUĞU
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint.isNotEmpty()) }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(12.dp))
                .background(Color.DarkGray, RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp, vertical = 14.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)
            )
        }
    }
}

//LİSTE EKRANI
@Composable
fun CryptoList(
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    val cryptoList by remember { viewModel.cryptoList }
    val errorMessage by remember { viewModel.errorMessage }
    val isLoading by remember { viewModel.isLoading }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(color = Color.White)
            }
            errorMessage.isNotEmpty() -> {
                RetryView(error = errorMessage) {
                    viewModel.loadCryptos()
                }
            }
            else -> {
                CryptoListView(cryptos = cryptoList, navController = navController)
            }
        }
    }
}

// COIN LİSTESİ
@Composable
fun CryptoListView(cryptos: List<CryptoListItem>, navController: NavController) {
    LazyColumn(contentPadding = PaddingValues(10.dp)) {
        items(cryptos) { crypto ->
            CryptoRow(navController = navController, crypto = crypto)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// COIN KARTLARI
@Composable
fun CryptoRow(navController: NavController, crypto: CryptoListItem) {
    val priceColor = if (crypto.price.toDoubleOrNull() ?: 0.0 >= 0) Color.Green else Color.Red

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("crypto_detail_screen/${crypto.currency}/${crypto.price}")
            }
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp), // Kartın gölgesini hafif artırdım
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray) // Kart arka planı koyu
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = crypto.currency,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Gray,
                            blurRadius = 3f,
                            offset = Offset(2f, 2f) // Gölgenin konumu
                        )
                    ),
                    color = Color.White // Coin ismi beyaz
                )
                Text(
                    text = "${crypto.price} USD",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Gray,
                            blurRadius = 3f,
                            offset = Offset(2f, 2f) // Gölgenin konumu
                        )
                    ),
                    color = priceColor // Coin fiyatı yeşil veya kırmızı
                )
            }
        }
    }
}

// HATA VE TEKRAR DENE BUTONU
@Composable
fun RetryView(
    error: String,
    onRetry: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "⚠️ $error",
            color = Color.Red,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        ) {
            Text(text = "🔄 Retry", fontSize = 16.sp, color = Color.White)
        }
    }
}
