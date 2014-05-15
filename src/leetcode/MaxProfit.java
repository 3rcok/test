package leetcode;


//http://leetcode.com/2010/04/hacking-google-interview-from-mit.html
public class MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices==null||prices.length<2){
            return 0;
        }
      
       int min=Integer.MAX_VALUE;
       int diff=0;
       
       for (int i=0; i<prices.length; i++){
           if (prices[i]<min){
               min=prices[i];
           }    
           
           if (diff<prices[i]-min){
               diff=prices[i]-min;
           }
       }
       
       return diff;
       
    }
}