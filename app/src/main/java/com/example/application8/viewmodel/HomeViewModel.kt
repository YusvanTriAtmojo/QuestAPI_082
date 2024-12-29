package com.example.application8.viewmodel

import com.example.application8.model.Mahasiswa

sealed class HomeUiState {
    data class Success(val mahasiswa: List<Mahasiswa>): HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeViewModel {
}
