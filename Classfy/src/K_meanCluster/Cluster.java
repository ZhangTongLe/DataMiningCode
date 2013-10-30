package K_meanCluster;

import java.io.File;
import java.util.Iterator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import Genera_decis_tree.PublicData;

public class Cluster {
	public static final int MAX_TIME=30;    //����������     
	 public int count=0;            //����������������
    SetSelAttr ssa=new SetSelAttr();
    String[] str=PublicData.getClusterAttr();
    static int cluCoreNum=PublicData.getClusterNum();
    BigDecimal[][] cluCore=new BigDecimal[str.length][cluCoreNum+1];    //��������,�û�ѡ���m�����ԣ�����ÿ��������k����������,+1�ǽ�0Ϊ�洢����λ��
    
public HashMap<String,BigDecimal[]> getCluster(){
	//��HashMap�� key��Ӧ���������ƣ�value������[0]�洢��Excel����λ��,[1]�洢�����Ե���Сֵ,[2]�洢���ֵ
	HashMap<String,BigDecimal[]> attr_loc_M=new HashMap<String,BigDecimal[]>();  
	String[][]  AttrAndMaMi=new String[str.length][4];
	//����Ѱ���û�ѡ��ľ������������������е�λ�ã�������һ��������Excel����Ѱ�ң�ע�����Ǵ�ʱֻ�Ǳ�����Excel���һ�� 
	try {   
        Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
        Sheet sheet = book.getSheet(0);  
        for(int i=0;i<sheet.getColumns();i++)
        {
        	Cell cell = sheet.getCell(i, 0);   //��ȡ��ǰ����������
            String result = cell.getContents().replaceAll("\"", "").trim();
            for(int j=0;j<str.length;j++)
            {
            	if(result.equals(str[j]))
            	    	{
            		     AttrAndMaMi[j][0]=str[j];    //����ĵ�һ��λ�ô洢�������������Ա��Ӧ
            		     AttrAndMaMi[j][1]=i+"";    //������������λ��Ҳ���String����
            		     String mi=sheet.getCell(i,2).getContents();      //�����Գ�ʼָ��Ϊ0,������Сֵ�п����ǲ����ڵ�0
            		     AttrAndMaMi[j][2]=mi;
            		     String ma=sheet.getCell(i,1).getContents();
            		     AttrAndMaMi[j][3]=ma;     //�ֱ�洢���ֵ����Сֵ    ,2����Сֵ��3�����ֵ
            	    	}         
            }
        }
      book.close();   
    } catch (Exception e) {   
    e.printStackTrace();   
    }  
    //*******************************���ϲ����ǻ�ȡ�û�ѡ��ľ���������Excel�ļ��е�λ�ã���������ʼ����Excel��ͳ��������������Сֵ
    
    try {   
        Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
        Sheet sheet = book.getSheet(0);  
        for(int i=1;i<sheet.getRows();i++)
        {  	
        	for(int j=0;j<AttrAndMaMi.length;j++)
        	{
        	   BigDecimal min=new BigDecimal(AttrAndMaMi[j][2]);
        	   BigDecimal max=new BigDecimal(AttrAndMaMi[j][3]);
       	       Cell c=sheet.getCell(Integer.parseInt(AttrAndMaMi[j][1]),i);   //ע��˴�������������λ�ã�����Ϊint����,��Ҫת������BigDecimal
        	   String res=c.getContents();
        	   if(res.equals("?"))                   //���Excel���г��ֵģ����ݵ�����
        	   {
        		   continue;
        	   }   
        	   else
        	   {
        	     BigDecimal ExcelValue=new BigDecimal(res);    //��ȡExcel���еľ�ȷֵ 
        	     if(ExcelValue.compareTo(max)==1)
        		    AttrAndMaMi[j][3]=ExcelValue.toString();     //����������е�ǰ���Ե����ֵ����Сֵ
        	     else if(ExcelValue.compareTo(min)==-1)
        		    AttrAndMaMi[j][2]=ExcelValue.toString();
        	   }
           }
    }
    book.close();   
  } catch (Exception e) {   
  e.printStackTrace();   
  }  
  //��������HashMap
  for(int i=0;i<AttrAndMaMi.length;i++)
  {
	BigDecimal[] p=new BigDecimal[3];
	p[0]=new BigDecimal(AttrAndMaMi[i][1]);
	p[1]=new BigDecimal(AttrAndMaMi[i][2]);
	p[2]=new BigDecimal(AttrAndMaMi[i][3]);
	attr_loc_M.put(AttrAndMaMi[i][0],p);
  }
    return attr_loc_M;   
}	
public void initCluCore(){
	HashMap<String,BigDecimal[]> map=this.getCluster();
	 Set<Map.Entry<String, BigDecimal[]>> set =map.entrySet();
    java.util.Iterator<Entry<String, BigDecimal[]>> it=set.iterator();
    int j=0;
    while(it.hasNext()) 
    {
   	 Map.Entry<String,BigDecimal[]> entry = (Map.Entry<String,BigDecimal[]>) it.next();
   	 BigDecimal[] value=entry.getValue();
   	 String name=entry.getKey();
   	 Random rd1 = new Random();
   	 System.out.println("-----��������"+name+"�����ֵ����Сֵ");
     System.out.print("������"+value[0]+"��Сֵ��"+value[1]+"\t"+"���ֵ��"+value[2]+"\t");     
     System.out.println("�����û�ѡ�����������ڵ������");
     // ���г�������,����200λС��,ĩλʹ���������뷽ʽ,���ؽ��
     //BigDecimal result = bd.divide(bd2, 200, BigDecimal.ROUND_HALF_DOWN);
     Double a=value[1].doubleValue();
     Double b=value[2].doubleValue();
     cluCore[j][0]=value[0];               //��0λ�洢������������
     /************************�˴������ƶ���ʼ�������ģ��Ա�������ǵľ����Ƿ���ȷ
      * ֻ��Ҫ��һ��ϵ���滻nextDouble����,����ѡ��0.5������ƽ��ֵ
      */
  	 
        for(int i=1;i<cluCoreNum+1;i++)
         {     
             cluCore[j][i]=new BigDecimal(a+(0.5+i*0.1)*(b-a));    //+i*0.1�Ǳ������������ĳ�ʼֵһ��
    	     System.out.print(a+rd1.nextDouble()*(b-a)+"\t"); 
         }
      /********************�ڶ�����������������ƶ���ʼ���������ĵ�
         for(int i=1;i<cluCoreNum+1;i++)
          {     
             cluCore[j][i]=new BigDecimal(a+rd1.nextDouble()*(b-a));
    	     System.out.print(a+rd1.nextDouble()*(b-a)+"\t"); 
          }
    */
          System.out.println();
         j++;
    }
    System.out.println("���Գ�ʼ���ľ�������ֵ--------------");
    PublicData.clearInitCore();
    for(int i=0;i<cluCore.length;i++)
    {
    	String s="";
    	for(int m=0;m<cluCore[i].length;m++)
    	{
    		System.out.print(cluCore[i][m].setScale(4,BigDecimal.ROUND_HALF_DOWN)+"\t");
    		s+=cluCore[i][m].setScale(4,BigDecimal.ROUND_HALF_DOWN)+"\t";
    	}
    	PublicData.setClusterInitCore(s);
    	System.out.println();
    }
}
//��ȡ������ �������ģ����̻��Ƿ�������
public BigDecimal[][] getRealCore()
{   
	 try {   
	        Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
	        Sheet sheet = book.getSheet(0);
	        Boolean f=false;      //�������Ƹ��²����Ƿ�������еı�־
	       PublicData.clearCluster();
	       String front="===============================�����Ǿ�����=======================";
	       PublicData.setClusterResult(front);
	       String setss="\r\n��������		";
	       for(int i=0;i<str.length;i++){
	    	   setss=setss+str[i]+"\t";
	    	   String s="";
	    	   for(int j=0;j<PublicData.getClusterNum();j++){               //ֻ��Ϊ�˶�ռ���tab����
	    		   s=s+"\t\t";										//ע��˴�ֻ������tab�����˾ͳ�����
	    	   }
	    	   setss=setss+s;            
	       }
	     PublicData.setClusterResult(setss);
	     PublicData.setClusterResult("\r\n");
	     while(!f&&count<MAX_TIME)
	     {	 count++;
	        HashMap<String,ArrayList<BigDecimal>> map=new HashMap<String,ArrayList<BigDecimal>>();
	        for(int i=1;i<sheet.getRows();i++)
	        {  	
	         for(int j=0;j<cluCore.length;j++)
	         {
	           Cell s=sheet.getCell(cluCore[j][0].intValue(),i);
	           String resl=s.getContents();
	           BigDecimal num=new BigDecimal(0);
	           if(!resl.equals("?"))
	        	   num=new BigDecimal(resl);
	           BigDecimal val=num;
	           //�������ȱ���ȷ����Excel���еĵõ���ֵ�����Ǹ��������ıȽϽ�
	           BigDecimal minLag=new BigDecimal(Math.abs(val.subtract(cluCore[j][1]).doubleValue()));
	           BigDecimal min=cluCore[j][1];
	           for(int m=1;m<cluCore[j].length;m++)
	           {
	        	   BigDecimal lag=new BigDecimal(Math.abs(val.subtract(cluCore[j][m]).doubleValue()));
	        	   if(lag.compareTo(minLag)==-1){
	        		   min=cluCore[j][m];                //�ҵ���������ĵ�
	        	   }
	           }
	           //�ٴα�����Ϊ���������ֵ��׼��
	           for(int n=0;n<cluCore[j].length;n++)
	           {  												  //cluCore�ĵ�һ��Ԫ�ش洢������λ��
	        	   String a=cluCore[j][0]+"";
	        	   String b=min+"";
	        	   String c=a+","+b;
	        
	        	   if(!map.containsKey(c))                //������λ��+����+��һ���ľ�������ֵ��Ϊkey
	        	   {  
	        		   ArrayList<BigDecimal> list=new ArrayList<BigDecimal>();
	        		   map.put(c,list);
	        		   System.out.println("���Ǽ���key     "+c);
	        	   }
	        	   else if(map.containsKey(c))
	        	   {
	        		   map.get(c).add(val);    //���»�õ�Excel�е�ֵ���뵽hashMap��list��ȥ
	        	   }
	           }          
	         }	        	
	        }
	     //��������������map����ø���ÿ���������ĵõ���ÿ��Excel����Ԫ�ص�ֵ��ƽ��ֵ
	        BigDecimal[][] newArray=new BigDecimal[str.length][cluCoreNum+1];
	        //System.arraycopy(cluCore, 0, newArray, 0, cluCore.length);
	        	//(BigDecimal[][])cluCore.clone();        //�����Ǹ���֮ǰ�ȱ�����һ���ģ�Ϊ����Ա���׼��
	        this.CopyArray(cluCore, newArray);
	    	Set<Map.Entry<String, ArrayList<BigDecimal>>> set =map.entrySet();
	    	Iterator<Map.Entry<String,ArrayList<BigDecimal>>> it=set.iterator();
	    	while(it.hasNext())
	    	    {
	    	        Map.Entry<String,ArrayList<BigDecimal>> souentry =(Map.Entry<String,ArrayList<BigDecimal>>)it.next();
	    	        ArrayList<BigDecimal> fg=souentry.getValue();
	    	        BigDecimal sum=new BigDecimal(0);
	    	        String[] keyName=souentry.getKey().split(",");   
	    	        for(int m=0;m<keyName.length;m++){
	    	        	System.out.println("keyName="+keyName[m]);
	    	        }
	    	        for(int i=0;i<fg.size();i++)
	    	        {
	    	        	sum=sum.add(fg.get(i));      //���
	    	        }
	    	     System.out.println("fg�ĳ�����"+fg.size());  
	    	     //ע���ڳ�������ʱ���г�������������������Ǳ����ƶ��䱣������λ���ƶ���������
	    	     BigDecimal avg=sum.divide(new BigDecimal(fg.size()),10,BigDecimal.ROUND_HALF_DOWN); //��ǰ�������ĵ�����ֵ��ƽ��ֵ
	    	     System.out.println("�µõ��ľ�������ֵ�ǣ�������ƽ��ֵ���㣩"+avg);
	             String row=keyName[0];                          //keyName[0]������Ǵ�������Excel���е�λ��
	             String clu=keyName[1];                           //keyName[1]������Ǵ����Ե�����֮һ��һ�������ĵ�
	             //ccluCore=new BigDecimal[str.length][cluCoreNum+1];,���е�0��λ�ô洢�������������������е�λ��
                 //*****************������Щ����ÿ��ֻ��ͬʱ�����û���ѡN�����Ե����еĵ�һ���������Ķ���
	             for(int i=0;i<cluCore.length;i++)
                 {
                	 if(cluCore[i][0].compareTo(new BigDecimal(row))==0)
                	 {
                		 System.out.println("cluCore[i][0]="+cluCore[i][0]+"\trow="+row);
                	    for(int j=1;j<cluCore[i].length;j++)
                	    {
                	    	System.out.println("��������ٴδ���cluCore[i]�ж��ٸ�Ԫ�أ����������ģ�cluCore[i][j]="+cluCore[i][j]); 
                	    	
                	    	if(cluCore[i][j].compareTo(new BigDecimal(clu))==0)
                	    	 {
                	    		cluCore[i][j]=avg;                        //��ƽ��ֵ����λ��Ϊrow-->��ʼ��������ΪcluCore��Ҳ��clu����ֵ
                	    		System.out.println("clu=="+clu);
                	    	 }	
                	    }
                	 }
                 }	                 
	    	    }
	    	
	    String resul="\t\n";                      //�����ǵľ�����������resul�У�Ȼ��д�뵽�ļ���ȥ   
        for(int i=0;i<cluCore.length;i++)
        {
       	    for(int j=1;j<cluCore[i].length;j++)
       	    {
       	    	resul=resul+cluCore[i][j].setScale(4, BigDecimal.ROUND_HALF_DOWN)+"\t";
       	    }
       	    
       	   for(int j=0;j<PublicData.getClusterNum();j++)
       	     {               //ֻ��Ϊ�˶�ռ���tab����
  		        resul=resul+"\t";										//ע��˴�ֻ������tab�����˾ͳ�����
  	         } 
        } 	  
        resul=resul+"\t\n\t";                            //����Ū�������ˣ����˻����˶�������
        String tt=count+"    ";
        PublicData.setClusterResult(tt+resul);		
        PublicData.setClusterResult("\r\n");
	    f=ifCore(newArray,cluCore);     //�ж��Ƿ��б�Ҫ��������        
	     }    
           book.close();   
       } 
	    catch (Exception e) {   
          e.printStackTrace();
       }
	    System.out.println("�����˶��ٴ�"+count);
	    return cluCore;
}
//�ж����������ǲ����Ѿ���ȫһ����
public Boolean ifCore(BigDecimal a[][],BigDecimal b[][]){
	Boolean flag=true;
	for(int i=0;i<a.length;i++)
	{
		for(int j=0;j<a[i].length;j++){
			System.out.println("a[i][j==]"+a[i][j]+"\tb[i][j]=="+b[i][j]);
			if(!(a[i][j].compareTo(b[i][j])==0))
				flag=false;	
		}
	}
	return flag;
	
}
public void CopyArray(BigDecimal[][] a,BigDecimal[][] b){
	for(int i=0;i<a.length;i++)
		for(int j=0;j<a[i].length;j++){
			b[i][j]=a[i][j];
		}
}
public static void main(String[] args){
		Cluster clu=new Cluster();
        clu.doCluster();
	}
public void doCluster(){
	Cluster clu=new Cluster();
    clu.initCluCore();
	BigDecimal[][] a=clu.getRealCore();
	ShowClusterResult show=new ShowClusterResult();
	show.show();
}
}
