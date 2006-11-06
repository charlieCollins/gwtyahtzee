package com.totsp.gwt.yahtzee.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class YahtzeeWidget extends VerticalPanel {
   
    public YahtzeeWidget() {
        super();

        // instantiate the controller
        final YahtzeeController controller = new YahtzeeController(); 
        
        // five die at top        
        final ButtonDie die1 = new ButtonDie(1, controller);        
        final ButtonDie die2 = new ButtonDie(2, controller);        
        final ButtonDie die3 = new ButtonDie(3, controller);        
        final ButtonDie die4 = new ButtonDie(4, controller);        
        final ButtonDie die5 = new ButtonDie(5, controller);        
        
        HorizontalPanel diePanel = new HorizontalPanel();
        diePanel.add(die1);
        diePanel.add(die2);
        diePanel.add(die3);
        diePanel.add(die4);
        diePanel.add(die5);        
        
        // roll dice button
        final ButtonRoll roll = new ButtonRoll("roll dice", die1, die2, die3, die4, die5, controller);
        
        // score grid
        Grid scoreGrid = new Grid(7, 2);
        scoreGrid.setStyleName("scoreGrid");
        
        // upper section
        ScoreRowPanel onesScore = new ScoreRowPanel("1's", 1, controller);
        scoreGrid.setWidget(0,0, onesScore);
        ScoreRowPanel twosScore = new ScoreRowPanel("2's", 2, controller);
        scoreGrid.setWidget(1,0, twosScore);
        ScoreRowPanel threesScore = new ScoreRowPanel("3's", 3, controller);
        scoreGrid.setWidget(2,0, threesScore);
        ScoreRowPanel foursScore = new ScoreRowPanel("4's", 4, controller);
        scoreGrid.setWidget(3,0, foursScore);
        ScoreRowPanel fivesScore = new ScoreRowPanel("5's", 5, controller);
        scoreGrid.setWidget(4,0, fivesScore);
        ScoreRowPanel sixesScore = new ScoreRowPanel("6's", 6, controller);
        scoreGrid.setWidget(5,0, sixesScore);       
        ScoreRowPanel k3Score = new ScoreRowPanel("3 of a kind", 7, controller);
        scoreGrid.setWidget(0,1, k3Score);
        ScoreRowPanel k4Score = new ScoreRowPanel("4 of a kind", 8, controller);
        scoreGrid.setWidget(1,1, k4Score);
        ScoreRowPanel fhScore = new ScoreRowPanel("full house", 9, controller);
        scoreGrid.setWidget(2,1, fhScore);
        ScoreRowPanel ssScore = new ScoreRowPanel("small straight", 10, controller);
        scoreGrid.setWidget(3,1, ssScore);
        ScoreRowPanel lsScore = new ScoreRowPanel("large straight", 11, controller);
        scoreGrid.setWidget(4,1, lsScore);
        ScoreRowPanel yScore = new ScoreRowPanel("Yahtzee!", 12, controller);
        scoreGrid.setWidget(5,1, yScore);
        ScoreRowPanel cScore = new ScoreRowPanel("chance", 13, controller);
        scoreGrid.setWidget(6,1, cScore);   
        
        // upper sect bonus
        Grid bonusGrid = new Grid(1, 2);
        Label upperBonusLabel = new Label("upper section bonus:");
        upperBonusLabel.setStyleName("bonus-Label");
        final Label upperBonusValue = new Label(); 
        upperBonusValue.setStyleName("bonus-Value"); 
        bonusGrid.setWidget(0, 0, upperBonusLabel);
        bonusGrid.setWidget(0, 1, upperBonusValue);
        scoreGrid.setWidget(6,0, bonusGrid);
        
        // total score
        final HorizontalPanel totalScorePanel = new HorizontalPanel();
        totalScorePanel.setStyleName("totalScore-Panel");
        final Label totalScoreLabel = new Label("total score:");
        totalScoreLabel.setStyleName("totalScore-Label");
        final Label totalScore = new Label();
        totalScore.setHorizontalAlignment(Label.ALIGN_CENTER);
        totalScore.setStyleName("totalScore-Value");
        totalScorePanel.add(totalScoreLabel);
        totalScorePanel.add(totalScore);

        // reset button
        final Button reset = new Button("reset");
        reset.addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    controller.reset();
                }
            });
        reset.setStyleName("reset-Button");        
        
        // debug
        final Label debugLabel = new Label();        
        
        // what to do when data changes
        controller.addChangeListener(new GenericChangeListener() {
            public void onChange(YahtzeeData data) {
                
                // reset
                if (controller.data.scoresSelected == 13)
                {
                    reset.setText("thank you sir may I have another?");
                }
                else
                {
                    reset.setText("reset");
                }
                
                // bonus
                if (data.upperBonus == 35)
                {
                    upperBonusValue.setStyleName("bonus-Value-On");
                    upperBonusValue.setText(String.valueOf(data.upperBonus));                    
                }
                else
                {
                    upperBonusValue.setStyleName("bonus-Value"); 
                    upperBonusValue.setText("");                    
                }
                
                // total score
                controller.setTotalScore(totalScore); 
                
                // debug
                debugLabel.setText(data.toString());                    
            }
        });         
        
        // add components to self
        this.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        this.setStyleName("yahtzee-Widget");
        this.add(diePanel);
        this.add(roll); 
        this.add(scoreGrid);
        this.add(totalScorePanel);
        this.add(reset);   
        //this.add(debugLabel);
    }    
}
