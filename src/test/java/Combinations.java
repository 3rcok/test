import java.util.ArrayList;


public class Combinations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


public ArrayList<ArrayList<Integer>> combine(int n, int k) {
    // Start typing your Java solution below
    // DO NOT write main() function
    if(n<k) return null;
    ArrayList<ArrayList<Integer>> all = new  ArrayList<ArrayList<Integer>>();       
    if(k==1){
        for(int i=1;i<=n;i++){
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(i);
            all.add(al);
        }
        return all;
    }
    for(int i=n;i>=k;i--){                
        for(ArrayList<Integer> al : combine(i-1,k-1)){
            al.add(i);
            all.add(al);
        } 
    }
    return all;       
}
}
