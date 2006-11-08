package com.totsp.gwt.yahtzee.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class ButtonRoll extends Button
{

    public ButtonRoll(final String label, final ButtonDie die1, final ButtonDie die2, final ButtonDie die3, final ButtonDie die4, final ButtonDie die5, final YahtzeeController controller)
    {
        super(label);
        setStyleName("roll-Button");

        this.addClickListener(new ClickListener() {            
                public void onClick(Widget sender) {
                    controller.processRoll(die1, die2, die3, die4, die5);                    
                }
            });

        controller.addChangeListener(new GenericChangeListener() {

            public void onChange(YahtzeeData data)
            {
                if (controller.data.scoresSelected == 13)
                {
                    setStyleName("roll-Button roll-Button-Disabled");
                    setEnabled(false);
                }
                else if (controller.data.currentRoll == 3)
                {
                    setStyleName("roll-Button roll-Button-Disabled");
                    setEnabled(false);
                }
                else
                {
                    setStyleName("roll-Button");
                    setEnabled(true);
                }
                
            }
        });
    }
}