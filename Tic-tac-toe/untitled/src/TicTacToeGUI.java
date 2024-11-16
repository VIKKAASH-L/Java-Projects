import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private JButton[][] buttons = new JButton[3][3];
    private JFrame frame;

    public TicTacToeGUI() {
        // Initialize the board with spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        // Set up the frame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        // Create buttons and add them to the frame
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[row][col].getText().equals(" ") && !isGameOver()) {
                            buttons[row][col].setText(String.valueOf(currentPlayer));
                            board[row][col] = currentPlayer;
                            if (haveWon(board, currentPlayer)) {
                                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                                resetBoard();
                            } else if (isDraw()) {
                                JOptionPane.showMessageDialog(frame, "It's a draw!");
                                resetBoard();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private boolean haveWon(char[][] board, char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isGameOver() {
        return haveWon(board, 'X') || haveWon(board, 'O') || isDraw();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j].setText(" ");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
