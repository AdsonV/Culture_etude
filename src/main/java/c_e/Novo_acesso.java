package c_e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Novo_acesso extends JFrame implements ActionListener {

    static JFrame n_janela = new JFrame();
    JPanel painel = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel painel3 = new JPanel();
    JLabel l = new JLabel();
    JLabel l2 = new JLabel();
    JLabel l_usuario = new JLabel();
    JLabel l_senha = new JLabel();
    JLabel l_senha2 = new JLabel();
    static JTextField novo_usuario = new JTextField();
    static JTextField nova_senha = new JPasswordField();
    static JTextField confirma_senha = new JPasswordField();
    static JButton b = new JButton();
    JButton b1 = new JButton();


    @Override
    public void actionPerformed(ActionEvent e) {

        try {

                int optionType = JOptionPane.YES_NO_OPTION;
                int messageType = JOptionPane.QUESTION_MESSAGE;

                int option = JOptionPane.showConfirmDialog(null,"PELO VISTO ESSE É O SEU PRIMEIRO ACESSO AO SISTEMA, DESEJA CONTINUAR?","LOGIN",optionType,messageType);

                if (option == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
                else {
                    Login.janela.dispose();

                    n_janela.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
                    n_janela.setTitle("CULTURE_ETUDE");
                    n_janela.setSize(1550, 800);
                    n_janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    n_janela.setLocationRelativeTo(null);

                    painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    painel.setPreferredSize(new Dimension(1130, 100));
                    n_janela.add(painel);

                    l.setPreferredSize(new Dimension(550, 80));
                    l.setFont(new Font("Arial", Font.BOLD, 60));
                    l.setText("CULTURE_ETUDE");
                    painel.add(l);

//--------------------------------------------------------------------------------------------------------------------//
                    painel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
                    painel2.setPreferredSize(new Dimension(400, 460));
                    n_janela.add(painel2);

                    l2.setPreferredSize(new Dimension(330, 60));
                    l2.setFont(new Font("Arial", Font.BOLD, 33));
                    l2.setText("PRIMEIRO ACESSO");
                    l2.setForeground(Color.DARK_GRAY);
                    painel2.add(l2);

                    l_usuario.setPreferredSize(new Dimension(200, 30));
                    l_usuario.setFont(new Font("Arial", Font.PLAIN, 30));
                    l_usuario.setText("Usuário:");
                    painel2.add(l_usuario);

                    novo_usuario.setPreferredSize(new Dimension(350, 35));
                    novo_usuario.setFont(new Font("Arial", Font.PLAIN, 28));
                    painel2.add(novo_usuario);

                    l_senha.setPreferredSize(new Dimension(300, 30));
                    l_senha.setFont(new Font("Arial", Font.PLAIN, 30));
                    l_senha.setText("Nova senha:");
                    painel2.add(l_senha);

                    nova_senha.setPreferredSize(new Dimension(350, 35));
                    nova_senha.setFont(new Font("Arial", Font.PLAIN, 28));
                    painel2.add(nova_senha);

                    l_senha2.setPreferredSize(new Dimension(300, 30));
                    l_senha2.setFont(new Font("Arial", Font.PLAIN, 30));
                    l_senha2.setText("Confirme a senha:");
                    painel2.add(l_senha2);

                    confirma_senha.setPreferredSize(new Dimension(350, 35));
                    confirma_senha.setFont(new Font("Arial", Font.PLAIN, 28));
                    confirma_senha.addActionListener(new Cadastrar());
                    painel2.add(confirma_senha);

//--------------------------------------------------------------------------------------------------------------------//
                    painel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 0));
                    painel3.setPreferredSize(new Dimension(1200, 60));
                    n_janela.add(painel3);

                    b.setText("Login");
                    b.setFont(new Font("Arial", Font.PLAIN, 33));
                    b.setPreferredSize(new Dimension(300, 60));
                    painel3.add(b);

                    b1.setText("Voltar");
                    b1.setFont(new Font("Arial", Font.PLAIN, 33));
                    b1.setPreferredSize(new Dimension(300, 60));
                    painel3.add(b1);

                    b.addActionListener(new Cadastrar());
                    b1.addActionListener(this::Voltar);

                    n_janela.setVisible(true);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());;
        }
        finally {
            b = new JButton();
            b1 = new JButton();
        }
    }

    public void Voltar(ActionEvent e)
    {
        novo_usuario.setText("");
        nova_senha.setText("");
        confirma_senha.setText("");

        b1 = new JButton();
        n_janela.dispose();
        n_janela = new JFrame();
        Login.janela.setVisible(true);
    }
}