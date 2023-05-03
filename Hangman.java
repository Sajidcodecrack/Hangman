import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hangman extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private int width = 400;
    private int height = 500;
    private int numGuesses = 7;
    private String[] wordList = {"programming", "java", "computer", "algorithm", "software"};
    private String word;
    private char[] letters;
    private JButton[] buttons;
    private JPanel lettersPanel;
    private JLabel wordLabel, statusLabel;

    public Hangman() {
        setTitle("Hangman Game");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        word = wordList[(int) (Math.random() * wordList.length)];
        letters = new char[word.length()];

        for (int i = 0; i < word.length(); i++) {
            letters[i] = '_';
        }

        wordLabel = new JLabel(new String(letters));
        wordLabel.setBounds(50, 50, 300, 50);
        wordLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(wordLabel);

        lettersPanel = new JPanel();
        lettersPanel.setBounds(50, 150, 300, 200);
        lettersPanel.setLayout(new GridLayout(5, 6));

        buttons = new JButton[26];

        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 65);
            buttons[i] = new JButton("" + c);
            buttons[i].addActionListener(this);
            lettersPanel.add(buttons[i]);
        }

        add(lettersPanel);

        statusLabel = new JLabel("Guesses remaining: " + numGuesses);
        statusLabel.setBounds(50, 400, 300, 50);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(statusLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        btn.setEnabled(false);
        btn.setBackground(Color.GRAY);

        char c = btn.getText().charAt(0);
        boolean found = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                letters[i] = c;
                found = true;
            }
        }

        if (!found) {
            numGuesses--;
            statusLabel.setText("Guesses remaining: " + numGuesses);
        }

        wordLabel.setText(new String(letters));

        if (new String(letters).equals(word)) {
            statusLabel.setText("Congratulations! You won!");
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
        } else if (numGuesses == 0) {
            statusLabel.setText("Game over! The word was " + word);
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);

        if (numGuesses < 7) {
            g.drawLine(150, 100, 300, 100);
        }
        if (numGuesses < 6) {
            g.drawLine(225);
        }
    }
    public static void main(String[] args)
    {
        new Hangman();
    }
}

