
import java.awt.Frame;
import java.awt.Label;
public class TestFrame extends Frame {
 
    Label l1 = new Label("Test11");
    Label l2 = new Label("Test22");
    public TestFrame(){
       //��ʹ��Layout
       this.setLayout(null);
       //���ô���λ�����С
       this.setBounds(100,100,200,200);
       //���Label
       this.add(l1);
       //���Label
       this.add(l2);
       //��Ϊ��ʹ��Layout,���Ա������ÿؼ���λ�����С
       l1.setBounds(10, 40, 60, 20);
       //��Ϊ��ʹ��Layout,���Ա������ÿؼ���λ�����С
       l2.setBounds(15, 60, 60, 20);
       //��ʾ����
       this.setVisible(true);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
       // TODO Auto-generated method stub
       TestFrame r = new TestFrame();
    }
}