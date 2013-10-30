


import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Genera_decis_tree.DataScanPane;
import Genera_decis_tree.FileAndFirstLine;
import Genera_decis_tree.OuterFace;
import Genera_decis_tree.PublicData;

public class MyMonitor implements ActionListener,MouseListener,MouseMotionListener{
	  FileAndFirstLine ffl=new FileAndFirstLine();
	  PublicData pd=new PublicData();
	  private Frame frame=new Frame("�ļ�����");
	  private FileDialog fd_load=new FileDialog(frame,"���ļ�",FileDialog.LOAD);
	  private FileDialog fd_save=new FileDialog(frame,"�����ļ�",FileDialog.SAVE);
	   String file="";
	   OuterFace ot=new OuterFace();
	   DataScanPane dsp=new DataScanPane();
	public void actionPerformed(ActionEvent e){
		String s=e.getActionCommand();
		if(s.equals("�˳�")){
			System.exit(0);
		}
		if(s.equals("������"))
		{
			System.out.println("����������С��");
			Attri_select_method asm=new Attri_select_method();
			asm.createUI();
		}
		
		if(s.equals("��"))
		{
		  fd_load.setVisible(true);
		  String d=fd_load.getDirectory();
		  String f=fd_load.getFile();
		  if((d!=null)&&(f!=null)){
				 file=d+f;				 	
				 if(file.endsWith(".txt")){
					 ffl.creatUI();             //�����������txt�ļ�����Ҫ�ֶ�ָ����������
					 PublicData.setFile(file);    //ͬʱ���ļ������óɵ�ǰ�ģ�������ʱ�ļ�����txt�ļ���
					                            //������ffl�Ĵ��ڽ������ύ�ٴα�������Ϊxls�ļ�
				 System.out.println(PublicData.getFile());
				 }
				 else if(file.endsWith(".xls")){
					 PublicData.setFile(file);
		
				 }
			   }
		   }
		//�ڿ������ڣ�û�ж������ݸ�ʽ���޷�����������Ϊ���ݣ����Ի���û��
		 else if(s.equals("���Ϊ"))
		 {		   
		   fd_save.setVisible(true);
		   String d=fd_save.getDirectory();
		   String f=fd_save.getFile();
		   if((d!=null)&&(f!=null))
		         {
				   file=d+f;					
				   frame.setTitle("�ļ����Ϊ"+file);
				   }
		  }
		 else if(s.equals("����")){
			   try{
				   PrintWriter pw=new PrintWriter(new FileWriter(file));
				   pw.close();
			   }catch(IOException e1){
				   e1.printStackTrace();
			   }
	 
		 }
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseMoved(MouseEvent e) {	 
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}