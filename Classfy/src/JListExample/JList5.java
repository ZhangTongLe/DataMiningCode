package JListExample;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
/**
 * DefaultListModel�࣬�ɷ��ִ����ṩ���ٺ��õķ�����������������������һ����Ŀ(
addElement())������ɾ��һ����Ŀ(removeElement)����������Ժܷ����������ѯ(getElementAt())����(copyInto())��Ŀ��
����������Է��֣�����DefaultListModel����ֱ�Ӷ�̬�ظ���JList����Ŀֵ��������Ҫ���в���һ��Vecotr����;�����JList(
Vector v)������캯������˵��������ʵ�����.
��������ListModel��AbstractListModel������JList��ʲô�ô�������ֻҪ��ô�룬ListModel���ľ��ǡ��г�ģʽ������ôÿ
����ʦ�������Լ����ε�ѧ���ɼ�����ʦӦ�ÿ��Կ���ÿ��ͬѧ�ĳɼ������Ӧ��ֻ�ܿ����Լ��ĳɼ����������Ǿͻ������ֲ�
ͬ�ġ��г�ģʽ��������ֻ��Ҫȥ��дgetElementAt()�������ͻ��в�ͬ���г�ģʽ����
 * 
 * 
 * @author Administrator
 *
 */
public class JList5
{
    public JList5()
    {
        JFrame f = new JFrame("JList");
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new GridLayout(1,2));
        ListModel mode = new DataModel(1);
        JList list1 = new JList(mode);
        list1.setVisibleRowCount(5);
        list1.setBorder(BorderFactory.createTitledBorder("�������Щ�����"));
        
        mode = new DataModel(2);
        JList list2 = new JList(mode);
        list2.setBorder(BorderFactory.createTitledBorder("�������Щ���ݿ������"));
        
        contentPane.add(new JScrollPane(list1));
        contentPane.add(new JScrollPane(list2));
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
        new JList5();
    }
}
class DataModel extends AbstractListModel
{
    String[] s = {"MS SQL","MySQL","IBM DB2","ORACLE","Windows 2000","Linux","UNix","OS2"};
    int flag;
    
    DataModel(int flag)
    {
        this.flag = flag;
    }
    public Object getElementAt(int index)
    {
        String tmp = null;
        
        if (flag == 1)
            tmp = (index+1)+"."+s[index++];
        if (flag == 2)
        {
            if(index < 4)
                tmp = (index+1)+"."+s[index++];     
        }
        
        return tmp;
    }
    
    public int getSize()
    {
        return s.length;
    }
}