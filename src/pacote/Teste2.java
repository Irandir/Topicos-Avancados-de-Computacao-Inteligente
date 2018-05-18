package pacote;

public class Teste2 {

	public static double media(double[] array) {
		double r = 0;
		for (int i = 0; i < array.length; i++) {
			r += array[i];
		}
		r /= array.length;
		return r;
	}

	public static void main(String[] args) {
		// teste2
		int n = 10;
		AgoritmoGenetico ag2;
		double mediaMelhorFitness = 0;
		double fitnessMedioMedioDaPopulacao = 0;
		double mediaGeracaoDaConvergencia = 0;
		double mediaDoMelhorIndividuoSolucao = 0;

		double[] mediaMelhorFitnessAuxliar = new double[n];
		double[] fitnessMedioMedioDaPopulacaoAuxiliar = new double[n];
		double[] geracaoDaConvergenciaAuxliar = new double[n];
		double[] mediaDoMelhorIndividuoSolucaoAuxiliar = new double[n];
		double[] melhoresFitness;
		double[] fitnessMedio;
		double cros[] = { 0.1, 0.3, 0.5, 0.7 };
		double mul[] = { 0.01, 0.05, 0.1, 0.2 };
		

		mediaMelhorFitnessAuxliar = new double[n];
		fitnessMedioMedioDaPopulacaoAuxiliar = new double[n];
		geracaoDaConvergenciaAuxliar = new double[n];
		mediaDoMelhorIndividuoSolucaoAuxiliar = new double[n];
		
		double auxValorConvergencia = 0;
		int cont = 0;
		
		for (int h = 0; h < cros.length; h++) {
			for (int i = 0; i < mul.length; i++) {
				ag2 = new AgoritmoGenetico(300, 150, 26, mul[i], cros[h]);
				for (int t = 0; t < n; t++) {

					ag2.torneioEUniforme();
					melhoresFitness = ag2.getMelhoresFitness();
					fitnessMedio = ag2.getFitnessMedio();

					// convergencia
					auxValorConvergencia = melhoresFitness[0];
					cont = 0;

					// System.out.println("interacao -->"+i);
					for (int j = 0; j < fitnessMedio.length; j++) {
						mediaMelhorFitnessAuxliar[t] += melhoresFitness[j];
						fitnessMedioMedioDaPopulacaoAuxiliar[t] += fitnessMedio[j];
						//System.out.println("g-->" + j + " Melhor fit --> " +melhoresFitness[j] + "| fit medio--> " + fitnessMedio[j]);
						if(cont ==1){
							continue;
						}
						auxValorConvergencia = melhoresFitness[j];
						if (auxValorConvergencia == melhoresFitness[melhoresFitness.length - 1]) {
							geracaoDaConvergenciaAuxliar[t] = j;
							cont++;
						}
						
					}
					mediaMelhorFitnessAuxliar[t] /= melhoresFitness.length;
					fitnessMedioMedioDaPopulacaoAuxiliar[t] /= fitnessMedio.length;
					mediaDoMelhorIndividuoSolucaoAuxiliar[t] = melhoresFitness[melhoresFitness.length - 1];
					//System.out.println("--> "+mediaMelhorFitnessAuxliar[t]+" "+fitnessMedioMedioDaPopulacaoAuxiliar[t]+" "+mediaDoMelhorIndividuoSolucaoAuxiliar[t]+" "+geracaoDaConvergenciaAuxliar[t]+"\n________________");

				}
				
				mediaDoMelhorIndividuoSolucao = media(mediaDoMelhorIndividuoSolucaoAuxiliar);
				fitnessMedioMedioDaPopulacao = media(fitnessMedioMedioDaPopulacaoAuxiliar);
				mediaMelhorFitness = media(mediaMelhorFitnessAuxliar);
				mediaGeracaoDaConvergencia = media(geracaoDaConvergenciaAuxliar);
				
				System.out.println("cros -- >"+cros[h]+"  "+mul[i]);
				System.out.println(mediaMelhorFitness);
				System.out.println(fitnessMedioMedioDaPopulacao);
				System.out.println(mediaDoMelhorIndividuoSolucao);
				System.out.println(mediaGeracaoDaConvergencia);
				System.out.println("________________________________________________");
			}

		}
				
	}
}
