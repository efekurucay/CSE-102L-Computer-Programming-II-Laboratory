  import java.util.ArrayList;
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

