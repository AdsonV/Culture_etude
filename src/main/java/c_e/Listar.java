package c_e;

import com.mysql.cj.jdbc.Blob;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static c_e.Consulta.ConsultaUsuario;
import static c_e.Cadastrar.lista_filmes;
import static c_e.Conexao.*;
import static c_e.Login.Senha;

public class Listar extends JFrame implements ActionListener {

    static JFrame janela = new JFrame();
    static JFrame janela2 = new JFrame();
    static JFrame janela3 = new JFrame();
    JPanel jp = new JPanel();
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    JPanel jp3 = new JPanel();
    JPanel jp4 = new JPanel();
    JPanel painel = new JPanel();
    JPanel painel1 = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel painel3 = new JPanel();
    JPanel painel4 = new JPanel();
    JPanel painel5 = new JPanel();
    JLabel f = new JLabel("FILTRAGEM");
    ButtonGroup bg = new ButtonGroup();
    ButtonGroup bg2 = new ButtonGroup();
    JRadioButton liv = new JRadioButton("LIVRO");
    JRadioButton fil = new JRadioButton("FILME");
    JRadioButton gen2 = new JRadioButton("GÊNERO");
    JRadioButton aut = new JRadioButton("AUTOR");
    JRadioButton dir = new JRadioButton("DIRETOR");
    JRadioButton dat = new JRadioButton("DATA");
    JRadioButton tem = new JRadioButton("TEMA");
    JTextField pesquisar = new JTextField();
    static JButton b = new JButton("Ver carrinho de compras");
    static JButton b1 = new JButton("DELETAR");
    static JButton b2 = new JButton("Adicionar ao carrinho");
    static JButton b3 = new JButton("SAIR");
    static JButton b2admin = new JButton("ADICIONAR ITEM");
    JLabel lblFoto = new JLabel();
    static JLabel lblnf = new JLabel();
    JLabel lblfdir = new JLabel();
    JLabel lblfdata = new JLabel();
    JLabel lblfgen = new JLabel();
    JLabel lblfdur = new JLabel();
    JTextArea lbldesc = new JTextArea();
    JLabel l = new JLabel("FILME");
    JLabel l1 = new JLabel("DIRETOR");
    JLabel l2 = new JLabel("DATA");
    JLabel l3 = new JLabel("GÊNERO");
    JLabel l4 = new JLabel("DURAÇÃO");
    final static String[] colunas = {"TIPO","NOME"};
    static DefaultTableModel tabela = new DefaultTableModel(colunas,0);
    static JTable jtable = new JTable(tabela);
    static JScrollPane j  = new JScrollPane();
    JPanel painelcar = new JPanel();
    JPanel painel1car = new JPanel();
    JPanel painel2car = new JPanel();
    static JButton bcar = new JButton();
    JLabel lcar = new JLabel("SEU CARRINHO DE COMPRAS:");
    final static String[] colunas2 = {"TIPO","NOME"};
    static DefaultTableModel tabelacar = new DefaultTableModel(colunas2,0);
    static JTable jtablecar = new JTable(tabela);
    JScrollPane jcar  = new JScrollPane();


