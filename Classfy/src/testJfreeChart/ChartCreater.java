package testJfreeChart;

import java.awt.Color;  
import java.awt.Font;  
import java.awt.Rectangle;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.AxisLocation;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.labels.PieToolTipGenerator;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PiePlot;  
import org.jfree.chart.plot.Plot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.plot.XYPlot;  
import org.jfree.chart.renderer.BarRenderer;  
import org.jfree.chart.renderer.LineAndShapeRenderer;  
import org.jfree.chart.renderer.XYItemRenderer;  
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.CombinedDataset;  
import org.jfree.data.XYZDataset;  
import org.jfree.util.TableOrder;  
  
/** */  
/** 
 * @author xum @ 2006/09/14 neusoft TODO To change the template for this 
 *         generated type comment go to Window - Preferences - Java - Code Style 
 *         - Code Templates 
 */  
public class ChartCreater {  
  
    /** */  
    /** 
     * 2D��ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createCategoryChart() {  
  
        JFreeChart chart = ChartFactory.createBarChart("Bar2D", // ͼ������  
                "Category", // X������  
                "Value", // Y������  
                ChartDataSet.createCategoryDataset(),  
                PlotOrientation.VERTICAL, // ͼ����  
                true, // ͼ��  
                true, // Tooltips  
                false); // URL  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * 3D��ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createCategoryChart3D() {  
  
        JFreeChart chart = ChartFactory.createBarChart3D("Bar2D", "Category",  
                "Value", ChartDataSet.createCategoryDataset(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * 2D����ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createBarChart() {  
  
        JFreeChart chart = ChartFactory.createBarChart("Bar2D", "Category",  
                "Value", ChartDataSet.createCategoryDataset(),  
                PlotOrientation.HORIZONTAL, true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * 3D����ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createBarChart3D() {  
  
        JFreeChart chart = ChartFactory.createBarChart3D("Bar2D", "Category",  
                "Value", ChartDataSet.createCategoryDataset(),  
                PlotOrientation.HORIZONTAL, true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * 2D��ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createLintChart() {  
  
        JFreeChart chart = ChartFactory.createLineChart("Bar2D", "Category",  
                "Value", ChartDataSet.createCategoryDataset(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) plot  
                .getRenderer();  
        lineAndShapeRenderer.setItemLabelsVisible(true);  
        lineAndShapeRenderer.setShapesFilled(true);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * 3D��ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createLintChart3D() {  
  
        JFreeChart chart = ChartFactory.createBarChart3D("Bar2D", "Category",  
                "Value", ChartDataSet.createCategoryDataset(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * ���ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createAreaChart() {  
        JFreeChart chart = ChartFactory.createAreaChart("Area", "Category",  
                "Value", ChartDataSet.createCategoryDataset(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * �ٲ�ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createWaterfallChart() {  
        JFreeChart chart = ChartFactory.createWaterfallChart("WaterfallChart",  
                "Category", "Value", ChartDataSet.createCategoryDataset(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * 2D��ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createPieChart() {  
        JFreeChart chart = ChartFactory.createPieChart("PieChart", ChartDataSet  
                .createPieDataset(), true, true, false);  
  
        PiePlot piePlot = (PiePlot) chart.getPlot();  
  
        piePlot.setCircular(false);  
        piePlot.setLabelGap(0.02);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * 3D��ͼ 
     *  
     * @return 
     */  
    public static JFreeChart createPieChart3D() {  
        JFreeChart chart = ChartFactory.createPieChart3D("PieChart3D",  
                ChartDataSet.createPieDataset(), true, true, false);  
  
        PiePlot piePlot = (PiePlot) chart.getPlot();  
  
        piePlot.setCircular(false);  
        piePlot.setLabelGap(0.02);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * ���ϱ�ͼ 12 
     */  
    public static JFreeChart createMultiplePieChart() {  
        TableOrder order = TableOrder.BY_ROW;  
        // TableOrder order = TableOrder.BY_COLUMN;  
  
        JFreeChart chart = ChartFactory.createMultiplePieChart(  
                "MultiplePieChart", ChartDataSet.createCategoryDataset(),  
                order, true, true, false);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * ���ϱ�ͼ3D 12 
     */  
    public static JFreeChart createMultiplePieChart3D() {  
        TableOrder order = TableOrder.BY_ROW;  
        // TableOrder order = TableOrder.BY_COLUMN;  
  
        JFreeChart chart = ChartFactory.createMultiplePieChart3D(  
                "MultiplePieChart3D", ChartDataSet.createCategoryDataset(),  
                order, true, true, false);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * ����ͼ 
     */  
    public static JFreeChart createRingChart() {  
        JFreeChart chart = ChartFactory.createPieChart("RingChart",  
                ChartDataSet.createPieDataset(), true, true, false);  
  
        Plot ringPlot = (Plot) chart.getPlot();  
  
        ringPlot.setBackgroundAlpha(1.0f);  
        ((PiePlot) ringPlot).setCircular(false);  
        ((PiePlot) ringPlot).setLabelGap(0.02);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * ɢ��ͼ 40 
     */  
    public static JFreeChart createScatterPlot() {  
        JFreeChart chart = ChartFactory.createScatterPlot("ScatterPlot",  
                "Categary", "Value", ChartDataSet.createXYSeriesCollection(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        XYPlot xyPlot = chart.getXYPlot();  
  
        setXYSeriesPlot(xyPlot);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * ��֯ͼ 
     */  
    public static JFreeChart createHistogram() {  
        JFreeChart chart = ChartFactory.createHistogram("Histogram",  
                "Categary", "Value", ChartDataSet.createXYSeriesCollection(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        XYPlot xyPlot = chart.getXYPlot();  
  
        setXYSeriesPlot(xyPlot);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * ���ݵ����ͼ 33 
     */  
    public static JFreeChart createXYStepChart() {  
        JFreeChart chart = ChartFactory.createXYStepChart("XYStepChart",  
                "Categary", "Value", ChartDataSet.createXYSeriesCollection(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        XYPlot xyPlot = chart.getXYPlot();  
  
        setXYSeriesPlot(xyPlot);  
  
        return chart;  
    }  
  
    /**//* 
         * �ѻ���״ͼ 01 
         */  
    public static JFreeChart createStackedCategoryChart() {  
        JFreeChart chart = ChartFactory.createStackedBarChart(  
                "StackedCategoryChar", "Categary", "Value", ChartDataSet  
                        .createCategoryDataset(), PlotOrientation.VERTICAL,  
                true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        return chart;  
    }  
  
    /**//* 
         * �ѻ���״ͼ3D 04 
         */  
    public static JFreeChart createStackedCategoryChart3D() {  
        JFreeChart chart = ChartFactory.createStackedBarChart3D(  
                "StackedCategoryChar3D", "Categary", "Value", ChartDataSet  
                        .createCategoryDataset(), PlotOrientation.VERTICAL,  
                true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        return chart;  
    }  
  
    /**//* 
         * �ѻ�����ͼ 21 
         */  
    public static JFreeChart createStackedBarChart() {  
        JFreeChart chart = ChartFactory.createStackedBarChart(  
                "StackedBarChart", "Categary", "Value", ChartDataSet  
                        .createCategoryDataset(), PlotOrientation.HORIZONTAL,  
                true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);  
  
        return chart;  
    }  
  
    /**//* 
         * �ѻ�����ͼ3D 23 
         */  
    public static JFreeChart createStackedBarChart3D() {  
        JFreeChart chart = ChartFactory.createStackedBarChart3D(  
                "StackedBarChart3D", "Categary", "Value", ChartDataSet  
                        .createCategoryDataset(), PlotOrientation.HORIZONTAL,  
                true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);  
  
        return chart;  
    }  
  
    /**//* 
         * �ѻ����ͼ 53 
         */  
    public static JFreeChart createStackedAreaChart() {  
        JFreeChart chart = ChartFactory.createStackedAreaChart(  
                "StackedAreaChart", "Categary", "Value", ChartDataSet  
                        .createCategoryDataset(), PlotOrientation.VERTICAL,  
                true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        return chart;  
    }  
  
    /**//* 
         * XY���ݵ����ͼ 52 
         */  
    public static JFreeChart createXYAreaChart() {  
        JFreeChart chart = ChartFactory.createXYAreaChart("XYAreaChart",  
                "Categary", "Value", ChartDataSet.createXYSeriesCollection(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        XYPlot xyPlot = chart.getXYPlot();  
  
        setXYSeriesPlot(xyPlot);  
  
        return chart;  
    }  
  
    /**//* 
         * XY���ݵ�������ͼ 51 
         */  
    public static JFreeChart createXYStepAreaChart() {  
        JFreeChart chart = ChartFactory.createXYStepAreaChart(  
                "XYStepAreaChart", "Categary", "Value", ChartDataSet  
                        .createXYSeriesCollection(), PlotOrientation.VERTICAL,  
                true, true, false);  
  
        XYPlot xyPlot = chart.getXYPlot();  
  
        setXYSeriesPlot(xyPlot);  
  
        return chart;  
    }  
  
    /**//* 
         * XY���ݵ�����ͼ 32 
         */  
    public static JFreeChart createXYLineChart() {  
        JFreeChart chart = ChartFactory.createXYLineChart("XYLineChart",  
                "Categary", "Value", ChartDataSet.createXYSeriesCollection(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        XYPlot xyPlot = chart.getXYPlot();  
  
        setXYSeriesPlot(xyPlot);  
  
        XYItemRenderer lineAndShapeRenderer = (XYItemRenderer) xyPlot  
                .getRenderer();  
  
        return chart;  
    }  
  
    /**//* 
         * XY���ݵ���״ͼ 05 
         */  
    public static JFreeChart createXYBarChart() {  
        JFreeChart chart = ChartFactory.createXYBarChart("XYBarChart",  
                "Categary", true, "Value", ChartDataSet  
                        .createXYSeriesCollection(), PlotOrientation.VERTICAL,  
                true, true, false);  
  
        XYPlot xyPlot = chart.getXYPlot();  
  
        setXYSeriesPlot(xyPlot);  
  
        return chart;  
    }  
  
    /**//* 
         * ����ͼ 
         */  
    public static JFreeChart createBubbleChart() {  
        JFreeChart chart = ChartFactory.createBubbleChart("BubbleChart",  
                "Categary", "Value", (XYZDataset) ChartDataSet.createDefaultXYZDataset(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        return chart;  
    }  
  
    /**//* 
         * ���ݵ�ѻ����ͼ 
         */  
    public static JFreeChart createStackedXYAreaChart() {  
        JFreeChart chart = ChartFactory.createStackedXYAreaChart(  
                "StackedXYAreaChart", "Categary", "Value", ChartDataSet  
                        .createDefaultTableXYDataset(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        XYPlot xyPlot = chart.getXYPlot();  
  
        setXYSeriesPlot(xyPlot);  
  
        return chart;  
    }  
  
    /**//* 
         * ����ͼ 
         */  
    public static JFreeChart createGanttChart() {  
        JFreeChart chart = ChartFactory.createGanttChart("GanttChart",  
                "Categary", "Value", ChartDataSet.createGanttDataset(), true,  
                true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        return chart;  
    }  
  
    /**//* 
         * �ɼ�ͼ 
         */  
    public static JFreeChart createHighLowChart() {  
        JFreeChart chart = ChartFactory.createHighLowChart("HighLowChart",  
                "Categary", "Value", ChartDataSet.createDefaultOHLCDataset(),  
                false);  
  
        return chart;  
    }  
  
    /**//* 
         * ��̨ͼ 
         */  
    public static JFreeChart createCandlestickChart() {  
        JFreeChart chart = ChartFactory.createCandlestickChart(  
                "CandlestickChart", "Categary", "Value", ChartDataSet  
                        .createDefaultOHLCDataset(), false);  
        return chart;  
    }  
  
    /**//* 
         * �״�ͼ 
         */  
    private JFreeChart createPolarChart() {  
        JFreeChart chart = ChartFactory.createPolarChart("PolarChart",  
                ChartDataSet.createXYSeriesCollection(), true, true, false);  
  
        return chart;  
    }  
  
    /**//* 
         * 2D����ͼ 
         */  
    public static JFreeChart createCombinedChart() {  
        JFreeChart chart = ChartFactory.createBarChart("CombinedChart",  
                "Categary", "Value", ChartDataSet.createCategoryDataset(),  
                PlotOrientation.VERTICAL, true, true, false);  
  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
  
        setCategoryPlot(plot);  
  
        BarRenderer3D barRenderer = (BarRenderer3D) plot.getRenderer();  
        barRenderer.setMaximumBarWidth(0.10D);  
        barRenderer.setItemMargin(0.10D);  
  
        NumberAxis numberAxis = new NumberAxis("");  
        plot.setRangeAxis(1, numberAxis);  
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);  
        plot.setDataset(1, ChartDataSet.createCategoryDataset());  
        plot.setRenderer(1, new org.jfree.chart.renderer.category.LineAndShapeRenderer());  
        plot.mapDatasetToDomainAxis(1, 0);  
        plot.mapDatasetToRangeAxis(1, 1);  
  
        return chart;  
    }  
  
    /** */  
    /** 
     * ����CategoryPlot �����ἰ������������ 
     *  
     * @param plot 
     */  
    private static void setCategoryPlot(CategoryPlot plot) {  
        plot.getDomainAxis().setVisible(true);  
        plot.getDomainAxis().setLabelFont(new Font("����", Font.PLAIN, 12));  
        plot.getDomainAxis().setLabelPaint(Color.BLACK);  
        plot.getDomainAxis().setTickLabelFont(new Font("����", Font.PLAIN, 12));  
        plot.getDomainAxis().setTickLabelPaint(Color.BLACK);  
        plot.getDomainAxis().setTickLabelsVisible(true);  
  
        plot.getRangeAxis().setVisible(true);  
        plot.getRangeAxis().setLabelFont(new Font("����", Font.PLAIN, 12));  
        plot.getRangeAxis().setLabelPaint(Color.BLACK);  
        plot.getRangeAxis().setTickLabelFont(new Font("����", Font.PLAIN, 12));  
        plot.getRangeAxis().setTickLabelPaint(Color.BLACK);  
        plot.getRangeAxis().setVerticalTickLabels(false);  
        plot.getRangeAxis().setLabelAngle(0.0D);  
  
        plot.setDomainGridlinesVisible(true);  
        plot.setRangeGridlinesVisible(true);  
    }  
  
    /** */  
    /** 
     * ���� XYPlot 
     *  
     * @param plot 
     */  
    private static void setXYSeriesPlot(XYPlot plot) {  
        plot.getDomainAxis().setVisible(true);  
        plot.getDomainAxis().setLabelFont(new Font("����", Font.PLAIN, 12));  
        plot.getDomainAxis().setLabelPaint(Color.BLACK);  
        plot.getDomainAxis().setTickLabelFont(new Font("����", Font.PLAIN, 12));  
        plot.getDomainAxis().setTickLabelPaint(Color.BLACK);  
        plot.getDomainAxis().setTickLabelsVisible(true);  
  
        plot.getRangeAxis().setVisible(true);  
        plot.getRangeAxis().setLabelFont(new Font("����", Font.PLAIN, 12));  
        plot.getRangeAxis().setLabelPaint(Color.BLACK);  
        plot.getRangeAxis().setTickLabelFont(new Font("����", Font.PLAIN, 12));  
        plot.getRangeAxis().setTickLabelPaint(Color.BLACK);  
        plot.getRangeAxis().setVerticalTickLabels(false);  
        plot.getRangeAxis().setLabelAngle(0.0D);  
  
        plot.setDomainGridlinesVisible(true);  
        plot.setRangeGridlinesVisible(true);  
    }  
  
}  