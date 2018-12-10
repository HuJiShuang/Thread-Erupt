package countdownlatch;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;



public class Demo2 {

	private int[] numbers;
	public Demo2(int length) {
		numbers = new int[length];
	}
	//����ÿһ�еĺ�
	public void add(String line,int index,CountDownLatch latch) {
		int sum = 0;
		String [] str = line.split(",");
		for (String string2 : str) {
			sum += Integer.parseInt(string2);
		}
		numbers[index] = sum;
		System.out.println(Thread.currentThread().getName()+"�߳�ִ�м�������"+line+"���Ϊ��"+sum);
		//��������������
		latch.countDown();
	}
	public void sum() {
		int total = 0;
		for (int number : numbers) {
			total += number;
		}
		System.out.println("�����߳̿�ʼִ����......"+total);
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
		//����һ��CountDownLatch����
		CountDownLatch latch = new CountDownLatch(list.size());
		Demo2 demo = new Demo2(list.size());
		for(int i=0;i<list.size();i++) {
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					demo.add(list.get(j), j,latch);
				}
			}).start();
		}
		//������������Ϊ0ʱ���ἤ�������̣߳������߳��ն���
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		demo.sum(); 
	}
}
