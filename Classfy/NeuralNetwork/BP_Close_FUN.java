package NeuralNetwork;
public class BP_Close_FUN {
    public static final Double study_speed=0.1;         //ѧϰ�ٶ�
    public static final int layer_num=10;               //Ԥ��10��������
    public static final int net_num=3;                    //ÿ����Ԫ����
    public static final String fun="3x3+-2x2+6x1+2x0";         //Ŀ�꺯����������Ҫ�ƽ��ĺ���,x֮ǰ�Ĵ���ϵ����x֮��Ĵ������
    
    public void  getFun(Double x){
    	String[] a=fun.split("\\+");
    	Double sum=0d;
    	for(int i=0;i<a.length;i++){
    		System.out.println(a[i]);
    		Double sum_this=1d;
    		String[] snow=a[i].split("x");
    		Double pre=Double.parseDouble(snow[0]);           //snow[0]�������ϵ��
    		int  jie=Integer.parseInt(snow[1]);              //snow[1]������ǽ���
    	     for(int m=0;m<jie;m++){
    	    	 sum_this=sum_this*x;
    	     }
    	     sum_this=sum_this*pre;                                //�õ�����һ���aX��3�η���ֵ
    	     sum=sum+sum_this;
    	}	
    	System.out.println("�ܺ��ǣ�"+sum);
    }
    public static void main(String[] args){
    	BP_Close_FUN bp=new BP_Close_FUN();
    	bp.getFun(2d);
    }
}
