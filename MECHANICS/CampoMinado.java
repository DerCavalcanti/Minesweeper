package MECHANICS;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class CampoMinado {
    private class Celula extends JButton {
        int row;
        int column;

        public Celula(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    int tileSize = 70;
    int numRow = 10;
    int numCol = numRow;
    int boardWidth = (numCol * tileSize);
    int boardHeight = (numRow * tileSize);

    JFrame frame = new JFrame("Campo Minado");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    int contador = 15;
    Celula[][] campo = new Celula[numRow][numCol];
    ArrayList<Celula> minaList;
    Random random = new Random();

    int cellClick = 0;
    boolean gameOver = false;

    CampoMinado() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Campo Minado: " + Integer.toString(contador));
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(numRow, numCol));
        frame.add(boardPanel);

        for (int row = 0; row < numRow; row++) {
            for (int column = 0; column < numCol; column++) {
                Celula celula = new Celula(row, column);
                campo[row][column] = celula;

                celula.setFocusable(false);
                celula.setMargin(new Insets(0, 0, 0, 0));
                celula.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));
                celula.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (gameOver) {
                            return;
                        }
                        Celula celula = (Celula) e.getSource();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (celula.getText() == "") {
                                if (minaList.contains(celula)) {
                                    revelar();
                                }
                                else {
                                    checar(celula.row, celula.column);
                                }
                            }
                        }
                        else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (celula.getText() == "" && celula.isEnabled()) {
                                celula.setText("🚩");
                            }
                            else if (celula.getText() == "🚩") {
                                celula.setText("");
                            }
                        }
                    }
                });

                boardPanel.add(celula);

            }
        }

        frame.setVisible(true);

        criar();
    }

    void criar() {
        minaList = new ArrayList<Celula>();
        int restante = contador;
        while (restante > 0) {
            int row = random.nextInt(numRow);
            int column = random.nextInt(numCol);

            Celula celula = campo[row][column];
            if (!minaList.contains(celula)) {
                minaList.add(celula);
                restante -= 1;
            }
        }
    }

    void revelar() {
        for (int i = 0; i < minaList.size(); i++) {
            Celula celula = minaList.get(i);
            celula.setText("💣");
        }

        gameOver = true;
        textLabel.setText("Game Over!");
    }

    void checar(int row, int column) {
        if (row < 0 || row >= numRow || column < 0 || column >= numCol) {
            return;
        }

        Celula celula = campo[row][column];
        if (!celula.isEnabled()) {
            return;
        }
        celula.setEnabled(false);
        cellClick += 1;

        int aoRedor = 0;

        aoRedor += contar(row-1, column-1);
        aoRedor += contar(row-1, column);
        aoRedor += contar(row-1, column+1);


        aoRedor += contar(row, column-1);
        aoRedor += contar(row, column+1);


        aoRedor += contar(row+1, column-1);
        aoRedor += contar(row+1, column);
        aoRedor += contar(row+1, column+1);

        if (aoRedor > 0) {
            celula.setText(Integer.toString(aoRedor));
        }
        else {
            celula.setText("");

            checar(row-1, column-1);
            checar(row-1, column);
            checar(row-1, column+1);


            checar(row, column-1);
            checar(row, column+1);


            checar(row+1, column-1);
            checar(row+1, column);
            checar(row+1, column+1);
        }

        if (cellClick == numRow * numCol - minaList.size()) {
            gameOver = true;
            textLabel.setText("Sucesso!");
        }
    }

    int contar(int row, int column) {
        if (row < 0 || row >= numRow || column < 0 || column >= numCol) {
            return 0;
        }
        if (minaList.contains(campo[row][column])) {
            return 1;
        }
        return 0;
    }


    public static void main(String[] args) throws Exception {
        CampoMinado campoMinado = new CampoMinado();
    }
}