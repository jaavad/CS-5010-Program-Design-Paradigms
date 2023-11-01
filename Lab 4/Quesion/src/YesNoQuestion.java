/**
 * this class represents a yes/no question.
 */
public class YesNoQuestion extends AbstractQuestion {


  /**
   * constructs a {@code YesNoQuestion} object.
   * in this constructor we call superclass method to access its constructor
   * to initialize YesNoQuestion constructor.
   *
   * @param text initial parameters gotten form Abstract class
   */
  public YesNoQuestion(String text) {
    super(text);
  }

  /**
   * this method returns the type of question.
   *
   * @return "YesNo" question type as a string
   */
  @Override
  protected String type() {

    return "YesNo";
  }

  /**
   * this method returns answer options.
   *
   * @return answer options {"yes", "no"}
   */
  @Override
  protected String[] getOption() {

    return new String[]{"yes", "no"};
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
