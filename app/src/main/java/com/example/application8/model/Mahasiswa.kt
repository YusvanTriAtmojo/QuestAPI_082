package com.example.application8.model

import kotlinx.serialization.Serializable


@Serializable
data class Mahasiswa (
    val nim: String,
    val nama: String,
    val jenisKelamin: String,
    val alamat: String,
    val kelas: String,
    val angkatan: String
)