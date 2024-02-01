import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author ������
 * @date 2024.01.31
 * @link https://www.acmicpc.net/problem/6603
 * @keyword_solution  
 * �ζǹ���
 * �ζ� 1~49 �� �� 6�� ����
 * k���� ���� �� ���� 6�� �̱�(�κ�����)
 * @input 
 * 6<k<13
 * �Է��� �������� 0
 * @output   
 * ��� ��� �������� ���(���̽����� �� �� ���)
 * @time_complex  
 * O(2^N)
 * @perf �޸� 15192�޸�, �ҿ�ð� 168ms 
 */
public class b6603 {
    public static int numbers[] = new int[6];
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws NumberFormatException, IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = null;
        while(true) {
        	tokens = new StringTokenizer(br.readLine());
        	//k�� �Է¹���
        	int num = Integer.parseInt(tokens.nextToken());
        	//k�� 0�̸� break;
        	if(num == 0) {
        		break;
        	}
        	
        	//�ζǿ� �� �� �ִ� ���� �迭
        	int[] lotto = new int[num];
            for(int i = 0; i<num; i++) {
            	lotto[i] = Integer.parseInt(tokens.nextToken());
            }
            //����
            combination(0, 0, numbers, lotto);
            sb.append("\n");
        }
        System.out.println(sb.toString());
 
    }
    //6�� �̴� �ζ�����
    public static void combination(int cnt, int start, int numbers[], int[] lotto) {
        if(cnt == numbers.length) {
            for(int i = 0; i<numbers.length; i++) {
            	sb.append(numbers[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for(int i = start; i<lotto.length; i++) {
            numbers[cnt] = lotto[i];
            combination(cnt+1, i+1, numbers, lotto);
        }
    }
}