

import java.util.Arrays;
public class quickSort {

 /**
     * ��������
     * 
     * ����˼��
     * ���赱ǰ�������������ΪR[low..high]�����÷��η��ɽ���������Ļ���˼������Ϊ��
     * �ٷֽ⣺ 
     * ��R[low..high]����ѡһ����¼��Ϊ��׼(Pivot)��
     * �Դ˻�׼����ǰ����������Ϊ����������С��������R[low..pivotpos-1)��R[pivotpos+1..high]��
     * ��ʹ��������������м�¼�Ĺؼ��־�С�ڵ��ڻ�׼��¼(������Ϊpivot)�Ĺؼ���pivot.key��
     * �ұߵ������������м�¼�Ĺؼ��־����ڵ���pivot.key��
     * ����׼��¼pivot��λ����ȷ��λ��(pivotpos)�ϣ�������μӺ���������
     * ע�⣺
     * ���ֵĹؼ���Ҫ�����׼��¼���ڵ�λ��pivotpos��
     * ���ֵĽ�����Լ򵥵ر�ʾΪ(ע��pivot=R[pivotpos])��
     * R[low..pivotpos-1].keys��R[pivotpos].key��R[pivotpos+1..high].keys
     * ����low��pivotpos��high��
     * ����⣺ 
     * ��ͨ���ݹ���ÿ������������������R[low..pivotpos-1]��R[pivotpos+1..high]��������
     */
    
 /**
  * @author fangtuo 2008-09-20
  * @param pData ��Ҫ���������
  * @param left ��ߵ�λ��,��ʼֵΪ0
  * @param right �ұߵ�λ��,��ʼֵΪ���鳤��
  */
    public static void quickSort(int[] pData,int left,int right)
    {
     int i ,j ;
     int middle,temp ;
     i = left ;
     j = right ;
     middle = pData[left] ;
     while(true)
     {
      while((++i)<right-1 && pData[i]<middle) ;
      while((--j)>left && pData[j]>middle) ;
      if(i>=j)
       break ;
         temp = pData[i] ;
         pData[i] = pData[j] ;
         pData[j] = temp ;
      
     }
     pData[left] = pData[j] ;
     pData[j] = middle ;
        
     if(left<j)
      quickSort(pData,left,j) ;
     
     if(right>i)
      quickSort(pData,i,right) ;
    }

 public static void main(String[] args) {
  // TODO Auto-generated method stub
  int[] pData = new int[]{49,38,65,97,76,13,27} ;
  quickSort(pData,0,pData.length) ;
  System.out.println(Arrays.toString(pData)) ;

 }

}