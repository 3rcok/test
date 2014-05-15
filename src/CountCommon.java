import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class CountCommon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	int countCommon(List list1, List list2){
		HashSet tmp = new HashSet(list1);
	    tmp.retainAll(list2);
	    return tmp.size();
	    
	    
	}
}
