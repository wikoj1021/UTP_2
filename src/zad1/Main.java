/**
 *
 *  @author Mińkowski Wiktor S16856
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    Function <String, List<String>> flines = (fname)->{
      LinkedList<String> lines = new LinkedList<>();

      try {
        FileReader fileReader = new FileReader(fname);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = null;
        while((line = bufferedReader.readLine()) != null)
        {
          lines.add(line);
        }

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

      return lines;
    };

    Function<List<String>, String> join = (listToJoin)->{
      String txt = new String();

      for(String s : listToJoin)
      {
        txt += s;
      }

      return txt;
    };

    Function<String, List<Integer>> collectInts = (txt)->{
      LinkedList<Integer> list = new LinkedList<>();

      Pattern p = Pattern.compile("[0-9]+");
      Matcher m = p.matcher(txt);

      while(m.find())
      {
        list.add(Integer.parseInt(m.group()));
      }

      return list;
    };

    Function<List<Integer>, Integer> sum = (list)->{
      Integer ret = 0;

      for (Integer n:list)
      {
        ret+=n;
      }

      return ret;
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
