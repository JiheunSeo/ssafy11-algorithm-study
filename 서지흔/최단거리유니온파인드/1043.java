package 알고리즘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class 거짓말 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int pNum = Integer.parseInt(st.nextToken()); //사람의수
		int party = Integer.parseInt(st.nextToken());//파티의 수
		
		HashSet<Integer> secretSet = new HashSet<>();
		
		
		st = new StringTokenizer(br.readLine());
		int secret = Integer.parseInt(st.nextToken()); //진실의눈개수
		//진실을 아는 사람들 집합
		for(int i=0; i<secret; i++) { 
			int p = Integer.parseInt(st.nextToken());
			secretSet.add(p);
		}
		
		//파티별로 온 사람의 번호를 Deque형태로 담아 저장
		ArrayDeque<HashSet> stack = new ArrayDeque<>();
		for(int i = 1 ; i<=party ; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());//해당파티에 온 사람수
			HashSet<Integer> peopleSet = new HashSet<>(); //해당파티에 온 사람 저장 집합
			for(int j=1; j<=p; j++) {
				int pn = Integer.parseInt(st.nextToken()); //파티에 온 사람의 번호
				peopleSet.add(pn);
			}
			stack.offerFirst(peopleSet);
		}
		
		//진실을 아는 사람 비교
		int curSize = stack.size();
		dfs(secretSet, stack, 0);
		System.out.println(stack.size());

		
	}
	//
	public static void dfs(HashSet<Integer> secretSet, ArrayDeque<HashSet> stack, int size) {
		//기저조건 (함수반복했을때 deque 사이즈가 변함이 없다면 종료)
		if(stack.size() == size) return;
		
		//현재 디큐 사이즈 저장
		int curSize = stack.size();
		
		//순회하면서 진실의눈집합과 파티에 온 사람집합의 교집합 구함
		//없다면 deque의 마지막에 추가
		//있다면 해당파티에 온사람 모두 진실의눈집합에 추가
		for(int i=0; i<curSize; i++) {
			HashSet<Integer> set1 = stack.pollFirst();
			HashSet<Integer> clone = (HashSet<Integer>) set1.clone();
			set1.retainAll(secretSet);
			if(set1.size() != 0) {
				secretSet.addAll(clone);
			} else stack.offerLast(clone);
		}
		dfs(secretSet, stack, curSize);
		
	}

}
