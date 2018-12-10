package countdownlatch;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Demo {

	private int[] numbers;
	public Demo(int length) {
		numbers = new int[length];
	}
	//计算每一行的和
	public void add(String line,int index) {
		int sum = 0;
		String [] str = line.split(",");
		for (String string2 : str) {
			sum += Integer.parseInt(string2);
		}
		numbers[index] = sum;
		System.out.println(Thread.currentThread().getName()+"线程执行计算任务"+line+"结果为："+sum);
	}
	public void sum() {
		int total = 0;
		for (int number : numbers) {
			total += number;
		}
		System.out.println("汇总线程开始执行了......"+total);
	}
	private static List<String> readLine(){
		List<String> list = new ArrayList<>();
		String line = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("F:/LocalRepositoryThread_Erupt/CountDownLatch/nums.txt"));
			try {
				while((line = br.readLine()) != null)
					list.add(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}
	public static void main(String[] args) {
	
		int sum = 0;
		List<String> list = readLine();
		Demo demo = new Demo(list.size());
		for(int i=0;i<list.size();i++) {
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					demo.add(list.get(j), j);
				}
			}).start();
		}
		while(Thread.activeCount() > 1) {	
		}
		demo.sum(); 
	}
}
