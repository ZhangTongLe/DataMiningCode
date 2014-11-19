package Apriori;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**Ƶ��ģʽ�ھ��㷨Aprioriʵ��
 * @author yangliu
 * @qq 772330184
 * @blog http://blog.csdn.net/yangliuy
 * @mail yang.liu@pku.edu.cn
 *
 */

public class AprioriFPMining {
	private int minSup;//��С֧�ֶ�
	private static List<Set<String>> dataTrans;//��List<Set<String>>��ʽ������������ݿ�,����Set��������
	
	public int getMinSup() {
		return minSup;
	}
	
	public void setMinSup(int minSup) {
		this.minSup = minSup;
	}
	
	/**
	 * @param args
	 */
	 public static void main(String[] args) throws IOException { 
		AprioriFPMining apriori = new AprioriFPMining();
		double [] threshold = {0.25, 0.20, 0.15, 0.10, 0.05};   //���Ŷȣ�  ����֧�ֶ�
		String srcFile = "D:/predata/mushroom.dat";
		String shortFileName = srcFile.split("/")[2];      //��Mushroom.dat
        String targetFile = "D:/predata/" + shortFileName.substring(0, shortFileName.indexOf("."))+"_fp_threshold";
        //��D:/predata/Ŀ¼��Ϊÿ������ÿ��֧�ֶȷֱ�洢����ϸ��Ƶ����
        //substring(int beginIndex,int endIndex) ���� һ���µ��ַ��������Ǵ��ַ�����һ���Ӵ�
		dataTrans = apriori.readTrans(srcFile);
		for(int k = 0; k < threshold.length; k++){               //�Բ�ͬ���Ŷȷֱ�ִ�и��㷨
			System.out.println(srcFile + " threshold: " + threshold[k]);
			long totalItem = 0;
			long totalTime = 0;
			FileWriter tgFileWriter = new FileWriter(targetFile + (threshold[k]*100));
			apriori.setMinSup((int)(dataTrans.size() * threshold[k]));
			//Ƶ��ģʽ��ռ��ֱ�*��������=Ƶ��ģʽ�ܸ���
			//ԭʼĢ��������0.25ֻ��Ҫ67���ܳ��������lwl��15��
			long startTime = System.currentTimeMillis();
			Map<String, Integer> f1Set = apriori.findFP1Items(dataTrans);
			long endTime = System.currentTimeMillis();
			totalTime += endTime - startTime;
			//Ƶ��1���Ϣ�ü���֧�ֶ�
			Map<Set<String>, Integer> f1Map = new HashMap<Set<String>, Integer>();
			for(Map.Entry<String, Integer> f1Item : f1Set.entrySet()){
				Set<String> fs = new HashSet<String>();
				fs.add(f1Item.getKey());
				f1Map.put(fs, f1Item.getValue());
			}
			
			totalItem += apriori.printMap(f1Map, tgFileWriter);
			Map<Set<String>, Integer> result = f1Map;
			do {	
				startTime = System.currentTimeMillis();
				result = apriori.genNextKItem(result);
				endTime = System.currentTimeMillis();
				totalTime += endTime - startTime;
				totalItem += apriori.printMap(result, tgFileWriter);
			} while(result.size() != 0);
			tgFileWriter.close();
			System.out.println("����ʱ��" + totalTime + "ms");
			System.out.println("����" + totalItem + "��Ƶ��ģʽ");
		}
	}

