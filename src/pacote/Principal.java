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
	
	public static void mostra(double individuos[][], int numeroBitX) {
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

	
	public static double[][] converteIntParaReal(int individuosInteiro[][], int min, int max,int numeroBits ) {
		double individuosReal[][] = new double[individuosInteiro.length][2];
		for (int i = 0; i < individuosReal.length; i++) {
			for (int j = 0; j < individuosReal[0].length; j++) {
				
				individuosReal[i][j] = min+(max-min)/(Math.pow(2, numeroBits)-1)*individuosInteiro[i][j];
			}
		}
		
		return individuosReal;
	}
	
	
	public static void main(String[] args) {

		int numeroDeIndividuo = 10;
		int numeroBitX = 3;
		int numeroBitY = 3;

		int individuos[][] = gerandoPopulacao(numeroDeIndividuo, numeroBitX, numeroBitY);
		System.out.println("             x                                           y");
		mostra(individuos, numeroBitX);
		int individuosInteiro[][] = converteBinarioInt(individuos, numeroBitX);
		System.out.println();
		System.out.println("  x       y");
		mostra(individuosInteiro, 1);
		double individuosReal[][]  = converteIntParaReal(individuosInteiro, 0, 5, 3);
		System.out.println("  x                  y");
		mostra(individuosReal, 1);
	}

}
