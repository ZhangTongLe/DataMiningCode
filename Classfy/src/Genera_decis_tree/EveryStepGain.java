package Genera_decis_tree;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class EveryStepGain {
	public static final float MIN_GAIN=(float)0.001;
	InformationGain ing=new InformationGain();
	ShowTreePane tree=new ShowTreePane();
	MathLog mlog=new MathLog();      //�����Զ���ļ���2�Ķ����������˺�����getLog()����
	public HashMap<String,String[]> hs=ing.getMap();//�������ã�ÿ�ε���֮��Ҫ��α���Excel��ֻҪһ�ε���ֻ�оͿ���ֻ������ͬ���ļ��еõ�list
    public  void showDescionTree(){
    	EveryStepGain  erv=new EveryStepGain();  
        System.out.println("*************************************ִ����������֮ǰ****************************");
        LinkedList<TreeNode> newlist=erv.SetTree();
        System.out.println("*************************************ִ����������֮��****************************");
        System.out.println("�Ѿ�������ٸ���㵽�����ˣ�������������������"+newlist.size());
        for(int i=0;i<newlist.size();i++){
        System.out.println("��ǰ����ǣ�"+newlist.get(i).name+"\t��Ϣ������"+newlist.get(i).gain+"\t֧�ֶ���"+newlist.get(i).YES
        		+"\t�������"+newlist.get(i).NO+"\t�����"+newlist.get(i).Row+"\t�����"+newlist.get(i).Comlumn);
   
        }
        JFrame f=new JFrame("��������ʾ");
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
  	    f.setIconImage(icon);
        JScrollPane scroll=new JScrollPane();
        scroll.setViewportView(new ShowTreePane());
        f.add(scroll);
        f.setLocation(200,100);
        f.setSize(1000,900);
        f.setVisible(true);
    }
    //������name���������Ե�����һ����֧���ƣ����صĽ��Ǹ�������Excel��������λ��
    public TreeSet<Integer> setLocation(String name,TreeSet<Integer> field){
    	int yes_attr=0;
    	System.out.println("������֧����ѡ������Է�֧�ǣ�"+name);
    	TreeSet<Integer> set=new TreeSet<Integer>();
    	String[] t={};
    	int mp=0;    //�洢�������Ƶ�������Excel���е�����
    	int c=0;
    	String local="";
    	String[] sel=PublicData.getSelvar();
    	for(int l=0;l<sel.length;l++){
    		t=hs.get(sel[l]);
    		System.out.println("������"+sel[l]+"��"+t.length+"����֧");
    		System.out.print("�ֱ��ǣ�");
    		for(int n=0;n<t.length;n++){
    			System.out.print(t[n]+"\t");
    			if(t[n].equals(name)){
    				local=sel[l];
    			}
    		}
    		System.out.println();
    	}
    	String[] all=PublicData.getAttr();  	
    	for(int p=0;p<all.length;p++){
    		if(all[p].equals(local))
    			mp=p;
    		else if(all[p].equals(PublicData.getTarvar())){
    			c=p;
    		}
    	}
    	try {   
            Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
            Sheet sheet = book.getSheet(0);     
            Iterator<Integer> it=field.iterator();
            //for(int i=1;i<sheet.getRows();i++)   //Ĭ�ϵ�һ��Ϊ���Ա���У����������
            while(it.hasNext())
            {        int i=it.next(); 	  
           		     Cell cell = sheet.getCell(mp, i);   //��ȡ��ǰ����������
                     String result = cell.getContents().replaceAll("\"", "").trim();  
                     //System.out.print(result+"\t");
                     Cell c2=sheet.getCell(c,i);
                     String tar=c2.getContents().replaceAll("\"", "").trim();    //����ȥ���ַ��е�˫����
    	             if(result.equals(name)){    	
    	            	 set.add(i);
    	            	 if(tar.equals("yes")||tar.equals("y")){ 	            		 
    	            		 yes_attr++;
    	            	 }
    	             }
            }
            book.close();   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   	
        System.out.println("����set��С�ǣ�"+set.size());
        int last=0;   //����TreeSet��˳���ԣ����һ��Ԫ�ر�Ȼ�����˽�֧�ֶ������һ���ڵ���Ӻ��ٷ���set�оͿ�
                                       //���洢�˵�ǰ�ַ�����λ�ã�Ҳ��֪����֧�ֶ�
        if(set.size()>0){
        	System.out.println("set���λ����"+set.last());
        	last=set.last()+yes_attr;
        }
        else
        {   System.out.println("�÷�֧���Ѿ�û��Ԫ�أ����ص�set����Ϊ�գ�");
        	return set;
        }
       
        if(set.add(last)){
        System.out.println("����ɹ���set�����һ��Ԫ��");
        }
        else 
        	System.out.println("����ʧ�ܣ�set���һ��Ԫ��------");    //�������һ��Ԫ����֮ǰ��Ԫ���ظ���Ҳ��yes_attr=0
        Iterator<Integer> it=set.iterator();
        while(it.hasNext())
        	System.out.print(it.next()+"\t");
        return set;
    }
    
    //���ڸ�����Excel����set����������Ϣ���棬��Ҫָ������set�ļ��Ϻ͵�ǰ��Ҫ���������һ������
    public String computeGain(TreeSet<Integer> set,String local){
         ArrayList<String> list=new ArrayList<String>();   
    	 String gain="";
    	int com=0;    //��ȡ��ǰ����������
    	int cla=0;   //���������Ŀ
    	int part=0;   //���շ�����������
    	Iterator<Integer> allline=set.iterator();
    	String[] attr=PublicData.getAttr();
    	for(int i=0;i<attr.length;i++){
    		if(attr[i].equals(local)){
    			com=i;
    		}
    		else if(attr[i].equals(PublicData.getTarvar())){
    			part=i;
    		}
    	}
    	int sum=set.size();    //������
    	int[][] value=new int[30][2];
    	int yes_to_attr=0;   //���ڵ�ǰ�����Ŀ
    	int no_to_attr=0;
    	BigDecimal info=new BigDecimal(0);     //����Ԫ����������������Ϣ
    	BigDecimal infoD=new BigDecimal(0);    //�洢��ǰ�����Ե���Ϣ����
       	java.text.DecimalFormat DataFormat=(java.text.DecimalFormat)java.text.DecimalFormat.getInstance();
	      //Ϊ�˵õ������ദ������λС���Ľ����ע�⣬���������Ĵ���󣬼���õ��Ľ����String����
  	  DataFormat.applyPattern("#.####"); //Ϊ�˵õ������ദ������λС���Ľ��
  	  DecimalFormat df1 = new DecimalFormat("#.######");
  	   try {   
           Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
           Sheet sheet = book.getSheet(0);      
           while(allline.hasNext())   //Ĭ�ϵ�һ��Ϊ���Ա���У����������
           {         int i=allline.next(); 	  
          		     Cell cell = sheet.getCell(com, i);   //��ȡ��ǰ����������
          		     Cell cell2=sheet.getCell(part,i);     //��ȡ����У���ѵ��Ԫ�����շ��������һ��
          		     String c2=cell2.getContents().replaceAll("\"", " ").trim();
                    String result = cell.getContents().replaceAll("\"", " ").trim();  
                    if(list.contains(result)==false&&result!=null)      //����ѡ���Լ��뵽list��
                    {   list.add(result);   cla=list.indexOf(result);   
                    }
                    else if(list.contains(result)==true)
                   	  cla=list.indexOf(result);    //�Ե�ǰ���Ե����String���ͣ�����list�е�����Ϊvalue�ĵ�һά
                    if(c2.equals("y")||c2.equals("yes"))   //�Զ�ά����value�ĵڶ�ά�ĵ�һ�����洢���������������
                    {	
                   	    value[cla][0]++;
                        yes_to_attr++;          //�������з�������ռ��
                    }  
                    else  //if(c2.equals("n")||c2.equals("no"))
                    {
                   	 value[cla][1]++;             //�ڶ������洢�����������е�����
                   	 no_to_attr++;            //����������Ϣ���棬Ԫ����������������Ϣ 
                    }                                   
           }
           //writer.close(); 
           book.close();   
       } catch (Exception e) {   
           e.printStackTrace();   
       }   	
       sum=yes_to_attr+no_to_attr;
       if(yes_to_attr==0){       //���ȫ�����Ƿ񶨽�㣬�ͷ���Ҷ��
    	   gain="NLeaf";
    	   return gain;
       }
       else if(no_to_attr==0){   //���ȫ�����ǿ϶���㣬�ͷ���Ҷ��
    	   gain="YLeaf";
    	   return gain;
       }
       
       BigDecimal ysum=mlog.getDivide(yes_to_attr,sum);
       BigDecimal nsum=mlog.getDivide(no_to_attr,sum);
      info=(nsum.multiply(mlog.getLog(nsum))).abs().add((ysum.multiply(mlog.getLog(ysum))).abs());
      
   	  String[] at=new String[list.size()];
      for(int p=0;p<list.size();p++)        
    	  
   	   {  /**
    	  if(!(list.get(p).equals("")||list.get(p).equals(null)))
   	                     { at[p]=list.get(p);    }
   	        */             
   		   int a=value[p][0];
   	       int b=value[p][1];
   	       System.out.println("|"+list.get(p)+"| "+a+" | "+b+" |");
   	       int c=a+b;
   	       BigDecimal asum=mlog.getDivide(a,c );  
   	       BigDecimal bsum=mlog.getDivide(b,c);   //asum�ǿ϶�������bsum�Ƿ�����
   	       BigDecimal csum=mlog.getDivide(c,sum);
   		    if(a==0&&b!=0)
   		        infoD=infoD.add((csum.multiply(bsum.multiply(mlog.getLog(bsum)))).abs());	   
   		    else if(b==0&&a!=0)
   		    	infoD=infoD.add((csum.multiply(asum.multiply(mlog.getLog(asum)))).abs());
   		    else if(a!=0&&b!=0)   
   		    	infoD=infoD.add(((csum.multiply(asum.multiply(mlog.getLog(asum)))).abs()).add((csum.multiply(bsum.multiply(mlog.getLog(bsum)))).abs()));
   		    else if(a==0&&b==0)
   		    	infoD=infoD.add(new BigDecimal(0));
      }
     gain=info.subtract(infoD).toString();
	 System.out.println("��ǰ������Ϣ���棺"+infoD+"   ������"+attr[com]+"     ����computeGain");
    return gain;
    }
    
    
//���Ȼ�ȡ������InformationGain���в������������Ե���Ϣ���棬���бȽϵó���õ����ԣ�Ȼ���ո�����������������������
public LinkedList<TreeNode> SetTree(){
    	LinkedList<TreeNode> list=new LinkedList<TreeNode>();  
    	PublicData.clearRule();                      //�����ǽ��������ɾ�������������������
    	String[] allgain=PublicData.getVarinformationgain().split(",");
        int tp=1;
        //****************��һ���ȴ�Ĭ����������PublicData�л�ȡ��ѡ���Ե������Ϣ����������Ϊͷ���***************
        int allline=0;//��һ�����Ե�������������Excel�����������
        float MAX=0;   //�����Ϣ����
        String MAX_attr="";
    	while(tp<allgain.length){
    		String s=allgain[tp];
    		Float f=Float.valueOf(s);
    		if(f>MAX){
    			MAX=f;
    			MAX_attr=allgain[tp-1];
    		}
    		tp+=2;
    	}
    	System.out.println("��Ϣ������ߵ��ǣ�"+MAX+"\t"+"�����ǣ�"+MAX_attr);
    	//�����ͷ�����Ϣ
        String[] head=ing.getInformationGain(PublicData.getTarvar(),MAX_attr); 	
        float f1=Float.parseFloat(head[1]);
        float f2=Float.parseFloat(head[2]);
        //ͷ����λ����0��0��
        tree.addNode(MAX_attr, (int)f1, (int)f2, Float.parseFloat(head[0]), 0, 0);
    	list.add(new TreeNode(MAX_attr,(int)f1,(int)f2, Float.parseFloat(head[0]), 0, 0));
    	//*****************�Ѿ��������մ洢�������**************************************
    	System.out.println("�����Ѿ��ɹ���������"+list.size());
    	System.out.println("�����Ǹ���ֵ"+"\t������"+list.get(0).name+"\t֧�ֶ�"+list.get(0).YES+"\t�����"+list.get(0).NO+"\t��Ϣ����"+list.get(0).gain);
        //�ٴδ�Excel���õ����е�����
    	try {   
            Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
            Sheet sheet = book.getSheet(0);
            allline=sheet.getRows();
            book.close();   
    } catch (Exception e) {   
        e.printStackTrace();   
    } 
    TreeSet<Integer> HeadAttr=new TreeSet<Integer>();   //�洢ͷ������������
    for(int i=0;i<allline;i++){
    	HeadAttr.add(i);
    }
   
    //��ʽ��ʼ����
    HashMap<String,String[]> h=new HashMap<String,String[]>();    //�Ƚ��������������Լ����֧��Map���Ƶ��µ�Map�У�����ԶԲ�����ɺ����ʹ������
    h.putAll(hs);         //ֻ���������������ĸ��Ƶ��±���
    System.out.println("ԭ�б�ĳ����ǣ�"+hs.size()+"�Ƿ����"+MAX_attr+hs.containsKey(MAX_attr));
    System.out.println("��Map�����ǣ�"+h.size()+"�Ƿ����"+"credit"+h.containsKey("credit"));
   
    //����֮ǰ�洢���Լ����֧��HashMap������value��String[] 
    //�����ú�����������������HashMap��Keyÿ������һ�Σ����������һ��HashMap��value��listÿ������һ�Σ�������һ
    
    //�Ƚ�������Map�����е�key�洢���ǵ�ǰ��֧������String���磺age+yes+income...��������value�洢���ǵ�ǰkey��ʣ�������б�
    //������̻᲻�ϼ����µ�key(�����ڳ��ȵ�����),��value�ĳ��Ȳ��ϼ��٣����ǻ᲻�����ࣩ���ж�������value�Ƿ�ȫ�����ǿգ���Ҷ�������value
    HashMap<String,HashMap<String, String[]>> source=new HashMap<String,HashMap<String, String[]>>();
	HashMap<String,HashMap<String, String[]>> newMap=new HashMap<String,HashMap<String, String[]>>();   //����Map����
	HashMap<String,TreeSet<Integer>> sset=new HashMap<String,TreeSet<Integer>>();                        //��HashMapͬ���Ĵ洢������֧��λ�õ���Set
    source.put(MAX_attr,h);
    sset.put(MAX_attr,HeadAttr);
    int Att_length=1;
    boolean flag=true;    //������������Ŀ���
    while(flag){
    	Set<Map.Entry<String, HashMap<String, String[]>>> souset = source.entrySet();
    	Iterator<Map.Entry<String, HashMap<String, String[]>>> souit=souset.iterator();
    	int Column=0; 
    	while(souit.hasNext())
    	    {
    	        Map.Entry<String, HashMap<String, String[]>> souentry =(Map.Entry<String, HashMap<String, String[]>>) souit.next();
    	        HashMap<String,String[]> fg=souentry.getValue();
    	        String keyName=souentry.getKey();
    	        
    	        System.out.println("fg�ĳ����Ƕ���---"+fg.size());
    	        if(fg.size()>0)
    	        //if(keyName!="Leaf")
    	        {
    	           String[] allstr=keyName.split(",");
    	           TreeSet<Integer> parentset=sset.get(keyName);
    			   String key=allstr[allstr.length-1];
    			   String[] part=hs.get(key);           //���һ���ַ��������ѵ�,��ȡ���֧����,ע����������Ǵ�δ�޸ĵ�hs����������fg
    			   HashMap<String,String[]> newFg=souentry.getValue();
    			   newFg.putAll(fg);         //��������fg���������Լ����֧������ֻ�������������ִ��ɾ��
    			   newFg.remove(key);
    			   System.out.println("��ʼ��Map�����ǣ������ڲ鿴MapΪ������Ϊ�գ�"+newFg.size()); 			   
    			   HashMap<String,String[]> tempMap=new HashMap<String,String[]>();  			   
    			   for(int i=0;i<part.length;i++)
    			     {
    				   tempMap.putAll(newFg);          //��ʱ��Map�������ƣ���ֹ���������ŷ�֧������ɾ�������Ǵ�ԭMap��ɾ��һ��
    				   System.out.println(part[i]+"��֧\t-----------------------"+"������"+part.length);
    				   TreeSet<Integer> s=this.setLocation(part[i],parentset);   //���ع�ϣ���з�֧������Excel���е�λ��,ע���set���һ��Ԫ�ش洢��֧�ֶ���Ϣ			
    				   Iterator<Integer> its=s.iterator();
    				   System.out.println("ִ���󽻼�֮���setԪ�ذ���");
    				   while(its.hasNext())
    					   System.out.print(its.next()+"\t");
    				   if(s.size()>0)
    				   { 
                  	      int a=s.last();                 //������Ƶ�ʱ���ǽ�����һ��Ԫ��ԭ�������һ��Ԫ�ؼ���֧�ֶȵõ����ģ�����ʹ��ǰ�������Ƴ�
                          s.remove(a);
                          System.out.println("�Ƴ�֮���set��ʣ�¶���Ԫ��===="+s.size());
                          if(s.size()==0)
                        	  break;
                          int num=s.last();
                          int yes=a-num;    //�õ���ǰ���Է�֧��֧�ֶ�
                          int no=s.size()-yes;   //�õ���ǰ���Է�֧�ķ����
                          if(no<0){                  //���noΪ��������������set��ֵΪ0��Ҳ��yesΪ0
                        	  s.add(a);              //�ٽ����һ��Ԫ�طŻ�ȥ
                        	  yes=0;
                        	  no=s.size();
                          }           
                          System.out.println("�鿴Ϊ��yes��no�д��ڸ���,���е�set����Ԫ��  "+s.size()+"  yes�ж���   "+yes+"  no�ж���    "+no);
                          //��ǰһ���ҳ���ǰ���Եķ�֧����һ�������Ϣ��������----������ѭ��
                          float MAX_gain=0;
                          String MAX_at="��ʼ����ֵ";    //���ÿ����õ������Ϣ�������������
                          Column++; 
                          Set<Map.Entry<String, String[]>> hset=newFg.entrySet();                                                           
                   	      Iterator<Map.Entry<String, String[]>> hit=hset.iterator();
                          while(hit.hasNext())
                             {   
                              Map.Entry<String, String[]> htmp=hit.next();   //��ʣ�µ�������Ѱ��������Ϣ����ֵ
                              String gain=this.computeGain(s,htmp.getKey() );    //��������Ϣ����
                              if(gain.equals("YLeaf")){
                            	  gain=1+"";
                            	  MAX_gain=1;
                            	  MAX_at=htmp.getKey();
                              }                      
                              else if(gain.equals("NLeaf")){
                            	  gain=0+"";
                              }
                              else if(Float.parseFloat(gain)>MAX_gain)
                                {
                      	        MAX_gain=Float.parseFloat(gain);
                      	        MAX_at=htmp.getKey();      
                                }
                            }
                           tree.addNode(part[i]+","+MAX_at,yes,no,MAX_gain,Att_length,Column);
                           list.add(new TreeNode(MAX_at,yes,no,MAX_gain,Att_length,Column));
                           tempMap.remove(MAX_at);
                           System.out.println("��ʼ��Map�����ǣ������ڲ鿴MapΪ������Ϊ�գ�ɾ��֮�����"+tempMap.size());
                           String newKeyName=keyName+","+part[i]+","+MAX_at;
                           if(MAX_gain==0||MAX_gain==1){
                        	   tempMap.clear();
                           }
                           System.out.println("tempMap�л��ж���Ԫ��"+tempMap.size());
                           newMap.put(newKeyName,tempMap);
                           sset.put(newKeyName,s);
    				     }
    			     }
    			    }	
             }
                 Att_length+=2;                                   //����ÿ�μ���ö��Ƿ�֧����һ�����Ի�����2
             	source.clear();
            	source.putAll(newMap);                            //���µ�Map���ԭ����   
                int null_num=0;
                int Max_Attr_len=0;
                //������Map�����ȫ������null�Ļ���ʾ���Ѿ�ȫ���������
            	Set<Map.Entry<String, HashMap<String, String[]>>> checkset = source.entrySet();
            	Iterator<Map.Entry<String, HashMap<String, String[]>>> checkit=checkset.iterator();
            	while(checkit.hasNext())
            	    {
            		 Map.Entry<String, HashMap<String, String[]>> checkentry =(Map.Entry<String, HashMap<String, String[]>>) checkit.next();
         	         HashMap<String,String[]> lastfg=checkentry.getValue();
         	         String LastName=checkentry.getKey();
         	         if(LastName.length()>Max_Attr_len)
         	        	      Max_Attr_len=LastName.length();
         	         System.out.println("���Ľ�㵽����ʲô--"+LastName+"����Map������"+lastfg.size());
         	         //���õ��Ĺ���д�뵽�ı���
         	         if(lastfg.size()==0){
         	        	 PublicData.setDescionTreeRule(LastName);
         	        	 PublicData.setDescionTreeRule("\r\n");
         	         }
            	     if(lastfg.size()>1)           //����Ϊ���Ǵ���1������0����Ҳ�޴ӽ��͡�����0�������⣬Ȼ������1ȴ�ܵõ���ȷ��
            	        {
            	    	 null_num++;
            	        }
            	    }   
            	//���ȫ������null,������ѭ��
                if(null_num==0){
                	flag=false;
                }           		
             }          	      
    return list;
       }       

  }