package BasicWoodcutter;

import org.apache.commons.lang3.ArrayUtils;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.wrappers.interactive.NPC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class MainFrame extends JFrame{
    private JButton OKButton;
    private JButton clearButton;
    private JLabel firstName;
    private JTextField tflastName;
    private JLabel lastName;
    private JTextField tffirstName;
    private JLabel Answer;
    private JPanel mainPanel;

    //this is a constructor that initializes parameters for an object of class MainFrame before it even exists
    public MainFrame(){
        setContentPane(mainPanel);
        setTitle("Welcome");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<NPC> localNPC = NPCs.all();
                String[] names = new String[localNPC.size()];

                for(int i=0; i < localNPC.size(); i++) {
                    NPC element = localNPC.get(i);
                    names[i] = element.getName();
                }
                //String firstName =tffirstName.getText();
                //String lastName =tflastName.getText();
                Answer.setText("Welcome " + Arrays.toString(names) );
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Answer.setText("");
            }
        });
    }

    public static void main(String[] args){
        MainFrame myFrame = new MainFrame();
    }

    public static void main() {
        MainFrame myFrame = new MainFrame();
    }
}
