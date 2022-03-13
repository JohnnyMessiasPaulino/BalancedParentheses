package br.cefsa.compiladores.balancedparentheses;

import java.io.IOException;

public class Principal_BalancedParentheses {

	public static void main(String[] args) throws IOException 
	{
		String enderecoTxtLeitura = "D:\\Teste\\validar.txt";
		String enderecoTxtGravacao = "D:\\Teste\\validar-check.txt";
		String conteudo = Utilitarios.lerArquivoTxt(enderecoTxtLeitura);
		
		String conteudoProcessado = Utilitarios.validaLinhas(conteudo);
		
		Utilitarios.gravarArquivoTxt(enderecoTxtGravacao, conteudoProcessado);

	}

}
