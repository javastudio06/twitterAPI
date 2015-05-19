package devoriginal;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PINScreen extends JDialog implements ActionListener  {

    JLabel labelName;
    JLabel labelPass;
    JTextField textName;
    JPasswordField passField;
    JButton okButton;
    JButton cancelButton;

    JDialog dialog;

    public PINScreen() {
        JPanel panelOne = new JPanel();
        labelName = new JLabel("PIN");
        textName = new JTextField(32);
        panelOne.add(labelName);
        panelOne.add(textName);


        JPanel panelThree = new JPanel();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        panelThree.add(okButton);
        panelThree.add(cancelButton);

        dialog = new JDialog();
        dialog.setResizable(false);
        dialog.getContentPane().add(panelOne);
        dialog.getContentPane().add(panelThree);
        dialog.setTitle("Please Enter The PIN Number On The Web Page");
        dialog.getContentPane().setLayout(new FlowLayout());
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(null); // place in center of screen
        dialog.setModal(true);
        dialog.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            dialog.dispose();
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }

    public String getPIN() {
        return textName.getText();
    }
	
}
