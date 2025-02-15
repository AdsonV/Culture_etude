package c_e;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin extends JFrame implements ActionListener {

    static JFrame a_janela = new JFrame();
    JPanel painel = new JPanel();
    JPanel painel1 = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel painel3 = new JPanel();
    JPanel painel4 = new JPanel();
    JLabel l = new JLabel();
    JLabel l_imagem = new JLabel();
    JLabel l_filme = new JLabel();
    JLabel l_livro = new JLabel();
    JLabel l_data = new JLabel();
    JLabel l_duracao = new JLabel();
    JLabel l_paginas = new JLabel();
    JLabel l_genero = new JLabel();
    JLabel l_autor = new JLabel();
    JLabel l_diretor = new JLabel();
    JLabel l_descricao = new JLabel();
    static JTextField filme_livro = new JTextField();
    static JTextField diretor_autor = new JTextField();
    static JTextArea descricao = new JTextArea();
    static JTextField genero = new JTextField();
    MaskFormatter mascaraDuracao;
    static JFormattedTextField duracao = new JFormattedTextField();
    MaskFormatter mascaraData;
    static JFormattedTextField data = new JFormattedTextField();
    static JTextField paginas = new JTextField();
    static JRadioButton liv = new JRadioButton("LIVRO");
    static JRadioButton fil = new JRadioButton("FILME");
    static JButton b = new JButton();
    JButton b1 = new JButton();
    JButton bimagem = new JButton();
    ButtonGroup bg = new ButtonGroup();
    static JLabel l_imagem2 = new JLabel();
    Image image = null;
    static JFileChooser arquivo = new JFileChooser();

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            Listar.janela.dispose();

            mascaraDuracao = new MaskFormatter("##:##:##");
            duracao = new JFormattedTextField(mascaraDuracao);

            mascaraData = new MaskFormatter("####/##/##");
            data = new JFormattedTextField(mascaraData);

            a_janela.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
            a_janela.setTitle("CULTURE_ETUDE");
            a_janela.setSize(1550, 800);
            a_janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            a_janela.setLocationRelativeTo(null);

            painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            painel.setPreferredSize(new Dimension(1130, 100));
            a_janela.add(painel);

            l.setPreferredSize(new Dimension(550, 80));
            l.setFont(new Font("Arial", Font.BOLD, 60));
            l.setText("ADICIONAR ITEM.");
            painel.add(l);

//--------------------------------------------------------------------------------------------------------------------//
            painel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
            painel1.setPreferredSize(new Dimension(400, 500));
            a_janela.add(painel1);

            painel1.add(l_imagem2);

            l_imagem.setPreferredSize(new Dimension(200, 30));
            l_imagem.setFont(new Font("Arial", Font.PLAIN, 30));
            l_imagem.setText("IMAGEM:");
            painel1.add(l_imagem);

            bimagem.setText("SELECIONAR IMAGEM");
            bimagem.setFont(new Font("Arial", Font.PLAIN, 23));
            bimagem.setPreferredSize(new Dimension(330, 80));
            painel1.add(bimagem);
            bimagem.addActionListener(this::Imagem);

            fil.setFont(new Font("Arial", Font.PLAIN, 18));
            fil.setHorizontalTextPosition(SwingConstants.LEFT);
            liv.setFont(new Font("Arial", Font.PLAIN, 18));
            liv.setHorizontalTextPosition(SwingConstants.LEFT);

            bg.add(liv);
            bg.add(fil);

            painel1.add(fil);
            painel1.add(liv);
//--------------------------------------------------------------------------------------------------------------------//
            painel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 17));
            painel2.setPreferredSize(new Dimension(400, 500));
            a_janela.add(painel2);

            l_filme.setPreferredSize(new Dimension(250, 30));
            l_filme.setFont(new Font("Arial", Font.PLAIN, 30));
            l_filme.setText("Nome Do Filme:");

            l_livro.setPreferredSize(new Dimension(250, 30));
            l_livro.setFont(new Font("Arial", Font.PLAIN, 30));
            l_livro.setText("Nome do livro:");

            filme_livro.setPreferredSize(new Dimension(350, 35));
            filme_livro.setFont(new Font("Arial", Font.PLAIN, 28));

            l_diretor.setPreferredSize(new Dimension(200, 30));
            l_diretor.setFont(new Font("Arial", Font.PLAIN, 30));
            l_diretor.setText("Diretor:");

            diretor_autor.setPreferredSize(new Dimension(350, 35));
            diretor_autor.setFont(new Font("Arial", Font.PLAIN, 28));

            l_autor.setPreferredSize(new Dimension(200, 30));
            l_autor.setFont(new Font("Arial", Font.PLAIN, 30));
            l_autor.setText("Autor:");

            l_data.setPreferredSize(new Dimension(100, 30));
            l_data.setFont(new Font("Arial", Font.PLAIN, 30));
            l_data.setText("Data:");

            data.setPreferredSize(new Dimension(350, 35));
            data.setFont(new Font("Arial", Font.PLAIN, 28));

            l_genero.setPreferredSize(new Dimension(200, 30));
            l_genero.setFont(new Font("Arial", Font.PLAIN, 30));
            l_genero.setText("Gênero:");

            genero.setPreferredSize(new Dimension(350, 35));
            genero.setFont(new Font("Arial", Font.PLAIN, 28));

            l_paginas.setPreferredSize(new Dimension(240, 30));
            l_paginas.setFont(new Font("Arial", Font.PLAIN, 30));
            l_paginas.setText("N° de Páginas:");

            l_duracao.setPreferredSize(new Dimension(200, 30));
            l_duracao.setFont(new Font("Arial", Font.PLAIN, 30));
            l_duracao.setText("Duração:");

            paginas.setPreferredSize(new Dimension(350, 35));
            paginas.setFont(new Font("Arial", Font.PLAIN, 28));

            duracao.setPreferredSize(new Dimension(350, 35));
            duracao.setFont(new Font("Arial", Font.PLAIN, 28));

