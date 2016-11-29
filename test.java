package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class test {

	public static ArrayList<String[]> lines = new ArrayList<>();

	public static ArrayList<String[]> result = new ArrayList<>();

	public static final String input = "/Users/wuhuan/Downloads/file3.txt";
	public static final String output = "/Users/wuhuan/Downloads/file4.txt";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File(input);
		if (file.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String s;
			try {
				while ((s = br.readLine()) != null) {//读行
					// s.replace(" ", "");
					String[] splitted = s.split("\\@");
					if (lines.isEmpty())
						lines.add(splitted);
					else {
						if (splitted[0].equals(lines.get(lines.size() - 1)[0])) {// 同一组
							lines.add(splitted);
						} else {
							cacl();
							lines.clear();
							lines.add(splitted);
						}
					}

				}
				FileWriter fw = new FileWriter(output,false);  
				// BufferedWriter writer = new BufferedWriter(fw);
				for (int i = 0; i < result.size(); i++) {
					for (int j = 0; j < result.get(0).length; j++)
						fw.write(result.get(i)[j] + " ");
					// System.out.print(result.get(i)[j]);
					fw.write("\n");
					// System.out.println("\n");

				}
				fw.flush();
				fw.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void cacl() {
		String[] temp = new String[lines.get(0).length];
		for (int j = 1; j < lines.get(0).length; j++) {// 列
			temp = lines.get(0);
			if (lines.size() > 1)
				for (int i = 1; i < lines.size(); i++) {// 行
					try {

						Integer tInteger = Integer.parseInt(lines.get(i)[j]) + Integer.parseInt(temp[j]);
						temp[j] = tInteger.toString();
					} catch (Exception e) {//debug 哪一行少数
						System.out.println(lines.get(i)[0] + " " + lines.get(i)[1] + " " + lines.get(i)[2] + " "
								+ lines.get(i)[3] + " " + lines.get(i)[4] + " " + lines.get(i)[5]);
					}
				}
		}
		temp[0] = lines.get(0)[0];// 组号
		for (int k = 1; k < temp.length; k++) {
			try {

				Float tfloat = Float.parseFloat(temp[k]) / lines.size();
				DecimalFormat decimalFormat=new DecimalFormat("###.##");//整数不显示小数点，小数会显示
				
				temp[k] = decimalFormat.format(tfloat);//format 返回的是字符串
			} catch (Exception e) {
				for (int i = 0; i < temp.length; i++)
					;
				// System.out.println(temp[i]);
			}
		}
		result.add(temp);
	}

}
