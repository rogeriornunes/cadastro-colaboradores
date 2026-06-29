package br.com.treinamento.colaboradores.modelos

data class Colaborador(
    val id: Int,
    val nome: String,
    val email: String,
    val nivel: Nivel
)
