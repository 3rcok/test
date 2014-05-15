import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


public class Stutter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		list.add("hi");
		list.add("howe");
		list.add("are");
		stutter(list,4);
		System.out.println(list);
	}
	public static void stutter(ArrayList<String> list, int k) {
		if(k<=0) {
			list.clear();
		}
		ListIterator<String> it = list.listIterator();
		while(it.hasNext()) {
			String tmp = it.next();
			for(int i=0;i<k-1;i++) {
				it.add(tmp);
			}
		}
	}
}
