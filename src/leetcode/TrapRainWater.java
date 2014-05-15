package leetcode;
//http://rleetcode.blogspot.com/2014/03/trapping-rain-water-java-python.html
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 
For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 
 
 
 // To calculate the total volume is to calculate volume can hold at
        // each position.
        
        // To calculate how many volume can hold at each position is to calculate it's right bound height
        // and right bound height
        
        //Current position can hold water only at situation when the lowest side among both sides higher than the height at current position
        //if so,  use the lower one minues current height as height to multily the width 1 is how many volume can hold at current position
        
        // How to calculate the height of both sides for each position? we can apply DP theory
        // to record  hightest height bound can get from left to current and highest height bound can get from right to current
        
        //  HigehstLeftSideHeight so far for given example  should be 
        //  0,1,1,2,2,2,2,3,3,3,3,3
        //  HighestRightSideHeight so far for given example is 
        //  1,2,2,2,3,3,3,3,3,3,3,3
        
        // then loop through given array for each posiiton calculate how many volume can hold there and update the total voluem it can hold
*/
 
public class TrapRainWater {
    public int trap(int[] A) {
        
        if (A==null ||A.length==0){
            return 0;
        }
        
        int[] highestLeftSoFar=new int[A.length];
        int[] highestRightSoFar=new int[A.length];
        
        // left->right
        for (int i=0; i<highestLeftSoFar.length; i++){
            highestLeftSoFar[i]=i==0?A[i]:Math.max(A[i], highestLeftSoFar[i-1]);
        }
        // right -> left
        for (int i=A.length-1; i>=0; i--){
            highestRightSoFar[i]=i==A.length-1?A[i]:Math.max(A[i], highestRightSoFar[i+1]);
        }
        
        int totalVolume=0;
        
        for (int i=0; i<A.length; i++){
            int height=Math.min(highestLeftSoFar[i], highestRightSoFar[i]);
            
            if (height>A[i]){
                height=height-A[i];
                totalVolume+=height*1;
            }
        }
        
        
        return totalVolume;
    }
}