package FCFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * n개의 일을 처리하는 기계
 * 배열로 일을 처리하는데 걸리는 시간 {5,2,1,5,6,7}
 * 처리되는 순서 배열에 저장해서 return
 */
public class FCFS_Algorithm {
    static int n;
    static int m;
    static Queue<Integer> queue = new LinkedList<>();
    static List<Queue<Integer>> list = new ArrayList<Queue<Integer>>();
    static int pi(String s){return Integer.parseInt(s);}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = pi(st.nextToken());
        m = pi(st.nextToken());

        int[] arr = new int[m];

        //배열에 일 처리 시간 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            queue.add(pi(st.nextToken()));
        }

        //Queue_를 n개 생성해서 List_에 저장
        for (int i = 0; i < n; i++) {
            Queue<Integer> qu = new LinkedList<>();
            list.add(qu);
        }

        int idx = 1;
        int result_idx = 0;
        PriorityQueue<Integer> result = new PriorityQueue<>();
        while(true){
            if(result_idx==m) break; //모든 일이 처리가 되면 break;

            for (int i = 0; i < n; i++) { //모든 큐에 대해서 일을 수행

                //list_에 있는 큐를 빼서 각각 일 처리
                Queue<Integer> q = list.get(i);

                //만약 일을 할 수 있는 큐가 있고 해야하는 일이 있다면 큐의 소요 시간 만큼 일 할당
                if(q.isEmpty()&&!queue.isEmpty()){
                    Integer time = queue.poll();
                    for (int j = 0; j < time; j++) {
                        q.add(idx); //인덱스을 알기 위해서 인덱스를 소요 시간 만큼 add
                    }
                    idx++;
                }
                //큐에 일이 있다면 일을 처리
                else if(!q.isEmpty()) {
                    Integer q_idx = q.poll();

                    //일을 모두 처리했다면
                    if(q.isEmpty()){
                        //해야할 일이 있는지 확인
                        if(!queue.isEmpty()){
                            //시간 만큼 일을 add
                            Integer time = queue.poll();
                            for (int j = 0; j < time; j++) {
                                q.add(idx);
                            }
                            idx++;
                        }
                        //끝난 일에 대해서는 result 우선순위 큐에 저장
                        //우선순위 큐를 사용한 이유는 동시에 끝난 일이 있다면
                        //먼저 들어간 순서대로 출력해야하기 때문
                        result.add(q_idx);
                    }
                }
            }

            //result 큐에 데이터를 arr에 저장
            while(!result.isEmpty()){
                Integer num = result.poll();
                arr[result_idx] = num;
                result_idx++;
            }
        }

        //끝난 순서대로 인덱스 출력
        for (int i = 0; i < arr.length; i++) {
            System.out.println((i+1) + "번째 :" +arr[i]);

        }
    }
}
