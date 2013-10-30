package TestExcel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TransferExcel {
	public static int Sum_Of_Row;
    private static int j=1;
	public static void  main(String[] args){
		//ReadExcel re=new ReadExcel();
	
		TransferExcel te=new TransferExcel();
		String name="D:/�����ھ�/�����ھ�����/4/bank-full.xls";
		String[] attr={"age","job","marital","education","default","balance","housing","loan","contact","day","month","duration","campaign","pdays","previous","poutcome","y"};
		te.read(name,1);
	}
	 public void read(String name,int Sum_Of_Attr) {   
		 int k=0;
	        try {   
	        	  //FileWriter file=new FileWriter(new File("D:\\�����ھ�\\�����ھ�����\\4\\temp2.txt"),true);
	             //BufferedWriter bw=new BufferedWriter(file);
	            //get a Sheet object.
	        	
	        	WritableWorkbook book2 = Workbook.createWorkbook(new File("D:/�����ھ�/�����ھ�����/4/bank-full2.xls"));     
	             WritableSheet sheet2 = book2.createSheet("������һ", 0);  
	        	
	             Workbook book = Workbook.getWorkbook(new File(name));     
	             Sheet sheet = book.getSheet(0);      
	             for(int i=0;i<sheet.getRows()||k<sheet2.getRows();i++,k++)
	             {
	            	  for(int j=0;j<Sum_Of_Attr;j++){
	            		     Cell cell = sheet.getCell(j, i);   
	                         String result = cell.getContents();   
	                         //bw.write(result.replaceAll(";", ","));
	                         //bw.flush();
	                         
	                         String[] msg=new String(result).trim().split(";"); 
	                         for(int l=0;l<msg.length;l++)
	                         {
	                        	  Label label = new Label(l, k, msg[l]);
	                        	  System.out.print(msg[l].toString()+"\t");
	                              sheet2.addCell(label); 
	                              System.out.print("cell="+label.getContents());
	                         }
	                         		  
	            	  }
	            	  System.out.println("������"+i);
	             }
	             Sum_Of_Row=sheet.getRows();
	             book.close(); 
	             book2.write();   //����û�д��У�ǰ��д��Ķ�������
	             book2.close();
	         } catch (Exception e) {   
	             e.printStackTrace();   
	             System.out.println("failed to create xls!");
	         }   
	
	 }
	
}
