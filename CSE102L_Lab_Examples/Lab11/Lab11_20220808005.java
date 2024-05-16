  

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

 class Efe {
    public static void main(String[] args) throws Exception {
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
        map.remove(1); //for remove the elements
        System.out.println(map);
        map.put(1, "three");
        System.out.println(map);
        map.put(1, "one");




        map.putIfAbsent(1, "four");
        System.out.println(map);
        map.putIfAbsent(4, "four");
        System.out.println(map);
        


        map = new LinkedHashMap<>();
        map = new TreeMap<>();

        TreeSet<Course> treeset = new Treeset<>();
        treeset.add(c2);
        treeset.add(c4);

        

        
    }




















    
}































class Lab11_20220808005 {

    // Task 1: Word Frequency Counter
    public static TreeMap<String, Integer> wordFrequencyCounter(String fileName) throws IOException {
        TreeMap<String, Integer> wordFrequencyMap = new TreeMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.toLowerCase().replaceAll("[^a-zA-Z ]", ""); // Remove punctuation and convert to lowercase
            String[] words = line.split("\\s+"); // Split by whitespace
            for (String word : words) {
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        reader.close();
        return wordFrequencyMap;
    }

    // Task 2: Reverse Mapping
    public static <K, V> TreeMap<V, Set<K>> reverseMapping(Map<K, V> map) {
        TreeMap<V, Set<K>> reversedMap = new TreeMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            reversedMap.computeIfAbsent(entry.getValue(), k -> new TreeSet<>()).add(entry.getKey());
        }
        return reversedMap;
    }

    // Task 3: Find Most Frequent Set
    public static <K, V> Map.Entry<K, Set<V>> findMostFrequentSet(TreeMap<K, Set<V>> treeMap, int n) {
        for (Map.Entry<K, Set<V>> entry : treeMap.descendingMap().entrySet()) {
            if (entry.getValue().size() == n) {
                return entry;
            }
        }
        return null; // Not found
    }

    // Task 4: Find All Sets of a Given Size
    public static <K, V> Map<K, Set<V>> findAllSetsOfGivenSize(TreeMap<K, Set<V>> treeMap, int n) {
        Map<K, Set<V>> result = new TreeMap<>();
        for (Map.Entry<K, Set<V>> entry : treeMap.entrySet()) {
            if (entry.getValue().size() == n) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    // Task 5: Find Kth Largest Set
    public static <K, V> Map.Entry<K, Set<V>> findKthLargestSet(TreeMap<K, Set<V>> treeMap, int n, int k) {
        int count = 0;
        for (Map.Entry<K, Set<V>> entry : treeMap.descendingMap().entrySet()) {
            if (entry.getValue().size() == n) {
                count++;
                if (count == k) {
                    return entry;
                }
            }
        }
        return null; // Not found
    }

    public static void main(String[] args) throws IOException {
        // Test the methods
        String fileName = "shakespeare.txt"; // Provide the correct file path
        TreeMap<String, Integer> wordFrequencyMap = wordFrequencyCounter(fileName);
        System.out.println("Word Frequencies:");
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Test other methods with sample data
        TreeMap<Integer, Set<String>> treeMap = new TreeMap<>();
        treeMap.put(1, new HashSet<>(Arrays.asList("apple", "banana", "cherry")));
        treeMap.put(2, new HashSet<>(Arrays.asList("apple", "banana")));
        treeMap.put(3, new HashSet<>(Arrays.asList("apple", "orange")));
        treeMap.put(4, new HashSet<>(Arrays.asList("banana", "cherry")));
        treeMap.put(5, new HashSet<>(Arrays.asList("apple", "banana", "cherry", "date")));

        System.out.println("\nReversed Mapping:");
        System.out.println(reverseMapping(treeMap));

        System.out.println("\nMost Frequent Set of Size 2:");
        System.out.println(findMostFrequentSet(treeMap, 2));

        System.out.println("\nAll Sets of Size 3:");
        System.out.println(findAllSetsOfGivenSize(treeMap, 3));

        System.out.println("\n2nd Largest Set of Size 3:");
        System.out.println(findKthLargestSet(treeMap, 3, 2));
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
 */