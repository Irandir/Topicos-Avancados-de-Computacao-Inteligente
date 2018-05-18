package pacote;
import java.util.Random;

public class AgoritmoGenetico {

	private double melhoresFitness[];
	private double fitnessMedio[];
        
	private static Random rand = new Random();

	//no construtor
	private int numeroDeGeracoes;
	private int numeroDeIndividuo;
	private int numeroBitXY;
	private double probM;
	private double probC;
	
	public AgoritmoGenetico(int numeroDeGeracoes, int numeroDeIndividuo, int numeroBitXY, double probM, double probC) {
		super();
		this.numeroDeGeracoes = numeroDeGeracoes;
		this.numeroDeIndividuo = numeroDeIndividuo;
		this.numeroBitXY = numeroBitXY;
		this.probM = probM;
		this.probC = probC;
	}
	
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
		System.out.println("__________Popula��o_________");
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
		System.out.println("__________Popula��o_Real_________");
		for (int i = 0; i < individuos.length; i++) {
			for (int j = 0; j < individuos[0].length; j++) {
				System.out.print(individuos[i][j] + " ");
				/*
				 * if (j == (numeroBitX - 1)) { System.out.print("| "); }
				 */

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
		//System.out.println("____________________________");
		for (int i = 0; i < individuosReal.length; i++) {

			fitness[i] = Math.pow(individuosReal[i][0], 2) + Math.pow(individuosReal[i][1], 2);
			//System.out.println("Fun��o original (4 - x� - y�) -->"+(4-Math.pow(individuosReal[i][0], 2) - Math.pow(individuosReal[i][1], 2)));
			fitness[i] = 1000 / (1 + Math.abs(fitness[i]));

		}
		 //System.out.println("_______________________________");

		return fitness;
	}

	public static int[] selecaoRoleta(double[] fitness) {
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
		/*
		 * for (int i = 0; i < roleta.length; i++) { System.out.print(roleta[i] + " ");
		 * }
		 */
		// System.out.println("\n________________Selecionados________________");
		for (int i = 0; i < indice.length; i++) {
			rand2 = rand.nextDouble();
			loop: for (int j = 0; j < roleta.length; j++) {
				if (rand2 < roleta[j]) {
					indice[i] = j;
					// System.out.println("indice --> " + j + " rand --> " + rand2);
					break loop;
				}
			}

		}
		return indice;
	}

	public static int[][] crossoverPonto(int individuos[][], int[] indices, double probDoCrossover) {
		// System.out.println("__________________Crossover_________________");
		int[][] individuosSelecionados = new int[indices.length][individuos[0].length];
		// selecionados
		for (int i = 0; i < individuosSelecionados.length; i++) {
			individuosSelecionados[i] = individuos[indices[i]];
			/*
			 * System.out.print("indice --> "+indices[i]+" individuo Selecionado --> "); for
			 * (int j = 0; j < individuosSelecionados.length; j++) {
			 * System.out.print(" "+individuosSelecionados[i][j]); } System.out.println();
			 */
		}

		int individuosRespostas[][] = new int[individuos.length][individuos[0].length];
		int ponto[] = new int[individuosRespostas.length / 2];
		int pontoAux = 0;
		double prob[] = new double[individuosRespostas.length / 2];
		for (int i = 0; i < individuosRespostas.length / 2; i++) {
			prob[i] = rand.nextDouble();
			if (prob[i] <= probDoCrossover) {
				ponto[i] = rand.nextInt(individuos[0].length - 1);
				ponto[i] += 1;
				for (int duasVezes = 0; duasVezes < 2; duasVezes++) {
					pontoAux = 0;
					for (int j = 0; j < individuosSelecionados[0].length; j++) {
						if (pontoAux < ponto[i]) {
							individuosRespostas[i * 2 + duasVezes][j] = individuosSelecionados[i * 2 + duasVezes][j];
						} else {
							individuosRespostas[i * 2 + (1 - duasVezes)][j] = individuosSelecionados[i * 2
									+ duasVezes][j];
						}
						pontoAux++;
					}
				}

			} else {
				individuosRespostas[i * 2] = individuosSelecionados[i * 2];
				individuosRespostas[i * 2 + 1] = individuosSelecionados[i * 2 + 1];
			}
		}
		/*
		 * for (int i = 0; i < individuosRespostas.length; i++) {
		 * System.out.print(" individuo Selecionado --> "); for (int j = 0; j <
		 * individuosSelecionados.length; j++) { if(ponto[i/2] == j && prob[i/2]
		 * <=probDoCrossover){ System.out.print("|"); }
		 * System.out.print(" "+individuosSelecionados[i][j]); }
		 * System.out.print(" individuo Do P�s Crossover --> "); for (int j = 0; j <
		 * individuosSelecionados.length; j++) { if(ponto[i/2] == j && prob[i/2]
		 * <=probDoCrossover){ System.out.print("|"); }
		 * System.out.print(" "+individuosRespostas[i][j]); }
		 * System.out.print(" ponto["+i/2+"] --> "+ ponto[i/2]);
		 * System.out.println(" prob ["+i/2+"] --> "+prob[i/2]); }
		 */
		return individuosRespostas;
	}

	public static int[][] multacao(int[][] individuosPosCruzamento, double probDaMultacao) {
		// System.out.println("__________________________Multa��o__________________________");
		int[][] individuosMultados = new int[individuosPosCruzamento.length][individuosPosCruzamento[0].length];
		for (int i = 0; i < individuosMultados.length; i++) {
			for (int j = 0; j < individuosMultados[0].length; j++) {
				individuosMultados[i][j] = individuosPosCruzamento[i][j];
			}
		}
		double m = 0;
		for (int i = 0; i < individuosMultados.length; i++) {
			for (int j = 0; j < individuosMultados[0].length; j++) {
				m = rand.nextDouble();
				if (m <= probDaMultacao) {
					if (individuosMultados[i][j] == 0) {
						individuosMultados[i][j] = 1;
					} else {
						individuosMultados[i][j] = 0;
					}
				}

			}
		}
		/*
		 * System.out.println("Antes"); for (int i = 0; i < individuosMultados.length;
		 * i++) { for (int j = 0; j < individuosMultados[0].length; j++) {
		 * System.out.print(individuosPosCruzamento[i][j]+" "); } System.out.println();
		 * } System.out.println("Depois"); for (int i = 0; i <
		 * individuosMultados.length; i++) { for (int j = 0; j <
		 * individuosMultados[0].length; j++) {
		 * System.out.print(individuosMultados[i][j]+" "); } System.out.println(); }
		 */
		return individuosMultados;
	}

	public static int[] melhorIndividuo(int[][] individuos, double[] fitness) {
		int[] individuoR = new int[individuos[0].length];
		int indice = 0;
		double aux = 0;
		for (int i = 0; i < fitness.length; i++) {
			if (aux < fitness[i]) {
				aux = fitness[i];
				indice = i;
			}
		}
		for (int i = 0; i < individuoR.length; i++) {
			individuoR[i] = individuos[indice][i];
		}
		
		/* System.out.print("Melhor individuo --> "); for (int i = 0; i <
		 individuoR.length; i++) { System.out.print(individuoR[i]+" "); }
		 System.out.println();*/
		 
		return individuoR;
	}

	public static double mediaDoFitness(double[] fitness) {
		double aux = 0;
		for (int i = 0; i < fitness.length; i++) {
			aux += fitness[i];
		}
		aux = aux / fitness.length;
		// System.out.println("Media -->"+aux);
		return aux / fitness.length;
	}

	public static int[][] eletimo(int[][] individuos, int[] melhorIndividuo, double[] fitness) {
		int[][] individuosPosEletimo = new int[individuos.length][individuos[0].length];
		int indiceDoPio = -1;
		double aux = 0;
		for (int i = 0; i < fitness.length; i++) {
			if (i == 0) {
				aux = fitness[i];
				indiceDoPio = i;
			} else if (fitness[i] < aux) {
				aux = fitness[i];
				indiceDoPio = i;
			}
		}

		for (int i = 0; i < individuosPosEletimo.length; i++) {
			for (int j = 0; j < individuosPosEletimo[0].length; j++) {
				individuosPosEletimo[i][j] = individuos[i][j];
			}
		}
		for (int i = 0; i < individuosPosEletimo[0].length; i++) {
			individuosPosEletimo[indiceDoPio][i] = melhorIndividuo[i];
		}
		/*
		 * System.out.println("______________Eletimos____________"); for (int i = 0; i <
		 * individuosPosEletimo.length; i++) {
		 * 
		 * for (int j = 0; j < individuosPosEletimo[0].length; j++) {
		 * System.out.print(individuosPosEletimo[i][j]+" "); } System.out.print("|");
		 * for (int j = 0; j < individuos.length; j++) {
		 * System.out.print(individuos[i][j]+" "); }
		 * 
		 * System.out.print(fitness[i]+" ");
		 * 
		 * System.out.println(); } System.out.println("___________________");
		 */
		return individuosPosEletimo;
	}

	public static int[] selecaoTorneio(double[] fitness, int n) {
		int[] indice = new int[fitness.length];
		double torneio[][] = new double[fitness.length][n];
		int indAux = 0;
		int ind = 0;
		double fit = 0;
		double aux = 0;
		double p;
		for (int i = 0; i < fitness.length; i++) {
			for (int j = 0; j < n; j++) {
				indAux = rand.nextInt(fitness.length);
				if (aux <= fitness[indAux]) {
					aux = fitness[indAux];
					ind = indAux;
				}
				indice[i] = ind;
			}
		}

		return indice;
	}

	public static int[][] crossoverUniforme(int individuos[][], int[] indices, double probDoCrossover) {
		int[][] individuosSelecionados = new int[indices.length][individuos[0].length];
		int[][] individuosRespostas = new int[indices.length][individuos[0].length];
		for (int i = 0; i < individuosSelecionados.length; i++) {
			for (int j = 0; j < individuosSelecionados[0].length; j++) {
				individuosSelecionados[i][j] = individuos[indices[i]][j];
			}
		}
		double randAux = rand.nextDouble();
		int mascara[] = new int[individuosSelecionados[0].length];
		for (int i = 0; i < individuosRespostas.length / 2; i++) {
			randAux = rand.nextDouble();
			if (randAux > probDoCrossover) {
				individuosRespostas[i * 2] = individuosSelecionados[i * 2];
				individuosRespostas[i * 2 + 1] = individuosSelecionados[i * 2 + 1];
			} else {
				for (int j = 0; j < mascara.length; j++) {
					mascara[j] = rand.nextInt(2);
				}
				for (int j = 0; j < mascara.length; j++) {
					if (mascara[j] == 0) {
						individuosRespostas[i * 2][j] = individuosSelecionados[i * 2][j];
						individuosRespostas[i * 2 + 1][j] = individuosSelecionados[i * 2 + 1][j];
					} else {
						individuosRespostas[i * 2][j] = individuosSelecionados[i * 2 + 1][j];
						individuosRespostas[i * 2 + 1][j] = individuosSelecionados[i * 2][j];
					}
				}
			}
		}
		return individuosRespostas;
	}

	public static double melhorFitness(double[] fitness) {
		double r = fitness[0];
		for (int i = 0; i < fitness.length; i++) {
			if (r < fitness[i]) {
				r = fitness[i];
			}
		}
		return r;
	}

	// roletaEPonto 1
	public void roletaEPonto() {

		double[] fitness = null;
		// int numeroBitY = 5;

		int individuos[][] = gerandoPopulacao(numeroDeIndividuo, numeroBitXY, numeroBitXY);
		int individuosInteiro[][];
		double individuosReal[][];
		// double media = 0;
		int melhorIndividuo[] = null;

		melhoresFitness = new double[numeroDeGeracoes];
		fitnessMedio = new double[numeroDeGeracoes];
		for (int contGeracoes = 0; contGeracoes < numeroDeGeracoes; contGeracoes++) {

			// mostra(individuos, numeroBitXY);
			individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
			// mostra(individuosInteiro, 1);
			individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
			// mostra(individuosReal)
			// fitness = fitness(individuosReal);
			if (contGeracoes > 0) {
				try {
					individuos = eletimo(individuos, melhorIndividuo, fitness);
					individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
					individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
				} catch (NullPointerException e) {
					// TODO: handle exception
				}
			}
			fitness = fitness(individuosReal);
			melhorIndividuo = melhorIndividuo(individuos, fitness);
			melhoresFitness[contGeracoes] = melhorFitness(fitness);

			int indices[] = selecaoRoleta(fitness);
			int individuosPosCruzamento[][] = crossoverPonto(individuos, indices, probC);
			individuos = multacao(individuosPosCruzamento, probM);
			fitnessMedio[contGeracoes] = mediaDoFitness(fitness);

		}

	}

	// roletaEUniforme 2
	public void roletaEUniforme() {


		double[] fitness = null;
		// int numeroBitY = 5;

		int individuos[][] = gerandoPopulacao(numeroDeIndividuo, numeroBitXY, numeroBitXY);
		int individuosInteiro[][];
		double individuosReal[][];
		// double media = 0;
		int melhorIndividuo[] = null;

		melhoresFitness = new double[numeroDeGeracoes];
		fitnessMedio = new double[numeroDeGeracoes];
		for (int contGeracoes = 0; contGeracoes < numeroDeGeracoes; contGeracoes++) {

			// mostra(individuos, numeroBitXY);
			individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
			// mostra(individuosInteiro, 1);
			individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
			// mostra(individuosReal)
			// fitness = fitness(individuosReal);
			if (contGeracoes > 0) {
				try {
					individuos = eletimo(individuos, melhorIndividuo, fitness);
					individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
					individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
				} catch (NullPointerException e) {
					// TODO: handle exception
				}
			}
			fitness = fitness(individuosReal);
			melhorIndividuo = melhorIndividuo(individuos, fitness);
			melhoresFitness[contGeracoes] = melhorFitness(fitness);

			int indices[] = selecaoRoleta(fitness);
			int individuosPosCruzamento[][] = crossoverUniforme(individuos, indices, probC);
			individuos = multacao(individuosPosCruzamento, probM);
			fitnessMedio[contGeracoes] = mediaDoFitness(fitness);

		}

	}

	// torneioEUniforme 3
	public void torneioEUniforme() {

		double[] fitness = null;
		// int numeroBitY = 5;

		int individuos[][] = gerandoPopulacao(numeroDeIndividuo, numeroBitXY, numeroBitXY);
		int individuosInteiro[][];
		double individuosReal[][];
		// double media = 0;
		int melhorIndividuo[] = null;

		melhoresFitness = new double[numeroDeGeracoes];
		fitnessMedio = new double[numeroDeGeracoes];
		for (int contGeracoes = 0; contGeracoes < numeroDeGeracoes; contGeracoes++) {

			// mostra(individuos, numeroBitXY);
			individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
			// mostra(individuosInteiro, 1);
			individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
			// mostra(individuosReal)
			// fitness = fitness(individuosReal);
			if (contGeracoes > 0) {
				try {
					individuos = eletimo(individuos, melhorIndividuo, fitness);
					individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
					individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
				} catch (NullPointerException e) {
					// TODO: handle exception
				}
			}
			fitness = fitness(individuosReal);
			melhorIndividuo = melhorIndividuo(individuos, fitness);
			melhoresFitness[contGeracoes] = melhorFitness(fitness);

			int indices[] = selecaoTorneio(fitness, 3);
			int individuosPosCruzamento[][] = crossoverUniforme(individuos, indices, probC);
			individuos = multacao(individuosPosCruzamento, probM);
			fitnessMedio[contGeracoes] = mediaDoFitness(fitness);

		}

	}

	// torneioEPonto 4
	public void torneioEPonto() {

		double[] fitness = null;
		// int numeroBitY = 5;

		int individuos[][] = gerandoPopulacao(numeroDeIndividuo, numeroBitXY, numeroBitXY);
		int individuosInteiro[][];
		double individuosReal[][];
		// double media = 0;
		int melhorIndividuo[] = null;

		melhoresFitness = new double[numeroDeGeracoes];
		fitnessMedio = new double[numeroDeGeracoes];
		for (int contGeracoes = 0; contGeracoes < numeroDeGeracoes; contGeracoes++) {

			// mostra(individuos, numeroBitXY);
			individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
			// mostra(individuosInteiro, 1);
			individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
			// mostra(individuosReal)
			// fitness = fitness(individuosReal);
			if (contGeracoes > 0) {
				try {
					individuos = eletimo(individuos, melhorIndividuo, fitness);
					individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
					individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
				} catch (NullPointerException e) {
					// TODO: handle exception
				}
			}
			fitness = fitness(individuosReal);
			melhorIndividuo = melhorIndividuo(individuos, fitness);
			melhoresFitness[contGeracoes] = melhorFitness(fitness);

			int indices[] = selecaoTorneio(fitness, 3);
			int individuosPosCruzamento[][] = crossoverPonto(individuos, indices, probC);
			individuos = multacao(individuosPosCruzamento, probM);
			fitnessMedio[contGeracoes] = mediaDoFitness(fitness);

		}

	}

	// teste
		public static void main(String[]args) {

			double[] fitness = null;
			// int numeroBitY = 5;
			int numeroDeIndividuo = 10;
			int numeroBitXY = 10;
			int numeroDeGeracoes = 300;
			int individuos[][] = gerandoPopulacao(numeroDeIndividuo, numeroBitXY, numeroBitXY);
			int individuosInteiro[][];
			double individuosReal[][];
			// double media = 0;
			int melhorIndividuo[] = null;

			//melhoresFitness = new double[numeroDeGeracoes];
			//fitnessMedio = new double[numeroDeGeracoes];
			for (int contGeracoes = 0; contGeracoes < numeroDeGeracoes; contGeracoes++) {

				// mostra(individuos, numeroBitXY);
				individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
				// mostra(individuosInteiro, 1);
				individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
				// mostra(individuosReal)
				// fitness = fitness(individuosReal);
				if (contGeracoes > 0) {
					try {
						individuos = eletimo(individuos, melhorIndividuo, fitness);
						individuosInteiro = converteBinarioInt(individuos, numeroBitXY);
						individuosReal = converteIntParaReal(individuosInteiro, 0, 16, numeroBitXY);
					} catch (NullPointerException e) {
						// TODO: handle exception
					}
				}
				fitness = fitness(individuosReal);
				melhorIndividuo = melhorIndividuo(individuos, fitness);
				//melhoresFitness[contGeracoes] = melhorFitness(fitness);
				System.out.println(melhorFitness(fitness));
				int indices[] = selecaoRoleta(fitness);
				int individuosPosCruzamento[][] = crossoverPonto(individuos, indices, 0.7);
				individuos = multacao(individuosPosCruzamento, 0.05);
				//fitnessMedio[contGeracoes] = mediaDoFitness(fitness);

			}

		}
	
	
	public double[] getMelhoresFitness() {
		return melhoresFitness;
	}

	public void setMelhoresFitness(double[] melhoresFitness) {
		this.melhoresFitness = melhoresFitness;
	}

	public double[] getFitnessMedio() {
		return fitnessMedio;
	}

	public void setFitnessMedio(double[] fitnessMedio) {
		this.fitnessMedio = fitnessMedio;
	}

}