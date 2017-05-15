import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Form extends JFrame implements ActionListener {
    public int lampnumber = 0;
    private JTextField lampstatuswaarde;
    private JButton lampstatusverzenden;
    private JLabel arduinostatus;
    public Form() {
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setSize(200,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel lampstatus = new JLabel("Lampstatus");
        lampstatuswaarde = new JTextField(4);
        lampstatusverzenden = new JButton("Verzenden");
        lampstatusverzenden.addActionListener(this);
        arduinostatus = new JLabel("De lamp staat nu uit");

        add(lampstatus);
        add(lampstatuswaarde);
        add(lampstatusverzenden);
        add(arduinostatus);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == lampstatusverzenden) {
            lampnumber = Integer.parseInt(lampstatuswaarde.getText());
            switch(lampnumber) {
                case 1:
                    ArduinoConnect.writeData("1");

                    arduinostatus.setText("De lamp staat nu aan");
                    break;
                case 2:
                    ArduinoConnect.writeData("0");
                    arduinostatus.setText("De lamp staat nu uit");
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }


}