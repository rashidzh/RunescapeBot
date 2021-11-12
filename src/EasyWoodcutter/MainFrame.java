package EasyWoodcutter;

import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.wrappers.interactive.NPC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainFrame extends JFrame{
    private JButton OKButton;
    private JButton clearButton;
    private JLabel Answer;
    private JPanel mainPanel;
    private JComboBox treeList;

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

        treeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTree = treeList.getSelectedItem().toString();
                Answer.setText( selectedTree );
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
