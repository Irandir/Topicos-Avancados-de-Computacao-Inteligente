package pacote;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Teste2G {

	static List<String> n = new ArrayList<>();
	static List<Double> p = new ArrayList<>();
	static List<Double> p2 = new ArrayList<>();
	static List<Double> p3 = new ArrayList<>();
	static List<Double> p4 = new ArrayList<>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		leituraDeArquivo();
		
		for (int i = 0; i < n.size(); i++) {
			System.out.println(n.get(i));
			System.out.println(p.get(i));
			System.out.println(p2.get(i));
			System.out.println(p3.get(i));
			System.out.println(p4.get(i));
			System.out.println("_________________________________");
		}
		
		new GraficoDeBarrasTeste2().mostrar(p, n,"Media Melhor Fitness");
		new GraficoDeBarrasTeste2().mostrar(p2, n,"Fitness Medio Medio Da Populacao");
		new GraficoDeBarrasTeste2().mostrar(p3, n,"Media Do Melhor IndividuoSolucao");
		new GraficoDeBarrasTeste2().mostrar(p4, n,"Media Geracao Da Convergencia");
		
	}
	
	public static void leituraDeArquivo() {

		BufferedReader br = null;
		int Linha = 0;

		try {

			String path = Teste2G.class.getResource("teste2.txt").getPath();
			br = new BufferedReader(new FileReader(path));
			int aux = 0;
			String linha = null;
			boolean a;
			int cont = 0;
			double valor = 0;
			while ((linha = br.readLine()) != null) {
				a = linha.startsWith("cros");
				if (a == true) {
					n.add(linha);
					cont=-1;
				}else if(cont == 0){
					valor = Double.parseDouble(linha);
					p.add(valor);
				}else if(cont == 1){
					valor = Double.parseDouble(linha);
					p2.add(valor);
				}else if(cont == 2){
					valor = Double.parseDouble(linha);
					p3.add(valor);
				}else if(cont == 3){
					valor = Double.parseDouble(linha);
					p4.add(valor);
				}
				cont++;
			}

			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
}
