package EasyFighter;


import EasyWoodcutter.MainFrame;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;

import java.awt.*;

@ScriptManifest(
        name = "Easy Fighter",
        description = "A script which allows you to automate fighting any monster and deposit any items they may drop in the bank",
        author = "Hejir Rashidzadeh",
        version = 1.0,
        category = Category.COMBAT,
        image = "")


public class EasyFighter extends AbstractScript {

    @Override
    public void onStart(){
        MainFrame.main();
        log("Hello");
    }

    @Override
    public int onLoop() {
        /**GameObject tree = GameObjects.closest(gameObject -> gameObject !=null && gameObject.getName().equals("Tree"));
        GameObject tree = GameObjects.closest(new Filter<GameObject>() {
            @Override
            public boolean match(GameObject gameObject) {
                return gameObject !=null && gameObject.getName().equals("Tree");
            }
        });

        MethodProvider.sleepUntil(() -> Inventory.count("Log") > countLog,600);
        MethodProvider.sleepUntil(new Condition() {
            @Override
            public boolean verify() {
                return false;
            }
        }; */


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
    public void onPaint(Graphics graphics) {
        super.onPaint(graphics);
    }
}