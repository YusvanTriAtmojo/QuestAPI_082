package com.example.application8.viewmodel

import com.example.application8.model.Mahasiswa

class InsertViewModel {
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jensiKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = "",
)

fun InsertUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jensiKelamin,
    kelas = kelas,
    angkatan = angkatan,
)