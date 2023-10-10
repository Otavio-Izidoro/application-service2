package com.estudo.applicationservice.constants;

public class GPTConstants {

    public static final String QUIZ_PROMPT_TEMPLATE;

    static {
        StringBuilder builder = new StringBuilder();
        builder.append("Monte um quiz com 3 perguntas de multipla escolha contendo apenas uma resposta correta sobre o tema '{}'.");
        builder.append(" Apos a última pergunta, pule uma linha e escreva em apenas uma linha as letras respectivas das respostas corretas separando-as por vírgula.");
        builder.append(" Não digite nada alem das perguntas e da ultima linha com as respostas contendo apenas letras.");
        builder.append(" Caso nao seja possível construir nenhuma pergunta baseada no tema escreva apenas a palavra 'erro' e nada além disso.");
        builder.append(" Para validar se é possível construir a pergunta leve em consideração as seguintes pautas:");
        builder.append(" O tema vai ser escrito por um aluno, caso o tema nao faça sentido ou pareça ser alguma brincadeira então nao é valido.");
        builder.append(" O tema contém conteúdo misógino, racista, homofóbico ou que fere os direitos individuais entao nao é valido.");
        builder.append(" O tema é extremamente sensível e pode gerar perguntas duvidosas do ponto de vista ético e moral entao nao é valido.");
        builder.append(" Voce nao consegue gerar perguntas por ser um tema que nao está no seu conhecimento que vai até setembro de 2021 entao nao é valido.");
        builder.append(" Portanto em todos esses cenários, incluindo cenários nao listados em que voce nao conseguir gerar perguntas");
        builder.append(" retorne estritamente apenas 'erro', nada escrito antes e nada escrito depois.");
        QUIZ_PROMPT_TEMPLATE = builder.toString();
    }
    public static final String GPT_ROLE =  "assistant";
}
