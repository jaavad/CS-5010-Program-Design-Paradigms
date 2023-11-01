
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * tests all specifications of {@link Question interface} and {@link YesNoQuestion}.
 */

public class YesNoQuestionTest extends AbstractQuestionTest {

  /**
   * tests creating valid YesNo question.
   */
  @Test
  public void testCreateValidYesNoQuestion() {
    Random r = new Random(200);
    for (int i = 0; i < 1000; i++) {
      int start = r.nextInt(longRandom.length() - 1);
      int end = start + r.nextInt(longRandom.length() - start - 1) + 1;
      String questionText = longRandom.substring(start, end);
      Question q = new YesNoQuestion(questionText + "?");
      assertEquals(questionText + "?", q.getQuestionText());
      assertEquals("YesNo", q.getType());
    }

  }

  /**
   * tests throwing an IllegalArgumentException for empty text.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateYesNoQuestionNoText() {
    new YesNoQuestion("");
  }

  /**
   * tests throwing an IllegalArgumentException for a text without question mark.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateYesNoQuestionNoQuestionMark() {
    new YesNoQuestion("Is this fun");
  }

  /**
   * tests getEnteredAnswer, hasBeenAnswered and answer methods when the given answer is correct.
   */
  @Test
  public void testAnswerCorrectly() {
    String[] answers = {"yes", "Yes", "YEs", "YeS", "YES", "yEs", "yES", "yeS", "no", "No", "nO",
        "NO"};
    for (String answer : answers) {
      Question q = new YesNoQuestion("Is this a trick question?");
      assertFalse(q.hasBeenAnswered());

      q.answer(answer);
      assertEquals(answer.toLowerCase(), q.getEnteredAnswer());
      assertTrue(q.hasBeenAnswered());
    }
  }

  /**
   * tests getEnteredAnswer, hasBeenAnswered and answer methods when the given answer is incorrect.
   */
  @Test
  public void testAnswerInCorrectly() {
    String[] answers = {"yess", ""};
    for (String answer : answers) {
      Question q = new YesNoQuestion("Is this a trick question?");
      assertFalse(q.hasBeenAnswered());

      try {
        q.answer(answer);
        fail("Yes No question accepted an invalid answer");
      } catch (IllegalArgumentException e) {
        assertFalse(q.hasBeenAnswered());
      }
    }
  }


}