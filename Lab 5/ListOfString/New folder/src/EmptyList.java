
/**
 * This represents an empty node in the list
 */
public class EmptyList implements ListOfString {

  /**
   * Return the number of strings in this list
   *
   * @return the size of this list
   */
  @Override
  public int size() {
    return 0;
  }

  /**
   * Add the given string to the front of this list and return the resulting
   * list
   *
   * @param s the string to be added
   * @return the resulting list
   */
  @Override
  public ListOfString addFront(String s) {
    return new NonEmptyList(s, this);
  }

  /**
   * Add the given string to the back of this list and return the resulting list
   *
   * @param s the string to be added
   * @return the resulting list
   */
  @Override
  public ListOfString addBack(String s) {
    return addFront(s);
  }


  /**
   * A method that adds the given string at the given index in this list
   * . If index is 0, it means this string should be added to the front of this
   * list
   *
   * @param index the position to be occupied by this object, starting at 0
   * @param s     the string to be added
   * @return the resulting list
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override
  public ListOfString add(int index, String s) throws
          IllegalArgumentException {
    if (index == 0) {
      return addFront(s);
    }
    throw new IllegalArgumentException("Invalid index to add an element");
  }

  /**
   * Get the string at the specified index, with 0 meaning the first object in
   * this list
   *
   * @param index the specified index
   * @return the string at the specified index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override
  public String get(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("Wrong index");
  }


  /**
   * returns ListOfString rest.
   *
   * @return ListOfString rest
   */
  public ListOfString advance() {
    throw new UnsupportedOperationException("cannot advance"); //such an operation does
    // not make sense
  }

  /**
   * checks if there is a next node.
   *
   * @return true
   */
  public boolean canAdvance() {
    return false;
  }

  /**
   * Determine if the specified words are found, in that order somewhere
   * in this list. The words matching is case-insensitive. The words may not be next to each other,
   * but should be in the same order as specified.
   *
   * @param words the list of words to be found in this list
   * @return true if these words are found in that order in this list
   * or if the list of words is empty, false otherwise
   */
  @Override
  public boolean find(String[] words) {

    return false;
  }


  /**
   * Return an independent, but reversed version of this list.
   *
   * @return the reverse of this list
   */
  @Override
  public ListOfString reverse() {

    return null;
  }

  /**
   * Return a list of strings that interleaves the elements of this list and the other,
   * beginning with this list.
   *
   * @return an interleaved list of strings
   */

  @Override
  public ListOfString interleave(ListOfString other) {

    return null;
  }


}
