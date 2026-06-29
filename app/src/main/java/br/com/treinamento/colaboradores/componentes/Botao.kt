package br.com.treinamento.colaboradores.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BotaoPrincipal(texto: String, acao: () -> Unit) {
    Button(
        onClick = acao,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(texto)
    }
}

@Composable
fun BotaoSecundario(texto: String, acao: () -> Unit) {
    OutlinedButton(
        onClick = acao,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(texto)
    }
}

@Composable
fun BotaoNivel(
    texto: String,
    selecionado: Boolean,
    acao: () -> Unit
) {
    if (selecionado) {
        Button(
            onClick = acao,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F766E))
        ) {
            Text(texto)
        }
    } else {
        OutlinedButton(onClick = acao) {
            Text(texto)
        }
    }
}
