package thread_erupt;

import java.util.Arrays;
import java.util.List;

/**
 * ʹ��Lambda���ʽʵ�ֶ��߳�,�˴���ʾ����������
 * @author ��hjs
 *
 */
public class LambdaTest {
		
	public int  add(List<Integer> values) {
		//��������ʽ��������е�����
		values.parallelStream().forEach(System.out::println);
		return values.parallelStream().mapToInt(i -> i).sum();
	}
	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(10,20,30,40);
		int res = new LambdaTest().add(values);
		System.out.println(res);
	}
}
