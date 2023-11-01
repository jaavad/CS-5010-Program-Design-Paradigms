import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * tests all specifications of {@link Question interface} and {@link MultipleChoiceQuestion}.
 */

public class MultipleChoiceQuestionTest extends AbstractQuestionTest {


  /**
   * tests creating valid multiple-choice question.
   */
  @Test
  public void testCreateValidMultipleChoiceQuestion() {
    Random r = new Random(200);
    for (int i = 0; i < 1000; i++) {
      int start = r.nextInt(longRandom.length() - 1);
      int end = start + r.nextInt(longRandom.length() - start - 1) + 1;
      String questionText = longRandom.substring(start, end);
      Question q = new MultipleChoiceQuestion(questionText + "?", new String[]{"1", "2"});
      assertEquals(questionText + "?", q.getQuestionText());
      assertEquals("MultipleChoice", q.getType());
    }

  }

  /**
   * tests throwing an IllegalArgumentException for empty text.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testCreateMultipleChoiceQuestionNoText() {
    new YesNoQuestion("");
  }

  /**
   * tests throwing an IllegalArgumentException for a text without question mark.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMultipleChoiceQuestionQuestionMark() {
    new MultipleChoiceQuestion("Is this fun", new String[]{"1", "2"});
  }

  /**
   * tests getEnteredAnswer, hasBeenAnswered and answer methods when the given answer is correct.
   */
  @Test
  public void testAnswerCorrectly() {
    String[] answers = {"1", "1", "2"};
    for (String answer : answers) {
      Question q = new MultipleChoiceQuestion("Is this a trick question?", new String[]{"1", "2"});
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
    String[] answers = {"1.", "", "3"};
    for (String answer : answers) {
      Question q = new MultipleChoiceQuestion("Is this a trick question?",
              new String[]{"1", "2"});
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


