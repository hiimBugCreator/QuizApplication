import components.QuizPanel;
import models.Question;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Question> questions = List.of(
                new Question("Compared to their body weight, what animal is the strongest?",
                        new String[]{"Dung Beetle", "Elephant", "Ant", "Cow"}, 1),
                new Question("How many dots appear on a pair of dice?",
                        new String[]{"12", "24", "36", "42"}, 4),
                new Question("How many hearts does an octopus have?",
                        new String[]{"1", "2", "3", "4"}, 3),
                new Question("What phone company produced the 3310?",
                        new String[]{"Nokia", "Sony", "Blackberry", "Motorola"}, 1),
                new Question("Who was the first Disney princess?",
                        new String[]{"Cinderella", "Snow White", "Ariel", "Jasmine"}, 2)
        );
        SwingUtilities.invokeLater(() -> new QuizPanel(questions).setVisible(true));
    }
}