package com.mycompany.a1;

import com.codename1.ui.Form;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form {
    private GameWorld gw;

    public Game() {
        gw = new GameWorld();
        gw.init();
        play();
    }

    private void play() {
        Label myLabel = new Label("Enter a Command:");
        this.addComponent(myLabel);
        final TextField myTextField = new TextField();
        this.addComponent(myTextField);
        this.show();
        myTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String sCommand = myTextField.getText().toString();
                myTextField.clear();
                if (sCommand.length() != 0)
                    switch (sCommand.charAt(0)) {
                        case 'a':
                            gw.accelerate();
                            break;
                        case 'b':
                            gw.break();
                            break;
                        case 'l':
                            gw.left();
                            break;
                        case 'r':
                            gw.right();
                            break;
                        case 'c':
                            gw.setFoodConusmptionRate();
                            break;

                        case '1':
                            gw.colideFlag1();
                            break;
                        case '2':
                            gw.colideFlag2();
                            break;
                        case '3':
                            gw.colideFlag3();
                            break;
                        case '4':
                            gw.colideFlag4();
                            break;
                            
                        case 'f':
                            gw.colideFoodStation();
                            break;
                        case 'g':
                            gw.colideSpider();
                            break;
                        case 't':
                            gw.tick();
                            break;
                        case 'd':
                            gw.display();
                            break;
                        case 'm':
                            gw.map();
                            break;
                        case 'x':
                            gw.exit();
                            break;
                        case 'y':
                            gw.confirm();
                            break;
                        case 'n':
                            gw.deny();
                            break;
                    } // switch
            } // actionPerformed
        } // new ActionListener()
        ); // addActionListener
    } // Play
}