	 /**��Ƶ��K-1�����Ƶ��K�
	 * @param preMap ����Ƶ��K���map
	 * @param tgFileWriter ����ļ����
	 * @return int Ƶ��i�����Ŀ
	 * @throws IOException 
	 */
	private Map<Set<String>, Integer> genNextKItem(Map<Set<String>, Integer> preMap) {
		// TODO Auto-generated method stub
		Map<Set<String>, Integer> result = new HashMap<Set<String>, Integer>();
		
		long startTime = 0;
		long endTime = 0;
		long totalTime=0;
			
		//��������k-1�����k�
		List<Set<String>> preSetArray = new ArrayList<Set<String>>();   //��list�ݴ洫�����Map���͵�key����Ƶ��ģʽ��
		                    //����������Ͳ�һ�»������ֱ��ʹ���β� ��
		for(Map.Entry<Set<String>, Integer> preMapItem : preMap.entrySet()){
			preSetArray.add(preMapItem.getKey());
		}
		int preSetLength = preSetArray.size();
		/*************************************���Ӳ���********************/
		for (int i = 0; i < preSetLength - 1; i++) {                                 
			for (int j = i + 1; j < preSetLength; j++) {
				String[] strA1 = preSetArray.get(i).toArray(new String[0]);
				String[] strA2 = preSetArray.get(j).toArray(new String[0]);
				if (isCanLink(strA1, strA2)) { // �ж�����k-1��Ƿ�������ӳ�k���������
					Set<String> set = new TreeSet<String>();    //����һ���µĿ� set���� set ����Ԫ�ص���Ȼ˳������
					//ע�⣺TreeSet ʵ����ʹ���� compareTo���� compare������ִ�����еļ��Ƚ�
					for (String str : strA1) {
						set.add(str);                  //�ȼ���A1������Ԫ��
					}
					set.add((String) strA2[strA2.length - 1]); //�ټ�����A1��ǰk-1����ͬ�����һ�ͬ��A2�����һ��   ���ӳ�k�
					// �ж�k��Ƿ���Ҫ���е����������Ҫ��cut��������뵽k��б���
		/***********************************��֦����**********************************/
					startTime = System.currentTimeMillis();
					if (!isNeedCut(preMap, set)) {//���ڵ����ԣ����뱣֤k�������k-1���Ӽ�����preMap�г��֣�����͸ü��и�k�
						result.put(set, 0);
					}
					endTime=System.currentTimeMillis();
					totalTime+= endTime - startTime;
					System.out.println("����Ƿ��ȥ��������ģʽ��ʱ��"+totalTime+"ms");          //���������֦�����ʱ
				}
			}
		}
		return assertFP(result);//�����������ݿ⣬��֧�ֶȣ�ȷ��ΪƵ���
	}
	
	/**���k��Ƿ�ü��С����ڵ����ԣ����뱣֤k�������k-1���Ӽ�����preMap�г��֣�����͸ü��и�k�
	 * @param preMap k-1��Ƶ����map
	 * @param set ������k�
	 * @return boolean �Ƿ�ü���
	 * @throws IOException 
	 */
	private boolean isNeedCut(Map<Set<String>, Integer> preMap, Set<String> set) {
		// TODO Auto-generated method stub
		boolean flag = false;
		List<Set<String>> subSets = getSubSets(set);    //�Ȼ�ȡk�set��k-1�����
		for(Set<String> subSet : subSets){
			if(!preMap.containsKey(subSet)){
				flag = true;
				break;
			}
		}
		
		return flag;
	}
/**���Լ�д���ж��Ƿ���Ҫ���в��裬˼·������k-1�A������Ԫ�ع��ɵ�ȫ��O��Ȼ���ȱʧ�Ĳ��ּ�O-A��ΪB
 * ���жϣ����K�����B����С�
 * */
	
	private boolean isMyNeedCut(Map<Set<String>, Integer> preMap, Set<String> set) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Set<String> all=new HashSet<String>();
		Set<String>  n=new HashSet<String>();
		for(Map.Entry<Set<String>, Integer> f1MapItem : preMap.entrySet()){
			for(String p : f1MapItem.getKey()){
				char[] fre;
				fre=p.toCharArray();
				
	     }
		}
		
		
		
