package br.com.treinamento.colaboradores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.treinamento.colaboradores.componentes.TelaCadastroColaboradores
import br.com.treinamento.colaboradores.ui.theme.CadastroColaboradoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CadastroColaboradoresTheme {
                TelaCadastroColaboradores()
            }
        }
    }
}
