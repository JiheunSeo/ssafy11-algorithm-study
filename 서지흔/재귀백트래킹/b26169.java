import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author 서지흔
 * @date 2024.01.30
 * @link  https://www.acmicpc.net/problem/26169
 * @keyword_solution  
 * 제한된 행동으로 사과를 2개 이상 먹을 수 있는지 확인(시작점은 사과x)
 * 움직일 수 있는 모든 경우의 수 구하기(재귀) - O(1) ?맞나..
 *  - 앞으로 이동했다가 다시 뒤로 가는 경우는 제외(오->왼, 위->아래) 
 *  경우의 수 마다 3회 움직이며 사과 개수 체크하면 끝 - O(N)
 *  - 장애물 걸릴 시 종료, 배열 바깥 빠져나가면 종료
 * @input 
 * 0 ≤ r, c ≤ 4
 * 5x5행렬이므로 재귀함수써도 괜찮음
 * (r,c
 * @output   
 * 사과 2개이상 먹을 수 있으면 1출력, 아니면 0출력
 * @time_complex  
 * O(N)
 * @perf 메모리 16176kb, 소요시간 160ms 
 */
public class b26169 {
	//델타배열(상하좌우)
	static int[][] delta = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[][] board = new int[5][5];
	static int r;
	static int c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = null;
		
		//보드판배열
		for(int r = 0; r<5; r++) {
			tokens = new StringTokenizer(br.readLine());
			for(int c = 0; c<5; c++) {
				board[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		tokens = new StringTokenizer(br.readLine());
		r = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
		
		//탐색방향을 담는 배열
		int[][] way = new int[3][2];
		
		wayToGo(0, way);
		System.out.println(0);
	}
	//가능한 탐색 방법::어떻게 3칸 움직일 것인가
    //앞뒤, 양옆으로 움직이는 탐색방향은 제외
    //앞뒤, 양옆 델타 곱셈 시 행,열 중 무조건 -1이 있음
	public static void wayToGo(int count,int way[][]) {
		//기저조건
        if(count == way.length) {
			for(int i = 0; i<way.length-1; i++) {
				int a = way[i][0] * way[i+1][0]; 
				int b = way[i][1] * way[i+1][1];
				if(a == -1 || b == -1) return;
			}
			int flag = eat(way); //flag : 사과개수
			if(flag >=2) {
				System.out.println(1);
				System.exit(0);
			}
			return;
		}
		//반복(재귀)
		for(int i = 0; i<delta.length; i++) {
			way[count] = delta[i];
			wayToGo(count +1, way);
		}
	}
	
	//배열 탐색
	public static int eat(int way[][]) {
		int cnt = 0;
		int cr =r;
		int cc = c;
		for(int i=0; i<3; i++) {
			cr = cr + way[i][0];
			cc = cc + way[i][1];
			if(cr >=0 && cr <5 && cc>=0 && cc<5) {
				if(board[cr][cc] == -1) {
					break;
				}else if(board[cr][cc] == 1) {
					cnt++;
				}
			}else break;
		}
		return cnt;
	}
}
