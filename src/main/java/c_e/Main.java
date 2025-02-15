package c_e;

import javax.swing.*;

class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            new Login().Logar();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSÍVEL INICIAR O PROGRAMA!");
            System.out.println(t.getMessage());
        }
    }
}