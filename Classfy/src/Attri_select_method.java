
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Genera_decis_tree.CreateXLS;
import Genera_decis_tree.DataScanPane;
import Genera_decis_tree.InformationGain;
import Genera_decis_tree.PublicData;
import Genera_decis_tree.ShowTreePane;
/**
 * ���ݱ�ʱ��Ҫ�޸ĵĴ��벿�֣�
 * InformationGain��c2�ı��������yes|recomm����������
 * �����е�ing.getInformationGain(temp, (String[])attr, 8, 6); �е������к������
 * @author Shartoo
 *
 */
public class Attri_select_method{
     static CreateXLS cls=new CreateXLS();    // ��������ı��ļ����룬ת����Excel�ļ�
     static DataScanPane dsp=new DataScanPane();
     Object data[][]={};     
     static Object[] attr;
	 static InformationGain ing=new InformationGain();     //������Ϣ������
	// static ShowStatis ss=new ShowStatis();
     public void createUI(){  	 
    	 attr=PublicData.getAttr();          //����GetArrayList��ȡ�����ֶ�����
    	 
    	 ShowTreePane stp=new ShowTreePane();
    	 stp.setPreferredSize(new Dimension(400,200));
    	/** 
    	 TestPane tp=new TestPane();
    	 tp.setPreferredSize(new Dimension(200,200));//��������PerferedSizeֵ����JPanel�߽���ڣ�100,100���Ͳ���������ʾ
    	 JScrollPane jsp=new JScrollPane(tp);          //����JScrollPanel�Ͳ�����ʾ��TP
    	 
    	 jsp.setViewportView(stp);
    	 */
         Scanner sc=new Scanner(System.in);
   	     System.out.println("��ָ��Դ�ļ����ı���ʽ����");
    	 String name=sc.next();
    	  
    	  //�ȴ���Excel�ļ����ٶ�ȡExcel�ļ������е�Sum_Of_Attr����ָ������
          cls.create(name,(String[])attr);
          //������Ϣ�����Ƿ�õ�
          String temp="d:/Temp.xls";
          //for(int i=0;i<attr.length;i++)
          //{
        //	  System.out.println("--------------");
       // 	  System.out.println("����"+attr[i]+"����Ϣ����Ϊ��"
       //	  +ing.getInformationGain(temp, (String[])attr, 4, i));
        //	  ing.clear();
       //  }
          
          ing.getInformationGain(temp, (String[])attr, 8, 6); 
          //ss.setAttr(ing.getList());
          //ss.setNum(ing.getValue());
          //ss.createUI();
          ing.clear();
    	  JFrame jf=new JFrame("�������");
    	  JScrollPane pane=new JScrollPane(dsp.getTable());
    	 
    	  jf.add("Center",pane);
    	 // jf.add(jsp,"East");
    	  jf.setSize(500,600);
    	   
    	  jf.addWindowListener(new WindowAdapter(){
    		  public void windowClosing(WindowEvent e){
    			  System.exit(0);
    		  }
    	  });
    	  jf.setVisible(true);
    	 
     }
}   
