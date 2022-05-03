
public class ArrayUtils {

	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		// TODO
		int n = array.length;
		move %= n;
		if (move == 0)
			return array;
		//transfer all scenario to step for right side
		switch (direction) {
			case ('R'):
				if (move < 0)
					move += n;
				break;
			case ('L'):
				if (move > 0)
					move = n - move;
				else move=Math.abs(move);
				break;
			default:
				return array;

		}
		//private method see below
		int num_of_cycle = Count_cycle(n, move);

		//for each loop we start replace element till we got back to the start point
		for (int last_index = 0; last_index < num_of_cycle; last_index++) {
			int temp = array[last_index];
			int index_before = last_index + n - move;
			//in total we get O(n) because each loop is independent
			do {
				array[(index_before + move) % n] = array[index_before];
				index_before = (index_before + n - move) % n;
			}
			while (index_before != last_index);

			array[last_index + move] = temp;
		}
		return array;

	}

	public static int findShortestPath(int[][] m, int i, int j) {
		// TODO
		//BFS from algorithm course
		int v = m[0].length,cnt=1,k=0;
		int distance = 0;
		//its just impliment all of the Queue method i need, i wasnt sure if i could use  java.util to do it with Queue but the idea is the same
		int[] arrayQueue =new int[v];
		for (int vi=0;vi<arrayQueue.length;vi++)
			arrayQueue[vi]=-1;

		boolean visited[] = new boolean[v];
		arrayQueue[0]=i;
		visited[i] = true;

		if(i==j)
        return 0;

		do {
			int temp=arrayQueue[k];
			distance++;
			k++;
			for (int adj = 0; adj < v; adj++) {
				if (m[temp][adj] != 0) {
					if (m[temp][j] == 1)
						return distance;
					if (!(visited[adj]))
					{
						visited[adj] = true;
						arrayQueue[cnt]=adj;
						cnt++;
					}
				}
			}
		}while (arrayQueue[k]!=-1);
		return -1;
	}


	//*       private method         *//


	//we count how many "loops" of mod(move) we we need to do to cover all the elemnts in arr
		private static int Count_cycle ( int target, int move) {
			while (move != 0) {
				int temp = target;
				target = move;
				move = temp % target;
			}
			return target;
		}


}

