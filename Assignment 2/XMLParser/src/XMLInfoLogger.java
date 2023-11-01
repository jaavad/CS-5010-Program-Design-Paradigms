/**
 * this class represents XMLInfoLogger class.
 */
public class XMLInfoLogger extends AbstractXMLParser {


  /**
   * constructs a {@code XMLParser} object.
   * in this constructor we call superclass method to access abstract's constructor
   * to initialize XMLInfoLogger constructor.
   */


  public XMLInfoLogger() {
    super();
  }

  /**
   * constructs a {@code XMLParser} object.
   * in this constructor we call superclass method to access abstract's constructor
   * to initialize XMLInfoLogger constructor. it creates the parser as a String object. it creates
   * the parser after handling the provided character.
   *
   * @param t the entered character
   */

  public XMLInfoLogger(String t) {
    super(t);
  }

  /**
   * Provide the output of the parser, given all the inputs it has been provided
   * so far. The content and format of this output is defined by individual
   * implementations.
   *
   * @return the output of the parser as a String object
   */
  @Override
  public String output() {

    return outputs;

  }
}
