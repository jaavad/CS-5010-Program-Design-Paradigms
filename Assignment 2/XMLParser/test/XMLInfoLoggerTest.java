
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * tests all specifications of {@link XMLParser interface} and {@link XMLInfoLogger}.
 */


public class XMLInfoLoggerTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();


  /**
   * tests input method for throwing InvalidXMLException when there is an invalid tag name.
   *
   * @throws InvalidXMLException when there is invalid XML
   */

  @Test(expected = InvalidXMLException.class)
  public void testInputInvalidTagNames() throws InvalidXMLException {

    Character[] inputs = {'<', 'h', '<', 't', 'm', 'l', '>'};

    XMLParser xmlInfoLogger = new XMLInfoLogger();

    for (char character : inputs) {

      xmlInfoLogger.input(character);
      //System.out.println(XMLInfoLogger.toString());

    }

  }

  /**
   * tests input method for throwing InvalidXMLException when there is an invalid starter character
   * for tag name instead of <.
   *
   * @throws InvalidXMLException when there is invalid XML
   */

  @Test(expected = InvalidXMLException.class)
  public void testInputInvalidStarterCharacter() throws InvalidXMLException {

    Character[] inputs = {'l'};

    XMLParser xmlInfoLogger = new XMLInfoLogger();

    for (char character : inputs) {

      xmlInfoLogger.input(character);
      //System.out.println(XMLInfoLogger.toString());

    }

  }


  /**
   * tests input method for throwing InvalidXMLException when there is an invalid starter character
   * for tag name after using <.
   *
   * @throws InvalidXMLException when there is invalid XML
   */


  @Test(expected = InvalidXMLException.class)
  public void testInputInvalidStarterCharacterTagName() throws InvalidXMLException {

    Character[] inputs = {'<', '4'};

    XMLParser xmlInfoLogger = new XMLInfoLogger();

    for (char character : inputs) {

      xmlInfoLogger.input(character);
      //System.out.println(XMLInfoLogger.toString());

    }

  }


  /**
   * tests valid complex XML.
   *
   * @throws InvalidXMLException when there is invalid XML
   */

  @Test
  public void testInputValidComplexXML() throws InvalidXMLException {

    Character[] inputs = {'<', 'h', '>', '<', 'd', '_', '-', 'A', '9', ':', '>',
        '<', '/', 'd', '_', '-', 'A', '9', ':', '>',
        '$', '*', '_', 'A', 's', '3', '\n', '\t', '\"', '?', '?', '\b',
        '<', '/', 'h', '>'};

    XMLParser xmlInfoLogger = new XMLInfoLogger();

    for (char character : inputs) {

      xmlInfoLogger.input(character);
      //System.out.println(XMLInfoLogger.toString());

    }

    String expected = "<h><d_-A9:></d_-A9:>$*_As3" + '\n' + '\t' + '\"' + '?' + '?' + '\b' +
            '<' + '/' + 'h' + '>';

    assertEquals(expected, xmlInfoLogger.toString());

  }


  /**
   * tests input method for throwing InvalidXMLException when it receives a char after
   * closing root tag.
   *
   * @throws InvalidXMLException when there is invalid XML
   */


  @Test(expected = InvalidXMLException.class)
  public void testInputReceivingCharAfterClosingRootTag() throws InvalidXMLException {

    Character[] inputs = {'<', 'h', '>', '<', '/', 'h', '>', '2'};

    XMLParser xmlInfoLogger = new XMLInfoLogger();

    for (char character : inputs) {

      xmlInfoLogger.input(character);
      //System.out.println(XMLInfoLogger.toString());

    }

  }

  /**
   * tests input method for throwing InvalidXMLException when there is invalid XML nesting.
   *
   * @throws InvalidXMLException when there is invalid XML
   */

  @Test(expected = InvalidXMLException.class)
  public void testInputInvalidXMLNesting() throws InvalidXMLException {

    Character[] inputs = {'<', 'h', '>', '<', 'd', '>', '<', '/', 'h', '>'};

    XMLParser xmlInfoLogger = new XMLInfoLogger();

    for (char character : inputs) {

      xmlInfoLogger.input(character);
      //System.out.println(XMLInfoLogger.toString());

    }

  }

  /**
   * tests input method for throwing InvalidXMLException when using '>' in not appropriate place
   * (in character location).
   *
   * @throws InvalidXMLException when there is invalid XML
   */


  @Test(expected = InvalidXMLException.class)
  public void testInputInvalidXML_UsingMoreThanSign() throws InvalidXMLException {

    Character[] inputs = {'<', 'h', '>', '>'};

    XMLParser xmlInfoLogger = new XMLInfoLogger();

    for (char character : inputs) {

      xmlInfoLogger.input(character);
      //System.out.println(XMLInfoLogger.toString());

    }

  }

  /**
   * tests input method for throwing InvalidXMLException when tag name has no name.
   *
   * @throws InvalidXMLException when there is invalid XML
   */

  @Test(expected = InvalidXMLException.class)
  public void testInputInvalidXMLWhenTagNameHasNoName() throws InvalidXMLException {

    Character[] inputs = {'<', '>'};

    XMLParser xmlInfoLogger = new XMLInfoLogger();

    for (char character : inputs) {

      xmlInfoLogger.input(character);
      //System.out.println(XMLInfoLogger.toString());

    }

  }

  /**
   * tests output method when there would be any output.
   */

  @Test
  public void testOutput() throws InvalidXMLException {

    Character[] inputs = {'<', 'h', '>', 'd', '<', '/', 'h', '>'};
    String[] outputsArray = {"Started:h" + "\n", "Characters:d" + "\n" +
        "Ended:h" + "\n"};

    String outputs = "";

    XMLParser xmlInfoLogger = new XMLInfoLogger();
    int j = 0;
    int i = 0;

    for (char character : inputs) {
      j += 1;
      xmlInfoLogger.input(character);
      if (j == 3 || j == 8) {
        outputs += outputsArray[i];
        //System.out.println(outputs);
        assertEquals(outputs, xmlInfoLogger.output());
        i += 1;
      }
    }
  }


  /**
   * tests output method when there would be none output.
   */

  @Test
  public void testOutput2() throws InvalidXMLException {

    Character[] inputs = {'<', 'h', '>', 'd', '<', '/', 'h', '>'};
    String[] outputsArray = {"Started:h" + "\n", "Characters:d" + "\n" +
        "Ended:h" + "\n"};

    String outputs = "";

    XMLParser xmlInfoLogger = new XMLInfoLogger();
    assertEquals(outputs, xmlInfoLogger.output());
    //System.out.println(xmlInfoLogger.output());

  }

}