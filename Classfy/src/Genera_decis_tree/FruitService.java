package Genera_decis_tree;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
public class FruitService extends JPanel{
    public static JFreeChart createBarChart() {
        CategoryDataset dataset = getDataSet2();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "ˮ������ͼ", // ͼ�����
                "ˮ��", // Ŀ¼�����ʾ��ǩ
                "����", // ��ֵ�����ʾ��ǩ
                dataset, // ���ݼ�
                PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                true,   // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                true,   // �Ƿ����ɹ���
                true    // �Ƿ�����URL����
                );
        return chart;
    }
 
    private static CategoryDataset getDataSet2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "����", "ƻ��");
        dataset.addValue(100, "�Ϻ�", "ƻ��");
        dataset.addValue(100, "����", "ƻ��");
        dataset.addValue(200, "����", "����");
        dataset.addValue(200, "�Ϻ�", "����");
        dataset.addValue(200, "����", "����");
       // dataset.addValue(300, "����", "����");
        dataset.addValue(300, "�Ϻ�", "����");
        dataset.addValue(300, "����", "����");
        dataset.addValue(400, "����", "�㽶");
        dataset.addValue(400, "�Ϻ�", "�㽶");
        dataset.addValue(400, "����", "�㽶");
        dataset.addValue(500, "����", "��֦");
        dataset.addValue(500, "�Ϻ�", "��֦");
        dataset.addValue(500, "����", "��֦");
        return dataset;
    }
    public static JFreeChart createPaiChart() {
        DefaultPieDataset data = getDataSet();
        JFreeChart chart = ChartFactory.createPieChart3D("ˮ������ͼ",  // ͼ�����
        data,
        true, // �Ƿ���ʾͼ��
        false,
        false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        resetPiePlot(plot);
        return chart;
    }
   
    private static void resetPiePlot(PiePlot plot) {
        String unitSytle = "{0}={1}({2})";
       
        plot.setNoDataMessage("�޶�Ӧ�����ݣ������²�ѯ��");
        plot.setNoDataMessagePaint(Color.red);
       
        //ָ�� section �����ߵĺ��(OutlinePaint����Ϊnull)
        plot.setOutlineStroke(new BasicStroke(0));
        //���õ�һ�� section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���
        plot.setStartAngle(90);        
 
        plot.setToolTipGenerator(new StandardPieToolTipGenerator(unitSytle,
                NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
       
        //ָ��ͼƬ��͸����
        plot.setForegroundAlpha(0.65f);
       
        //������ǩ��ʾ��ʽ
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,
                NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
           
        //ͼ����ʾ��ʽ
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,
                NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
    }
 
    private static DefaultPieDataset getDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("ƻ��",100);
        dataset.setValue("����",200);
        dataset.setValue("����",300);
        dataset.setValue("�㽶",400);
        dataset.setValue("��֦",500);
        return dataset;
    }
 public static void main(String[] args){
	 JFrame f=new JFrame("ͼ��ʵ��");
	 FruitService fruit=new FruitService();
	 JFreeChart jc=fruit.createBarChart();
	 JFreeChart pie=fruit.createPaiChart();
	 JScrollPane jsp=new JScrollPane();
	 JScrollPane jp=new JScrollPane();
	 //������chart���panel���뵽frame��
	 ChartPanel pane1=new ChartPanel(jc,false);
	 ChartPanel pane2=new ChartPanel(pie,false);
	 jsp.setViewportView(pane1);
	 jp.setViewportView(pane2);
	 f.add(jsp,"South");
	 f.add(jp,"North");
	 f.setSize(600,800);
	 f.setVisible(true);
	 //������ֻ��һ��frame��ʾ�Ĵ���
	 //ChartFrame frame=new ChartFrame("��״ͼ����",jc); 
	 //frame.pack();
	 //frame.setVisible(true);
 }
}