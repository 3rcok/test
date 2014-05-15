import java.util.ArrayList;


public class ArrayList3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> list1 = new ArrayList();
		list1.add(10);
		list1.add(20);
		list1.add(30);
//		mystery3(list1);
		mystery4(list1);

	}
		public static void mystery3(ArrayList<Integer> list) { 
		    for (int i = list.size() - 2; i > 0; i--) { 
		        int a = list.get(i); 
		        int b = list.get(i + 1); 
		        list.set(i, a + b); 
		    } 
		    System.out.println(list); 
		} 
		public static void mystery4(ArrayList<Integer> list) {
		    for (int i = 0; i < list.size(); i++) {
		        int element = list.get(i);
		        list.remove(i);
		        list.add(0, element + 1);
		    }
		    System.out.println(list);
		}
}
