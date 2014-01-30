import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// Given a stream of unsorted integers, find the median element in sorted order at any given time.
// http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/
public class MedianOfIntegerStream {

	public Queue<Integer> minHeap;
	public Queue<Integer> maxHeap;
	public int numOfElements;
	
	public MedianOfIntegerStream() {
		minHeap = new PriorityQueue<Integer>();
		maxHeap = new PriorityQueue<Integer>(10, new MaxHeapComparator()); 
		numOfElements = 0;
	}
	
	public void addNumberToStream(Integer num) {
		System.out.println("add num " + num + " to maxHeap");
		maxHeap.add(num);
		System.out.println("numOfElements " + numOfElements);
		if (numOfElements%2 == 0) {
			if (minHeap.isEmpty()) {
				numOfElements++;
				return;
			}
			else if (maxHeap.peek() > minHeap.peek()) {
				Integer maxHeapRoot = maxHeap.poll();
				Integer minHeapRoot = minHeap.poll();
				maxHeap.add(minHeapRoot);
				minHeap.add(maxHeapRoot);
			} 
		} else {
			minHeap.add(maxHeap.poll());
		}
		numOfElements++;
		System.out.println(" minHeap: ");
		printMinQueue(minHeap);
		System.out.println(" maxHeap " + maxHeap.toString());
		printMaxQueue(maxHeap);
		
		
	}
	public void printMinQueue(Queue q) {

		Object[] arr = q.toArray();
		 Arrays.sort(arr);
		 System.out.println(Arrays.toString(arr));
	}
	
	public void printMaxQueue(Queue q) {

		Object[] arr = q.toArray();
	    Arrays.sort(arr,Collections.reverseOrder()); 
		 System.out.println(Arrays.toString(arr));
	}
	
	public Double getMedian() {
		if (numOfElements%2 != 0)
			return new Double(maxHeap.peek());
		else
			return (maxHeap.peek() + minHeap.peek()) / 2.0; 
	}
	
	private class MaxHeapComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
	
	public static void main(String[] args) {
		MedianOfIntegerStream streamMedian = new MedianOfIntegerStream();
		
		streamMedian.addNumberToStream(1);
		System.out.println(streamMedian.getMedian()); // should be 1
		
		streamMedian.addNumberToStream(5);
		streamMedian.addNumberToStream(10);
		streamMedian.addNumberToStream(12);
		streamMedian.addNumberToStream(2);
		System.out.println(streamMedian.getMedian()); // should be 5
		
		streamMedian.addNumberToStream(3);
		streamMedian.addNumberToStream(8);
		streamMedian.addNumberToStream(9);
		System.out.println(streamMedian.getMedian()); // should be 6.5
	}
}
