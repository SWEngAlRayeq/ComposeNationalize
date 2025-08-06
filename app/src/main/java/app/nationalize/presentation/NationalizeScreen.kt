package app.nationalize.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.nationalize.presentation.viewmodel.NationalizeViewModel

@Composable
fun NationalizeScreen(viewModel: NationalizeViewModel = hiltViewModel()) {

    val name = viewModel.name
    val result = viewModel.result
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Color(0xFF121212))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "🌍 Nationalize This",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1DB954)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.name = it },
            label = { Text("Enter Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF1DB954),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF1DB954),
                unfocusedLabelColor = Color.Gray
            )
        )

        Button(
            onClick = { viewModel.fetchNationality() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Predict")
        }

        when {
            isLoading -> Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }

            error.isNotEmpty() -> Text(error, color = Color.Red)
            result != null -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Name: ${result.name}", color = Color.White)
                    result.country.sortedByDescending { it.probability }.forEach {
                        Text(
                            "🇨🇭 ${it.country_id} - ${(it.probability * 100).format(2)}%",
                            color = Color.LightGray
                        )
                    }
                }
            }
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)
