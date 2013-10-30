package JListExample;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
/**
 * ����������δ�����JList��˫�����Ĳ���������JList�������ṩ������EventListener,������Ǳ�������
MouseListener���ﵽ����˫�������¼�������Ҫ��ô֪�����ǵ������ĸ���Ŀ��˫������أ����ǿ�������JList�����ṩ��
LocatToindex()������֪������Ϊ�������ٵķ���:
   �������һ��ʼ������й������ƣ�������ĳ������������˫����꣬����������ƾͻ��Ƶ��ұ�ȥ����֮��ͬ��
   1.�����������Ӧ��DefaultListModel�࣬����DefaultListModel��ʵ����Vector�еķ�����ʹ�����ڴ���̬��JList��Ŀֵ 
     �Ƚ�����.
   2.��������Ҫ��������¼���Ϊ�˱�д���㣬�ڳ��������Ǽ̳�MouseAdapte������.
   3.�ڳ����У����ǽ�������DataModel����һ��JList,Ҳ����list1һ��ʼʱ�ὫString Array s�е�����ֵ���η���list1����
     Ŀ�У���list2һ��ʼΪ�հס�
 *
 *
 */
public class JList8 extends MouseAdapter implements ActionListener{
JList list1=null; 
JList list2=null; 
JList tarlist=null;
DefaultListModel mode1=null;
DefaultListModel mode2=null;
DefaultListModel mode3=null;

JLabel var=new JLabel("����");
JLabel selvar=new JLabel("�Ա���");
JLabel tarvar=new JLabel("�����");
JPanel right=new JPanel();
JButton tarBut=new JButton("���������");
JButton selBut=new JButton("�����Ա���");

public static boolean varFlag=false;
public static boolean tarFlag=false;
public static boolean selFlag=false;          //�ֱ�����ʶ��ѡ���List�������ĸ�list
public static int index=0;                    //�����๫�õ� �洢��ǰѡ���list�������

String[] s = {"����","�ձ�","��½","Ӣ��","����","�����","����","����"};

public JList8(){
JFrame f=new JFrame("JList");
Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
f.setIconImage(icon);

Container contentPane=f.getContentPane();
contentPane.setLayout(new GridLayout(2,2));

tarBut.addActionListener(this);
selBut.addActionListener(this);

mode1=new DataModel(1);
list1=new JList(mode1);
list1.setBorder(BorderFactory.createTitledBorder("��������!"));
list1.addMouseListener(this);//һ��������¼�����ִ��.

mode2=new DataModel(2);
list2=new JList(mode2);
list2.setBorder(BorderFactory.createTitledBorder("����ϲ�����ĸ���������!"));
list2.addMouseListener(this);//һ��������¼�����ִ��.

mode3=new DataModel(3);
tarlist=new JList(mode3);
tarlist.setVisibleRowCount(1);           //��������ʾ������ֻ��ʾһ��
tarlist.addMouseListener(this);

right.setLayout(new GridLayout(2,2));
right.add(tarBut);
right.add(tarlist);
right.add(selBut);
right.add(new JScrollPane(list2));

contentPane.add(var);
contentPane.add(tarvar);
contentPane.add(new JScrollPane(list1));
contentPane.add(right);
   f.pack();             //�ô�������Ӧ��Ļ
   f.setVisible(true);
   f.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
         System.exit(0);
      }
   });    
}
public static void main(String[] args){
new JList8();
}
//�����������¼�.
public void mouseClicked(MouseEvent e){
    /*��list1���ԣ��������ĳ����Ŀ����������ʱ����������JList���ṩ��locationToIndex()�������ҵ�����������Ŀ����
     *��tmpȡ�ô���Ŀ����Ŀֵ��Ȼ�󽫴���Ŀֵ���ӵ�mode2��[mode2.addElement(tmp)],��setModel��������list2��
     *ListModel,ʹlist2����ʾ�������ӵ���Ŀ�����ո���list1˫������Ŀɾ��.
     */
if (e.getSource()==list1){
    	varFlag=true;
    	selFlag=false;
    	
       index=list1.locationToIndex(e.getPoint());
}
if (e.getSource()==list2){
    	selFlag=true;
    	varFlag=false;   //��һ�Կ���
    	
       index=list2.locationToIndex(e.getPoint());
 }
if(e.getSource()==tarlist){
	tarFlag=true;
	varFlag=false;
	
	index=tarlist.locationToIndex(e.getPoint());
}
}
class DataModel extends DefaultListModel{
   DataModel(int flag){
      if (flag==1){
      for (int i=0;i<s.length;i++) addElement(s[i]);         
      }
   }
}
@Override
public void actionPerformed(ActionEvent e) {
	String s=e.getActionCommand();
	if(s.equals("���������")&&(tarlist.getModel().getSize()==0)&&varFlag==true){            //��Ŀ�����List����ӽ��
		String tmp=(String)mode1.getElementAt(index);
	    System.out.println("�������"); 
		 mode3 .addElement(tmp);
	     tarlist.setModel(mode3);
	     mode1.removeElementAt(index);
	     list1.setModel(mode1);
	}
	else if(s.equals("���������")&&tarlist.getModel().getSize()>0&&tarFlag==true){   //��Ŀ�����List���Ƴ����
		String tmp=(String)mode1.getElementAt(index);
		System.out.println("�Ƴ�����");
		mode1.addElement(tmp);
		list1.setModel(mode1);
		mode3.removeElementAt(index);
		tarlist.setModel(mode3);
	}
	else if(s.equals("�����Ա���")&&varFlag==true){
		String tmp=(String)mode1.getElementAt(index);
		mode2.addElement(tmp);
		list2.setModel(mode2);
		mode1.removeElementAt(index);
		list1.setModel(mode1);
	}
	else if(s.equals("�����Ա���")&&selFlag==true){
		String tmp=(String)mode2.getElementAt(index);
		mode1.addElement(tmp);
		list1.setModel(mode1);
		mode2.removeElementAt(index);
		list2.setModel(mode2);	
	}
	
}
}