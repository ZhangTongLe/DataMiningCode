package Genera_decis_tree;
//name�����洢���ڵ�ĵ�ǰ���ԣ�value�����洢��ǰ���Ե���Ϣ����ֵ
public class TreeNode {
   public String name;
   public float value;
   public  int YES=0;
   public  int NO=0;
   public  float gain=0;
   public  int Row=0;    //�洢���ڵ������
   public  int Comlumn=0;  //�洢���ڵ������
   public TreeNode(String name,int yes,int no,float gain,int row,int Column){
	   this.name=name;
	   this.YES=yes;
	   this.NO=no;
	   this.gain=gain;
	   this.Row=row;
	   this.Comlumn=Column;
   }
public TreeNode(String name, float value) {
	 this.name=name;
	   this.value=value;
}
public String getName() {
	return name;
}
public float getValue() {
	return value;
}
public int getYES() {
	return YES;
}
public int getNO() {
	return NO;
}
public float getGain() {
	return gain;
}
public int getRow() {
	return Row;
}
public int getComlumn() {
	return Comlumn;
}

}
