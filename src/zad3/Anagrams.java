/**
 *
 *  @author Mińkowski Wiktor S16856
 *
 */

package zad3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Anagrams {

    String fileName;

    Anagrams(String file){
        this.fileName = file;
    }

    List<List<String>> getSortedByAnQty(){

        List<List<String>> list = new LinkedList<>();
        list.add(new LinkedList<>());

        try {
            Scanner scan = new Scanner(new File(fileName));
            while(scan.hasNext()) {
                String toCheck = scan.next();
                for(List<String> checkList: list){
                    String tmpCheck = toCheck;
                    String checking = tmpCheck;
                    String anagram = checkList.get(0);
                    int index = -1;
                    if(checking.length() == anagram.length()) {
                        for (int i = 0; i < anagram.length() && (index = tmpCheck.indexOf(anagram.charAt(i))) != -1; i++) {
                            tmpCheck = checking.substring(0, index-1) + checking.substring(index+1);
                            checking = tmpCheck;
                        }
                    }
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

}  