    @Override
    public void actionPerformed(ActionEvent e) {

        String usuario = Login.Login.getText().trim();
        String senha = Senha.getText().trim();

        try {

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA TODOS OS DADOS!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
        else {

            Senha = new JPasswordField();
            Senha.setText(null);

            Janela();

            if (usuario.equals("administrador") && senha.equals("admin0123")) {

                Conexao.conectar2();

                janela.add(painel);

                b2admin.setFont(new Font("Calibri", Font.PLAIN, 30));
                b2admin.setPreferredSize(new Dimension(360, 70));

                painel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
                painel4.setPreferredSize(new Dimension(600, 100));
                painel4.add(b2admin);

                janela.add(painel4);
                janela.add(painel1);
                janela.add(painel2);
                janela.add(painel3);
                janela.add(j);

                ListarFilme(e);
                ListarLivro(e);

                b2admin.addActionListener(new Admin());
                Filtrar(e);
                Pesquisar(e);
                janela.setVisible(true);
                desconectar2();
            }
            else if (ConsultaUsuario(usuario, senha)) {

                JOptionPane.showMessageDialog(null, "SEJA BEM VINDO " + usuario.toUpperCase());

                Conexao.conectar();

                janela.add(painel);

                b.setFont(new Font("Calibri", Font.PLAIN, 30));
                b.setPreferredSize(new Dimension(360, 70));

                painel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
                painel4.setPreferredSize(new Dimension(600, 100));
                painel4.add(b);

                janela.add(painel4);
                janela.add(painel1);
                janela.add(painel2);
                janela.add(painel3);
                janela.add(j);

                ListarFilme(e);
                ListarLivro(e);

                b.addActionListener(this::Carrinho);
                Filtrar(e);
                Pesquisar(e);
                janela.setVisible(true);
                desconectar();
            }
            else {
                JOptionPane.showMessageDialog(null, "USUÁRIO NÃO ENCONTRADO!", "ERRO", JOptionPane.ERROR_MESSAGE);
                Login.Logar();
            }
        }
        }
        catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL ESTABELECER A CONEXÃO", "ERRO", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        lista_filmes.clear();
        Cadastrar.lista_usuario.clear();
    }
    public void Pesquisar(ActionEvent e) {
        pesquisar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                try {
                    Conexao.conectar();

                    if (arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !liv.isSelected() && fil.isSelected()
                            && !gen2.isSelected() && !dir.isSelected() && !dat.isSelected() & !tem.isSelected()) {

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM filme WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"FILME",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 30));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    else if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !liv.isSelected() && fil.isSelected() && dir.isSelected())
                    {
                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM filme WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"FILME",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 30));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    else if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !liv.isSelected() && fil.isSelected() && gen2.isSelected())
                    {

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM filme WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"FILME",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 30));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    else if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !liv.isSelected() && fil.isSelected() && dat.isSelected())
                    {

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM filme WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"FILME",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 30));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    else if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !liv.isSelected() && fil.isSelected() && tem.isSelected())
                    {

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM filme WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"FILME",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 20));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    Conexao.desconectar();
                }
                catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"ERRO!");
                    System.out.println(e.getMessage());
                }
            }
        });

