package br.com.treinamento.colaboradores.componentes

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import br.com.treinamento.colaboradores.modelos.Colaborador

@Composable
fun DialogExcluir(
    colaborador: Colaborador,
    onConfirmar: () -> Unit,
    onCancelar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Remover colaborador") },
        text = { Text("Deseja realmente remover ${colaborador.nome}?") },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text("Remover")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}
