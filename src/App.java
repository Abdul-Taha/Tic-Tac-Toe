import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class App {
    
    private static JButton[] Buttons = new JButton[9];
    private static String CurrentTurn = "X";
    private static boolean Tie = false;
    public static void main(String[] args) throws Exception {
    
        JFrame Screen = new JFrame("Tic Tac Toe");

        JPanel ButtonPanel = new JPanel();

        Screen.setSize(600,600);
        Screen.setVisible(false);
        Screen.setResizable(false);
        Screen.setLayout(new BorderLayout());
        Screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Screen.add(ButtonPanel);
        ButtonPanel.setLayout(new GridLayout(3,3));

        for (int i = 0; i < 9; i++) {
            JButton Button = new JButton("");
            Button.setFocusable(false);
            Button.setFont(new Font("Trebuchet MS",Font.BOLD,150));
            Buttons[i] = Button;
            ButtonPanel.add(Button);
            Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent Click) {
                    JButton ButtonClicked = (JButton) Click.getSource();
                        
                    ButtonClicked.setText(CurrentTurn);
                    ButtonClicked.setEnabled(false);
                    if (CheckWinner()) {
                        JOptionPane.showMessageDialog(Screen, CurrentTurn + " has Won!");
                        for (int i = 0; i < 9; i++) {
                            Buttons[i].setText("");
                            Buttons[i].setEnabled(true);
                        }
                    } else if (Tie) {
                        JOptionPane.showMessageDialog(Screen, "Tie!");
                        for (int i = 0; i < 9; i++) {
                            Buttons[i].setText("");
                            Buttons[i].setEnabled(true);
                        } 
                    } else {
                        CurrentTurn = (CurrentTurn.equals("X")) ? "O" : "X";
                    }
                }
            });
        }

        Screen.setVisible(true);

    }

    private static boolean CheckWinner() {

        int[][] WinningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int i = 0; i < 8; i++) {
            if (!Buttons[WinningCombinations[i][0]].getText().equals("") && Buttons[WinningCombinations[i][0]].getText().equals(Buttons[WinningCombinations[i][1]].getText()) && Buttons[WinningCombinations[i][0]].getText().equals(Buttons[WinningCombinations[i][2]].getText()))
                return true;
        }
        for (int i = 0; i < 9; i++) {
            if (Buttons[i].getText().equals("")) {
                break;
            } else if (i == 8 && !Buttons[i].getText().equals("")) {
                Tie = true;
            }
        }
        return false;
    }

}