		return flag;
	}
	
	
	
	
	/**��ȡk�set������k-1���Ӽ�
	 * @param set Ƶ��k�
	 * @return List<Set<String>> ����k-1���Ӽ�����
	 * @throws IOException 
	 */
	private List<Set<String>> getSubSets(Set<String> set) {
		// TODO Auto-generated method stub
		String[] setArray = set.toArray(new String[0]);    //set.toArray()  ����һ������ set ������Ԫ�ص�����
		List<Set<String>> result = new ArrayList<Set<String>>();
		for(int i = 0; i < setArray.length; i++){
			Set<String> subSet = new HashSet<String>();           //subset��set�����磺{12,345,6,7}
			for(int j = 0; j < setArray.length; j++){
				if(j != i) subSet.add(setArray[j]);               //subset��ÿ�ζ���ȱʧһ��set�е��ֱ��ȫ���ֻ�һ��
			}
			result.add(subSet);                                    //result��list���ͣ�ÿ�μ���subset��subset�����³�ʼ��
			                                                   //�磺{{12,345,6,7},{234,5645,345,234},{.........}........}
		}
		return result;
	}

	/**�����������ݿ⣬��֧�ֶȣ�ȷ��ΪƵ���
	 * @param allKItem ��ѡƵ��k�
	 * @return Map<Set<String>, Integer> ֧�ֶȴ�����ֵ��Ƶ�����֧�ֶ�map
	 * @throws IOException 
	 */
	private Map<Set<String>, Integer> assertFP(
			Map<Set<String>, Integer> allKItem) {
		// TODO Auto-generated method stub
		Map<Set<String>, Integer> result = new HashMap<Set<String>, Integer>();
		for(Set<String> kItem : allKItem.keySet()){
			for(Set<String> data : dataTrans){
				boolean flag = true;
				for(String str : kItem){
					if(!data.contains(str)){
						flag = false;
						break;
					}
				}
				if(flag) allKItem.put(kItem, allKItem.get(kItem) + 1);
			}
			if(allKItem.get(kItem) >= minSup) {
				result.put(kItem, allKItem.get(kItem));
			}
		}
		return result;
	}

	/**�������Ƶ��K��Ƿ�������ӣ�����������ֻ�����һ���ͬ
	 * @param strA1 k�1
	 * @param strA1 k�2
	 * @return boolean �Ƿ��������
	 * @throws IOException 
	 */
	private boolean isCanLink(String[] strA1, String[] strA2) {
		// TODO Auto-generated method stub
		boolean flag = true;
		if(strA1.length != strA2.length){
			return false;
		}else {
			for(int i = 0; i < strA1.length - 1; i++){               //ǰk-1��������
				if(!strA1[i].equals(strA2[i])){
					flag = false;
					break;
				}
			}
			if(strA1[strA1.length -1].equals(strA2[strA1.length -1])){   //���һ��ȴ�������
				flag = false;
			}
		}
		return flag;
	}

	/**��Ƶ��i������ݼ�֧�ֶ�������ļ� ��ʽΪ ģʽ:֧�ֶ�
	 * @param f1Map ����Ƶ��i�������<i� , ֧�ֶ�>
	 * @param tgFileWriter ����ļ����
	 * @return int Ƶ��i�����Ŀ
	 * @throws IOException 
	 */
	private int printMap(Map<Set<String>, Integer> f1Map, FileWriter tgFileWriter) throws IOException {
		// TODO Auto-generated method stub
		for(Map.Entry<Set<String>, Integer> f1MapItem : f1Map.entrySet()){
			for(String p : f1MapItem.getKey()){
				tgFileWriter.append(p + " ");
			}
			tgFileWriter.append(": " + f1MapItem.getValue() + "\n");
		}
		tgFileWriter.flush();
		return f1Map.size();
	}	
	/**����Ƶ��1�
	 * @param fileDir �����ļ�Ŀ¼
	 * @return Map<String, Integer> ����Ƶ��1�������<1� , ֧�ֶ�>
	 * @throws IOException 
	 */
	private Map<String, Integer> findFP1Items(List<Set<String>> dataTrans) {
		// TODO Auto-generated method stub
		Map<String, Integer> result = new HashMap<String, Integer>();
		Map<String, Integer> itemCount = new HashMap<String, Integer>(); //�洢��������{I1,I2,I3}��һ�I1�ĸ���
		for(Set<String> ds : dataTrans){
			for(String d : ds){
				if(itemCount.containsKey(d)){
					itemCount.put(d, itemCount.get(d) + 1); //����洢�����Ѿ����������+1
				} else {
					itemCount.put(d, 1);
				}
			}
		}
		
		for(Map.Entry<String, Integer> ic : itemCount.entrySet()){   
			//ӳ�����-ֵ�ԣ���Map.entrySet ��������ӳ��� collection ��ͼ�����е�Ԫ�����ڴ��ࡣ
			//���ӳ�������õ�Ωһ ������ͨ���� collection ��ͼ�ĵ�������ʵ��
			// entrySet()���ش�ӳ���а�����ӳ���ϵ�� set ��ͼ��
			//���ص� set �е�ÿ��Ԫ�ض���һ�� Map.Entry���� set ��ӳ��֧�֣����Զ�ӳ��ĸı���ڴ� set �з�ӳ��������֮��Ȼ��
			if(ic.getValue() >= minSup){
				result.put(ic.getKey(), ic.getValue());  //�����㷧ֵ�������������
			}
		}
		return result;
	}

	/**��ȡ�������ݿ�
	 * @param fileDir �����ļ�Ŀ¼
	 * @return List<String> �������������
	 * @throws IOException 
	 */
	private List<Set<String>> readTrans(String fileDir) {
		// TODO Auto-generated method stub
		List<Set<String>> records = new ArrayList<Set<String>>(); 
        try { 
            FileReader fr = new FileReader(new File(fileDir)); 
            BufferedReader br = new BufferedReader(fr); 
       
            String line = null; 
            while ((line = br.readLine()) != null) { 
                if (line.trim() != "") { 
                    Set<String> record = new HashSet<String>(); 
                    String[] items = line.split(" "); 
                    for (String item : items) { 
                        record.add(item); 
                    } 
                    records.add(record); 
                } 
            } 
        } catch (IOException e) { 
            System.out.println("��ȡ�����ļ�ʧ�ܡ�"); 
            System.exit(-2); 
        } 
        return records; 
	}
}
 