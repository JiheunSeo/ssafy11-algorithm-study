import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * @author 서지흔
 * @date 2024.01.31
 * @link https://www.acmicpc.net/problem/15686
 * @keyword_solution  
 * 최소 치킨거리를 찾는 문제
 * 치킨집 중에 m개를 고르고 모든 집과 치킨집 m개를 이은 거리의 합이 최소가 되는 거리 찾기
 * - map 배열에 지도를 저장하며 지도 좌표가 2(치킨집)이면 해당 좌표를 담는 2차원 리스트(chick) 생성
 * - 치킨집 좌표리스트를 통해 map배열을 순회하며 지도 좌표가 1(집)이면 집-치킨집 거리 구한 후 2차원리스트(dist)에 저장
 * - 재귀함수를 통해 치킨집중 m개 선택(조합)
 * - m개 선택할 때마다 치킨거리 계산 후 최솟값 저장하면 끝
 * @input 
 * 2<=N<=50
 * 1<=M<=13
 * 0:빈칸 , 1:집(2N개 이하), 2:치킨집(M이상 13이하)
 * @output   
 * 최소 치킨거리 출력
 * @time_complex  
 * O(N)
 * @perf 메모리 15696메모리, 소요시간 208ms 
 */
public class b15686 {
	public static int n; //nxn행렬
	public static int m; //살아남는 치킨집
	public static List<List<Integer>> chick = new ArrayList<>();
	public static List<List<Integer>> dist = new ArrayList<>();
	public static int[][] map;
	public static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = null;
		tokens = new StringTokenizer(br.readLine());
		n = Integer.parseInt(tokens.nextToken());
		m = Integer.parseInt(tokens.nextToken());
		
		//지도 배열저장
		map = new int[n][n];
		for(int r = 0; r<n ; r++) {
			tokens = new StringTokenizer(br.readLine());
			for(int c = 0; c<n ; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c] == 2) {//치킨집 탐색해 chick배열에 행열값 저장
					chick.add(new ArrayList<>());
					chick.get(chick.size()-1).add(r);
					chick.get(chick.size()-1).add(c);
				}
			}
			
		}
		
		
		//집을 기준으로 모든 치킨집과의 거리 저장 (dist리스트)
		for(int r = 0; r<n ; r++) {
			for(int c = 0; c<n ; c++) {
				if(map[r][c] == 1) {
					dist = distSave(r,c);
				}
			}
		}
		
		//나올 수 있는 조합의 방법담는 배열
		int[] way = new int[m];
		
		combination(0, 0, way);
		System.out.println(min); //최소치킨거리 출력
		
	}
	
	//집을 기준으로 모든 치킨집과의 거리 저장 (dist리스트)
	public static List<List<Integer>> distSave(int r, int c) {
		dist.add(new ArrayList<>());
		for(int i = 0; i<chick.size(); i++) {
			int distance = Math.abs((chick.get(i).get(0))- r) + Math.abs((chick.get(i).get(1))- c);
			dist.get(dist.size()-1).add(distance);
		}
		return dist;
	}
	
	//재귀 :: 나올 수 있는 치킨매장 m개의 조합
	public static void combination(int cnt, int start, int[] way) {
		//기저조건
		if(cnt == way.length) {
			min = Math.min(min, getDist(way)); //현재 치킨거리의 최솟값과 비교 후 더 작은 값 저장
			return;
		}
		for(int i = start; i<chick.size(); i++) {
			way[cnt] = i;
			combination(cnt+1, i+1, way);
		}
	}
	
	//way방법을 가지고 치킨거리 구하기
	public static int getDist(int[] way) {
		int sum = 0;
		for(int i = 0; i< dist.size(); i++) {
			int ckickToHouseMin = Integer.MAX_VALUE; //집마다 치킨집m개와의 거리 중 최소값 찾아야함
			for(int j = 0 ; j< way.length ; j++) {
				ckickToHouseMin = Math.min(dist.get(i).get(way[j]), ckickToHouseMin);
			}
			sum += ckickToHouseMin;
		}
		return sum;
			
	}
}