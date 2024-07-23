package models;

public class Question {
    private final String questionText;
    private final String[] answers;
    private final int correctAnswerIndex;
    private int userAnswerIndex;

    // Constructor
    public Question(String questionText, String[] answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.userAnswerIndex = -1;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getUserAnswerIndex() {
        return userAnswerIndex;
    }

    public void setUserAnswerIndex(int userAnswerIndex) {
        this.userAnswerIndex = userAnswerIndex;
    }
}

