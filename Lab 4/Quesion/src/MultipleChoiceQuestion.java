
/**
 * this class represents a multiple-choice question.
 * It must take in the possible answers, and the valid answers should be numbers starting from 1.
 */
public class MultipleChoiceQuestion extends AbstractQuestion {

  private final String[] options;

  /**
   * constructs a {@code MultipleChoiceQuestion} object.
   * in this constructor we call superclass method to access its constructor
   * to initialize MultipleChoiceQuestion constructor.
   *
   * @param text    initial parameters gotten form Abstract class
   * @param options the answer options which should be numbers starting from 1
   */
  public MultipleChoiceQuestion(String text, String[] options) {
    super(text);
    this.options = options;
  }

  /**
   * this method returns the type of question.
   *
   * @return "MultipleChoice" question type as a string
   */
  @Override
  protected String type() {

    return "MultipleChoice";
  }

  /**
   * this method returns answer options.
   *
   * @return answer options which should be numbers starting from 1
   */
  @Override
  protected String[] getOption() {

    return options;
  }

  /**
   * this method checks the text of the question if it is not empty and has question mark.
   *
   * @param text of the question
   */
  @Override
  protected void checkCondition(String text) {

    if ((text.length() == 0) || (text.charAt(text.length() - 1) != '?')) {
      throw new IllegalArgumentException("Invalid question text");
    }

  }


}
