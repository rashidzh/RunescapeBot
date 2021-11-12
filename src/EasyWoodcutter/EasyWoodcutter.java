package EasyWoodcutter;


import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.utilities.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;



import java.awt.*;

@ScriptManifest(
        name = "Easy Woodcutter",
        description = "A script which cuts any tree down and banks at the nearest bank",
        author = "Hejir Rashidzadeh",
        version = 1.0,
        category = Category.WOODCUTTING,
        image = "")



public class EasyWoodcutter extends AbstractScript {

    private Image mainPaint = getImage("http://i.imgur.com/qbyHTS8.png");
    private int logsCut;
    private Timer timeRan;

    private Image getImage(String url){
        try {
            return ImageIO.read(new URL(url));
        }catch (IOException e){
            log("no image found");
            return null;
        }
    }


    @Override
    public void onStart(){
        MainFrame.main();
        timeRan = new Timer();
        log("Hello");
    }

    @Override
    public int onLoop() {

        // Creating an object called tree of class GameObject, we then call the method GameObjects.closest() which is a method with
        // return type static GameObject {aka an object for its return type}. This is then set equal to our tree which is of type GameObject.

        GameObject tree = GameObjects.closest("Tree");
        Player myPlayer = getLocalPlayer();

        // We then call the method interact() on our tree object which is a method of the class interactable of which GameObject extends.
        // It returns a boolean to let us know if we were successful or not
        if (!Inventory.isFull() && tree.interact("Chop down")){
            int countLog = Inventory.count("Logs");
            /** The first parameter of sleepWhile() is an interface, therefore you can use a lambda statement to pass the parameters to the
             *  interface function, which in this case is verify() **/
            MethodProvider.sleepWhile(() -> tree.exists(),Calculations.random(8000,12000));

        }

        if (Inventory.isFull()){
            if (Bank.openClosest()) {
                // If this returns true, that means the bank is open and ready
                // You can see other things you can do now below
                if(Bank.depositAllItems()){
                    sleep(500,1000);
                    Bank.close();

                }

            } else {
                // If this returns false, that means the client is still walking
                // or interacting with the bank
                return 2500;
            }
        }

        MethodProvider.log(myPlayer.getAnimation());
        return Calculations.random(220,600);
    }


    @Override
    public void onExit() {
        super.onExit();
    }

    @Override
    public void onPaint(Graphics g) {
        super.onPaint(g);
        Font font = new Font("Times new roamn", Font.PLAIN, 19);
        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawImage(mainPaint, 121, 340, null);

        g.drawString("" + timeRan.formatTime(), 121, 371);

        g.drawString("" + logsCut, 121, 400);

        g.drawString("" + logsCut * (int)(3600000D / (timeRan.elapsed())),121, 430);
    }

}