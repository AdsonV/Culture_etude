package c_e;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Login extends JFrame {

    static JLabel l = new JLabel();
    static JTextField Login = new JTextField();
    static JTextField Senha = new JPasswordField();
    static JFrame janela = new JFrame();
    static JButton buttonLogin = new JButton("LOGIN");

    public static void Logar() throws Exception {

        janela = new JFrame();

        BufferedImage imagem = ImageIO.read(new File("Captura de tela 2024-10-25 201418.png"));
        ImagePanel imagePanel = new ImagePanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        imagePanel.setImage(imagem);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(1000, 200));
        imagePanel.add(panel);

        l.setPreferredSize(new Dimension(670, 90));
        l.setFont(new Font("Calibri", Font.BOLD, 90));
        l.setText("CULTURE_ETUDE");
        l.setForeground(Color.red);
        panel.add(l);

        imagePanel.add(criarPanelLogin());
        imagePanel.add(criarPanelSenha());
        imagePanel.add(criarPanelBotoes());

        janela.setContentPane(imagePanel);
        janela.setMinimumSize(new Dimension(1370, 750));
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    private static JPanel criarPanelLogin() {
        JPanel panelLogin = new JPanel(new FlowLayout());
        panelLogin.setOpaque(false);

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setFont(new Font("Arial", Font.BOLD, 45));
        labelLogin.setPreferredSize(new Dimension(158, 60));
        panelLogin.add(labelLogin);

        Login.setFont(new Font("Calibri", Font.PLAIN, 34));
        Login.setPreferredSize(new Dimension(300, 40));
        panelLogin.add(Login);
        panelLogin.setPreferredSize(new Dimension(800, 63));
        return panelLogin;
    }

    private static JPanel criarPanelSenha() {

        JPanel panelSenha = new JPanel(new FlowLayout());
        panelSenha.setOpaque(false);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", Font.BOLD, 45));
        labelSenha.setPreferredSize(new Dimension(160, 60));
        panelSenha.add(labelSenha);

        Senha.setPreferredSize(new Dimension(300, 40));
        panelSenha.add(Senha);
        panelSenha.setPreferredSize(new Dimension(1300, 100));
        Senha.addActionListener(new Listar());

        return panelSenha;
    }

    private static JPanel criarPanelBotoes() throws Exception {

        buttonLogin = new JButton("LOGIN");

        JPanel panelBotoes = new JPanel(new GridLayout(2,1));
        panelBotoes.setPreferredSize(new Dimension(310, 100));
        panelBotoes.setOpaque(false);

        buttonLogin.setFont(new Font("Arial", Font.BOLD, 40));
        buttonLogin.setPreferredSize(new Dimension(300, 60));
        panelBotoes.add(buttonLogin);

        JButton buttonLogin2 = new JButton("Primeiro acesso");
        buttonLogin2.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonLogin2.setPreferredSize(new Dimension(300, 20));
        panelBotoes.add(buttonLogin2);

        buttonLogin.addActionListener(new Listar());
        buttonLogin2.addActionListener(new Novo_acesso());

        return panelBotoes;
    }
}