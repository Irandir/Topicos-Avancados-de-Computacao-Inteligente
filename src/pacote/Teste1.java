package pacote;

public class Teste1 {

    public static double media(double[] array) {
        double r = 0;
        for (int i = 0; i < array.length; i++) {
            r += array[i];
        }
        r /= array.length;
        return r;
    }


    public static void main(String[] args) {
        AgoritmoGenetico ag = new AgoritmoGenetico(300, 150, 26, 0.1, 0.6);
        int n = 10;
        //os graficos 

        double[] melhoresFitness;
        double[] fitnessMedio;

        double[] mediaMelhorFitness = new double[4];
        double[] fitnessMedioMedioDaPopulacao = new double[4];
        double[] mediaGeracaoDaConvergencia = new double[4];
        double[] mediaDoMelhorIndividuoSolucao = new double[4];

        double auxValorConvergencia = 0;
        int cont = 0;

        double[] mediaMelhorFitnessAuxliar;
        double[] fitnessMedioMedioDaPopulacaoAuxiliar;
        double[] geracaoDaConvergenciaAuxliar;
        double[] mediaDoMelhorIndividuoSolucaoAuxiliar;

        for (int contMetodoAG = 0; contMetodoAG < 4; contMetodoAG++) {
            mediaMelhorFitnessAuxliar = new double[n];
            fitnessMedioMedioDaPopulacaoAuxiliar = new double[n];
            geracaoDaConvergenciaAuxliar = new double[n];
            mediaDoMelhorIndividuoSolucaoAuxiliar = new double[n];

            for (int i = 0; i < n; i++) {
                //inciando o ag
                if (contMetodoAG == 0) {
                    ag.roletaEPonto();
                } else if (contMetodoAG == 1) {
                    ag.roletaEUniforme();
                } else if (contMetodoAG == 0) {
                    ag.torneioEPonto();
                } else if (contMetodoAG == 3) {
                    ag.torneioEUniforme();
                }

                melhoresFitness = ag.getMelhoresFitness();
                fitnessMedio = ag.getFitnessMedio();

                //convergencia
                auxValorConvergencia = melhoresFitness[0];
                cont = 0;

                for (int j = 0; j < fitnessMedio.length; j++) {
                    mediaMelhorFitnessAuxliar[i] += melhoresFitness[j];
                    fitnessMedioMedioDaPopulacaoAuxiliar[i] += fitnessMedio[j];
                    if(cont ==1){
						continue;
					}
					auxValorConvergencia = melhoresFitness[j];
					if (auxValorConvergencia == melhoresFitness[melhoresFitness.length - 1]) {
						geracaoDaConvergenciaAuxliar[i] = j;
						cont++;
					}
                }
                mediaMelhorFitnessAuxliar[i] /= melhoresFitness.length;
                fitnessMedioMedioDaPopulacaoAuxiliar[i] /= fitnessMedio.length;
                mediaDoMelhorIndividuoSolucaoAuxiliar[i] = melhoresFitness[melhoresFitness.length - 1];
                //System.out.println("-->"+mediaMelhorFitnessAuxliar[i]+"  "+fitnessMedioMedioDaPopulacaoAuxiliar[i]+"  "+mediaDoMelhorIndividuoSolucaoAuxiliar[i]+" "+geracaoDaConvergenciaAuxliar[i]+"\n________________");

            }

            mediaDoMelhorIndividuoSolucao[contMetodoAG] = media(mediaDoMelhorIndividuoSolucaoAuxiliar);
            fitnessMedioMedioDaPopulacao[contMetodoAG] = media(fitnessMedioMedioDaPopulacaoAuxiliar);
            mediaMelhorFitness[contMetodoAG] = media(mediaMelhorFitnessAuxliar);
            mediaGeracaoDaConvergencia[contMetodoAG] = media(geracaoDaConvergenciaAuxliar);

            /*if (contMetodoAG == 0) {
                System.out.println("roleta E Ponto");
            } else if (contMetodoAG == 1) {
                System.out.println("roleta E Uniforme");
            } else if (contMetodoAG == 2) {
                System.out.println("torneio E Ponto");
            } else if (contMetodoAG == 3) {
                System.out.println("torneio E Uniforme");
            }
            System.out.println("media melhor fit--> " + mediaMelhorFitness[contMetodoAG]);
            System.out.println("fit medio da populaï¿½ï¿½o --> " + fitnessMedioMedioDaPopulacao[contMetodoAG]);
            System.out.println("media fit soluï¿½ï¿½o --> " + mediaDoMelhorIndividuoSolucao[contMetodoAG]);
            System.out.println("media geracao de congergencia --> " + mediaGeracaoDaConvergencia[contMetodoAG]);
            System.out.println("____________________________________");*/
        }

        GraficoDeBarrasTeste1 g = new GraficoDeBarrasTeste1();
        g.mostrar(mediaDoMelhorIndividuoSolucao, "Media da Melhor Solução");
        g.mostrar(fitnessMedioMedioDaPopulacao, "Fitness Medio Medio DaPopulacao");
        g.mostrar(mediaMelhorFitness, "Media Melhor Fitness");
        g.mostrar(mediaGeracaoDaConvergencia, "Media Geracao Da Convergencia");

    }

    // = new AgoritmoGenetico(cont, cont, cont, auxValorConvergencia, auxValorConvergencia);
}

