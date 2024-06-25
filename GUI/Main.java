package GUI;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame{
    JFrame frame = new JFrame("Minesweeper");
    JLabel label = new JLabel("ESCOLHA SEU MODO:", SwingConstants.CENTER);
    Image background;
    JButton b1 = new JButton("MODO CLÁSSICO");
    JButton b2 = new JButton("MODO CRONOMETRADO");
    JPanel panel = new JPanel(new GridBagLayout()) {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            try{
                background = ImageIO.read(new File("C:\\Users\\STI\\Pictures\\background.jpg"));
            }catch(Exception e){
                System.out.println("Erro: " + e);
            }
            g.drawImage(background, 0, 0, 1557, 935, this);
        }
    };

    void CreateOptionPanel(){
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 5;
        c.weighty = 5;
        c.insets = new Insets(10,25,25,25);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        label.setForeground(Color.white);
        label.setFont(new Font("Arial",Font.BOLD,40));

        b1.setForeground(Color.white);
        b1.setFont(new Font("Arial", Font.BOLD, 30));
        b1.setOpaque(false);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        b2.setForeground(Color.white);
        b2.setFont(new Font("Arial", Font.BOLD, 30));
        b2.setOpaque(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);

        panel.add(label, c);
        panel.add(b1, c);
        panel.add(b2,c);
        frame.add(panel);

        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\STI\\Downloads\\icon.png");
        frame.setIconImage(icon);
        frame.setSize(new Dimension(1000,850));
        frame.setMinimumSize(new Dimension(1000,850));
        frame.setMaximumSize(new Dimension(1000,850));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("background.png");
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Main screen = new Main();
        screen.CreateOptionPanel();
    }
}