package br.cefsa.compiladores.balancedparentheses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class Utilitarios 
{
	public static String lerArquivoTxt(String endereco)
	{
		Scanner ler = new Scanner(System.in);
		String conteudo = "";
		
		try {
		      FileReader arq = new FileReader(endereco);
		      BufferedReader lerArq = new BufferedReader(arq);

		      String linha = lerArq.readLine();
		      conteudo += linha + "\n";

		      while (linha != null) {

		        linha = lerArq.readLine();
		        
		        if(linha != null)
		        	conteudo += linha + "\n";
		      }
		      arq.close();
		    } 
		catch (IOException e) 
		{
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		}
		return conteudo;
	}
	
	public static void gravarArquivoTxt(String endereco, String conteudo) throws IOException
	{
		 FileWriter arq = new FileWriter(endereco);
		 PrintWriter gravarArq = new PrintWriter(arq);
		 
		 gravarArq.printf(conteudo);
		 
		 arq.close();
		 
	}
	
	public static String validaLinhas(String texto)
	{
		String conteudoValidado = "";
		Stack<String> pilhaAbertos  = new Stack<String>();
		String[] textoLinhas = texto.split("\n");
		String caracterAtual = "";
		String caracterDesempilhado = "";
		boolean[] linhaEstaOk = new boolean[textoLinhas.length];
		
		//Empilhar todos os abertos
		//Se houver um fechado, desempilhar para verificar se foi aberto
		//Se o ultimo fechar um que não foi aberto...linha invalida
		//Verificar se a pilha foi consumida totalmente... se sim linha ok
		
		
		// Este for itera pelas linhas do arquivo texto
		for (int i = 0; i < textoLinhas.length ; i++)
		{
			char[] linhaAtualEmChars = textoLinhas[i].toCharArray();
			linhaEstaOk[i] = false;
			pilhaAbertos.clear();
			
			// Este for itera pelos caracteres de cada linha
			for(int k = 0; k < linhaAtualEmChars.length; k++)
			{
				caracterAtual = String.valueOf(linhaAtualEmChars[k]);
				
				// If para empilhar o caracter atual que "abre"
				if(caracterAtual.equals("(") ||
						caracterAtual.equals("[") ||
						caracterAtual.equals("{") ||
						caracterAtual.equals("<"))
				{
					pilhaAbertos.add(caracterAtual);
				}
				
				// If para verificar se o caracter atual fecha
				if(caracterAtual.equals(")") ||
						caracterAtual.equals("]") ||
						caracterAtual.equals("}") ||
						caracterAtual.equals(">"))
				{
					// Se o caracter atual fechar, as ações abaixo devem ser feitas
					// 1 - Verificar se a pilha esta vazia, se estiver o mesmo não foi aberto, já podendo invalidar a linha
					// 2 - Se a pilha não estiver vazia, devera ser desempilhado um item para verificar se corresponde ao que fechou
					// 2.1 - Se corresponder, mudar a flag para linha OK
					// 2.2 - Se não corresponder, mudar a flag para linha invalida
					
					if(!pilhaAbertos.empty())
						caracterDesempilhado = pilhaAbertos.pop();
					else
					{
						linhaEstaOk[i] = false;
						break;
					}
						
					
					if(caracterDesempilhado.equals("(") && caracterAtual.equals(")") ||
							caracterDesempilhado.equals("[") && caracterAtual.equals("]") ||
							caracterDesempilhado.equals("{") && caracterAtual.equals("}") ||
							caracterDesempilhado.equals("<") && caracterAtual.equals(">"))
					{
						linhaEstaOk[i] = true;
					}
					else
					{
						linhaEstaOk[i] = false;
					}
				}
					
			} // Aqui termina o for que itera sobre os caracteres da linha
			
			// verifica se a pilha de abertos ainda tem itens, se sim a linha é invalida.
			if(!pilhaAbertos.empty())
				linhaEstaOk[i] = false;
			
			//Se o boolean for verdadeiro, a linha esta ok
			if(linhaEstaOk[i])
				textoLinhas[i] += " - OK";
			else
				textoLinhas[i] += " - Invalido";
			
			//Concatenar valor da linha em string para retornar, para gravação do texto.
			conteudoValidado += textoLinhas[i] + "\n";
			
		} // Aqui terminha o for que itera sobre as linhas do arquivo texto
		
		return conteudoValidado;
	}

}
