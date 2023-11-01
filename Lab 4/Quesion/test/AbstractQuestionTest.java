/**
 * Test abstract base class for testing {@link Question}. This class
 * contains all the method definitions that are common to the concrete
 * test classes including {@link YesNoQuestionTest},
 * {@link LikertQuestionTest} and {@link MultipleChoiceQuestionTest}.
 */


abstract class AbstractQuestionTest {

  protected String longRandom;

  /**
   * Constructs a long random texts.
   */


  public AbstractQuestionTest() {
    longRandom = "aosdifjaso oifhas;ldihv;al skdfha;osidghv;osiadhvbasdjkhvn";
  }

}