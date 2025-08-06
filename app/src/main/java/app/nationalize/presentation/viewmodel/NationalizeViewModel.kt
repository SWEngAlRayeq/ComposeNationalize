package app.nationalize.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.nationalize.data.model.NationalizeResponse
import app.nationalize.domain.usecase.GetNationalityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NationalizeViewModel @Inject constructor(
    private val getNationalityUseCase: GetNationalityUseCase
): ViewModel(){

    var name by mutableStateOf("")
    var result by mutableStateOf<NationalizeResponse?>(null)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf("")

    fun fetchNationality() {
        viewModelScope.launch {
            isLoading = true
            error = ""
            try {
                result = getNationalityUseCase(name)
            } catch (e: Exception) {
                error = e.message ?: "Unknown error"
            }
            finally {
                isLoading = false
            }
        }
    }



}