/**
 * * ��WorkbookParser/Workbook���ࣺ 
 * WorkbookParser.getWorkbook(InputStream ����)������һ��WorkbookParser����
 * getSheets()�����ع�������Workbook���й�����Sheet���������� 
 * getSheet(int i)�����ָ����sheet����,i��0��ʼ
 * getNumberOfSheets()����ù������й�����sheet�ĸ��� 
 * ��WritableWorkbook���ࣺ������д��Ĺ���������
 * Workbook.createWorkbook(File����)������һ��WritableWorkbook����
 * createSheet(String arg0,int arg1):arg0,sheet�����ƣ�arg1��ʾ�ڼ���sheet���±��0��ʼ
 * ��Sheet���ࣺ 
 * getName()�����sheet������
 * getColumns(0)����ȡĳһ�����еĵ�Ԫ��
 * ��WritableSheet���ࣺ
 * ��Cell���ࣺ
 * ��WritableCell���ࣺ
 * ��CellType���ࣺ
 * ��Label���ࣺ
 * ��NumberFormat���ࣺ
 * ��WritableCellFormat���ࣺ
start
����jar���½�һ����ͨ��java��
 * @author Administrator
 *
 */
   import java.io.File;
   import java.io.FileInputStream;
   import java.io.FileNotFoundException;
   import java.io.FileOutputStream;
   import java.io.OutputStream;
   //import java.io.FileOutputStream;
   import java.io.IOException;
  import java.io.InputStream;
  //import java.io.OutputStream;
 import java.util.Date;
 import jxl.*;
