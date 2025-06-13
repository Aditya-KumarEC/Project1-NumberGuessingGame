import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class numberGuessGUI extends JFrame implements ActionListener {

    private Random random = new Random();
    private int targetNumber;
    private int attemptsLeft = 7;

    private JTextField inputField;
    private JLabel messageLabel;
    private JButton guessButton, resetButton;

    public numberGuessGUI() {
        setTitle("Number Guessing Game");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Guess a number between 1 and 100:"));

        inputField = new JTextField(10);
        add(inputField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        add(guessButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        add(resetButton);

        messageLabel = new JLabel("You have 7 attempts.");
        add(messageLabel);

        generateNewNumber();
    }

    private void generateNewNumber() {
        targetNumber = random.nextInt(100) + 1;
        attemptsLeft = 7;
        messageLabel.setText("You have 7 attempts.");
        inputField.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(inputField.getText());

                if (guess < 1 || guess > 100) {
                    messageLabel.setText("Enter a number from 1 to 100.");
                    return;
                }

                attemptsLeft--;

                if (guess == targetNumber) {
                    messageLabel.setText("Correct! You win!");
                    disableInput();
                } else if (attemptsLeft == 0) {
                    messageLabel.setText("Out of attempts! Number was: " + targetNumber);
                    disableInput();
                } else if (guess < targetNumber) {
                    messageLabel.setText("Too low! " + attemptsLeft + " left.");
                } else {
                    messageLabel.setText("Too high! " + attemptsLeft + " left.");
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input. Enter a number.");
            }
        } else if (e.getSource() == resetButton) {
            generateNewNumber();
            inputField.setEditable(true);
            guessButton.setEnabled(true);
        }
    }

    private void disableInput() {
        inputField.setEditable(false);
        guessButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new numberGuessGUI().setVisible(true);
    }
}
