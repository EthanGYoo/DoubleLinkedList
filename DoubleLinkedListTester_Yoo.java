import java.util.NoSuchElementException;



//Tests the DoubleLinkedList class
class DoubleLinkedListTester_Yoo{
   public static void main(String[] args){
      DoubleLinkedList list = new DoubleLinkedList();
      list.addFirst(1);
      System.out.println("First element of list: " + list.get(0));
      list.addFirst(2);
      System.out.println("First element of list: " + list.get(0));
      list.addLast(3);
      list.addLast(4);
      System.out.println("Last element of list: " + list.get(list.size()-1));
      System.out.println("Last element of list removed: " + list.removeLast());
      System.out.println("Last element of list: " + list.get(list.size()-1));
      System.out.println("Second element of list: " + list.get(1));
      list.add(1, 5);
      System.out.println("Second element of list: " + list.get(1));
      System.out.println("Third element of list removed: " + list.remove(2));
      System.out.println("Third element of list: " + list.get(2));
      System.out.println("Size of list: " + list.size());
      System.out.print("List elements: ");
      for (int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");
      System.out.println("\nReversed!");
      list.reverse();
      System.out.println("Size of list: " + list.size());
      System.out.print("List elements: ");
      for (int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");
      System.out.println("\nReversed!");
      list.reverse();
      System.out.println("Size of list: " + list.size());
      System.out.print("List elements: ");
      for (int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");
      System.out.println();
      list.addLast(4);
      System.out.print("List elements: ");
      for (int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");
      System.out.println("\nReversed!");
      list.reverse();
      System.out.println("Size of list: " + list.size());
      System.out.print("List elements: ");
      for (int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");


   }
}

//class that stores elements using a double linked format
class DoubleLinkedList<E> {

	private Node first;
   private Node last;
   private int size = 0;
   
	private class Node 	{ 
		public E data;
		public Node next;
      public Node before;
	}
	
   //constructs the list
	public DoubleLinkedList() {
		first = null;
      last = null;
	}
	
	/**
	* @return the first element in the linked list
	*/	
	public E getFirst() {
		if (first == null)
			throw new NoSuchElementException();
		return first.data;
	}
	
	/**
	* Removes the first element in the linked list.
	* @return the removed element
	*/
	public E removeFirst() {
		if (first == null)
			throw new NoSuchElementException();
		E element = first.data;
		first = first.next;
      first.before = null;
      size--;
		return element;
	}

	/**
	* Adds an element to the front of the linked list.
	* @param element the data to store in the linked list
	*/
	public void addFirst(E element)  {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first;
      newNode.before = null;
      if (!(first == null)) first.before = newNode;
		first = newNode;
      size++;
      if (size == 1) last = first;
	}
	
   //adds an element to the end of the list
   public void addLast(E e){
      size++;
      Node newNode = new Node();
      newNode.data = e;
      newNode.next = null;
      newNode.before = last;
      if (!(last == null)) last.next = newNode;
      last = newNode;
   }
   
   //removes the element at the end of the list if applicable
   public E removeLast(){
      if (first == null) throw new NoSuchElementException();
      E removed = last.data;
      if (first.next == null){
         first = null;
         last = null;
         return removed;
      }
      last = last.before;
      last.next = null;
      size--;
      return removed;
   }
   
   //returns the element at given index if available
   public E get(int index){
      if (index < 0 || first == null) throw new NoSuchElementException();
      Node current = first;
      E ret = first.data;
      for (int i = 0; i < index; i++){
         current = current.next;
         if (current == null) throw new NoSuchElementException();
         ret = current.data;
      }
      return ret;
   }
   
   //adds an element at given index if applicable
   public void add(int index, E element){
      Node newNode = new Node();
      newNode.data = element;
      Node current = first;
      Node before = null;
      for (int i = 0; i < index; i++){
         if (i == index - 1) before = current;
         current = current.next;
      }
      newNode.next = current;
      current.before = newNode;
      if (!(before == null)){
         before.next = newNode;
         newNode.before = before;
      }
      if (newNode.next == null) last = newNode;
      size++;
   }
   
   //removes element at given index if applicable
   public E remove(int index){
      if (first == null) throw new NoSuchElementException();
      Node ret = first;
      if (index == 0){
         first = first.next;
         first.before = null;
         size--;
         return ret.data;
      }
      Node current = first;
      for (int i = 0; i < index - 1; i++){
         if (current == null) throw new NoSuchElementException();
         current = current.next;
      }
      
      ret = current.next;
      current.next = current.next.next;
      current.next.before = current;

      size--;
      return ret.data;
   }
   
   //returns size of the list
   public int size(){
      return size;
   }
   
   //reverses the order of elements in the list
   public void reverse() {
      if (size <= 1) return;
   
      Node current = first;
      while (current != null) {
         Node temp = current.next;
         current.next = current.before;
         current.before = temp;
         current = temp;
      }
   
      Node temp = first;
      first = last;
      last = temp;
   }
   

}