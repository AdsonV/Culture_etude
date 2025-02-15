package c_e;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static c_e.Conexao.conn2;

public class Deletar {

    public static void Delete()
    {
        try{
            Conexao.conectar2();
            Statement st = conn2.createStatement();
            st.executeQuery("SELECT * FROM filme WHERE nome LIKE " + "'%" + Listar.jtable.getValueAt(Listar.jtable.getSelectedRow(), 1) + "%'");
            ResultSet r = st.getResultSet();

            Statement st1 = conn2.createStatement();
            st1.executeQuery("SELECT * FROM livro WHERE nome LIKE " + "'%" + Listar.jtable.getValueAt(Listar.jtable.getSelectedRow(), 1) + "%'");
            ResultSet r1 = st1.getResultSet();

            Statement st4 = conn2.createStatement();
            st4.executeQuery("SELECT * FROM carrinho WHERE nome LIKE " + "'%"+Listar.jtable.getValueAt(Listar.jtable.getSelectedRow(),1) + "%'");
            ResultSet r4 = st4.getResultSet();

            if(r.next() && Listar.jtable.getSelectedRow() != -1)
            {
                   Cadastrar.index = r.getInt("id");

                   Statement st2 = conn2.createStatement();
                   st2.execute("DELETE FROM filme WHERE nome LIKE " + "'%" + Listar.jtable.getValueAt(Listar.jtable.getSelectedRow(), 1) + "%'");
                   JOptionPane.showMessageDialog(null,"FILME EXCLUÍDO COM SUCESSO!");

                    Statement st3 = conn2.createStatement();
                    st3.executeQuery("SELECT * FROM filme WHERE id >" + Cadastrar.index);
                    ResultSet r3 = st3.getResultSet();

                while (r3.next()) {

                    PreparedStatement st7 = conn2.prepareStatement("UPDATE filme SET id =? WHERE id =" + r3.getInt("id"));
                    st7.setInt(1, r3.getInt("id") - 1);
                    st7.executeUpdate();
                }

                Listar.janela3.dispose();
                Listar.janela.setVisible(true);
            }
            else if(r1.next() && Listar.jtable.getSelectedRow() != -1)
            {
                Cadastrar.index = r1.getInt("id");

                Statement st2 = conn2.createStatement();
                st2.execute("DELETE FROM livro WHERE nome LIKE " + "'%" + Listar.jtable.getValueAt(Listar.jtable.getSelectedRow(), 1) + "%'");
                JOptionPane.showMessageDialog(null,"LIVRO EXCLUÍDO COM SUCESSO!");

                Statement st3 = conn2.createStatement();
                st3.executeQuery("SELECT * FROM livro WHERE id >" + Cadastrar.index);
                ResultSet r3 = st3.getResultSet();

                while (r3.next()) {

                    PreparedStatement st7 = conn2.prepareStatement("UPDATE livro SET id =? WHERE id =" + r3.getInt("id"));
                    st7.setInt(1, r3.getInt("id") - 1);
                    st7.executeUpdate();
                }
                Listar.janela3.dispose();
                Listar.janela.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null,"SELECIONE UM ÍTEM PARA EXCLUIR!","SELECIONAR",JOptionPane.INFORMATION_MESSAGE);
            }

            Cadastrar.index = r4.getInt("id");

            Statement st5 = conn2.createStatement();
            st5.execute("DELETE FROM carrinho WHERE nome LIKE " + "'%" + Listar.jtable.getValueAt(Listar.jtable.getSelectedRow(), 1) + "%'");
            JOptionPane.showMessageDialog(null,"FILME EXCLUÍDO COM SUCESSO!");

            Statement st6 = conn2.createStatement();
            st6.executeQuery("SELECT * FROM carrinho WHERE id >" + Cadastrar.index);
            ResultSet r6 = st6.getResultSet();

            while (r6.next()) {

                PreparedStatement st7 = conn2.prepareStatement("UPDATE carrinho SET id =? WHERE id =" + r6.getInt("id"));
                st7.setInt(1, r6.getInt("id") - 1);
                st7.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"ERRO AO DELETAR O ITEM!","ERRO",JOptionPane.ERROR_MESSAGE);
        }
        Listar.janela = new JFrame();
        Listar.b1 = new JButton();
        Listar.janela3 = new JFrame();
        Listar.janela.setVisible(true);
        Conexao.desconectar2();
    }
}