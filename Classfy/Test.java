import java.math.BigDecimal;


public class Test {
    public static void main(String[] args){
    	Test t=new Test();
    	t.test();
    }
    public void test(){
    	MathLog ml=new MathLog();
    	BigDecimal a=new BigDecimal(4);
    	System.out.println("4�Ķ�����"+ml.getLog(a));
    	float b=(float) 6.72;
    	float c=(float)3.000;
    	ml.getDivide(b,c);
    	System.out.println("����������"+ml.getDivide(b,c));
    	
    	
    	
    	
    }
}
