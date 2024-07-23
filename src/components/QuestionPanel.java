package components;

import models.Question;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {

    public QuestionPanel(Question question) {
        setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel(question.getQuestionText());
        add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel();
        answersPanel.setLayout(new GridLayout(question.getAnswers().length, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[question.getAnswers().length];

        for (int i = 0; i < question.getAnswers().length; i++) {
            radioButtons[i] = new JRadioButton(question.getAnswers()[i]);
            buttonGroup.add(radioButtons[i]);
            answersPanel.add(radioButtons[i]);

            int index = i;
            radioButtons[i].addActionListener(e -> question.setUserAnswerIndex(index));
        }

        add(answersPanel, BorderLayout.CENTER);
    }
}
