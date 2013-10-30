package TestExcel;
import java.io.*;   
import jxl.*;   
public class ReadExcel {    
	public static int Sum_Of_Row;
    public void read(String name,int Sum_Of_Attr) {   
        try {   
             Workbook book = Workbook.getWorkbook(new File(name));   
            //get a Sheet object.   
             Sheet sheet = book.getSheet(0);      
             for(int i=0;i<sheet.getRows();i++)
             {
            	  for(int j=0;j<Sum_Of_Attr;j++){
            		     Cell cell = sheet.getCell(j, i);   
                         String result = cell.getContents();   
                         System.out.print(result+"\t");      		  
            	  }
            	  System.out.println("������"+i);
             }
             Sum_Of_Row=sheet.getRows();
             book.close();   
         } catch (Exception e) {   
             e.printStackTrace();   
         }   
     }   
    public int getRows(){            //����Excel��������
    	return Sum_Of_Row;
    }
}  