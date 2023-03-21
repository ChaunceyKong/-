package map_;

import java.util.Hashtable;

public class HashTableExercise {
    public static void main(String[] args) {
        Hashtable hashTable = new Hashtable();

        hashTable.put("john",100); //ok
        //hashTable.put(null,100); //异常 NullPointerException
        //hashTable.put("john",null); //异常 NullPointerException
        hashTable.put("lucy",100); //ok
        hashTable.put("lic",100); //ok
        hashTable.put("lic",88); //替换
        hashTable.put("hello1",1);
        hashTable.put("hello2",1);
        hashTable.put("hello3",1);
        hashTable.put("hello4",1);
        hashTable.put("hello5",1);
        hashTable.put("hello6",1);

        System.out.println(hashTable);

    }
}
