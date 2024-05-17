  

/**---------------------------------------------------
 * Akdeniz University CSE102L Examples
 * author: Yahya Efe Kuruçay
 * since: 16.05.2024
 * Description: Lab11
 * Score: ?
 * Website: https://efekurucay.com
*---------------------------------------------------*/


/***
 *    ███████ ███████ ███████   |    ███████ ███████ ███████ 
 *    ██      ██      ██        |    ██      ██      ██      
 *    █████   █████   █████     |    █████   █████   █████   
 *    ██      ██      ██        |    ██      ██      ██      
 *    ███████ ██      ███████   |    ███████ ██      ███████ 
 *                            
 *                            
 */



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Lab11F_20220808005 {

    

        public static void main2(String[] args) throws Exception {
            Set<String> fruits = new HashSet<>();
    
            fruits.add("apple");
            fruits.add("banana");
            fruits.add("orange");
            System.out.println(fruits);
            fruits.add("apple");
            System.out.println(fruits);
            fruits.remove("apple");
            System.out.println(fruits);
            fruits.add("apple");
            System.out.println(fruits);
            fruits.clear();
            System.out.println(fruits);
            Set<String> otherFruits = new HashSet<>();
            otherFruits.add("kiwi");
            otherFruits.add("mango");
            otherFruits.add("banana");
            otherFruits.add("mango");
            System.out.println(otherFruits);
            fruits.removeAll(otherFruits);
            System.out.println(fruits);
            System.out.println(fruits.contains("apple"));
            System.out.println(fruits.contains("banana"));
            otherFruits.removeAll(fruits);
            fruits = new TreeSet<>();
            fruits.add("banana");
            fruits.add("Orange");
            fruits.add("Apple");
            System.out.println(fruits);
    
            //HashMap
            Map<Integer,String> map = new HashMap<>();
            map.put(1, "One");
            map.put(2, "two");
            System.out.println(map.get(1));
            System.out.println(map.get(2));
            map.remove(1);
            System.out.println(map);
            map.put(1, "three");
            System.out.println(map);
            map.put(1, "one");
            System.out.println(map);
            map.put(3, "one");
            System.out.println(map);
            System.out.println(map.keySet());
            System.out.println(map.values()); 
            System.out.println(new HashSet<>(map.values()));
            System.out.println(map.size()); 
            System.out.println(map.containsKey(1));
            System.out.println(map.containsKey(2)); 
            System.out.println(map.containsKey(3));
            map.containsValue("one");
            map.containsValue("two");
            map.containsValue("three");
            for (var e : map.entrySet()) {
                var key = e.getKey();
                var value = e.getValue();
                System.out.println(key + "," + value);
            }
    
            for (var keys : map.keySet()) {
                System.out.println(map.get(keys)); 
            }
    
            map.putIfAbsent(1, "four"); 
            System.out.println(map);
    
            map.putIfAbsent(4, "four"); 
            System.out.println(map);
    
            map.replace(1, "five");
            System.out.println(map);            
    
            System.out.println(map.get(8));
            System.out.println(map.getOrDefault(1, "five"));
            System.out.println(map.getOrDefault(1, "one"));
    
            map = new LinkedHashMap<>();
            map = new TreeMap<>();
            map.put(2, "two");
            map.put(1, "two");
            map.put(-1, "two");
            System.out.println(map);
    
            @SuppressWarnings("unused")
            TreeMap<String, TreeSet<Course>> treeMap = new TreeMap<>();
    
            @SuppressWarnings("unused")
            Student s1 = new Student(5);
            @SuppressWarnings("unused")
            Student s2 = new Student(2);
            @SuppressWarnings("unused")
            Student s3 = new Student(8);
            @SuppressWarnings("unused")
            Course c1 = new Course("Math");
            Course c2 = new Course("Physics");
            Course c4 = new Course("cse102L");
    
            TreeSet<Course> treeSet = new TreeSet<>();
            treeSet.add(c2);
            treeSet.add(c4);
    
              
            
        }
    }
    
class Student implements Comparable<Student> {
        int studentId;

        Student(int id) {
            
        }

        @Override
        public int compareTo(Student o) {
            return studentId;
        }

        @Override
        public String toString() {
            return String.valueOf(studentId);
        }
    }

class Course implements Comparable<Course> {
        String courseId;

        Course(String courseId) {
            this.courseId = courseId;
        }

      

        @Override
        public int compareTo(Course o) {
            throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
        }

    }


public class Lab11_20220808005 {
        
        public static TreeMap<String, Integer> wordFrequencyCounter(String fileName) throws IOException {
                TreeMap<String, Integer> wordFrequencyMap = new TreeMap<>();
        
                BufferedReader reader = new BufferedReader(new FileReader(fileName));

                String line;

                while ((line = reader.readLine()) != null) {

                line = line.toLowerCase().replaceAll("[^a-zA-Z ]", ""); // noktalamaları silip küçük harfe dönüştüruyro

                 String[] words = line.split("\\s+"); // boşlukları ayırıyor

                 for (String word : words) {

                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                }
                }
                reader.close();
                return wordFrequencyMap;


            }
        public static void main(String[] args) throws IOException {
                String fileName = "shakespeare.txt"; 
                TreeMap<String, Integer> wordFrequencyMap = wordFrequencyCounter(fileName);
                System.out.println("Word Frequencies:");
                for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
                    System.out.print(entry.getKey() + ": " + entry.getValue()+ " ");
                }
        
                
        

            }
        }
        




/*
 * /***
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

/*
 *            __     _                                                          
 *           / _|   | |                                                         
 *       ___| |_ ___| | ___   _ _ __ _   _  ___ __ _ _   _   ___ ___  _ __ ___  
 *      / _ \  _/ _ \ |/ / | | | '__| | | |/ __/ _` | | | | / __/ _ \| '_ ` _ \ 
 *     |  __/ ||  __/   <| |_| | |  | |_| | (_| (_| | |_| || (_| (_) | | | | | |
 *      \___|_| \___|_|\_\\__,_|_|   \__,_|\___\__,_|\__, (_)___\___/|_| |_| |_|
 *                                                    __/ |                     
 *                                                   |___/                      
 */
 