package pacote;


import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoDeBarrasTeste2 extends JFrame {


	public static void mostrar(List<Double> v1,List<String> nome,String nome2) {
		DefaultCategoryDataset dados = new DefaultCategoryDataset();
		
		for (int i = 0; i < v1.size(); i++) {
			dados.addValue(v1.get(i),nome.get(i),"");
		}
		

		JFreeChart grafico = ChartFactory.createBarChart(nome2, "torneio e uniforme", "Saída", dados, PlotOrientation.VERTICAL,
				true, true, true);
		CategoryPlot plot = (CategoryPlot) grafico.getPlot();
		CategoryItemRenderer itemRerender = plot.getRenderer();
		//Caso vc queira mudar a cor das barras
		itemRerender.setSeriesPaint(0, Color.blue);
		itemRerender.setSeriesPaint(1, Color.RED);
		itemRerender.setSeriesPaint(2, Color.YELLOW);
		itemRerender.setSeriesPaint(3, Color.green);
		
		JFrame frame = new JFrame();
		frame.add(new ChartPanel(grafico));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	

	}
	

}
