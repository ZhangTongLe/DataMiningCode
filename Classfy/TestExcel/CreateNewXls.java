package TestExcel;
import java.io.*;   
import jxl.*;   
import jxl.write.*;   
  
/**
* Ĭ�ϴ���d:/Temp.xls�ļ�
*/  
public class CreateNewXls {   
	    private static int j=0;      //�ڶ�ȡ�ļ������У�����������i���ϵĴ�0��ʼ������j��ʼ��Ϊ0
	    public void create(String name,String[] Attr){ 
	        try {   
	            //�½��ļ���
	             WritableWorkbook book = Workbook.createWorkbook(new File("D:/�����ھ�/�����ھ�����/5/university.xls"));     
	             WritableSheet sheet = book.createSheet("Sheet_1", 0);                 
	             try{          	    
	        		   FileReader fr=new FileReader(name);
	        		   BufferedReader br=new BufferedReader(fr);
	        		   String s;
	        	       while((s=br.readLine())!=null)
	          		 {
	        	     String[] msg=new String(s).toLowerCase().replaceAll("\\(|\\)"," ").trim().split("def-instance|state|control|no-of-students|male:female ratio|student:faculty ratio|sat verbal|sat math|expenses|percent-financial-aid|no-applicants|percent-admittance|percent-enrolled|academics scale|social scale|quality-of-life scale|academic-emphasis"); 
	          	     if(j==0){
	          	    	 for(int m=0;m<Attr.length;m++){
	          	    		 Label label=new Label(m,j,Attr[m]);
	          	    		 sheet.addCell(label);
	          	    	 }
	          	    	j++; 
	          	     }
	          else{
	        	     
	        	     for(int i=0;i<Attr.length;i++)
	          	     {     
	          	    	 String str=null;
	          	       for(int k=0;k<msg.length;k++){
	          	    	   if(msg[k]!=null)
	          	    		   str=msg[k];
	          	       }
	          	       
	                  Label label = new Label(i, j, str);
	                  sheet.addCell(label);     	         	 
	          	      }
	          	     j++;
	          	  }
	          		 }     
	          	 }catch(IOException e){
	          				e.printStackTrace();
	          				System.out.println("cannot find file");
	          			}    
	             book.write();     
	             book.close();   
	         } catch (Exception e) {   
	             e.printStackTrace();   
	         }   
	     }   
	} 	

