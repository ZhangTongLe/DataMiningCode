

import java.util.Scanner;
//���ڻ�ȡ�����ֶ�������
public class GetArryList {
       public static String[] str;
	public Object[] getAttrList(){            //�õ����Ի����ֶ�
		   Scanner scan=new Scanner(System.in);
		   System.out.println("***���������Ա�ʾ�ֶΣ�,�Զ��ŷָ����س��������룡********");
		   String attr=scan.next();
		   String[] str=attr.split("��|,");
		   return str;
	}
	public int getAttrSum(){
		return str.length;
	}
}
