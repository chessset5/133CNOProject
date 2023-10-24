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
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.accelerate();
                            break;
                        case 'b':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.brake();
                            break;
                        case 'l':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.left();
                            break;
                        case 'r':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.right();
                            break;
                        case 'c':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.setFoodConsumptionRate(15);
                            break;

                        case '1':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.collideFlag1();
                            break;
                        case '2':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.collideFlag2();
                            break;
                        case '3':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.collideFlag3();
                            break;
                        case '4':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.collideFlag4();
                            break;

                        case 'f':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.collideFoodStation();
                            break;
                        case 'g':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.collideSpider();
                            break;
                        case 't':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.tick();
                            break;
                        case 'd':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                            gw.display();
                            break;
                        case 'm':
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
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
                        default:
                            if (gw.inExit()) {
                                gw.exit();
                                break;
                            }
                    } // switch
            } // actionPerformed
        } // new ActionListener()
        ); // addActionListener
    } // Play
}