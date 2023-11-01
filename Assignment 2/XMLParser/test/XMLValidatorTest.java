
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * tests all specifications of {@link XMLParser interface} and {@link XMLValidator}.
 */


public class XMLValidatorTest {


  @Rule
  public ExpectedException exception = ExpectedException.none();


  /**
   * tests input method for throwing InvalidXMLException when there is an invalid tag name.
   * @throws InvalidXMLException when there is invalid XML
   */

  @Test (expected = InvalidXMLException.class)
  public void testInputInvalidTagNames() throws InvalidXMLException {

    Character[] inputs = {'<','h','<','t','m','l','>'};

    XMLParser xmlValidator = new XMLValidator();

    for (char character : inputs) {

      xmlValidator.input(character);
      //System.out.println(XMLValidator.toString());

    }

  }

  /**
   * tests input method for throwing InvalidXMLException when there is an invalid starter character
   * for tag name instead of <.
   * @throws InvalidXMLException when there is invalid XML
   */

  @Test (expected = InvalidXMLException.class)
  public void testInputInvalidStarterCharacter() throws InvalidXMLException {

    Character[] inputs = {'l'};

    XMLParser xmlValidator = new XMLValidator();

    for (char character : inputs) {

      xmlValidator.input(character);
      //System.out.println(xmlValidator.toString());

    }

  }


  /**
   * tests input method for throwing InvalidXMLException when there is an invalid starter character
   * for tag name after using <.
   * @throws InvalidXMLException when there is invalid XML
   */


  @Test (expected = InvalidXMLException.class)
  public void testInputInvalidStarterCharacterTagName() throws InvalidXMLException {

    Character[] inputs = {'<','4'};

    XMLParser xmlValidator = new XMLValidator();

    for (char character : inputs) {

      xmlValidator.input(character);
      //System.out.println(xmlValidator.toString());

    }

  }


  /**
   * tests valid complex XML.
   * @throws InvalidXMLException when there is invalid XML
   */

  @Test
  public void testInputValidComplexXML() throws InvalidXMLException {

    Character[] inputs = {'<','h','>','<','d','_','-','A','9',':','>',
        '<','/','d','_','-','A','9',':','>',
        '$','*','_','A','s','3','\n','\t','\"','?','?','\b',
        '<','/','h','>'};

    XMLParser xmlValidator = new XMLValidator();

    for (char character : inputs) {

      xmlValidator.input(character);
      //System.out.println(xmlValidator.toString());

    }
    // System.out.println(xmlValidator.toString());
    String expected = "<h><d_-A9:></d_-A9:>$*_As3" + '\n' + '\t' + '\"' + '?' + '?' + '\b' +
        '<' + '/' + 'h' + '>';

    assertEquals(expected, xmlValidator.toString());

  }


  /**
   * tests input method for throwing InvalidXMLException when it receives a char after
   * closing root tag.
   * @throws InvalidXMLException when there is invalid XML
   */


  @Test (expected = InvalidXMLException.class)
  public void testInputReceivingCharAfterClosingRootTag() throws InvalidXMLException {

    Character[] inputs = {'<','h','>','<','/','h','>','2'};

    XMLParser xmlValidator = new XMLValidator();

    for (char character : inputs) {

      xmlValidator.input(character);
      //System.out.println(xmlValidator.toString());

    }

  }

  /**
   * tests input method for throwing InvalidXMLException when there is invalid XML nesting.
   * @throws InvalidXMLException when there is invalid XML
   */

  @Test (expected = InvalidXMLException.class)
  public void testInputInvalidXMLNesting() throws InvalidXMLException {

    Character[] inputs = {'<','h','>','<','d','>','<','/','h','>'};

    XMLParser xmlValidator = new XMLValidator();

    for (char character : inputs) {

      xmlValidator.input(character);
      //System.out.println(xmlValidator.toString());

    }

  }

  /**
   * tests input method for throwing InvalidXMLException when using '>' in not appropriate place
   * (in character location).
   * @throws InvalidXMLException when there is invalid XML
   */


  @Test (expected = InvalidXMLException.class)
  public void testInputInvalidXML_UsingMoreThanSign() throws InvalidXMLException {

    Character[] inputs = {'<','h','>','>'};

    XMLParser xmlValidator = new XMLValidator();

    for (char character : inputs) {

      xmlValidator.input(character);
      //System.out.println(xmlValidator.toString());

    }

  }

  /**
   * tests input method for throwing InvalidXMLException when tag name has no name.
   * @throws InvalidXMLException when there is invalid XML
   */


  @Test (expected = InvalidXMLException.class)
  public void testInputInvalidXMLWhenTagNameHasNoName() throws InvalidXMLException {

    Character[] inputs = {'<','>'};

    XMLParser xmlValidator = new XMLValidator();

    for (char character : inputs) {

      xmlValidator.input(character);
      //System.out.println(xmlValidator.toString());

    }

  }

  /**
   * tests output method.
   */


  @Test
  public void testOutput() throws InvalidXMLException {

    Character[] inputs = {'<','h','>','d','<','/','h','>'};
    String[] outputs = {"Status:Empty", "Status:Incomplete", "Status:Incomplete",
        "Status:Incomplete", "Status:Incomplete", "Status:Incomplete", "Status:Incomplete",
        "Status:Incomplete", "Status:Valid"};

    XMLParser xmlValidator = new XMLValidator();
    int j = 0;
    assertEquals(outputs[j], xmlValidator.output());

    for (char character : inputs) {
      j += 1;
      xmlValidator.input(character);
      assertEquals(outputs[j], xmlValidator.output());

    }
  }


}