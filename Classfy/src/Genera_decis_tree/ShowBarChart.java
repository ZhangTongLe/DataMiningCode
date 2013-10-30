package Genera_decis_tree;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ShowBarChart extends JFrame{

	
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
	public void Look(){
		ShowBarChart sbc=new ShowBarChart();
		sbc.setTitle("ֱ��ͼ");
		 JFreeChart jc=sbc.createBarChart();
		 ChartPanel pane1=new ChartPanel(jc,false);
		 JScrollPane jsp=new JScrollPane();
		jsp.setViewportView(pane1);
		sbc.add(jsp,"Center");
		sbc.setSize(400,600);
		sbc.setVisible(true);
		
	}
	public static void main(String[] args){
		ShowBarChart sb=new ShowBarChart();
		sb.Look();
	}
	
}
