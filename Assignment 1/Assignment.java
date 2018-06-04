/**
 * This is a program that bubble sorts a singly linked list
 * @author Eddy Qiang 
 * @version 1.0
 * @since June 3, 2018
 */
public class Assignment
{
	public static <T extends Comparable<? super T>> void BubbleSort (SLL<T> list)
	{
		int size = list.getLength();
		SLLNode<T> head = list.head;
		boolean swapped = true;
		if(head != null)
		{
            while(swapped) 
            {
            	SLLNode<T> prevNode = null;
            	SLLNode<T> currentNode = head;
            	SLLNode<T> nextNode = currentNode.next;
                swapped = false;
                while (currentNode.next != null) 
                {
                	//if node > node.next, swap them
	                if (currentNode.info.compareTo(nextNode.info)>0) 
	                {
	                	//if same move to next
	                    if (head == currentNode) 
	                    {
	                        head = nextNode;
	                    } 
	                    else 
	                    {
	                        prevNode.next = nextNode;
	                    }
	                    //swapping
	                    currentNode.next = nextNode.next;
	                    nextNode.next = currentNode;                         
	                    currentNode = nextNode;
	                    swapped = true;
	                }
	                //update
	                prevNode = currentNode;
	                currentNode = prevNode.next;
	                nextNode = currentNode.next;
                }
            }
        }
		//reconstruct the singly linked list with sorted nodes
        list = new SLL<T>();           
        for(int i = 0; i<size; i++)
        {
        	list.addToTail(head.info);
        	head=head.next;
        }
        //display results
        System.out.print("Sorted  : ");
        list.printAll();     
        System.out.println();
	}  
	//tests to see if program works
	public static void main(String[] args)
	{
		//testing with a list of integers
		SLL<Integer> list1 = new SLL<>();
		list1.addToTail( 4 );
		list1.addToTail( 5 );
		list1.addToTail( 1 );
		list1.addToTail( 9 );
		list1.addToTail( 6 );
		//display results
		System.out.print("Unsorted: ");
		list1.printAll();
		System.out.println();
		BubbleSort(list1);
		
		//testing with a list of doubles
		SLL<Double> list2 = new SLL<>();
		list2.addToTail( 5.2 );
		list2.addToTail( 2.9 );
		list2.addToTail( 2.4 );
		list2.addToTail( 2.7 );
		list2.addToTail( 1.7 );
		//display results
		System.out.print("Unsorted: ");
		list2.printAll();
		System.out.println();
		BubbleSort(list2);
	}
}