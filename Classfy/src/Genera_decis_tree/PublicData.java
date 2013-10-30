package Genera_decis_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//���������࣬��Ҫ�ǲ����ļ����ļ�����������
public class PublicData {
  // public static String file;   //�ļ���
   public  static String[] attr;   //������
   //public static final String attr_and_part="d:/predata/attr_and_part.txt";
   public static final String partrule="d:/predata/partrule.txt";    //�洢���������ѵ����й������з�֧,ÿ��һ��,��ͷ��������Զ��ŷָ�
                                                                   //����ʱ�����봫���ά���飬��ȡʱҲ�Ƕ�ά����
   public static final String FileDataStatic="d:/predata/FileDataSatic.txt"; 
   public static final String filename="d:/predata/filename.txt";
   public static final String attrname="d:/predata/attr.txt";
   public static final String tarvar="d:/predata/tarvar.txt";    //�洢��������Ŀ������
   public static final String selvar="d:/predata/selvar.txt";   //�洢��������ѡ���������
   public static final String varInformationGain="d:/predata/varinfg.txt";  //�洢���Ե���Ϣ���棬����(1,3,..)index�洢���棬ż���洢����
   public static final String  descionTreeRule="d:/predata/DescionTree/rule.txt";
   public static final String clusterAttr="d:/predata/Cluster/SelAttr.txt";
   public static final String clusterNum="d:/predata/Cluster/clusterNum.txt";
   public static final String clusterResult="d:/predata/Cluster/clusterResult.txt";
   public static final String clusterInitCore="d:/predata/Cluster/clusterInitCore.txt";
   
   
public static String getFile(){
	BufferedReader read;
	String f=null;
	try {
		read = new BufferedReader(new FileReader(filename));
		f=read.readLine();                 //�ļ���ֻ��洢�ļ���һ��
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("�ļ������ڣ�");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return f;
}
public static void setInformationGain(String gain){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(varInformationGain)));
	   writer.append(gain.replaceAll("\"", ""));   //ȥ��˫����
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}	
}
public static String getVarinformationgain() {           //������һ�������ַ������������Ժ���Ϣ���棬���߽�����ȫ���Զ��ŷָ�
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(varInformationGain));
		s=r.readLine();
	}catch(IOException e){
         e.printStackTrace();		
         System.out.println("��ȡ��Ϣ����ʧ�ܣ�û���ҵ��ļ�");
	}
	return s;
}
public static void setFile(String f){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
	   writer.append(f.replaceAll("\"", ""));   //ȥ��˫����
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public static String[] getAttr() {
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(attrname));
		s=r.readLine();
		attr=s.split(",");                //���������ж��оͻ�������
	}catch(IOException e){
         e.printStackTrace();		
         System.out.println("û���ҵ��ļ�");
	}
	return attr;
}
public static void setAttr(String[] attr) {    //��ʵ���ǽ�����ȫ���洢��attrname�ļ���
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(attrname)));
		for(int i=0;i<attr.length;i++){
			if(i==0){
				writer.append(attr[i].replaceAll("\"", "").trim());
			}
			else{
				writer.append(","+attr[i].replaceAll("\"", "").trim());	//ȥ��˫����	
			}
			writer.flush();
		}   
	} catch (IOException e) {
		e.printStackTrace();
	}	
}
public static String getTarvar() {
	String tar=null;
	BufferedReader r;
	try{
		r=new BufferedReader(new FileReader(tarvar));
		tar=r.readLine();
	}catch(IOException e){
         e.printStackTrace();		
	}
	return tar;
}
public static String[] getSelvar() {
	String var[]={};
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(selvar));
		s=r.readLine();
		 var=s.split(",");                //���������ж��оͻ�������
	}catch(IOException e){
         e.printStackTrace();		
	}
	return var;
}
  public void setSelvar(String[] sel){      //���þ��������Ѿ�ѡ�������
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(selvar)));
			writer.append(sel[0]);
			for(int i=1;i<sel.length;i++){
				writer.append(","+sel[i].replaceAll("\"", "").trim());	
				writer.flush();
			}   
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
  }
  public void setTarvar(String tar){              //���þ�������Ŀ������  
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(tarvar)));
		   writer.append(tar.replaceAll("\"", ""));
		   writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
  }
  public static void setDescionTreeRule(String rule){              //���þ�������Ŀ������  
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(descionTreeRule),true));
		   writer.append(rule.replaceAll("\"", ""));
		   writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
}
  //������չ�������������д�����֮ǰ
public static void clearRule(){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(descionTreeRule),true));
	   writer.append("");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
//============================================�����õ�����==========================
public static void setClusterAttr(String var) {
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterAttr)));
	   writer.append(var.replaceAll("\"", ""));
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}

}
public static String[] getClusterAttr() {
	String var[]={};
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(clusterAttr));
		s=r.readLine();
		 var=s.split(",");                //���������ж��оͻ�������
	}catch(IOException e){
         e.printStackTrace();		
	}
	return var;
}
public static void setClusterNum(String n){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterNum)));
	   writer.append(n.replaceAll("\"", ""));
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public static int getClusterNum(){
	int num=0;
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(clusterNum));
		s=r.readLine();
	    num=Integer.parseInt(s);
	}catch(IOException e){
         e.printStackTrace();		
	}
	return num;
}
//�����������ʼ���ľ������Ĵ洢�����Ա�Ƚ�
public static void clearInitCore(){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterInitCore)));
	   writer.append("");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public static void setClusterInitCore(String str){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterInitCore),true));
	   writer.append(str.replaceAll("\"", ""));
	   writer.append("\r\n");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}


//�洢������
public static void setClusterResult(String n){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterResult),true));
	   writer.append(n.replaceAll("\"", ""));
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
//ÿ�ο�ʼ�����ʱ�򶼱��������֮ǰ�ı��ľ�������������������շ���
public static void clearCluster(){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterResult)));
	   writer.append("");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
  //***********************************************��ʱδʹ��
  public static void setStaticData(String f){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FileDataStatic),true));
		   writer.append(f.replaceAll("\"", ""));   //ȥ��˫����
		   writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  
}
