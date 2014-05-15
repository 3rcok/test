import java.util.ArrayList;
import java.util.ListIterator;


public class Marklength4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		list.add("hi");
		list.add("howe");
		list.add("are");
		markLength4(list);
		System.out.println(list);
		System.out.println(reverseWords("b  a "));
	}
	public static void markLength4 (ArrayList<String> list) {
		if(list==null||list.isEmpty()) {
			return;
		}
		for(int i=0;i<list.size();i++) {
			if(list.get(i).length()==4) {
				list.add(i, "*****");
				System.out.println(list);
				i++;
			}
		}
	}
	
    public static String reverseWords(String s) {
        if(s==null ) return s;
        s=s.trim();
        if(s.length()==0||s.length()==1) return s;
        String[] array = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i=array.length-1; i>0;i--){
               sb.append(array[i]).append(" ");
        }
        sb.append(array[0]);
        return sb.toString();
        }
        
	public static void interleave(ArrayList<Integer> list1, ArrayList<Integer> list2 ){
		 ListIterator<Integer> it1 = list1.listIterator();
		    ListIterator<Integer> it2 = list2.listIterator();
		    while(it1.hasNext()&&it2.hasNext()){
		        it1.next();
		            it1.add(it2.next());
		        
		    }
		    while(it2.hasNext()){
		        list1.add(it2.next());
		    }
		    
		}
}
