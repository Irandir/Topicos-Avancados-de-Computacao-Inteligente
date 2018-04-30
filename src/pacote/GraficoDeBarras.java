package pacote;


import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.orsoncharts.label.StandardCategoryItemLabelGenerator;

public class GraficoDeBarras extends JFrame {


	public static void mostrar(double v1[],String nome) {
		DefaultCategoryDataset dados = new DefaultCategoryDataset();

		dados.addValue(v1[0],"Roleta E Ponto","");
		dados.addValue(v1[1],"Roleta E Uniforme","");
		dados.addValue(v1[2],"Torneio E Ponto","");
		dados.addValue(v1[3],"Torneio E Uniforme","");

		JFreeChart grafico = ChartFactory.createBarChart(nome, "metodo", "Saída", dados, PlotOrientation.VERTICAL,
				true, true, true);
		CategoryPlot plot = (CategoryPlot) grafico.getPlot();
		CategoryItemRenderer itemRerender = plot.getRenderer();
		//Caso vc queira mudar a cor das barras
		itemRerender.setSeriesPaint(0, Color.blue);
		itemRerender.setSeriesPaint(1, Color.RED);
		itemRerender.setSeriesPaint(2, Color.YELLOW);
		itemRerender.setSeriesPaint(3, Color.green);
		
		JFrame frame = new JFrame(nome);
		frame.add(new ChartPanel(grafico));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	

	}
	

}
