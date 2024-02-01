import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author 서지흔
 * @date 2024.01.31
 * @link https://www.acmicpc.net/problem/6603
 * @keyword_solution  
 * 로또문제
 * 로또 1~49 중 수 6개 선택
 * k개의 숫자 중 숫자 6개 뽑기(부분집합)
 * @input 
 * 6<k<13
 * 입력의 마지막은 0
 * @output   
 * 모든 방법 오름차순 출력(케이스마다 한 줄 띄움)
 * @time_complex  
 * O(2^N)
 * @perf 메모리 15192메모리, 소요시간 168ms 
 */
public class b6603 {
    public static int numbers[] = new int[6];
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws NumberFormatException, IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = null;
        while(true) {
        	tokens = new StringTokenizer(br.readLine());
        	//k개 입력받음
        	int num = Integer.parseInt(tokens.nextToken());
        	//k가 0이면 break;
        	if(num == 0) {
        		break;
        	}
        	
        	//로또에 들어갈 수 있는 숫자 배열
        	int[] lotto = new int[num];
            for(int i = 0; i<num; i++) {
            	lotto[i] = Integer.parseInt(tokens.nextToken());
            }
            //조합
            combination(0, 0, numbers, lotto);
            sb.append("\n");
        }
        System.out.println(sb.toString());
 
    }
    //6개 뽑는 로또조합
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