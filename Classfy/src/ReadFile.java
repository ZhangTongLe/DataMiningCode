
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public void Read(){
    	  Scanner sc=new Scanner(System.in);
    	  System.out.println("��ָ��Դ�ļ����ı���ʽ����");
     	  String name=sc.next();
      try{
     	    
  		   FileReader fr=new FileReader(name);
  		   BufferedReader br=new BufferedReader(fr);
  		   String s;
  	       while((s=br.readLine())!=null)
    		 {
  			 String[] msg=new String(s).trim().split(","); 
    	     for(int i=0;i<msg.length;i++){ 
    	    	// System.out.println("SUm_of_attr="+Sum_Of_Attr);
                   System.out.print("���ݣ�"+msg[i]+"\t");
                   if(i%9==0) System.out.println();
    	            }
    		 } 
    	 }catch(IOException e){
    				e.printStackTrace();
    			}
    }
    public static void main(String[] args){
    	new ReadFile().Read();
    }
}
