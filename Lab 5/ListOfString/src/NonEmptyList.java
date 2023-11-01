import java.util.ArrayList;
import java.util.Arrays;


/**
 * This represents a non-empty node of the list. It contains a piece of data along with
 * the rest of the list.
 */
public class NonEmptyList implements ListOfString {
  private String str;
  private ListOfString rest;

  private ListOfString node;

  private ListOfString node2;


  /**
   * constructs a ListofString using str and rest.
   *
   * @param s first string element
   * @param rest a ListOfString object
   */
  public NonEmptyList(String s, ListOfString rest) {
    this.str = s;
    this.rest = rest;
    node = this;
    node2 = new EmptyList();
  }

  /**
   * Return the number of strings in this list.
   *
   * @return the size of this list
   */
  @Override
  public int size() {
    return 1 + this.rest.size();
  }

  /**
   * Add the given string to the front of this list and return the resulting list.
   *
   * @param s the string to be added
   * @return the resulting list
   */
  @Override
  public ListOfString addFront(String s) {
    return new NonEmptyList(s, this);
  }

  /**
   * Add the given string to the back of this list and return the resulting list.
   *
   * @param s the string to be added
   * @return the resulting list
   */
  @Override
  public ListOfString addBack(String s) {
    this.rest = this.rest.addBack(s);
    return this;
  }

  /**
   * A method that adds the given string at the given index in this list. If index is 0,
   * it means this string should be added to the front of this list.
   *
   * @param index the position to be occupied by this object, starting at 0
   * @param s     the string to be added
   * @return the resulting list
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override
  public ListOfString add(int index, String s) {
    if (index == 0) {
      return addFront(s);
    } else {
      return new NonEmptyList(this.str, this.rest.add(index - 1, s));
    }
  }

  /**
   * Get the string at the specified index, with 0 meaning the first object in this list.
   *
   * @param index the specified index
   * @return the string at the specified index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override
  public String get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.str;
    }
    return this.rest.get(index - 1);
  }

  /**
   * Determine if the specified words are found, in that order somewhere
   * in this list. The words matching is case-insensitive. The words may not be next to each other,
   * but should be in the same order as specified.
   *
   * @param words the list of words to be found in this list
   * @return true if these words are found in that order in this list, false otherwise
   */

  //public boolean find(String[] words) {
   // return foundString(words) == words.length;
 // }

  @Override
  public boolean find(String[] words) {

    int r = words.length;
    int acc = 0;
    boolean resultt = false;

    while (acc <= r-1) {

      resultt = found((Arrays.asList(words).get(acc)).toLowerCase());
      if (!resultt){
        break;
      }
      acc += 1;
    }
   // node = this;
    return resultt;
  }

  public boolean found (String s) {

    if (((NonEmptyList) node).str.toLowerCase()
            .equals((s).toLowerCase())) {
      node = ((NonEmptyList) node).rest;
      return true;
    }else {

      return ((NonEmptyList) node).rest.found(s);
    }

  }

  /**
   * returns ListOfString rest.
   *
   * @return ListOfString rest
   */
  public ListOfString advance() {
    return this.rest; //always exists
  }

  /**
   * checks if there is a next node.
   *
   * @return true
   */
  public boolean canAdvance() {
    return true; //always has a rest
  }


  private int foundString(String[] words) {
    int acc = 0;
    ListOfString node = this;
    while (node.canAdvance() & words.length - 1 >= acc) {
      if (((NonEmptyList) node).str.toLowerCase()
              .equals((Arrays.asList(words).get(acc)).toLowerCase())) {
        acc = acc + 1;
      }
      node = node.advance();
    }
    return acc;
  }

  private ListOfString makeList(ArrayList<String> words) {
    ListOfString list = new EmptyList();
    for (int i = words.size() - 1; i >= 0; i -= 1) {
      list = list.addFront(words.get(i));
    }
    return list;
  }

  /**
   * Return an independent, but reversed version of this list.
   *
   * @return the reverse of this list
   */
  @Override
  public ListOfString reverse() {
    ArrayList<String> words = new ArrayList<>();

    for (int j = this.size() - 1; j >= 0; j--) {
      words.add(this.get(j));
    }

    return makeList(words);
  }

  public ListOfString reverse1() {

    node2.addFront(this.str);

    this.rest.reverse();
    int r = this.size();
    int acc =0;

    while (acc<=r-1){

      this.get(acc)

    }
    return node2;
  }


  /**
   * Return a list of strings that interleaves the elements of this list and the other,
   * beginning with this list.
   *
   * @return an interleaved list of strings
   */

  @Override
  public ListOfString interleave(ListOfString other) {

    ArrayList<String> words = new ArrayList<>();

    int maxIndex = Math.max(other.size() - 1, this.size() - 1);

    for (int j = 0; j <= maxIndex; j++) {

      if ((j <= (other.size() - 1)) && (j <= (this.size() - 1))) {
        words.add(this.get(j));
        words.add(other.get(j));
      } else if (j > (other.size() - 1)) {
        words.add(this.get(j));

      } else if (j > (this.size() - 1)) {
        words.add(other.get(j));
      }


    }

    return makeList(words);
  }

  public ListOfString interleave1(ListOfString other) {

    other.addFront(this.str);
    this.rest.interleave(other)


  }




}
