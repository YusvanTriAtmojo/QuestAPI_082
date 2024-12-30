package com.example.application8.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application8.model.Mahasiswa
import com.example.application8.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val mhs: MahasiswaRepository): ViewModel() {
    var uiDetailState: DetailUiState by mutableStateOf(DetailUiState())
        private set

    fun getMhsDetail(nim: String) {
        viewModelScope.launch {
            uiDetailState = uiDetailState.copy(isLoading = true, isError = false)
            try {
                val DetailMhs = mhs.getMahasiswabyNim(nim)
                uiDetailState = uiDetailState.copy(
                    detailUiEvent = DetailMhs.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                uiDetailState = uiDetailState.copy(
                    isError = true,
                    errorMessage = "Error",
                    isLoading = false
                )
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertUiEvent()
}

fun Mahasiswa.toDetailUiEvent(): InsertUiEvent {
    return InsertUiEvent(
        nim = nim,
        nama = nama,
        jensiKelamin = jenisKelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan
    )
}
