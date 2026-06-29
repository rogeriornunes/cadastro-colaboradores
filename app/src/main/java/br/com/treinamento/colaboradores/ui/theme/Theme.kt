package br.com.treinamento.colaboradores.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Cores = lightColorScheme(
    primary = Color(0xFF0F766E),
    secondary = Color(0xFF2563EB),
    tertiary = Color(0xFFF59E0B),
    error = Color(0xFFDC2626)
)

@Composable
fun CadastroColaboradoresTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Cores,
        content = content
    )
}
