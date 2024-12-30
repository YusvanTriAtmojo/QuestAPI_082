package com.example.application8.ui.viewmodel

import com.example.application8.model.Mahasiswa

fun Mahasiswa.toUIStateMhs() : InsertUiState = InsertUiState(
    insertUiEvent = this.toDetailUiEvent(),
)