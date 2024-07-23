package components;

import Resource.Strings;
import models.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuizPanel extends JFrame {
    private final CardLayout cardLayout;
    private JPanel cardPanel;
    private final List<Question> questions;
    private int currentIndex = 0;
    private JButton nextButton;
    private JButton previousButton;
    private JPanel answerGridPanel;

    public QuizPanel(List<Question> questions) {
        this.questions = questions;
        cardLayout = new CardLayout();
        setupQuestion();
        setupPreviewPanel();
        setupButtonPanel();
        setTitle(Strings.appName);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupPreviewPanel() {
        int numOfAnswerButtons = 4;
        answerGridPanel = new JPanel(new GridLayout(
                Math.ceilDiv(questions.size(), numOfAnswerButtons),
                numOfAnswerButtons, 10, 10));
        updateGridPanel();
        getContentPane().add(answerGridPanel, BorderLayout.NORTH);
    }

    private void updateGridPanel() {
        answerGridPanel.removeAll();
        for (int i = 0; i < questions.size(); i++) {
            JButton quickBtn = new JButton(String.valueOf(i + 1));
            quickBtn.setHorizontalAlignment(SwingConstants.CENTER);
            int finalI = i;
            quickBtn.addActionListener(e -> {
                currentIndex = finalI;
                cardLayout.show(cardPanel, questions.get(finalI).getQuestionText());
                repaintUI();
            });
            if (questions.get(i).getUserAnswerIndex() == -1) {
                quickBtn.setOpaque(true);
                quickBtn.setForeground(Color.RED.darker());
                quickBtn.setBackground(Color.RED);
            } else {
                quickBtn.setOpaque(true);
                quickBtn.setForeground(Color.GREEN.darker());
                quickBtn.setBackground(Color.GREEN);
            }
            answerGridPanel.add(quickBtn);
        }
        answerGridPanel.revalidate();
        answerGridPanel.repaint();
    }

    private void setupQuestion(){
        cardPanel = new JPanel(cardLayout);
        for (Question question : questions) {
            cardPanel.add(new QuestionPanel(question), question.getQuestionText());
        }
        add(cardPanel, BorderLayout.CENTER);
    }

    private void setIndex() {
        cardPanel.setBorder(
                BorderFactory.createTitledBorder(
                        String.format(Strings.titleFormat, currentIndex + 1, questions.size())));
    }

    private void setupButtonPanel() {
        JPanel buttonPanel = new JPanel();
        nextButton = new JButton(Strings.btnNext);
        previousButton = new JButton(Strings.btnPrevious);
        JButton submitButton = new JButton(Strings.btnSubmit);
        nextButton.addActionListener(e -> {
            if (currentIndex < questions.size() - 1) {
                currentIndex++;
                cardLayout.next(cardPanel);
            }
            repaintUI();
        });
        previousButton.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                cardLayout.previous(cardPanel);
            }
            repaintUI();
        });
        submitButton.addActionListener(e -> {
            int score = 0;
            for (Question question : questions) {
                if (question.getUserAnswerIndex() == question.getCorrectAnswerIndex()) {
                    score++;
                }
            }
            JOptionPane.showMessageDialog(QuizPanel.this,
                    String.format(Strings.scoreFormat, score, questions.size()));
        });
        buttonPanel.add(previousButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(nextButton);
        repaintUI();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateButtonState() {
        previousButton.setEnabled(currentIndex >= 1);
        nextButton.setEnabled(currentIndex < questions.size() - 1);
    }

    private void repaintUI() {
        updateButtonState();
        setIndex();
        updateGridPanel();
    }
}