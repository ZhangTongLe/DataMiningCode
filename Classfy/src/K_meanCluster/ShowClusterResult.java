package K_meanCluster;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Genera_decis_tree.PublicData;

public class ShowClusterResult {
	 public static final String clusterResult="Data/Cluster/clusterResult.txt";
	 public static final String clusterInitCore="Data/Cluster/clusterInitCore.txt";
	 public static final Color color[]={Color.cyan,Color.lightGray,Color.blue,Color.green,Color.magenta,Color.orange,Color.pink};
   	 public int clusternum=PublicData.getClusterNum();
	 public String[] attr=PublicData.getClusterAttr();
     JFrame f=new JFrame("���پ�������ʾ");
     DefaultTableCellRenderer tcr;
     Image icon = Toolkit.getDefaultToolkit().getImage("img/logo.jpg");
     public void show()
     {
    	 f.setIconImage(icon);
         f.setLocation(100,200);
         JScrollPane pane=new JScrollPane();
         pane.setViewportView(this.getTable());
    	 f.getContentPane().add(pane);
         f.setVisible(true);
         f.setSize(f.getPreferredSize());
    	 
     }
     public JTable getTable(){
    	 JTable table;
    	 ArrayList<ArrayList<String>> realList=this.getRealCore();
    	 ArrayList<ArrayList<String>> initList=this.getInitCore();
         int[] color=new int[attr.length];
         for(int i=0;i<color.length;i++){
        	 color[i]=i+5;
         }
         
         
    	 for(int i=0;i<realList.size();i++){
    		 for(int j=0;j<realList.get(0).size();j++){
    			 System.out.print("realList��ֵ��"+realList.get(i).get(j)+"\t");
    		 }
    		 System.out.println();
    	 }
    	 
    	 
    	 
    	 int attrnum=attr.length; 
    	 table=new JTable(realList.size()+2,realList.get(1).size());    //��2���ǵ�һ����Ϊ����У��ڶ�����ʾ��ʼ�������������
    	 
    	 table.setValueAt("�������",0,0);       //����JTbale�������Ͻ���ʾ����
    	for(int i=0;i<attrnum;i++){
    		for(int j=0;j<clusternum;j++){
    			if(i==0)
    			  {   table.setValueAt(attr[i],0,j+1);
    			       System.out.println("�˴���ֵ�ǣ�"+attr[i]);
    			  
    			  }
    			
    			else
    			  {
    				table.setValueAt(attr[i],0,i*clusternum+j+1);
    				 System.out.println("�˴���ֵ��(�ǵ�һ��)��"+attr[i]);
    			  }	
    		}
    	}
    	//������д������
    	//����д���ʼ��������
    	table.setValueAt("��ʼ��������",1,0);
       	for(int i=0;i<attrnum;i++){
    		for(int j=0;j<clusternum;j++){
    			if(i==0)
    			  {   table.setValueAt(initList.get(i).get(j+1),1,j+1);
    			       System.out.println("�˴���ֵ�ǣ�"+attr[i]);
    			  
    			  }
    			
    			else
    			  {
    				table.setValueAt(initList.get(i).get(j+1),1,i*clusternum+j+1);
    				 System.out.println("�˴���ֵ��(�ǵ�һ��)��"+attr[i]);
    			  }	
    		}
    	}
    	//�ٶ���ɹ��������Ľ��
       	System.out.println("���Ǵ�����table��������"+table.getRowCount()+"�г�����"+table.getColumnCount());
       	this.setCellRenerer();    
       	for(int i=0;i<realList.size();i++){
       		table.setValueAt(i, i+2, 0);
       		for(int j=0;j<realList.get(i).size()-1;j++){
			      			
       			table.setValueAt(realList.get(i).get(j), i+2, j+1);
       		   table.getColumn(table.getColumnName(j)).setCellRenderer(tcr);
            
       			
       		  // table.setBackground(new Color(color[i%attr.length])); 
       		}  		
       	}
    	 return table;
     }
     
     
    
     //�����ʼ�������ģ���ArrayList���أ�ע����ṹ����list�е�Сlist�ĵ�һ��λ�ô洢�������������������б��е�λ�ö�����BigDecimal����
     public ArrayList<ArrayList<String>> getInitCore(){
    	 ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
    	 String f="";
    	 try{
    		 BufferedReader read=new BufferedReader(new FileReader(clusterInitCore)); 		 
    		 while((f=read.readLine())!=null)
    		 {
    		  ArrayList<String> arr=new ArrayList<String>();
    		  String[] s=f.split("\\\t");
    		  System.out.println("�����ʼ��������-----------");
    		  for(int i=0;i<s.length;i++)
    		    {
    			   if(s[i]!=null)
    			         {
    			    	  arr.add(s[i]);
    			    	  System.out.print(s[i]+"\t");
    			         }
    		   }
	         list.add(arr);
    		 } 
    		 System.out.println("initCore��list�����˼���"+list.size());
    	 }
    	 catch(Exception e){
    		 e.printStackTrace();
    	 } 
    	 return list;
     }
  //���ؾ����������ʼ�������ƣ�������ĳ�ʼ����洢�ṹ��ͬ�����������Կ����ı��ļ���
     public ArrayList<ArrayList<String>> getRealCore(){
    	 ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
    	 int attrnum=PublicData.getClusterNum();
    	 int selnum=PublicData.getClusterAttr().length;
    	 String f="";
    	 int j=0;              //������������Լ�����ģ�û�����塣�����Զ���
    	 try{
    		 BufferedReader read=new BufferedReader(new FileReader(clusterResult));
    		 while((f=read.readLine())!=null)
    		 {
    			 j++;   			 
    			 if(j>2)
    			 {
    		       ArrayList<String> arr=new ArrayList<String>();
    		//�ո�,�Ʊ�����Ƚ��в�֣�Ҳ����˵���ǰ��հײ��ֽ��в�֣���������հ�ʹ��ʲô�������µ�,����ո�� tab�������߻س�������       
    		       String[] s=f.split("\\s+",PublicData.getClusterNum()*attr.length+2);
    		      // String[] s=f.split("/s",PublicData.getClusterNum()*attr.length+2);
    		       System.out.println("����ѡ��Ĳ�ֳ�����"+PublicData.getClusterNum()*attr.length+2+"\t"+f);
    		       for(int i=0;i<s.length;i++)
    		       {
    			      if(s[i]!=null&&s[i]!="")
    			         {
    			    	  arr.add(s[i]);
    			         }
    		       }
    		       if(!(arr.size()<attrnum*selnum+1))        //����Ī���ļ�����ҵ����ݣ����ò�ȥ��һ��������
	                    list.add(arr);
    			}
    		 } 
    		 read.close();
    	 }
    	 catch(IOException e){
             e.printStackTrace();	
    	 }
    	 return list;
     }
     //��������ĳ�л���ĳ�е�ɫ�ķ���
     public void setCellRenerer(){
    	 int num=0;
    	 tcr = new DefaultTableCellRenderer() {
    	        public Component getTableCellRendererComponent(JTable table,
    	            Object value, boolean isSelected, boolean hasFocus,
    	            int row, int column) {
    	        	int m=column-1;
    	        	if(column==0)
    	        		setBackground(Color.white);
    	        	else
    	               setBackground(color[m/clusternum]); //����ż���е�ɫ
    	          return super.getTableCellRendererComponent(table, value,
    	              isSelected, hasFocus, row, column);
    	        }
    	      }; 
     }

   public static void main(String[] args){
	   ShowClusterResult result=new ShowClusterResult();
	   result.show();
   }
}

