package br.com.treinamento.colaboradores.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.treinamento.colaboradores.modelos.Colaborador
import br.com.treinamento.colaboradores.modelos.Nivel
import br.com.treinamento.colaboradores.ui.theme.CadastroColaboradoresTheme

@Composable
fun TelaCadastroColaboradores() {
    val colaboradores = remember { mutableStateListOf<Colaborador>() }

    var proximoId by remember { mutableIntStateOf(1) }
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nivel by remember { mutableStateOf(Nivel.ADMINISTRATIVO) }
    var colaboradorEditando by remember { mutableStateOf<Colaborador?>(null) }
    var colaboradorExcluindo by remember { mutableStateOf<Colaborador?>(null) }
    var mensagemErro by remember { mutableStateOf("") }

    fun cancelarAcao() {
        nome = ""
        email = ""
        nivel = Nivel.ADMINISTRATIVO
        colaboradorEditando = null
        mensagemErro = ""
    }

    fun salvarColaborador() {
        if (nome.isBlank()) {
            mensagemErro = "Informe o nome do colaborador."
            return
        }

        if (email.isBlank()) {
            mensagemErro = "Informe o e-mail do colaborador."
            return
        }

        val colaboradorAtual = colaboradorEditando

        if (colaboradorAtual == null) {
            colaboradores.add(
                Colaborador(
                    id = proximoId,
                    nome = nome.trim(),
                    email = email.trim(),
                    nivel = nivel
                )
            )
            proximoId++
        } else {
            val posicao = colaboradores.indexOfFirst { it.id == colaboradorAtual.id }
            if (posicao >= 0) {
                colaboradores[posicao] = colaboradorAtual.copy(
                    nome = nome.trim(),
                    email = email.trim(),
                    nivel = nivel
                )
            }
        }

        cancelarAcao()
    }

    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Gestão de Colaboradores",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Cadastro, edição, remoção e listagem em memória",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF64748B)
            )
        }

        FormularioColaborador(
            nome = nome,
            email = email,
            nivel = nivel,
            editando = colaboradorEditando != null,
            mensagemErro = mensagemErro,
            onNomeChange = { nome = it },
            onEmailChange = { email = it },
            onNivelChange = { nivel = it },
            onSalvar = { salvarColaborador() },
            onCancelar = { cancelarAcao() }
        )

        Text(
            text = "Colaboradores cadastrados: ${colaboradores.size}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        ListagemColaboradores(
            colaboradores = colaboradores,
            onEditar = { colaborador ->
                colaboradorEditando = colaborador
                nome = colaborador.nome
                email = colaborador.email
                nivel = colaborador.nivel
                mensagemErro = ""
            },
            onRemover = { colaborador ->
                colaboradorExcluindo = colaborador
            }
        )
    }

    colaboradorExcluindo?.let { colaborador ->
        DialogExcluir(
            colaborador = colaborador,
            onConfirmar = {
                colaboradores.remove(colaborador)
                colaboradorExcluindo = null

                if (colaboradorEditando?.id == colaborador.id) {
                    cancelarAcao()
                }
            },
            onCancelar = {
                colaboradorExcluindo = null
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TelaCadastroColaboradoresPreview() {
    CadastroColaboradoresTheme {
        TelaCadastroColaboradores()
    }
}
