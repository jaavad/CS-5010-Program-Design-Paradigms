import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Created by ashesh on 1/30/2017. it tests ListOfString.
 */
public class ListOfStringTest {

  /**
   * tests counts using emptyList and nonEmptyList methods.
   */
  @Test
  public void testCounts() {
    ListOfString emptyListOfStrings = new EmptyList();

    ListOfString partialListOfStrings = new NonEmptyList("capital",
            new NonEmptyList("of",
                    new NonEmptyList("Massachusetts", new
                            EmptyList())));

    ListOfString listOfStrings = new NonEmptyList("Boston",
            new NonEmptyList("is",
                    new NonEmptyList("the",
                            partialListOfStrings
                    )));
    assertEquals(0, emptyListOfStrings.size());
    assertEquals(3, partialListOfStrings.size());
    assertEquals(6, listOfStrings.size());
  }

  /**
   * tests easy string list assembly using emptyList, addFront and add methods.
   */
  @Test
  public void testEasyStringListAssembly() {
    ListOfString sentence = new EmptyList();
    sentence = sentence.addFront("Massachusetts");
    sentence = sentence.addFront("Boston");
    sentence = sentence.add(1, "is");
    sentence = sentence.add(2, "of");
    sentence = sentence.add(2, "the");
    sentence = sentence.add(3, "capital");

    String[] words = "Boston is the capital of Massachusetts".split("\\s+");
    assertEquals(words.length, sentence.size());

    for (int i = 0; i < words.length; i = i + 1) {
      assertEquals(words[i], sentence.get(i));
    }

  }

  /**
   * tests find method.
   */
  @Test
  public void testListFind() {
    ListOfString sentence = makeList("Boston is the capital of Massachusetts");
    ListOfString sentence2 = makeList("Boston is boston");

    String[] words = new String[]{"boston", "capital"};
    System.out.println(Arrays.asList(words).size());


    assertEquals(true, sentence.find(new String[]{"boston", "capital"}));
    assertEquals(false, sentence.find(new String[]{"boston", "boston"}));
    assertEquals(false, sentence.find(new String[]{"capital of New York"}));
    assertEquals(true, sentence2.find(new String[]{"boston", "boston"}));
  }

  /**
   * tests reverse method.
   */
  @Test
  public void testReverse() {
    ListOfString sentence = makeList("Boston is the capital of Massachusetts");

    ListOfString reversed = sentence.reverse();

    assertEquals(sentence.size(), reversed.size());
    for (int i = 0; i < sentence.size(); i = i + 1) {
      assertEquals(sentence.get(i), reversed.get(sentence.size() - 1 - i));
    }

  }

  /**
   * tests interLeave method.
   */
  @Test
  public void testInterLeave() {
    ListOfString part1 = makeList("Boston the of state in USA");
    ListOfString part2 = makeList("is capital Massachusetts");
    ListOfString part1PlusPart2 = part1.interleave(part2);
    ListOfString part2PlusPart1 = part2.interleave(part1);

    int j = 0;
    int k = 0;
    for (int i = 0; i < 7; i = i + 1) {
      if (i % 2 == 0 && j < part1.size()) {
        assertEquals(part1.get(j), part1PlusPart2.get(i));
        j += 1;
      } else {
        assertEquals(part2.get(k), part1PlusPart2.get(i));
        k += 1;
      }
    }

    j = 0;
    k = 0;
    for (int i = 0; i < 7; i = i + 1) {
      if (i % 2 == 0 && k < part2.size()) {
        assertEquals(part2.get(k), part2PlusPart1.get(i));
        k += 1;
      } else {
        assertEquals(part1.get(j), part2PlusPart1.get(i));
        j += 1;
      }
    }


  }


  private ListOfString makeList(String sentence) {
    String[] words = sentence.split("\\s+");
    ListOfString list = new EmptyList();
    for (int i = words.length - 1; i >= 0; i -= 1) {
      list = list.addFront(words[i]);
    }
    return list;
  }


}