//--------------------------------------------------------------------------------------------------------------------//

            painel4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 30));
            painel4.setPreferredSize(new Dimension(500, 500));
            a_janela.add(painel4);

            l_descricao.setPreferredSize(new Dimension(200, 32));
            l_descricao.setFont(new Font("Calibri", Font.PLAIN, 30));
            l_descricao.setText("DESCRIÇÃO:");

            descricao.setPreferredSize(new Dimension(400, 200));
            descricao.setFont(new Font("Arial", Font.PLAIN, 20));
            descricao.setLineWrap(true);
//--------------------------------------------------------------------------------------------------------------------//
            painel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 0));
            painel3.setPreferredSize(new Dimension(1200, 60));
            a_janela.add(painel3);

            b.setText("Adicionar");
            b.setFont(new Font("Arial", Font.PLAIN, 33));
            b.setPreferredSize(new Dimension(300, 60));
            painel3.add(b);

            b1.setText("Voltar");
            b1.setFont(new Font("Arial", Font.PLAIN, 33));
            b1.setPreferredSize(new Dimension(300, 60));
            painel3.add(b1);

            b.addActionListener(this::Cadastro);
            b1.addActionListener(this::Voltar);

            Filtrar(e);
            a_janela.setVisible(true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

         }finally {
            b = new JButton();
        }
    }

    public void Voltar(ActionEvent e) {

        b1.addActionListener(new Listar());
        b1 = new JButton();
        Listar.janela.setVisible(true);
        a_janela.dispose();
        a_janela = new JFrame();
    }

    public void Filtrar(ActionEvent e) {
        fil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);

                a_janela.dispose();
                a_janela.setVisible(true);

                painel2.removeAll();
                painel2.add(l_filme);
                painel2.add(filme_livro);
                painel2.add(l_diretor);
                painel2.add(diretor_autor);
                painel2.add(l_data);
                painel2.add(data);
                painel2.add(l_genero);
                painel2.add(genero);
                painel2.add(l_duracao);
                painel2.add(duracao);
                painel4.add(l_descricao);
                painel4.add(descricao);

            }
        });

        liv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);

                a_janela.dispose();
                a_janela.setVisible(true);

                painel2.removeAll();
                painel2.add(l_livro);
                painel2.add(filme_livro);
                painel2.add(l_autor);
                painel2.add(diretor_autor);
                painel2.add(l_data);
                painel2.add(data);
                painel2.add(l_genero);
                painel2.add(genero);
                painel2.add(l_paginas);
                painel2.add(paginas);
                painel4.add(l_descricao);
                painel4.add(descricao);

            }
        });
    }

    public void Imagem(ActionEvent e)
    {
        arquivo = new JFileChooser();
        arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("imagem","jpg","jpeg","png");

        arquivo.setFileFilter(filtro);
        int retorno = arquivo.showOpenDialog(this);
       File file = arquivo.getSelectedFile();

        if(retorno == JFileChooser.APPROVE_OPTION) {

            try {
                image = ImageIO.read(file);

                l_imagem2.setPreferredSize(new Dimension(300,200));
                l_imagem2.setIcon(new ImageIcon(image.getScaledInstance(300,200, Image.SCALE_DEFAULT)));
                l_imagem2.repaint();
            } catch (IOException ex) {

                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void Cadastro(ActionEvent e)
    {
        Cadastrar.CadastrarAdmin();
    }
}