public class JXLUse {
	 public static void main(String[] args) throws RowsExceededException,
	             WriteException, IOException, BiffException {
	         String path = "src//com//office//test.xls";
	          writeDataToExcel(path);
	          getExcelData(path);
	  
	      }
 }  
	     // ����jxl.jar��ȡexcle��������
	     public static void getExcelData(String path) throws BiffException,
	            IOException {
	         InputStream is = new FileInputStream(path);
	        //Workbook wb = null;
	          WorkbookParser rwb = (WorkbookParser) WorkbookParser.getWorkbook(is);
	         System.out.println("��ù������й�����sheet�ĸ���=" + rwb.getNumberOfSheets());
	         @SuppressWarnings("unused")
	         Sheet[] sheets = rwb.getSheets();// ���ع�������Workbook���й�����Sheet����������
	       Sheet rs = rwb.getSheet(0);
        System.out.println("���sheet������=" + rs.getName());
	        System.out.println("���sheet����������������=" + rs.getColumns());
	         @SuppressWarnings("unused")
         Cell[] cells1 = rs.getColumn(0);// ��ȡĳһ�����еĵ�Ԫ��
	        System.out.println("���sheet����������������=" + rs.getRows());
	         @SuppressWarnings("unused")
	        Cell[] cells2 = rs.getRow(0);// ��ȡĳһ�е����е�Ԫ��
	
        Cell c00 = rs.getCell(0, 0);// ��һ�����������ڶ���������
	        String t = c00.getContents();// getContents()���κ����͵�Cellֵ����Ϊһ���ַ�������
	         System.out.println(t);
	         if (c00.getType() == CellType.LABEL) {
	             LabelCell labelc00 = (LabelCell) c00;
	             String strc00 = labelc00.getString();
	             System.out.println(strc00);
	        }
	        if (c00.getType() == CellType.NUMBER) {
	             NumberCell number00 = (NumberCell) c00;
	            double strc00 = number00.getValue();
	             System.out.println(strc00);
	         }
	         if (c00.getType() == CellType.DATE) {
	            DateCell datec00 = (DateCell) c00;
	            Date strc00 = datec00.getDate();
	             System.out.println(strc00);
           }
	         rwb.close();
	        is.close();
	    }
	 
	     // ����jxl.jar����excle���д����
	     public static void writeDataToExcel(String path) throws IOException,
	             RowsExceededException, WriteException {
	        File file = new File(path);
	         // ����Workbook����ֻ��Workbook����
	// Method1:������д���excle������
	        WritableWorkbook wwb1 = Workbook.createWorkbook(file);
	         // Method2:��WritableWorkbookֱ��д�뵽�����
	 //OutputStream os = new FileOutputStream(path);
	 //WritableWorkbook wwb2 = Workbook.createWorkbook(os);
	 // ����Excel������
	          WritableSheet ws = wwb1.createSheet("����", 0);//arg0,��ʾsheet������,arg1��ʾ�ڼ���sheet���±��0��ʼ
	  
	  //�����еĿ�ȡ������еĸ߶� jxl��20���߶ȶ�Ӧexcel��1���߶ȣ�jxl��1����ȶ�Ӧexcel��7�����
	         ws.getSettings().setDefaultColumnWidth(25); //�����п�������
	         ws.getSettings().setDefaultRowHeight(600); //�����иߣ�������
	 
	         ws.setColumnView(0, 20);//�����п���һ������Ϊ�ڼ��У��ڶ�������Ϊ�п�
	         ws.setRowView(0, 800);//�����иߣ���һ������Ϊ�ڼ��У��ڶ�������Ϊ�и�
	 
	 //�Ƿ���ʾ���� Ĭ��Ϊtrue��ʾ����
	         ws.getSettings().setShowGridLines(true);
	 
         // (1)���Label����
         Label labelC = new Label(0, 0, "Label��Ԫ��");
	         ws.addCell(labelC);

	        // (2) ������������Formatting�Ķ���  
         WritableFont wf = new WritableFont(WritableFont.TIMES, 18,
                 WritableFont.BOLD, true);
         WritableCellFormat wcfF = new WritableCellFormat(wf);
         //����Label���캯��������˼����Ϊ��������������Ҫд������ݡ����Label����ʽ(��ѡ)
	         Label labelCF = new Label(1, 0, "������������Formatting�Ķ���", wcfF);
        ws.addCell(labelCF);
	 
	         // (3)��������������ɫFormatting�Ķ���
	         WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
	                 WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                 Colour.RED);
         WritableCellFormat wcfFC = new WritableCellFormat(wfc);
	         wcfFC.setAlignment(jxl.format.Alignment.LEFT);//����ˮƽ���뷽ʽΪ����
	         wcfFC.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//�Ѵ�ֱ���뷽ʽָ��Ϊ���� 
	        wcfFC.setWrap(true);//�����Զ�����
	         Label labelCFC = new Label(2, 0, "��������������ɫFormatting�Ķ���", wcfFC);
	         ws.addCell(labelCFC);
	 
	         // (4)���Number����
	         NumberFormat nf = new NumberFormat("#.##");//���캯����#.##��ʾ������λС��
	        WritableCellFormat wcfN = new WritableCellFormat(nf);
        jxl.write.Number labelNF = new jxl.write.Number(1, 1, 3.1415926, wcfN);
	         ws.addCell(labelNF);

	         // (5)���Boolean����
         jxl.write.Boolean labelB = new jxl.write.Boolean(0, 2, false);//��������Ϊ��������������ֵ
	         ws.addCell(labelB);
	
	        // (6)���DateTime����
	         jxl.write.DateTime labelDT = new jxl.write.DateTime(0, 3,
	                 new java.util.Date());
	        ws.addCell(labelDT);
	
	         // (7)��Ӵ���formatting��DateFormat����
	        jxl.write.DateFormat df = new jxl.write.DateFormat(
	                 "dd MM yyyy hh:mm:ss");//�������ڵĸ�ʽ
	         jxl.write.WritableCellFormat wcfDF = new jxl.write.WritableCellFormat(
	                 df);
	       jxl.write.DateTime labelDTF = new jxl.write.DateTime(1, 3,
	                new java.util.Date(), wcfDF);
	        ws.addCell(labelDTF);

	         //(8)���ͼƬ ֻ֧��PNG��ʽ��ͼƬ
	        File image=new File("src//com//office//1.png");
         //ǰ��λ����ʼ�񣬺���λ��ͼƬռ���ٸ��񣬲�����λ�ã��ĸ����������Ͷ���double�������� x, y, width, height,ע�⣬
	//����Ŀ�͸߿ɲ���ͼƬ�Ŀ�͸ߣ�����ͼƬ��Ҫռ�ĵ�λ��ĸ���
	        WritableImage wimage=new WritableImage(3,6,4,8,image);
	        ws.addImage(wimage);
	         
	         //(10)�����ע
	        Label labelStr=new Label(1,10,"");
	       
	         
	        //�ϲ���Ԫ��
	         ws.mergeCells(3, 0, 5, 0);// �ϲ���һ�еĵ�4�е���6��   ˮƽ����
	         ws.mergeCells(3, 1, 3, 5);//�ϲ���4�еĵĵڶ��е�������  ��ֱ����
	        ws.mergeCells(1, 6, 2, 9);
	        
	        // ���ñ߿�
	       jxl.write.WritableCellFormat wcsB=new jxl.write.WritableCellFormat ();
	        wcsB.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.DOUBLE);
	        wcsB.setBackground(Colour.GREEN);//����ɫ
	        Label border=new Label(1,4,"�߿�����",wcsB);
	        ws.addCell(border);
	        
	        //ͨ����˾��ֵ  ��ʱ��������
	        NumberFormat nf1 = new NumberFormat("#");
	//        WritableCellFormat wcfN1 = new WritableCellFormat(nf1);
	 //        jxl.write.Number a = new jxl.write.Number(0, 6, 3, wcfN1);
	 //        jxl.write.Number b = new jxl.write.Number(0, 7,2, wcfN1);
	 //        ws.addCell(a);
	 //        ws.addCell(b);
	 //        Formula l3 = new Formula(0,8,"=SUM(A7:A8)");
 //        WritableCell cell = l3.copyTo(0, 8);
	 //        ws.addCell(cell);
	
	        
	 // д��excle������
	        wwb1.write();
     System.out.println("����jxl��excleд���������");
	         
	        // �ر�excle����������
	        wwb1.close();
	
	    }
	}

