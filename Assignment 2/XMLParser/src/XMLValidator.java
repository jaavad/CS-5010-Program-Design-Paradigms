/**
 * this class represents XMLValidator class.
 */
public class XMLValidator extends AbstractXMLParser {


  /**
   * constructs a {@code XMLXMLParser} object.
   * in this constructor we call superclass method to access abstract's constructor
   * to initialize XMLValidator constructor.
   */


  public XMLValidator() {
    super();
  }

  /**
   * constructs a {@code XMLParser} object.
   * in this constructor we call superclass method to access abstract's constructor
   * to initialize XMLValidator constructor. it creates the parser as a String object. it creates
   * the parser after handling the provided character.
   *
   * @param t the entered character
   */


  public XMLValidator(String t) {
    super(t);
  }


  /**
   * Provide the output of the parser, given all the inputs it has been provided
   * so far. The content and format of this output is defined by individual
   * implementations. it returns the parser as a String object
   *
   * @return the output of the parser as a String object
   */
  @Override
  public String output() {

    if (complete == 1) {

      return "Status:Valid";
    } else if (this.s.length() == 0) {

      return "Status:Empty";

    } else {

      return "Status:Incomplete";

    }

  }

}
