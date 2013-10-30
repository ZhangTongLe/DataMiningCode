package Genera_decis_tree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class InformationGain {
	ArrayList<Set<String>> attr_and_part=new ArrayList<Set<String>>();   //�����洢�������Լ����֧��ÿ����������set��0λ������������֧
	int[][] value;   //����˷�������Ե�����
	public ArrayList<String> list=new ArrayList<String>();          
	MathLog mlog=new MathLog();      //�����Զ���ļ���2�Ķ����������˺�����getLog()����
	//ShowTreePane treepane=new ShowTreePane();      //���������Լ����֧���µ�Tree�У��Ա����
	public String attr_part="";
	public static final String HEAD="d:/predata/";
	public static final String LAST=".txt";
   public String[] getInformationGain(String pt,String t){
    	String[] gain_and_support=new String[3];
	    String gain=null;
    	int cla=0;    //���������Ŀ
    	BufferedWriter writer = null;
    	int part=0;
    	int target=0;
    	String[] attr=PublicData.getAttr();
    	for(int p=0;p<attr.length;p++){
    		if(attr[p].equals(pt))
    			{
    			part=p;
    		    System.out.println("���������ǣ�"+attr[p]);
    			}
    		if(attr[p].equals(t))
    		{
    			target=p;
    			System.out.println("��ѡ�����ǣ�"+attr[p]);
    		}	
    	}
    	/**���в������ı������붼�ŵ������ڲ����������´μ���ʱ�ظ�*/
    	float yes_to_attr=0;   //���ڵ�ǰ�����Ŀ
    	float no_to_attr=0;
    	BigDecimal info=new BigDecimal(0);     //����Ԫ����������������Ϣ
    	BigDecimal infoD=new BigDecimal(0);    //�洢��ǰ�����Ե���Ϣ����
       	float sum=0;     //�洢������
    	java.text.DecimalFormat DataFormat=(java.text.DecimalFormat)java.text.DecimalFormat.getInstance();
	      //Ϊ�˵õ������ദ������λС���Ľ����ע�⣬���������Ĵ���󣬼���õ��Ľ����String����
    	DataFormat.applyPattern("##.###"); //Ϊ�˵õ������ദ������λС���Ľ��
    	value=new int[50][2];
    	//������������ȡExcel�ļ��еĵ�Ԫֵ
    	try {   
            Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
            Sheet sheet = book.getSheet(0);      
            for(int i=1;i<sheet.getRows();i++)   //Ĭ�ϵ�һ��Ϊ���Ա���У����������
            {        	  
           		     Cell cell = sheet.getCell(target, i);   //��ȡ��ǰ����������
           		     Cell cell2=sheet.getCell(part,i);       //��ȡ����У���ѵ��Ԫ�����շ��������һ��
           		     String c2=cell2.getContents().replaceAll("\"", "").trim();
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
    	//����������������Ϣ����ĳ����
        System.out.println("���Գ���"+list.size());
        sum=yes_to_attr+no_to_attr;
        BigDecimal ysum=mlog.getDivide(yes_to_attr,sum);
        BigDecimal nsum=mlog.getDivide(no_to_attr,sum);
        info=(nsum.multiply(mlog.getLog(nsum))).abs().add((ysum.multiply(mlog.getLog(ysum))).abs());  
    	String[] at=new String[list.size()];
    	System.out.println("���Գ���-------------"+list.size());
    	 for(int p=0;p<list.size();p++)                
     	   {   if(!(list.get(p).equals("")||list.get(p).equals(null)))
     	                     { at[p]=list.get(p);    }
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

    	
    	for(int i=0;i<at.length;i++){
    		if(i==0&&at[i]!=null){
    			attr_part=at[i];
    		}
    		else{
    			if(at[i]!=null)
    			{ attr_part=attr_part+","+at[i];}
    		}
    	}
    	System.out.println("attr_part�����ǣ�"+attr_part.length());
    	try{
    		String f=HEAD+t+LAST;
    		System.out.println("���ڳ���д���ļ�-------"+f);
    		BufferedWriter w = new BufferedWriter(new FileWriter(new File(f)));
    	   w.append(attr_part.trim());   //ȥ��˫����  
    	   w.flush();
           w.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}     
    	attr_part="";
    	gain=info.subtract(infoD).toString();
    	System.out.println("��ǰ������Ϣ���棺"+infoD);
    	list.clear();     
    	//���list���Ա��´δ洢ʱ��������ǰ��Ĳ���Ԫ���ܸ���
    	gain_and_support[0]=gain;
    	gain_and_support[1]=yes_to_attr+"";
    	gain_and_support[2]=no_to_attr-1+"";
    	return gain_and_support;
    }

    public void writeInformation(){//���ڵ��� getMap�����а����˷������������ڻ���û��
    	String informationGain="";    	
    	String[] attall=PublicData.getSelvar();    //ֻ�����û�ѡ������Ե���Ϣ����ֵ
    	for(int a=0;a<attall.length;a++){
    		if(a==0){
    			informationGain=attall[a]+","+this.getInformationGain(PublicData.getTarvar(),attall[a])[0];
    		}
    		else
    		 informationGain=informationGain+","+attall[a]+","+this.getInformationGain(PublicData.getTarvar(),attall[a])[0];
    	}
    	PublicData.setInformationGain(informationGain);
    
    }

    public static void main(String[] args){
    	InformationGain ing=new InformationGain();
    	String[] sel=PublicData.getSelvar();
    	String tar=PublicData.getTarvar();
    	 HashMap<String,String[]> h=ing.getMap();
    	for(int i=0;i<sel.length;i++){
    		System.out.println("��ǰ�������Ե���Ϣ���棺"+ing.getInformationGain(tar,sel[i])[0]);
    		System.out.println("��ǰ��������֧�ֶȣ�"+ing.getInformationGain(tar,sel[i])[1]);
    		System.out.println("��ǰ�������Է���ȣ�"+ing.getInformationGain(tar,sel[i])[2]);
    		System.out.println("����"+sel[i]+"����");
    		for(int j=0;j<h.get(sel[i]).length;j++){
    			System.out.print(h.get(sel[i])[j]+"\t");
    		}
    	}
    	//ing.writeInformation();
    	System.out.println("MAP=="+ing.getMap().size());
    }
    
   public HashMap<String,String[]> getMap(){    //����һ�����������֦��Map��Ӧ�����ҽ�ÿ�����Ե���Ϣ����д��d:/predata/varinfg.txt�ļ���,��Ϊִ�е�һ��
	   String informationGain="";  
	   HashMap<String,String[]> hs=new HashMap<String,String[]>();
	   String[] sel=PublicData.getSelvar();
    	for(int i=0;i<sel.length;i++)
    	{ 		
    		BufferedReader r;
    		String s=null;
    		String fl=HEAD+sel[i]+LAST;
    		try{
    			    r=new BufferedReader(new FileReader(fl));
    			    s=r.readLine();     
    		}catch(IOException e){
    	         e.printStackTrace();		
    		 }
    		//�ļ����������ַ��� s �У�֮��������try-catch����ɾ�����ļ������û�������͸����

         File file=new File(fl);
    	if(file.isFile()&&file.exists()){
    		file.delete();
    		System.out.println("ɾ���ļ�"+fl+"�ɹ���");
    	}
 
    		
    		if(i==0){
    			informationGain=sel[i]+","+this.getInformationGain(PublicData.getTarvar(),sel[i])[0];
    		}
    		else{
    		 informationGain=informationGain+","+sel[i]+","+this.getInformationGain(PublicData.getTarvar(),sel[i])[0];
    	      }
    	  String[] str=s.split(",");	
    	  hs.put(sel[i],str);
    	}
       PublicData.setInformationGain(informationGain);	
       System.out.println("hs�ж���Ԫ�أ�"+hs.size());
	   return hs;
	   
   }
}
