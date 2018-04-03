package pacote;

import java.util.Random;

public class Principal {
	static Random rand = new Random();

	public static int[][] gerandoPopulacao(int numeroDeIndividuo, int numeroBitX, int numeroBitY) {
		int[][] individuos = new int[numeroDeIndividuo][numeroBitX + numeroBitY];

		for (int i = 0; i < individuos.length; i++) {
			for (int j = 0; j < individuos[0].length; j++) {
				individuos[i][j] = rand.nextInt(2);
			}
		}

		return individuos;
	}

	public static void mostra(int individuos[][], int numeroBitX) {
		for (int i = 0; i < individuos.length; i++) {
			for (int j = 0; j < individuos[0].length; j++) {
				System.out.print(individuos[i][j] + " ");
				if (j == (numeroBitX - 1)) {
					System.out.print("| ");
				}

			}
			System.out.println();
		}
	}

	public static void mostra(double individuos[][]) {
		for (int i = 0; i < individuos.length; i++) {
			for (int j = 0; j < individuos[0].length; j++) {
				System.out.print(individuos[i][j] + " ");
/*				if (j == (numeroBitX - 1)) {
					System.out.print("| ");
				}*/

			}
			System.out.println();
		}
	}

	public static int[][] converteBinarioInt(int individuos[][], int numeroBitX) {

		int individuosInteiro[][] = new int[individuos.length][2];
		String linha = "";

		int j = 0;
		for (int i = 0; i < individuosInteiro.length; i++) {
			for (j = 0; j < individuos[0].length; j++) {

				linha += individuos[i][j];
				if (j == numeroBitX - 1) {
					individuosInteiro[i][0] = Integer.parseInt(linha, 2);
					linha = "";
				} else if (j == individuos[0].length - 1) {
					individuosInteiro[i][1] = Integer.parseInt(linha, 2);
					linha = "";
				}
			}

		}

		return individuosInteiro;
	}

	public static double[][] converteIntParaReal(int individuosInteiro[][], int min, int max, int numeroBits) {
		double individuosReal[][] = new double[individuosInteiro.length][2];
		for (int i = 0; i < individuosReal.length; i++) {
			for (int j = 0; j < individuosReal[0].length; j++) {
				individuosReal[i][j] = min + (max - min) / (Math.pow(2, numeroBits) - 1) * individuosInteiro[i][j];
			}
		}

		return individuosReal;
	}

	public static double[] fitness(double[][] individuosReal) {
		double[] fitness = new double[individuosReal.length];
		for (int i = 0; i < individuosReal.length; i++) {

			fitness[i] = 4 - Math.pow(individuosReal[i][0], 2) - Math.pow(individuosReal[i][1], 2);
			fitness[i] = 1000 / (1 + Math.abs(fitness[i]));

		}
		return fitness;
	}

	public static int[] selecao(double[] fitness) {
		int[] indice = new int[fitness.length];
		double roleta[] = new double[fitness.length];
		double demoninador = 0;
		double rand2 = 0;
		double aux = 0;
		for (int i = 0; i < fitness.length; i++) {
			demoninador += fitness[i];
		}
		for (int i = 0; i < roleta.length; i++) {
			roleta[i] = aux + (fitness[i] / demoninador);
			aux += (fitness[i] / demoninador);
		}
		for (int i = 0; i < roleta.length; i++) {
			System.out.print(roleta[i] + " ");
		}
		System.out.println("\n________________Selecionados________________");
		for (int i = 0; i < indice.length; i++) {
			rand2 = rand.nextDouble();
			loop: for (int j = 0; j < roleta.length; j++) {
				if (rand2 < roleta[j]) {
					indice[i] = j;
					System.out.println("indice --> " + j + " rand --> " + rand2);
					break loop;
				}
			}

		}
		return indice;
	}

	public static int [][] crossover(int individuos[][], int[] indices, double probDoCrossover) {
		System.out.println("__________________Crossover_________________");
		int[][] individuosSelecionados = new int[indices.length][individuos[0].length];
		// selecionados
		for (int i = 0; i < individuosSelecionados.length; i++) {
			individuosSelecionados[i] = individuos[indices[i]];
			System.out.print("indice --> "+indices[i]+" individuo Selecionado --> ");
			for (int j = 0; j < individuosSelecionados.length; j++) {
				System.out.print(" "+individuosSelecionados[i][j]);
			}
			System.out.println();
		}

		int individuosRespostas[][] = new int[individuos.length][individuos[0].length];
		int ponto[] = new int[individuosRespostas.length / 2];
		int pontoAux = 0;
		double prob[] =  new double[individuosRespostas.length / 2];
		for (int i = 0; i < individuosRespostas.length / 2; i++) {
			prob[i] = rand.nextDouble();
			if (prob[i] <= probDoCrossover) {
				ponto[i] = rand.nextInt(individuos[0].length-1);
				ponto[i] +=1; 
				for (int duasVezes = 0; duasVezes < 2; duasVezes++) {
					pontoAux = 0;
					for (int j = 0; j < individuosSelecionados[0].length; j++) {
						if (pontoAux < ponto[i]) {
							individuosRespostas[i * 2+duasVezes][j] = individuosSelecionados[i * 2 + duasVezes][j];
						} else {
							individuosRespostas[i * 2 + (1-duasVezes)][j] = individuosSelecionados[i * 2 + duasVezes][j];
						}
						pontoAux++;
					}
				}

			} else {
				individuosRespostas[i * 2] = individuosSelecionados[i * 2];
				individuosRespostas[i * 2 + 1] = individuosSelecionados[i * 2 + 1];
			}
		}
		for (int i = 0; i < individuosRespostas.length; i++) {
			System.out.print(" individuo Selecionado --> ");
			for (int j = 0; j < individuosSelecionados.length; j++) {
				if(ponto[i/2] == j && prob[i/2] <=probDoCrossover){
					System.out.print("|");
				}
				System.out.print(" "+individuosSelecionados[i][j]);
			}
			System.out.print(" individuo Do Pós Crossover --> ");
			for (int j = 0; j < individuosSelecionados.length; j++) {
				if(ponto[i/2] == j && prob[i/2] <=probDoCrossover){
					System.out.print("|");
				}
				System.out.print(" "+individuosRespostas[i][j]);
			}
			System.out.print(" ponto["+i/2+"] --> "+ ponto[i/2]);
			System.out.println(" prob ["+i/2+"] --> "+prob[i/2]);
		}
		return individuosRespostas;
	}

	public static void main(String[] args) {

		int numeroDeIndividuo = 10;
		int numeroBitXY = 5;
		//int numeroBitY = 5;

		int individuos[][] = gerandoPopulacao(numeroDeIndividuo, numeroBitXY, numeroBitXY);
		System.out.println("             x                                           y");
		mostra(individuos, numeroBitXY);
		int individuosInteiro[][] = converteBinarioInt(individuos, numeroBitXY);
		System.out.println();
		System.out.println("  x       y");
		mostra(individuosInteiro, 1);
		double individuosReal[][] = converteIntParaReal(individuosInteiro, 0, 10, numeroBitXY);
		System.out.println("  x                  y");
		mostra(individuosReal);
		System.out.println("--------------Avaliação da Parada-------------");
		double[] fitness = fitness(individuosReal);
		for (int i = 0; i < fitness.length; i++) {
			System.out.println("fit i=" + i + "--> " + fitness[i]);
		}
		int indices[] = selecao(fitness);
		crossover(individuos, indices, 0.7);
	}

}
