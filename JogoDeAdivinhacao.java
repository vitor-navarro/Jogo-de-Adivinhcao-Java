// Para fins informativos, esse programa foi feito em:
// Java version: 21.0.3
// IDE: Eclipse 2024-06 (4.32.0)
// OS: Windows 10

import java.lang.classfile.instruction.SwitchCase;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JogoDeAdivinhacao {
	
	//Criação das váriaveis de controle
	private static int numeroInicial = 0;
	private static int numeroFinal = 100;
	private static int numeroDeTentativas = 0;
	private static int numeroAtual;

	public static void main(String[] args) {
		//mensagem de boas vindas
		System.out.println("Seja bem vindo ao jogo de Adivinhação de Números");
		System.out.println("Utilize os números indicados para navegar na aplicação");
		
		//inicia o jogo
		inicioDoPrograma();
	}

	public static void inicioDoPrograma() {
		//Menu de escolhas
		System.out.println("Intervalo Atual: " + numeroInicial + " até " + numeroFinal);
		System.out.println("(1) Iniciar jogo de Adivinhação de Números");
		System.out.println("(2) Mudar o intervalo atual");
		System.out.println("(0) Fechar jogo");

		//Criação do Scanner para leitura de entradas do usuário
		Scanner scanner =  new Scanner(System.in);

		//Função para detectar erros, no caso quando o usuário inserir algum caractere não condizente com o tipo especificado, tem de ser Tipo Inteiro
		try {
			int escolha = scanner.nextInt();

			//Estrutura para direcionar o usuário ao local onde ele escolheu
			switch(escolha) {
			
			//Inicia o jogo
			case 1: {
				jogar();
				break;
			} 
			
			//Entra na opção de mudar o intervalo
			case 2:{
				mudarIntervaloAtual();
				break;
			}
			
			//fechamento do jogo, com código 0 que significa finalizado com sucesso
			case 0:{
				System.out.println("Jogo De Adivinhação Finalizado, Obrigado por jogar");
				System.exit(0);
			} 
			
			//Caso o usuário colocar um número que não condiza com as opções irá para essa opção.
			default:{
				System.out.println("Por favor escolha um Número dentro das alternativas");
				inicioDoPrograma();
				break;
			}
			}
		} catch (InputMismatchException e) { // tratamento de erros, no caso quando o usuário colocar qualquer caractere que não seja número
			System.out.println("Por favor utilize apenas números para navegar");
			inicioDoPrograma();
		}
	}

	//Função para Iniciar o Jogo de Adivinhação
	private static void jogar() {
		
		//Gera o número aleatório de acordo com a função que criei.
		numeroAtual = randomizer();
		System.out.println("Eu pensei em um número entre " + numeroInicial + " e " + numeroFinal + ". Será que você consegue adivinhar qual é?");
		
		//Criação do Scanner para leitura de entradas do usuário
		Scanner scanner = new Scanner(System.in);
		try {
			
			//Variável local de controle
			boolean acertou = false;
			
			//Estrutura de repetição, enquanto o usuário não acertar não irá sair, caso digite sair ele irá sair também
			while (!acertou) {
				System.out.println("Digite SAIR para sair do jogo");
				System.out.println("Digite sua tentativa: ");
				
				//Leitura de qualquer coisa que o usuário inserir.
				String escolha = scanner.nextLine();
				
				//Caso o usuário digite sair, seja em caixa alta ou não,irá imprimir o resultado e enviar de volta para o inicio do programa
				if(escolha.equalsIgnoreCase("sair")) {
					imprimeResultado(acertou);
					numeroDeTentativas = 0;
					inicioDoPrograma();
					break;
				}
				
				//Variável local para controle do palpite do usuário
				int palpite;
				
				//Estrutura para detecção de excessões/erros
				try {
					
					//Tentará converter para inteiro, caso não de lança uma excessão.
					palpite = Integer.parseInt(escolha);
				} catch (NumberFormatException e) { //Caso não seja uma entrada válida, ou seja um número irá imprimir a mensagem a baixo e continuar o programa para a próxima repetição
					System.out.println("Entrada inválida! Por favor, digite um número.");
					continue;
				}
				
				//Caso a entrada seja um número irá incrementar a tentativa
				numeroDeTentativas++;
				
				//Estrutura de condição, para se o usuário acertou, ou não. Aqui é onde serão fornecidas dicas.
				if (palpite == numeroAtual) {
					
					//caso o usuário acerte, imprime a mensagem de parabéns, o resultado com o número de tentativas e volta para a tela inicial do programa
					System.out.println("Parabéns! Você acertou o número.");
					acertou = true;
					imprimeResultado(acertou);
					numeroDeTentativas = 0;
					inicioDoPrograma();
				} else if (palpite < numeroAtual) {
					//Caso o palpite sejá MENOR que o número gerado, irá imprimir a dica abaixo
					System.out.println("O número é maior que " + palpite + ". Tente novamente.");
				} else {
					//Caso o palpite sejá MAIOR que o número gerado, irá imprimir a dica abaixo
					System.out.println("O número é menor que " + palpite + ". Tente novamente.");
				}
			}


		} catch (Exception e) { //Caso ocorra algum erro não previsto na programação do jogo, irá voltar para o inicio.
			System.out.println("Falha desconhecida ocorreu, reiniciando o jogo");
			jogar();
		}
	}
	
	//Função criada para mudar o intervalo
	private static void mudarIntervaloAtual() {
		
		//Imprime os números Inicial e Final do intervalo
		System.out.println("O número inicial Atual é: " + numeroInicial);
		System.out.println("O número final Atual é: " + numeroFinal);

		//Criação do Scanner para leitura de entradas do usuário
		Scanner scanner = new Scanner(System.in);

		//Estrutura para detectar excessões/erros
		try {
			
			//Requisita qual o novo número inicial e o atribui a variável numeroInicial
			System.out.println("Qual o novo número inicial?");
			System.out.println("Caso não queira mudar digite o que era anteriormente.");
			numeroInicial = scanner.nextInt();

			//Requisita qual o novo número final e o coloca em uma variável local
			System.out.println("Qual o novo número Final?");
			System.out.println("Caso não queira mudar digite o que era anteriormente.");
			int numeroFinalEscolhido = scanner.nextInt();

			//Se o novo número final for menor ou igual ao número inicial, não irá permitir a mudança e irá chamar de novo a função mudarIntervaloAtual
			if(numeroFinalEscolhido <= numeroInicial) {
				System.out.println("O número final deve ser maior que o número inicial\n");
				mudarIntervaloAtual();
			} else {
				//Caso o novo número final for maior que o número inicial escolhido, irá ser alterada a variável de controle do numero Final
				numeroFinal = numeroFinalEscolhido;
			}

		} catch (InputMismatchException e) { // Caso o usuário insirá qualquer coisa que não seja um número irá cair nessa excessão
			System.out.println("Por favor insira um número válido");
			System.out.println();
			//Volta para a mesma função que está
			mudarIntervaloAtual();
		}

		//Assim que terminar o que precisa ser feito irá voltar ao inicio do programa
		inicioDoPrograma();
	}

	//Função para a criaçaõ de número aleatórios
	private static int randomizer() {
		
		//Cria o número aleatório dentro do intervalo desejado
		Random random = new Random();
		int numeroGerado = numeroInicial + random.nextInt((numeroFinal - numeroInicial) + 1);
		
		//Pode ser feito o mesmo com a classe Math:
		//int numeroGerado = numeroInicial + (int)(Math.random() * ((numeroFinal - numeroInicial) + 1));

		//Retorna o número aleatório
		return numeroGerado;
	}

	//Imprime o resultado da partida, recebe se o usuário acertou ou não
	private static void imprimeResultado(Boolean acertou) {

		System.out.println();

		//Caso o usuário não tenha acertado irá imprimir a mensagem dentro desse if
		if(!acertou) {
			System.out.println("Você não conseguiu acertar o número");
		} 

		//Impressão padrão da função, o número que erá, e o número de tentativas
		System.out.println("O número era " +  numeroAtual);
		System.out.println("Você fez um total de " + numeroDeTentativas + " tentativas");
		System.out.println();
	}
}
