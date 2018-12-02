package thread_erupt;

import java.util.Arrays;
import java.util.List;

/**
 * 使用Lambda表达式实现多线程,此处演示并行输出结果
 * @author ，hjs
 *
 */
public class LambdaTest {
		
	public int  add(List<Integer> values) {
		//并行流方式输出集合中的数据
		values.parallelStream().forEach(System.out::println);
		return values.parallelStream().mapToInt(i -> i).sum();
	}
	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(10,20,30,40);
		int res = new LambdaTest().add(values);
		System.out.println(res);
	}
}
