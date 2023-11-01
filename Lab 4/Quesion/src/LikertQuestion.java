
/**
 * this class represents a likert question.
 * this class represents a 5-scale likert question. the scales are strongly agree, agree,
 * neither agree nor disagree, disagree and strongly disagree
 */

public class LikertQuestion extends AbstractQuestion {

  /**
   * constructs a {@code LikertQuestion} object.
   * in this constructor we call superclass method to access its constructor
   * to initialize LikertQuestion constructor.
   *
   * @param text initial parameter gotten form Abstract class
   */
  public LikertQuestion(String text) throws IllegalArgumentException {
    super(text);
  }

  /**
   * this method returns the type of question.
   *
   * @return "Likert" question type as a string
   */
  @Override
  protected String type() {
    return "Likert";
  }

  /**
   * this method returns answer options.
   *
   * @return the answer options of likert question
   */
  @Override
  protected String[] getOption() {

    return new String[]{"strongly agree", "agree", "neither agree nor disagree","disagree",
        "strongly disagree"};
  }

  /**
   * this method checks the text of the question if it is not empty.
   *
   * @param text of the question
   */
  @Override
  protected void checkCondition(String text) {

    if ((text.length() == 0)) {
      throw new IllegalArgumentException("Invalid question text");
    }

  }

}
