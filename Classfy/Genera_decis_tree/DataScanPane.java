package Genera_decis_tree;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
/**
 * ��ѡ���򹫹���������PublicData���ú��ļ�ֵ����ִ��
 * ����һ��getTable���������Excel�������ֶ�����
 * ����һ��JTabel
 * @author Administrator
 *
 */
public class DataScanPane {
	JTable table;
	 public  JTable getTable(){
		 String str="";
			   try {   
		             Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));// �����е����ݱ仯������PublicData��   
		            //get a Sheet object.   
		             Sheet sheet = book.getSheet(0);     
		             System.out.println("��ȡ�ļ���"+PublicData.getFile()+"������"+sheet.getRows()+"������"+sheet.getColumns());
		            table=new JTable(sheet.getRows(),sheet.getColumns());//����Excel���������У���һ�г����е�JTable
		             for(int i=0;i<sheet.getRows();i++)
		             {
		            	 for(int j=0;j<sheet.getColumns();j++){
		            		 if(i==0){
		                       str=str+sheet.getCell(j, i).getContents()+",";		
		            		 }
		            		     Cell cell = sheet.getCell(j, i);   
		                         String result = cell.getContents().replaceAll("\"", "").trim();	
		     	    			 table.setValueAt(result,i,j);    		  
		            	  }
		             }
		             book.close();   
		         } catch (Exception e) {   
		             e.printStackTrace();   
		         }     
		 	  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 PublicData.setAttr(str.split(","));                      //ֱ�Ӷ�ȡ��Excel�ļ�����ҲҪͬ������������
		 return table;
	 }
}
