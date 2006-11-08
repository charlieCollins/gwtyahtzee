package com.totsp.gwt.yahtzee.client;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

public class ScoreRowPanel extends HorizontalPanel
{
    public final Label label;
    public final Label value;
    public boolean chosen;

    // scoreTypes are 1-13
    // 1s, 2s, 4s, 4s, 5s, 6s, 3k, 4k, fh, ss, ls, y, c
    public ScoreRowPanel(String scoreLabel, final int scoreType, final YahtzeeController controller)
    {
        Grid grid = new Grid(1, 2);

        label = new Label(scoreLabel);
        label.setStyleName("scoreLabel");

        value = new Label("");
        value.setStyleName("scoreValue");

        grid.setWidget(0, 0, label);
        grid.setWidget(0, 1, value);
        this.add(grid);

        value.addMouseListener(new MouseListener() {

            public void onMouseDown(Widget sender, int i, int j)
            {
            }

            public void onMouseUp(Widget sender, int i, int j)
            {
            }

            public void onMouseMove(Widget sender, int i, int j)
            {
            }

            public void onMouseEnter(Widget sender)
            {
                if (!chosen)
                {
                    controller.displayPossibleScore(scoreType, value);
                }
            }

            public void onMouseLeave(Widget sender)
            {
                if (!chosen)
                {
                    value.setText("");
                }
            }
        });

        value.addClickListener(new ClickListener() {

            public void onClick(Widget sender)
            {
                if (!chosen)
                {
                    chosen = true;                    
                    value.setStyleName("scoreValue scoreValue-Chosen");
                    controller.chooseScore(scoreType, Integer.parseInt(value.getText()));
                }
            }
        });;

        controller.addChangeListener(new GenericChangeListener() {

            public void onChange(YahtzeeData data)
            {
                if (data.scoresSelected == 0)
                {
                    value.setText("");
                    chosen = false;
                    value.setStyleName("scoreValue"); 
                }
                else if (chosen)
                {
                   // nothing
                }                
                else if (data.currentRoll == 0)
                {
                    value.setText("");
                    chosen = false;
                    value.setStyleName("scoreValue");                    
                }
            }
        });
    }    
}