//==================================================================================================================================================================================================//
                    pesquisar.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent arg0) {
                            try {
                                Conexao.conectar();

                    if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !fil.isSelected() && liv.isSelected()
                            && !gen2.isSelected() && !aut.isSelected() && !dat.isSelected() & !tem.isSelected())
                    {

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM livro WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"LIVRO",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 30));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    else if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !fil.isSelected() && liv.isSelected() && aut.isSelected())
                    {

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM livro WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"LIVRO",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 20));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    else if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !fil.isSelected() && liv.isSelected() && gen2.isSelected())
                    {

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM livro WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"LIVRO",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 20));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    else if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !fil.isSelected() && liv.isSelected() && dat.isSelected())
                    {

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM livro WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"LIVRO",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 20));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    else if(arg0.getKeyCode() == KeyEvent.VK_ENTER && !pesquisar.getText().trim().isEmpty() && !fil.isSelected() && liv.isSelected() && tem.isSelected())
                    {
                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM livro WHERE nome LIKE " + "'%" + pesquisar.getText().trim() + "%'");
                        ResultSet r1 = st1.getResultSet();

                        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
                        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
                        JTableHeader cabecalho = jtable.getTableHeader();
                        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
                        tabela.setRowCount(0);

                        while (r1.next()) {

                            Object[] linha = new Object[]{"FILME",r1.getString("nome")};

                            tabela.addRow(linha);
                            jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                            jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                            jtable.setFont(new Font("Arial", Font.PLAIN, 30));
                            jtable.setRowHeight(50);
                            jtable.setModel(tabela);
                            tabela.fireTableDataChanged();
                        }
                    }
                    else if (arg0.getKeyCode() == KeyEvent.VK_ENTER && !fil.isSelected() && !liv.isSelected())
                    {
                        JOptionPane.showMessageDialog(null,"POR FAVOR SELECIONE SE É FILME OU LIVRO.","SELECIONAR",JOptionPane.INFORMATION_MESSAGE);
                    }

                    Conexao.desconectar();
                }
                            catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"ERRO!");
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    public void Filtrar(ActionEvent e) {
        fil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);

                    janela.dispose();
                    janela.setVisible(true);

                    painel2.removeAll();
                    painel2.add(gen2);
                    painel2.add(dir);
                    painel2.add(dat);
                    painel2.add(tem);

            }
        });

        liv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);

                janela.dispose();
                janela.setVisible(true);

                painel2.removeAll();
                painel2.add(gen2);
                painel2.add(aut);
                painel2.add(dat);
                painel2.add(tem);

            }
        });
    }
    public void Janela() {
        Login.janela.dispose();

        janela = new JFrame();
        janela.setTitle("CULTURE_ETUDE");
        janela.setSize(1500, 800);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        janela.setLocationRelativeTo(null);

        f.setFont(new Font("Arial", Font.BOLD, 40));

        painel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        painel.setPreferredSize(new Dimension(840, 60));
        painel.add(f);

        painel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painel1.setPreferredSize(new Dimension(120, 100));

        fil.setFont(new Font("Arial", Font.PLAIN, 18));
        fil.setHorizontalTextPosition(SwingConstants.LEFT);
        liv.setFont(new Font("Arial", Font.PLAIN, 18));
        liv.setHorizontalTextPosition(SwingConstants.LEFT);

        bg.add(liv);
        bg.add(fil);

        painel1.add(fil);
        painel1.add(liv);

        painel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painel2.setPreferredSize(new Dimension(1540, 50));

        gen2.setFont(new Font("Calibri", Font.PLAIN, 20));
        gen2.setForeground(Color.red);
        gen2.setHorizontalTextPosition(SwingConstants.LEFT);
        aut.setFont(new Font("Calibri", Font.PLAIN, 20));
        aut.setForeground(Color.red);
        aut.setHorizontalTextPosition(SwingConstants.LEFT);
        dir.setFont(new Font("Calibri", Font.PLAIN, 20));
        dir.setForeground(Color.red);
        dir.setHorizontalTextPosition(SwingConstants.LEFT);
        dat.setFont(new Font("Calibri", Font.PLAIN, 20));
        dat.setForeground(Color.red);
        dat.setHorizontalTextPosition(SwingConstants.LEFT);
        tem.setFont(new Font("Calibri", Font.PLAIN, 20));
        tem.setForeground(Color.red);
        tem.setHorizontalTextPosition(SwingConstants.LEFT);

        bg2.add(gen2);
        bg2.add(aut);
        bg2.add(dir);
        bg2.add(dat);
        bg2.add(tem);

        painel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painel3.setPreferredSize(new Dimension(1540, 50));

        pesquisar.setPreferredSize(new Dimension(500, 40));
        pesquisar.setFont(new Font("Calibri", Font.PLAIN, 30));
        painel3.add(pesquisar);

        painel5 = new JPanel(new FlowLayout(FlowLayout.LEFT,60,5));
        painel5.setPreferredSize(new Dimension(600, 400));

        j.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        j.setViewportView(jtable);

        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader cabecalho = jtable.getTableHeader();
        cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
        tabela.setRowCount(0);
        jtable.getColumnModel().getColumn(0).setCellRenderer(cellRender);
        jtable.getColumnModel().getColumn(1).setCellRenderer(cellRender);

        painel5.add(j);
    }
    public void ListarFilme(ActionEvent e) throws SQLException {
        ListSelectionModel model = jtable.getSelectionModel();

        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(jtable.getValueAt(jtable.getSelectedRow(),0).equals("FILME")) {

                    janela2.setTitle("CULTURE_ETUDE");
                    janela2.setSize(800, 800);
                    janela2.setLocationRelativeTo(null);
                    janela2.setLayout(new FlowLayout(FlowLayout.LEFT, 10,20));

                    jp.setPreferredSize(new Dimension(350, 400));
                    jp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                    janela2.add(jp);

                    jp4.setPreferredSize(new Dimension(50, 200));
                    jp4.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    janela2.add(jp4);

                    jp1.setPreferredSize(new Dimension(320,400));
                    jp1.setLayout(new FlowLayout(FlowLayout.CENTER, 10,15));
                    janela2.add(jp1);

                    jp2.setPreferredSize(new Dimension(700,200));
                    jp2.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    janela2.add(jp2);

                    jp3.setPreferredSize(new Dimension(600,100));
                    jp3.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
                    janela2.add(jp3);

                    try {
                        Conexao.conectar();

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM filme WHERE nome LIKE " + "'%" + jtable.getValueAt(jtable.getSelectedRow(), 1) + "%'");
                        ResultSet r1 = st1.getResultSet();

                        while (r1.next()) {

                            Blob blob = (Blob) r1.getBlob("imagem");
                            byte[] img = blob.getBytes(1, (int) blob.length());
                            BufferedImage imagem = null;

                            imagem = ImageIO.read(new ByteArrayInputStream(img));

                            ImageIcon icone = new ImageIcon(imagem);
                            Icon foto = new ImageIcon(icone.getImage().getScaledInstance(350, 400, Image.SCALE_SMOOTH));
                            lblFoto.setIcon(foto);
                            jp.add(lblFoto);

                            l.setForeground(Color.red);
                            l.setFont(new Font("Calibri", Font.ITALIC, 15));

                            l1.setForeground(Color.blue);
                            l1.setFont(new Font("Calibri", Font.ITALIC, 14));

                            l2.setForeground(Color.orange);
                            l2.setFont(new Font("Calibri", Font.ITALIC, 15));

                            l3.setForeground(Color.green);
                            l3.setFont(new Font("Calibri", Font.ITALIC, 14));

                            l4.setForeground(Color.black);
                            l4.setFont(new Font("Calibri", Font.ITALIC, 12));

                            jp4.add(l);
                            jp4.add(l1);
                            jp4.add(l2);
                            jp4.add(l3);
                            jp4.add(l4);

                            lblnf.setText(r1.getString("nome").toUpperCase());
                            lblnf.setFont(new Font("Calibri", Font.BOLD, 50));
                            lblnf.setForeground(Color.red);

                            lblfdir.setText(r1.getString("diretor").toUpperCase());
                            lblfdir.setFont(new Font("Calibri", Font.BOLD, 50));
                            lblfdir.setForeground(Color.BLUE);

                            lblfdata.setText(r1.getString("data"));
                            lblfdata.setFont(new Font("Calibri", Font.BOLD, 50));
                            lblfdata.setForeground(Color.ORANGE);

                            lblfgen.setText(r1.getString("genero").toUpperCase());
                            lblfgen.setFont(new Font("Calibri", Font.BOLD, 50));
                            lblfgen.setForeground(Color.green);

                            lblfdur.setText(r1.getString("duracao"));
                            lblfdur.setFont(new Font("Calibri", Font.BOLD, 50));

                            jp1.add(lblnf);
                            jp1.add(lblfdir);
                            jp1.add(lblfdata);
                            jp1.add(lblfgen);
                            jp1.add(lblfdur);

                            lbldesc.setLineWrap(true);
                            lbldesc.setEditable(false);
                            lbldesc.setText(r1.getString("descricao"));
                            lbldesc.setPreferredSize(new Dimension(700,200));
                            lbldesc.setFont((new Font("Calibri", Font.ITALIC, 40)));
                            jp2.add(lbldesc);

                            b2.setFont(new Font("Calibri", Font.PLAIN, 30));
                            b2.setPreferredSize(new Dimension(360, 70));

                            b1.setFont(new Font("Calibri", Font.PLAIN, 30));
                            b1.setPreferredSize(new Dimension(200, 70));

                            if(Login.Login.getText().equals("administrador") && Senha.getText().equals("admin0123")) {
                                jp3.add(b1);
                            }
                            else {
                                jp3.add(b2);
                            }

                            b3.setFont(new Font("Calibri", Font.PLAIN, 30));
                            b3.setPreferredSize(new Dimension(200, 70));
                            jp3.add(b3);

                            janela2.setVisible(true);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                    Conexao.desconectar();
                }
        });
            b1.addActionListener(this::Del);
            b2.addActionListener(this::Addf);
            b3.addActionListener(this::Sair);

    }
    public void ListarLivro(ActionEvent e) {
        ListSelectionModel model = jtable.getSelectionModel();

        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(jtable.getValueAt(jtable.getSelectedRow(),0).equals("LIVRO")) {

                    janela2.setTitle("CULTURE_ETUDE");
                    janela2.setSize(800, 800);
                    janela2.setLocationRelativeTo(null);
                    janela2.setLayout(new FlowLayout(FlowLayout.LEFT, 10,20));

                    jp.setPreferredSize(new Dimension(350, 400));
                    jp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                    janela2.add(jp);

                    jp4.setPreferredSize(new Dimension(50, 200));
                    jp4.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    janela2.add(jp4);

                    jp1.setPreferredSize(new Dimension(300,400));
                    jp1.setLayout(new FlowLayout(FlowLayout.CENTER, 10,15));
                    janela2.add(jp1);

                    jp2.setPreferredSize(new Dimension(700,200));
                    jp2.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                    janela2.add(jp2);

                    jp3.setPreferredSize(new Dimension(600,100));
                    jp3.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
                    janela2.add(jp3);

                    try {
                        Conexao.conectar();

                        Statement st1 = conn.createStatement();
                        st1.executeQuery("SELECT * FROM livro WHERE nome LIKE " + "'%" + jtable.getValueAt(jtable.getSelectedRow(), 1) + "%'");
                        ResultSet r1 = st1.getResultSet();

                        while (r1.next()) {

                            Blob blob = (Blob) r1.getBlob("imagem");
                            byte[] img = blob.getBytes(1, (int) blob.length());
                            BufferedImage imagem = null;

                            imagem = ImageIO.read(new ByteArrayInputStream(img));

                            ImageIcon icone = new ImageIcon(imagem);
                            Icon foto = new ImageIcon(icone.getImage().getScaledInstance(350, 400, Image.SCALE_SMOOTH));
                            lblFoto.setIcon(foto);
                            jp.add(lblFoto);

                            l.setForeground(Color.red);
                            l.setText("LIVRO");
                            l.setFont(new Font("Calibri", Font.ITALIC, 17));

                            l1.setForeground(Color.blue);
                            l1.setText("AUTOR");
                            l1.setFont(new Font("Calibri", Font.ITALIC, 15));

                            l2.setForeground(Color.orange);
                            l2.setFont(new Font("Calibri", Font.ITALIC, 15));

                            l3.setForeground(Color.green);
                            l3.setFont(new Font("Calibri", Font.ITALIC, 14));

                            l4.setForeground(Color.black);
                            l4.setText("PÁGINAS");
                            l4.setFont(new Font("Calibri", Font.ITALIC, 12));

                            jp4.add(l);
                            jp4.add(l1);
                            jp4.add(l2);
                            jp4.add(l3);
                            jp4.add(l4);

                            lblnf.setText(r1.getString("nome").toUpperCase());
                            lblnf.setFont(new Font("Calibri", Font.BOLD, 50));
                            lblnf.setForeground(Color.red);

                            lblfdir.setText(r1.getString("autor").toUpperCase());
                            lblfdir.setFont(new Font("Calibri", Font.BOLD, 50));
                            lblfdir.setForeground(Color.BLUE);

                            lblfdata.setText(r1.getString("data"));
                            lblfdata.setFont(new Font("Calibri", Font.BOLD, 50));
                            lblfdata.setForeground(Color.ORANGE);

                            lblfgen.setText(r1.getString("genero").toUpperCase());
                            lblfgen.setFont(new Font("Calibri", Font.BOLD, 50));
                            lblfgen.setForeground(Color.green);

                            lblfdur.setText(r1.getString("paginas"));
                            lblfdur.setFont(new Font("Calibri", Font.BOLD, 50));

                            jp1.add(lblnf);
                            jp1.add(lblfdir);
                            jp1.add(lblfdata);
                            jp1.add(lblfgen);
                            jp1.add(lblfdur);

                            lbldesc.setLineWrap(true);
                            lbldesc.setEditable(false);
                            lbldesc.setText(r1.getString("descricao"));
                            lbldesc.setPreferredSize(new Dimension(700,200));
                            lbldesc.setFont((new Font("Calibri", Font.ITALIC, 40)));
                            jp2.add(lbldesc);

                            b2.setFont(new Font("Calibri", Font.PLAIN, 30));
                            b2.setPreferredSize(new Dimension(360, 70));
                            jp3.add(b2);

                            b3.setFont(new Font("Calibri", Font.PLAIN, 30));
                            b3.setPreferredSize(new Dimension(200, 70));
                            jp3.add(b3);

                            janela2.setVisible(true);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                Conexao.desconectar();
            }
        });

        b2.addActionListener(this::Addl);
        b3.addActionListener(this::Sair);
    }
    public void Sair(ActionEvent e) {
        b3 = new JButton();
        janela2.dispose();
    }
    public void Addf(ActionEvent e) {
        try {
            Cadastrar.AddCarFilme(e);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void Addl(ActionEvent e) {
        try {
            Cadastrar.AddCarLivro(e);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void Carrinho(ActionEvent e) {

        try {

            Conexao.conectar();

            janela.dispose();

            janela3.setTitle("CULTURE_ETUDE");
            janela3.setSize(1500, 800);
            janela3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            janela3.setLocationRelativeTo(null);

            painelcar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            painelcar.setPreferredSize(new Dimension(janela.getWidth(), 60));
            janela3.add(painelcar);

            bcar.setText("VOLTAR");
            bcar.setFont(new Font("Arial", Font.PLAIN, 30));
            bcar.setPreferredSize(new Dimension(200, 35));
            painelcar.add(bcar);

            lcar.setFont(new Font("Calibri", Font.BOLD, 60));

            painel1car = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            painel1car.setPreferredSize(new Dimension(janela.getWidth(), 100));
            painel1car.add(lcar);
            janela3.add(painel1car);

            jcar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jcar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jcar.setViewportView(jtablecar);

            painel2car = new JPanel(new GridLayout(1, 1));
            painel2car.setPreferredSize(new Dimension(1100, 600));
            painel2car.add(jcar);
            janela3.add(painel2car);

            DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
            cellRender.setHorizontalAlignment(SwingConstants.CENTER);
            JTableHeader cabecalho = jtablecar.getTableHeader();
            cabecalho.setFont(new Font("Calibri", Font.BOLD, 20));
            tabelacar.setRowCount(0);

            Statement st1 = conn.createStatement();
            st1.executeQuery("SELECT * FROM carrinho WHERE user LIKE "+"'%"+Login.Login.getText()+"%'");
            ResultSet r1 = st1.getResultSet();

            while (r1.next()) {

                Object[] linha = new Object[]{"FILME", r1.getString("nome")};

                tabelacar.addRow(linha);
                jtablecar.getColumnModel().getColumn(0).setCellRenderer(cellRender);
                jtablecar.getColumnModel().getColumn(1).setCellRenderer(cellRender);
                jtablecar.setFont(new Font("Arial", Font.PLAIN, 25));
                jtablecar.setRowHeight(30);
                jtablecar.setModel(tabelacar);
                jtablecar.setEnabled(false);
                tabelacar.fireTableDataChanged();
            }

            bcar.addActionListener(this::Voltar);
            janela3.setVisible(true);

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        Conexao.desconectar();
    }
    public void Voltar(ActionEvent c) {
        bcar = new JButton();
        janela3.dispose();
        janela3 = new JFrame();
        janela.setVisible(true);
    }
    public void Del(ActionEvent e){
        Deletar.Delete();
    }
}