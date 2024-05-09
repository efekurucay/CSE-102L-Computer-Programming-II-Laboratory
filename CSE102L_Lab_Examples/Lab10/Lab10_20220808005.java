
/**---------------------------------------------------
 * Akdeniz University CSE102L Lab Examples
 * author: Yahya Efe Kuruçay
 * since: 09.05.2024
 * Description: Lab10
 * Website: https://efekurucay.com
*---------------------------------------------------*/
/***
 *    ███████ ███████ ███████   
 *    ██      ██      ██          
 *    █████   █████   █████     
 *    ██      ██      ██         
 *    ███████ ██      ███████   
 *                            
 *                            
 */

import java.util.LinkedList;


  public class Lab10_20220808005 {

      public static void main(String[] args) {
          
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        arrayList.add(0);
        arrayList.add(1);
        linkedList.add(0);
        linkedList.add(1);

        long start =System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
          arrayList.add(i);

        }
        long end = System.currentTimeMillis();

        System.out.println(end-start);
        System.out.println("/////////////////");

        Node<Integer>n1 = new Node (1);
        Node<Integer>n2 = new Node (-4);
        Node<Integer>n3 = new Node (6);
        n2.next=n1;
        n1.prev=n2;
        n1.next=n3;
        n3.prev=n1;

      }
  }

  interface Collection<E>extends Iterable<E>{
    boolean isEmpty();
    int size();
  }

  interface Iterator<E>{
    boolean hasNext();
    E next();
    void remove();
  }

  interface Iterable<E> {

    Iterator<E> iterator();


  }

  interface Comparable<E>{

    int compareTo(E other);
  }

  interface Comparator<E>{



    int compare(E x, E y);
  }

  interface ListIterator<E> extends Iterable <E>{  


      void add(E elements);
      boolean hasPrevious();
      int nextIndex();
      E previous();
      int previousIndex();
  }

  interface List<E>extends Collection<E>{

    boolean add(int index, E element);
    boolean add(E e); 
    E get(int index);
    E set(int index, E element);
    boolean remove (Object o);
    
  }

  interface Queue<E>extends Collection<E>{
        boolean offer (E o);
        E peek();
        E poll();

    }

  interface Stack<E>extends Collection<E>{

    E peek();
    E pop();
    boolean push(E e);
  }

  interface Deque<E>extends Queue<E>{
     
    boolean addFirst(E e);
    boolean addLast(E e);

    E getFirst();
    E getLast();
    E removeFirst();
    E removeLast();


  }

  class Node<E>{
    Node<E> prev;
    Node<E> next;
    E data;

    Node(E data){

      this.data=data;
      next=null;
    }
  }

  class ArrayList<E>implements List<E>{

      private static final int DEFAULT_CAPATICY=10;

      private Object[] elements;
      private int size;

      ArrayList(){

        elements = new Object[DEFAULT_CAPATICY];
        size=0;
      }

      @Override
      public boolean isEmpty() {
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
      }

      @Override
      public int size() {
        throw new UnsupportedOperationException("Unimplemented method 'size'");
      }

      @Override
      public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
      }

      @Override
      public boolean add(int index, E element) {
        throw new UnsupportedOperationException("Unimplemented method 'add'");
      }

      @Override
      public boolean add(E e) {
        throw new UnsupportedOperationException("Unimplemented method 'add'");
      }

      @Override
      public E get(int index) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
      }

      @Override
      public E set(int index, E element) {
        throw new UnsupportedOperationException("Unimplemented method 'set'");
      }

      @Override
      public boolean remove(Object o) {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
      }

     

  }



















    /***
 *              _____                    _____                    _____          
 *             /\    \                  /\    \                  /\    \         
 *            /::\    \                /::\    \                /::\    \        
 *           /::::\    \              /::::\    \              /::::\    \       
 *          /::::::\    \            /::::::\    \            /::::::\    \      
 *         /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \     
 *        /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \    
 *       /::::\   \:::\    \      /::::\   \:::\    \      /::::\   \:::\    \   
 *      /::::::\   \:::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \  
 *     /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \ 
 *    /:::/__\:::\   \:::\____\/:::/  \:::\   \:::\____\/:::/__\:::\   \:::\____\
 *    \:::\   \:::\   \::/    /\::/    \:::\   \::/    /\:::\   \:::\   \::/    /
 *     \:::\   \:::\   \/____/  \/____/ \:::\   \/____/  \:::\   \:::\   \/____/ 
 *      \:::\   \:::\    \               \:::\    \       \:::\   \:::\    \     
 *       \:::\   \:::\____\               \:::\____\       \:::\   \:::\____\    
 *        \:::\   \::/    /                \::/    /        \:::\   \::/    /    
 *         \:::\   \/____/                  \/____/          \:::\   \/____/     
 *          \:::\    \                                        \:::\    \         
 *           \:::\____\                                        \:::\____\        
 *            \::/    /                                         \::/    /        
 *             \/____/                                           \/____/         
 *                                                                               
 */
