  

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

public class Lab11_20220808005 {
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