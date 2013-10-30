package Genera_decis_tree;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ShowTreePane extends JPanel{
     public static ArrayList<TreeNode> list=new ArrayList<TreeNode>(); // list�����洢node���͵Ľ�㣬��Treeʱֱ�Ӵ�list��ȡֵ
     public float height=600;
     public float width=1200;
     public float  nodeW;    //ÿ�������Ŀ��
     public float  R;     //��Բ���뾶
     public int nodeX=300;   //��������ͷ����x��y����
     public int nodeY=50;
     public static final int Node_width=70;
     public static final int Node_height=50;
     //public static final int lag=
     public static String Head_Node_Name;
     InformationGain ing=new InformationGain();
     public HashMap<String,String[]>  attrmap=ing.getMap();
     public HashMap<String,ArrayList<String>> attrAndPart=new HashMap<String,ArrayList<String>>(); 
 	 ArrayList<String> lt=new ArrayList<String>(); 	 
 	HashMap<String,String[]> map=new HashMap<String,String[]>();    //ͨ��list��ȡ��㣬��map���ж����֧
    public void addNode(String name,int yes,int no,float gain,int row,int Column){
    	 TreeNode tn=new TreeNode(name,yes,no,gain,row,Column);
    	// lt.add(name);
    	 list.add(tn);
     }
     public void setMap(HashMap<String,String[]> map){
    	 this.map=map;
    	 if(map.size()!=0)
    		 System.out.println("��informationGain�и��¹��������е����Լ����֧�ɹ���");
     }
     public JPanel getTreePane(){
    	 JPanel jp=new ShowTreePane();    	 
    	 return jp;
     }
     public void paint(Graphics g){                         //�����
    	 super.paint(g);
    	 g.setColor(Color.black);
    	 ArrayList<ArrayList<TreeNode>> sortNode=new ArrayList<ArrayList<TreeNode>>();
    	 int layer=0;             //�洢���ж��ٲ�ڵ�
    	 int headIndex=0;
    	 //�Ȼ�ȡͷ�����ܵò��� 
    	 for(int i=0;i<list.size();i++){
    		 if(list.get(i).getRow()==0){
    			 Head_Node_Name=list.get(i).getName();
    			 headIndex=i;
    		 }
    		 if(list.get(i).getRow()>layer)
    			 layer=list.get(i).getRow()+1;          //С�ģ�row�ӵ�0�п�ʼ��   		 
    	 }
    	 //�Ƚ����ǵ����Լ����֧��hash���key=String   value=String[]ת����key=String   value=ArrayList<String>�������containsʹ��
    	   	Set<Map.Entry<String,String[]>> checkset = attrmap.entrySet();
        	Iterator<Map.Entry<String, String[]>> checkit=checkset.iterator();
        	while(checkit.hasNext())
        	    {
        		 Map.Entry<String,String[]> checkentry =(Map.Entry<String, String[]>) checkit.next();
     	         String[] allpart=checkentry.getValue();
     	         String attrname=checkentry.getKey();
    	        ArrayList<String> partlist=new ArrayList<String>();
    	        for(int i=0;i<allpart.length;i++)
    	         {
    	        	partlist.add(allpart[i]);
    	         }
    	        attrAndPart.put(attrname,partlist);
        	 }
        	
    	//�ٽ����еĽ������ÿһ��ÿһ��
        int nullIndex=0;	
    	 for(int i=0;i<layer;i++)
    	 {
    		ArrayList<TreeNode> tree=new ArrayList<TreeNode>(); 
    	   for(int j=0;j<list.size();j++)
    	   {
    		   if(list.get(j).getRow()==i)    //ͬ���ڵ�i�еļ���һ��
    			   tree.add(list.get(j));      //��ʱ�ļ�����������ģ��п��ܵ�3�е�2�еı��ӵ���һ��λ�ã�����5�������뵽��2��λ��
    		   
    	   }
    	  if(tree.size()==0)
    		  nullIndex=i;
    	      sortNode.add(tree); 
    	 }
        sortNode.remove(nullIndex);

    	 //����ͷ���
    	 g.setColor(Color.green);
   	     g.fillOval(nodeX,nodeY,70,50);
   	     g.setColor(Color.black);
   	     g.drawString(Head_Node_Name,nodeX+17, nodeY+23);  	  
   	     g.drawString("��Ϣ����    "+list.get(headIndex).getGain(),nodeX+80, nodeY-15); 	  
   	     g.drawString("֧�ֶ�       "+list.get(headIndex).getYES(),nodeX+80, nodeY);    //����֧�ֶȺͷ���ȼ���Ϣ���� 	 
   	     g.drawString("�����      "+list.get(headIndex).getNO(),nodeX+80, nodeY+12);
    	 //��0����ͷ��㣬�����Ѿ�����
         System.out.println("һ���ж��ٲ���"+layer);
         int lag=0;
    	 for(int i=1;i<layer-1;i++)
    	 {
    		 System.out.println("��"+i+"������ʼ����"+sortNode.get(i)+"�м������"+sortNode.get(i).size());  
    		 for(int j=0;j<sortNode.get(i).size();j++)
    		 {   			      
    			     lag=(int)width/(sortNode.get(i).size()+1);   //�����ÿһ�и����ڵ�ļ��
    			     Font font0=g.getFont();
    		   	     Font font1=new Font("TimesRoman",Font.ITALIC,15);
    			     g.setColor(Color.green);
    		   	     g.fillOval(j*lag,nodeY+i*100,Node_width,Node_height);  //���ƽ��
    		   	     g.setColor(Color.black);
    		   	     String part=sortNode.get(i).get(j).getName().split(",")[0];
    		   	     String attr=sortNode.get(i).get(j).getName().split(",")[1];
    		   	     g.setFont(font1);
    		   	     g.setColor(Color.BLUE);
    		   	     g.drawString(part,j*lag+10, nodeY+i*100-30);
    		   	     g.setFont(font0);
    		   	     g.setColor(Color.BLACK);
    		   	     g.drawString(attr,j*lag+15, nodeY+i*100+25);
    		   	     System.out.println("����i�ж���i=="+i);
    		   	     System.out.println("sortNode�Ľڵ�����ǣ�"+sortNode.get(i-1).size());
    		  	      for(int k=0;k<sortNode.get(i-1).size();k++)
    		   	        {     //�����һ�����е�ĳ��������������ķ�֧����һ��ֱ������
    		  	    	   String[] nodeName=sortNode.get(i-1).get(k).getName().split(",");
    		  	    	   
    		   	    	    System.out.println("key��"+sortNode.get(i-1).get(k).getName()+"�����鿴����"+attrAndPart.get(sortNode.get(i-1).get(k).getName()));
    		   	    	    if(attrAndPart.get(nodeName[0])!=null)
    		   	    	    {
    		   	              if(attrAndPart.get(sortNode.get(i-1).get(k).getName()).contains(part))
    		   	        	     g.drawLine(j*lag+15+Node_height/2,nodeY+i*100,(k+1)*lag+Node_width/2,nodeY+(i-1)*100+Node_height);       
    		   	    	  } 
    		   	    	  else 
    		   	    	  {             //��ʱ��Ȼ��nodeName����������string�����磺middle-aged,credit,������ֻ��һ������;age  	
    		   	    		 if(attrAndPart.get(nodeName[1]).contains(part))
		   	        	        g.drawLine(j*lag+15+Node_height/2,nodeY+i*100,k*lag+Node_width/2,nodeY+(i-1)*100+Node_height); 
    		   	    	 }
    		   	       }    		   	      
    		   	     g.drawString("��Ϣ����: "+sortNode.get(i).get(j).getGain(),j*lag+70, nodeY+i*100-30);
    		   	     g.drawString("֧�ֶ�:   "+sortNode.get(i).get(j).getYES(),j*lag+70, nodeY+i*100-17);    //����֧�ֶȺͷ���ȼ���Ϣ����
    		   	     g.drawString("�����:   "+sortNode.get(i).get(j).getNO(),j*lag+70, nodeY+i*100); 	     
    		   	    // g.drawLine(j*lag,nodeY+(i-1)*100,j*lag+Node_height/2,nodeY+i*100);  //��ֱ�ߣ���������Բ����x��ƽ�е��е㴦��������
    		 }		 
    	 }  	 
     }
}
