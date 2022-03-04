import java.util.*;

/**找出一个数组的中位数 优先级队列的使用
 * @Author zhangyong
 * @Description
 * @Date 14:32 2022/3/3 2022
 **/
class MedianFinder {
    // store the smaller half of the input numbers
    private PriorityQueue<Integer> maxHeap;

    // store the larger half of the input numbers
    private PriorityQueue<Integer> minHeap;


    /** initialize your data structure here. */
    public MedianFinder() {
        // Should provide comparator to support max heap
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());


        // by default, PriorityQueue is a min heap
        minHeap = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {

        maxHeap.add(num);

        // balancing
        minHeap.add(maxHeap.peek());
        maxHeap.poll();

        // maintain size property
        if(maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.peek());
            minHeap.poll();
        }
    }

    public double findMedian() {
        if((maxHeap.size() + minHeap.size()) % 2 == 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        else {
            return maxHeap.peek();
        }
    }
    public void TimerTest2(){
        Timer timer = new Timer();
        timer.schedule(new TimerTaskTest2(),1000,2000);//tiemr.schedule(执行的方法,延迟时间,多久执行一次)
        timer.purge();
    }

    class TimerTaskTest2 extends TimerTask {
        @Override
        public void run() {
            System.out.println("本次任务执行时间"+new Date());
        }
    }
}
