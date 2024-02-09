package 알고리즘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 경로찾기 {
	public static int N;
	public static int[][] rst;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		rst = new int[N+1][N+1]; //결과배열
		
		//기존에 주어진 데이터를 통해 1 저장
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1 ; c<=N ;c++) {
				int w = Integer.parseInt(st.nextToken());
				if (w == 1) {
					rst[r][c] = 1;
				}
			}
		}
		
		//중간값을 기준으로 탐색
		for(int mid=1 ; mid<N+1 ; mid++) { //중간값
			for(int start = 1; start<N+1 ; start++) { //앞쪽
				for(int end = 1; end<N+1 ; end++) {//뒤쪽
					//앞-중간, 중간-뒤가 연결됐으면 앞-뒤 연결
					if(rst[start][mid] == 1 && rst[mid][end] == 1) { 
						rst[start][end] = 1;
					}
				}
			}
		}
		
		
		for(int r=1; r<=N; r++) {
			for(int c=1 ; c<=N ;c++) {
				sb.append(rst[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
	}
}
