package NeuralNetwork;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test_Classfy {

    /**
     * @param args
     * @throws IOException
     * ���Գ����16��,����һ��BPNN,�趨�������Ԫ��Ŀ.
           ���Գ����43��,ͨ��train����ѵ������,��������������������Լ�ָ��,ֻ��Ҫ���㴴������Ԫ���Բ�(�������)�������Ĺ�ģ����
     */
	public Double trainData[][];
	public Double testTarget[][];

    public  void test_bp(){
        BP_Classfy bp = new BP_Classfy(32, 15, 4);
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i != 1000; i++) {
            int value = random.nextInt();
            list.add(value);
        }
        
        for (int i = 0; i != 200; i++) {
            for (int value : list) {
                double[] real = new double[4];
                if (value >= 0)
                    if ((value & 1) == 1)
                        real[0] = 1;      //������
                    else
                        real[1] = 1;    //��ż��
                else if ((value & 1) == 1)
                    real[2] = 1;        //������
                else  
                    real[3] = 1;        //��ż��
                double[] binary = new double[32];
                int index = 31;
                do {
                    binary[index--] = (value & 1);
                    value >>>= 1;
                } while (value != 0);

                bp.train(binary, real);
            }
        }
        System.out.println("ѵ����ϣ�����������һ���������֣������罫�Զ��ж������������Ǹ�������������ż����");
        while (true) {
            byte[] input = new byte[10];
            try {
				System.in.read(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Integer value = Integer.parseInt(new String(input).trim());
            int rawVal = value;
            double[] binary = new double[32];
            int index = 31;
            do {
                binary[index--] = (value & 1);
                value >>>= 1;
            } while (value != 0);

            double[] result = bp.test(binary);

            double max = -Integer.MIN_VALUE;
            int idx = -1;

            for (int i = 0; i != result.length; i++) {
                if (result[i] > max) {
                    max = result[i];
                    idx = i;
                }
            }

            switch (idx) {
            case 0:
                System.out.format("%d��һ��������\n", rawVal);
                break;
            case 1:
                System.out.format("%d��һ����ż��\n", rawVal);
                break;
            case 2:
                System.out.format("%d��һ��������\n", rawVal);
                break;
            case 3:
                System.out.format("%d��һ����ż��\n", rawVal);
                break;
            }
        }
    }
    //����ѵ������
  public void setTrainData(){
	  
  }
  //�����������
  public void setTargetData(){
	  
  }
  /**
  public static void main(String[] args){ 
  	Test_Classfy bp=new Test_Classfy();
  	bp.test_bp();
  }
  */
}

