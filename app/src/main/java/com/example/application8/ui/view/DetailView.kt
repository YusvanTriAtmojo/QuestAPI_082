package com.example.application8.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application8.model.Mahasiswa
import com.example.application8.ui.customwidget.CostumeTopAppBar
import com.example.application8.ui.viewmodel.DetailViewModel
import com.example.application8.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(
    nim: String,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: (String) -> Unit = { },
    navigateBack: () -> Unit
) {
    val uiState = viewModel.uiDetailState

    LaunchedEffect(nim) {
        viewModel.getMhsDetail(nim)
    }

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = { viewModel.getMhsDetail(nim) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(nim) },
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Text(
                    text = "Loading",
                    modifier = Modifier.padding(innerPadding).fillMaxWidth(),
                )
            }
            uiState.isError -> {
                Text(
                    text = "Error",
                    modifier = Modifier.padding(innerPadding).fillMaxWidth(),
                    color = Color.Red,
                )
            }
            uiState.isUiEventEmpty -> {
                Text(
                    text = "Tidak Ada",
                    modifier = Modifier.padding(innerPadding).fillMaxWidth(),
                )
            }
            uiState.isUiEventNotEmpty -> {
                val mahasiswa = Mahasiswa(
                    nim = uiState.detailUiEvent.nim,
                    nama = uiState.detailUiEvent.nama,
                    jenisKelamin = uiState.detailUiEvent.jensiKelamin,
                    alamat = uiState.detailUiEvent.alamat,
                    kelas = uiState.detailUiEvent.kelas,
                    angkatan = uiState.detailUiEvent.angkatan
                )
                ItemDetailMhs(mahasiswa = mahasiswa, modifier = Modifier.padding(innerPadding))
            }
        }
    }
}


@Composable
fun ItemDetailMhs(
    modifier: Modifier = Modifier,
    mahasiswa: Mahasiswa
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMhs(judul = "NIM", isinya = mahasiswa.nim)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailMhs(judul = "Nama", isinya = mahasiswa.nama)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailMhs(judul = "Alamat", isinya = mahasiswa.alamat)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailMhs(judul = "Jenis Kelamin", isinya = mahasiswa.jenisKelamin)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailMhs(judul = "Kelas", isinya = mahasiswa.kelas)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailMhs(judul = "Angkatan", isinya = mahasiswa.angkatan)
        }
    }
}

@Composable
fun ComponentDetailMhs(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
