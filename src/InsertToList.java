
public class InsertToList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


public void set(int index, List t){
    ListNode current = front;
    int count=1;
    while (count<=index){
        current= current.next();
        count++;
    }
    ListNode next = current.next;

    ListNode added = new ListNode(t);
    current.next=t;
    t.next=next;
    
    
}
}
