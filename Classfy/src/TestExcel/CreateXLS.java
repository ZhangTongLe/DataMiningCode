package TestExcel;
import java.io.*;   
import jxl.*;   
import jxl.write.*;   
  
/**
* Ĭ�ϴ���d:/Temp.xls�ļ�
*/  
public class CreateXLS {   
    private static int j=0;      //�ڶ�ȡ�ļ������У�����������i���ϵĴ�0��ʼ������j��ʼ��Ϊ0
    public static int o=0;
    public void create(String name,String[] Attr){ 
        try {   
            //�½��ļ���
             WritableWorkbook book = Workbook.createWorkbook(new File("D:/�����ھ�/�����ھ�����/1/adult.xls"));     
             WritableSheet sheet = book.createSheet("Sheet_1", 0);                 
             try{          	    
        		   FileReader fr=new FileReader(name);
        		   BufferedReader br=new BufferedReader(fr);
        		   String s;
        	       while((s=br.readLine())!=null)
          		 {
        	     //String[] msg=new String(s).toLowerCase().replaceAll("\\(|\\)"," ").trim().split(",|\\.|def-instance|state|control|no-of-students|male:female ratio|student:faculty ratio|sat verbal|sat math|expenses|percent-financial-aid|no-applicants|percent-admittance|percent-enrolled|academics scale|social scale|quality-of-life scale|academic-emphasis"); 
          	     String[] msg=new String(s).trim().split(",|\\.");
          	   for(int i=0;i<msg.length;i++){
        	    	 if(j==0)
        	    	 {
        	    		 for(int m=0;m<Attr.length;m++)
        	    		 {
        	    			 Label lab=new Label(m,0,Attr[m]);
        	    			 sheet.addCell(lab);
        	    		 }                  //������һ��������
        	    		 j++;
        	    	 }	 
        	    	//  else if(j!=0&&i!=0&&i%(Attr.length+1)==0)
	               //       {   j++;  }
  	                else if(j!=0&&i%Attr.length==0)
  	                     j++;
  	                
                   Label label = new Label(i, j, msg[i]);     //��ʼ�����������⣬��Ҫ�����еİ����ո񲿷ֵ�ɾ��
                   sheet.addCell(label); 	    	
        	            }
          	        }
          	    
          	 }catch(IOException e)
          	         {
          				e.printStackTrace();
          				System.out.println("cannot find file");   }    
             book.write();     
             book.close();   
         } catch (Exception e) 
            {   
             e.printStackTrace();   
            }   
     }   
}