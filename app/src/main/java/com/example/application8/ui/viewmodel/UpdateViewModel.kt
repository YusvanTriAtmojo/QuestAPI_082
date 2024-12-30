package com.example.application8.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application8.model.Mahasiswa
import com.example.application8.repository.MahasiswaRepository
import com.example.application8.ui.view.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdateViewModel(
    private val repositoryMhs: MahasiswaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var updateMhsUIState by mutableStateOf(InsertUiState())
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            updateMhsUIState = repositoryMhs.getMahasiswabyNim(_nim)
                .toUIStateMhs()
        }
    }

    fun updateState(insertUiEvent: InsertUiEvent) {
        updateMhsUIState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    fun updateData() {
        viewModelScope.launch {
            try {
                repositoryMhs.updateMahasiswa(_nim, updateMhsUIState.insertUiEvent.toMhs())
                updateMhsUIState = updateMhsUIState.copy(
                    snackBarMessage = "Data berhasil diupdate"
                )
            } catch (e: Exception) {
                e.printStackTrace()
                updateMhsUIState = updateMhsUIState.copy(
                    snackBarMessage = "Data gagal diupdate"
                )
            }
        }
    }
}

fun Mahasiswa.toUIStateMhs() : InsertUiState = InsertUiState(
    insertUiEvent = this.toDetailUiEvent(),
)