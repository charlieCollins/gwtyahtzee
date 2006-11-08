package com.totsp.gwt.yahtzee.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class ButtonDie extends Button
{

    public boolean clicked;

    public ButtonDie(final int die, YahtzeeController controller)
    {
        super();
        setStyleName("die-Button");

        this.addClickListener(new ClickListener() {

            public void onClick(Widget sender)
            {
                if (clicked)
                {
                    clicked = false;
                    String styleName = getStyleName();
                    setStyleName(styleName.substring(0, styleName.lastIndexOf(" ")));
                }
                else
                {
                    clicked = true;
                    setStyleName(getStyleName() + " die-Button-Clicked");
                }
            }
        });

        controller.addChangeListener(new GenericChangeListener() {

            public void onChange(YahtzeeData data)
            {
                if (data.currentRoll == 0)
                {
                    setText("");
                    setStyleName("die-Button");
                    clicked = false;
                }
                else
                {
                    if (!clicked)
                    {
                        switch (die)
                        {
                            case 1: {
                                setStyleName("die-Button die-Button-" + data.die1);
                                break;
                            }
                            case 2: {
                                setStyleName("die-Button die-Button-" + data.die2);
                                break;
                            }
                            case 3: {
                                setStyleName("die-Button die-Button-" + data.die3);
                                break;
                            }
                            case 4: {
                                setStyleName("die-Button die-Button-" + data.die4);
                                break;
                            }
                            case 5: {
                                setStyleName("die-Button die-Button-" + data.die5);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
}