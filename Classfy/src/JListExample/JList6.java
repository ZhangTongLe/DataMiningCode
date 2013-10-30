package JListExample;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
/**
 * ��JList�ϼ���Icon,
Ҫ���˽�ListCellRenderer interface.���Ǳ��������interface������ķ�������ͼ����JList�е�ÿ����Ŀ��
ListCellRenderer interface��ֻ������һ���������Ǿ���getListCellRendererComponent,������������е�࣬���ǰ����г���
����:
public Component getListCellRendererComponent(JList list, Object value,int index,boolean isSelected,boolean cellHasFocus)
list:����Ҫ���ϵ�ͼ���JList�����
value:JList��Ŀֵ����list.getModel().getElementAt(index)�����ص�ֵ��
index:ΪJList��Ŀ������ֵ����0��ʼ��
isSelected��cellHasFocus:�ж�JList�е���Ŀ�Ƿ��б�ѡȡ�����н������롣
������4����������������JList�Ļ�ͼ��ʽ(setCellRenderer())ʱ�Զ�����JList����ṩ����ֻҪ������ô����
getListCellRendererComponent()�����е�4�������������赣����ô�������롣
   Ҫ��JList�м���Iconͼ��ļ��ɾ��ǽ�JList�е�ÿһ����Ŀ������JLabel,��ΪJLabel��ʹ��������ͼ���Ϸǳ��ķ��㣬Ҫ����
JList��ͼ�񣬱���ʹ��setCellRenderer(ListCellRenderer cellRenderer)���������
 * 
 * 
 * @author Administrator
 *
 */
public class JList6
{
    public JList6()
    {
        String[] s = {"����","ƻ��","��ݮ","�㽶","����"};
        JFrame f = new JFrame("JList");
        Container contentPane = f.getContentPane();
        JList list1 = new JList(s);
        list1.setBorder(BorderFactory.createTitledBorder("��ϲ������Щˮ����"));
        /*������JList�л���ͼ���ڴ˲����У����ǲ���һ��CellRenderer���󣬴˶���ʵ����ListCellRenderer interface,
         *��˿��Է���һ��ListCellRenderer������setCellRenderer()�����Ĳ���.
         */
        list1.setCellRenderer(new CellRenderer());
        
        contentPane.add(new JScrollPane(list1));
        f.pack();
        f.show();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                    System.exit(0);
            }
        });
    }
    
    public static void main(String args[])
    {
        new JList6();
    }
}
class CellRenderer extends JLabel implements ListCellRenderer
{
   /*��CellRenderer�̳�JLabel��ʵ��ListCellRenderer.������������JLabel���ڲ�ͼ�����ԣ����CellRenderer�̳���JLabel
    *����JList�е�ÿ����Ŀ����Ϊ��һ��JLabel.
    */
    CellRenderer()
    {
        setOpaque(true);
    }
    /*�����ﵽ������ʵ��getListCellRendererComponent()����*/
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus)
    {   
        /*�����ж�list.getModel().getElementAt(index)�����ص�ֵ�Ƿ�Ϊnull,�����ϸ������У���JList�ı�����"�������
         *Щ���ݿ����"����index>=4����Ŀֵ����ȫ����Ϊnull.������������У���Ϊ������nullֵ�������û�м��������
         *�ϲ�û�й�ϵ.
         */
        if (value != null)
        {
            setText(value.toString());
            setIcon(new ImageIcon("d:\\predata\\JListPicture\\"+(index+1)+".jpg"));
        }
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            //����ѡȡ��ȡ��ѡȡ��ǰ���뱳����ɫ.
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }    
}