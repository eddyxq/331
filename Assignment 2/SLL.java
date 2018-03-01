/**
 * This is a a generic singly linked list class 
 * @author Eddy Qiang - CPSC 331 - T03
 * @version 1.0
 * @since February 28, 2018
 */

// -------------------------code below was provided by professor----------------------------
public class SLL<T> {
    protected SLLNode<T> head, tail;
    public SLL() {
        head = tail = null;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public void addToHead(T el) {
        head = new SLLNode<T>(el,head);
        if (tail == null)
            tail = head;
    }
    public void addToTail(T el) {
        if (!isEmpty()) {
            tail.next = new SLLNode<T>(el);
            tail = tail.next;
        }
        else head = tail = new SLLNode<T>(el);
    }
    public T deleteFromHead() { 
        if (isEmpty()) 
             return null;
        T el = head.info;
        if (head == tail)       
             head = tail = null;
        else head = head.next;
        return el;
    }
    public T deleteFromTail() { 
        if (isEmpty()) 
             return null;
        T el = tail.info;
        if (head == tail)       
             head = tail = null;
        else {                  
             SLLNode<T> tmp;   
             for (tmp = head; tmp.next != tail; tmp = tmp.next);
             tail = tmp;       
             tail.next = null;
        }
        return el;
    }
    public void delete(T el) { 
        if (!isEmpty())
            if (head == tail && el.equals(head.info)) 
                 head = tail = null;      
            else if (el.equals(head.info)) 
                 head = head.next;   
            else {                   
                 SLLNode<T> pred, tmp;
                 for (pred = head, tmp = head.next;  
                      tmp != null && !tmp.info.equals(el); 
                      pred = pred.next, tmp = tmp.next);
                 if (tmp != null) {  
                     pred.next = tmp.next;
                     if (tmp == tail) 
                        tail = pred;
                 }
            }
    }
    public void printAll() {
        for (SLLNode<T> tmp = head; tmp != null; tmp = tmp.next)
            System.out.print(tmp.info + " ");                
    }
    public boolean isInList(T el) {
        SLLNode<T> tmp;
        for (tmp = head; tmp != null && !tmp.info.equals(el); tmp = tmp.next);
        return tmp != null;
    }
// -------------------------code above was provided by professor----------------------------    
  
	/**
	 * This method calculates the intersection of two singly linked lists 
	 * @param  list1 A generic singly linked list
	 * @param  list2 A generic singly linked list
	 * @param  result The result of the intersection
	 */
	public static <T extends Comparable <? super T>> void intersection(SLL<T> list1, SLL<T> list2, SLL<T> result)
	{	
		SLLNode<T> a = list1.head;
		SLLNode<T> b = list2.head;
		
		while (a != null && b != null)
		{	
			if (a.info.compareTo(b.info) < 0)
			{
				a = a.next;
			}
			else if (a.info.compareTo(b.info) > 0)
			{
				b = b.next;
			}
			else
			{
				result.addToTail(a.info);
				a = a.next;
				b = b.next;
			}
		}
	}
	/**
	 * This method calculates the union of two singly linked lists 
	 * @param  list1 A generic singly linked list
	 * @param  list2 A generic singly linked list
	 * @param  result The result of the union
	 */
	public static <T extends Comparable <? super T>> void union(SLL<T> list1, SLL<T> list2, SLL<T> result)
	{
		SLLNode<T> a = list1.head;
		SLLNode<T> b = list2.head;
		// when neither list is empty, iterate through
		while (a != null && b != null)
		{	
			// when a < b, add a to result and a goes to next node
			if (a.info.compareTo(b.info) < 0)
			{
				result.addToTail(a.info);
				a = a.next;
			}
			// when a > b, add b to result and b goes to next node
			else if (a.info.compareTo(b.info) > 0)
			{
				result.addToTail(b.info);
				b = b.next;
			}
			// when a = b, add a or b (in this case I choose a) to result and advance both a and b to next node
			else
			{
				result.addToTail(a.info);
				a = a.next;
				b = b.next;
			}
		}
		// when list1 is longer than list2, continue adding to result
		while (a != null)
		{
			result.addToTail(a.info);
			a = a.next;
		}
		// when list2 is longer than list1, continue adding to result
		while (b != null)
		{
			result.addToTail(b.info);
			b = b.next;
		}
	}
}