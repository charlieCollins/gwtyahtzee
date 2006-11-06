package com.totsp.gwt.yahtzee.client;


public class YahtzeeData
{
    public int currentRoll;
    public int die1;
    public int die2;
    public int die3;
    public int die4;
    public int die5;
    public int score1; // 1s
    public int score2; // 2s
    public int score3; // 3s
    public int score4; // 4s
    public int score5; // 5s
    public int score6; // 6s
    public int score7; // 3 kind
    public int score8; // 4 kind
    public int score9; // full house
    public int score10; // sm straight
    public int score11; // lg straight
    public int score12; // yahtzee
    public int score13; // chance
    public int upperBonus; // upper sect (1s-6s) >= 63 bonus 35 
    public int lowerBonus;
    public int totalScore;
    public int scoresSelected;

    public YahtzeeData()
    {
    }  

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("***Data***\n");
        sb.append("die1 = " + this.die1 + "\n");
        sb.append("die2 = " + this.die2 + "\n");
        sb.append("die3 = " + this.die3 + "\n");
        sb.append("die4 = " + this.die4 + "\n");
        sb.append("die5 = " + this.die5 + "\n");
        sb.append("score1 = " + this.score1 + "\n");
        sb.append("score2 = " + this.score2 + "\n");
        sb.append("score3 = " + this.score3 + "\n");
        sb.append("score4 = " + this.score4 + "\n");
        sb.append("score5 = " + this.score5 + "\n");
        sb.append("score6 = " + this.score6 + "\n");
        sb.append("score7 = " + this.score7 + "\n");
        sb.append("score8 = " + this.score8 + "\n");
        sb.append("score9 = " + this.score9 + "\n");
        sb.append("score10 = " + this.score10 + "\n");
        sb.append("score11 = " + this.score11 + "\n");
        sb.append("score12 = " + this.score12 + "\n");
        sb.append("score13 = " + this.score13 + "\n");
        sb.append("scoresSelected = " + this.scoresSelected + "\n");
        sb.append("totalScore = " + this.totalScore + "\n");
        sb.append("currentRoll = " + this.currentRoll);
        return sb.toString();
    }
}
