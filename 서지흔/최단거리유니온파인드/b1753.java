package 알고리즘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로어게인 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		//간선 개수만큼 인접리스트 생성
		List<Place>[] adjList = new ArrayList[V+1];
		for(int i=1; i<V+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		//출발정점 기준으로 인접리스트 추가
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			adjList[u].add(new Place(v, e));
		}
		
		//정점-1 * 최대간선길이 + 1
		int inf = 10 * (V-1) + 1;
		
		//거리배열 생성 출발점 제외 초기값 inf 설정
		int[] dist = new int[V+1];
		boolean[] checked = new boolean[V+1]; //거쳐간 정점인지 확인하는 용도
		for(int i = 1; i<V+1 ; i++) {
			if(i == start) dist[i] = 0;
			else dist[i] = inf;
		}
		
		//PQ에 정점 먼저 넣어줌(출발정점, 출발정점의 dist값)
		PriorityQueue<Place> pq = new PriorityQueue<>();
		pq.offer(new Place(start, 0));
		
		
		while(!pq.isEmpty()) {
			Place p = pq.poll(); //여러개의 place 중 거리가 가장 짧은 녀석 poll
			if(checked[p.v]) continue; //이미 확인한 정점이면 패스
			checked[p.v] = true;
			
			for(Place place : adjList[p.v]) { //해당 출발점의 인접리스트 요소 순회
				//현재 인접리스트의 도착지점의 거리최소값 > 해당 p.v를 거쳐서 도착정점에 도착하는 거리라면 거리값 갱신
				if(dist[place.v] > dist[p.v] + place.w) { 
					dist[place.v] = dist[p.v] + place.w;
					//갱신 후 pq에 추가
					pq.offer(new Place(place.v , dist[place.v]));
				}
			}
		}
		
		//결과출력
		for(int i =1; i<dist.length ; i++) {
			if(dist[i] == inf) {
				sb.append("INF" + "\n");
			} else {
				sb.append(dist[i] + "\n");
			}
		}
		System.out.println(sb);
	}
	public static class Place implements Comparable<Place>{
		int v;
		int w;
		public Place(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Place p) {
			return Integer.compare(this.w, p.w);
		}
	}
}
