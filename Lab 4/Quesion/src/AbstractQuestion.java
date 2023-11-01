
/**
 * Abstract base class for implementations of {@link Question}. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link Question} interface.
 */

abstract class AbstractQuestion implements Question {

  //  String questionText;
  //  private String enteredAnswer;
  protected String questionText;
  protected String enteredAnswer;

  /**
   * in this constructor we initialize a constructor for all questions type. It should also throw an
   * IllegalArgumentException via checkCondition method. For MultipleChoice and YesNo questions,
   * the given question text should not be empty or should have question mark. For Likert questions
   * the given question text should not be empty.
   *
   * @param text of the question
   * @throws IllegalArgumentException via checkCondition methods
   */
  public AbstractQuestion(String text) throws IllegalArgumentException {
    checkCondition(text);
    this.questionText = text;
    enteredAnswer = "";
  }

  /**
   * Get the text of this question.
   *
   * @return the text of this question as a string
   */
  @Override
  public String getQuestionText() {

    return questionText;
  }

  /**
   * Get a string that represents the type of this question.
   * The actual string returned depends on the implementation.
   *
   * @return the type of this question, as a string
   */
  @Override
  public String getType() {

    return type();
  }

  /**
   * Enter an answer to this question. Specific implementations may enforce constraints
   * on what a valid answer can be.
   *
   * @param enteredAnswer the answer entered for this question by a student
   */
  @Override
  public void answer(String enteredAnswer) {
    String[] options = getOption();
    for (String option : options) {
      if (enteredAnswer.toLowerCase().equals(option)) {
        this.enteredAnswer = enteredAnswer.toLowerCase();
        return;
      }
    }

    throw new IllegalArgumentException("Invalid answer");
  }

  /**
   * Returns whether this question has been answered by the student.
   *
   * @return true if the question has been answered, false otherwise
   */
  @Override
  public boolean hasBeenAnswered() {
    String[] options = getOption();
    for (String option : options) {
      if (enteredAnswer.toLowerCase().equals(option)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return the answer entered by the student, if it has been entered.
   *
   * @return the answer entered by the student
   * @throws IllegalStateException if the question has not been answered yet
   */
  @Override
  public String getEnteredAnswer() {
    if (!hasBeenAnswered()) {
      throw new IllegalStateException("solution.Question not attempted yet!");
    } else {
      return enteredAnswer;
    }
  }

  abstract protected String type();

  abstract protected String[] getOption();

  abstract protected void checkCondition(String text);

}
