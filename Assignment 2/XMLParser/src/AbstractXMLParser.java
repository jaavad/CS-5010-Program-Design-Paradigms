import java.util.Stack;

/**
 * Abstract base class for implementations of {@link XMLParser}. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link XMLParser} interface.
 */




abstract class AbstractXMLParser implements XMLParser {

  protected String s;
  protected String startTagName;
  protected String characters;
  protected String endTagName;
  protected String starterTagName;

  protected int startTagStart;
  protected int startTagEnd;
  protected int endTagStart;

  protected int endTagEnd;
  protected int complete;
  protected int inChar;

  protected int j;

  protected Stack<String> tag;

  protected String outputs;


  /**
   * in this constructor we initialize a constructor.
   */


  public AbstractXMLParser() {

    this.s = "";
    startTagName = "";
    characters = "";
    endTagName = "";
    outputs = "";
    starterTagName = "";
    tag = new Stack<String>();

    startTagStart = 0;
    startTagEnd = 0;
    endTagStart = 0;
    endTagEnd = 0;
    complete = 0;
    inChar = 0;
    j = 0;
  }

  /**
   * in this constructor we initialize a constructor. it initializes the parser as a String object.
   * it initializes the parser after handling the provided character.
   */


  public AbstractXMLParser(String ch) {
    this.s = ch;
  }

  private boolean tagConditionCheck(char c) {
    return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || (c == ':')
            || (c == '_') || (c == '-') || (c == '>'));
  }

  private boolean tagStartConditionCheck(char c) {
    return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == ':')
            || (c == '_'));
  }

  /**
   * Accept a single character as input, and return the new parser as a result
   * of handling this character.
   *
   * @param c the input character
   * @return the parser after handling the provided character
   * @throws InvalidXMLException if the input causes the XML to be invalid
   */
  @Override
  public XMLParser input(char c) throws InvalidXMLException {

    if (this.s.length() == 0) {
      if (c != '<') {
        throw new InvalidXMLException("Invalid XML");
      } else {
        inChar = 0;
        this.s += c;
        startTagStart = 1;
      }
    } else if (startTagStart == 1) {
      if (this.s.charAt(this.s.length() - 1) == '<') {
        if (tagStartConditionCheck(c)) {
          this.s += c;
          startTagName += c;
        } else {
          throw new InvalidXMLException("Invalid XML");
        }
      } else {
        if (tagConditionCheck(c)) {
          if (c == '>') {
            startTagStart = 0;
            startTagEnd = 1;
            this.s += c;
            inChar = 0;
            tag.push(startTagName);

            if (!characters.equals("")) {
              // || characters.length() != 0
              outputs += "Characters:" + characters + "\n";
              characters = "";
            }

            outputs += "Started:" + startTagName + "\n";
            startTagName = "";
          } else {
            startTagName += c;
            this.s += c;
          }
        } else {
          throw new InvalidXMLException("Invalid XML");
        }
      }

    } else if (startTagEnd == 1 || endTagEnd == 1) {

      if (c == '>') {
        throw new InvalidXMLException("Invalid XML");
      } else {
        if (this.s.charAt(this.s.length() - 1) == '<') {
          if (c == '/') {

            endTagStart = 1;
            this.s += c;
            startTagEnd = 0;
            endTagEnd = 0;
          } else {

            if (tagStartConditionCheck(c)) {
              this.s += c;
              startTagName += c;
              startTagStart = 1;
              startTagEnd = 0;
            } else {
              throw new InvalidXMLException("Invalid XML");
            }

          }

        } else {

          if (c == '<') {
            this.s += c;
          } else {
            this.s += c;
            characters += c;
          }
        }

      }


    } else if (endTagStart == 1) {

      if (c == '>') {

        if (endTagName.equals(starterTagName)) {

          if (!characters.equals("")) {
            // || characters.length() != 0
            outputs += "Characters:" + characters + "\n";
            characters = "";
          }
          outputs += "Ended:" + endTagName + "\n";
          this.s += c;
          endTagStart = 0;
          inChar = 0;
          endTagName = "";
          starterTagName = "";
          j = 0;
          if (tag.empty()) {
            complete = 1;
            endTagStart = 0;
            startTagEnd = 0;
            startTagStart = 0;

          } else {
            endTagEnd = 1;
            endTagStart = 0;
          }
        } else {
          throw new InvalidXMLException("Invalid XML");
        }
      } else {

        if (this.s.charAt(this.s.length() - 1) == '/') {
          starterTagName = tag.pop();
          j = 0;
          if (starterTagName.charAt(j) == c) {
            if (tagStartConditionCheck(c)) {
              endTagName += c;
              this.s += c;
            } else {
              throw new InvalidXMLException("Invalid XML");
            }
          } else {
            throw new InvalidXMLException("Invalid XML");
          }
        } else {
          j += 1;
          if (starterTagName.charAt(j) == c) {
            if (tagConditionCheck(c)) {
              endTagName += c;
              this.s += c;
            } else {
              throw new InvalidXMLException("Invalid XML");
            }
          } else {
            throw new InvalidXMLException("Invalid XML");
          }
        }
      }
    } else if (complete == 1) {

      throw new InvalidXMLException("Invalid XML");

    }

    return this;
  }

  @Override
  public String toString() {

    return this.s;
  }

  //abstract protected String returns();
}
