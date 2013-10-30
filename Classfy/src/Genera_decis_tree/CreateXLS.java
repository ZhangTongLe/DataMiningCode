package Genera_decis_tree;
import java.io.*;   
import jxl.*;   
import jxl.write.*;   
import jxl.write.Number;
  
/**
* Ĭ�ϴ���d:/predata/Temp.xls�ļ�
* �ڴ���Excel�ļ���ͬʱ����ͬ����������������,����File���ó�Ĭ�ϴ������ļ���
*/  
public class CreateXLS {   
    private static int j=0;      //�ڶ�ȡ�ļ������У�����������i���ϵĴ�0��ʼ������j��ʼ��Ϊ0
    public void create(String name,String[] Attr){ 
        try {   
            //�½��ļ���
        	String[] n=name.split("\\.");        //���ݵ�ǰ�����txt�ļ����ٸ��ļ��ĵ�ǰĿ¼������ͬ����Excel�ļ�
        	String xls=n[0]+".xls";
             WritableWorkbook book = Workbook.createWorkbook(new File(xls));   
             WritableSheet sheet = book.createSheet("Sheet_1", 0);                 
             try{                 	   
        		   FileReader fr=new FileReader(name);
        		   BufferedReader br=new BufferedReader(fr);
        		   String s;
        		   //����д��Excel��ĵ�һ�б����
        			 for(int m=0;m<Attr.length;m++)
      	    		 {
      	    			 Label lab=new Label(m,0,Attr[m]);
      	    			 sheet.addCell(lab);
      	    		 }                  //������һ��������   
      	           int column=0;   //�����ı���index��������readline֮ǰ��   
        	       while((s=br.readLine())!=null)
          	       {
        	         String[] msg=new String(s.replaceAll("\"", "")).trim().split(","); //�Զ���Ϊ�ָ���
        	         //String[] msg=s.split("[\\t \\n]+");                                         //�Կո�Ϊ�ָ�����
          	        for(int i=0;i<msg.length;i++)
          	        {
          	          if(i%Attr.length==0)
       	                {   j++;
       	                   column=0;    //ͬʱindex��Ϊ0
       	                }
          	           
          	      // WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false);//ָ�����ʽ
     
                  Label label = new Label(column, j, msg[i]);    
                  column++;
                  sheet.addCell(label);            
          	     }
          	} 
          }catch(IOException e){
          				e.printStackTrace();
          			}    
             book.write();     
             book.close();   
         } catch (Exception e) {   
             e.printStackTrace();   
         }   
     }   
} 