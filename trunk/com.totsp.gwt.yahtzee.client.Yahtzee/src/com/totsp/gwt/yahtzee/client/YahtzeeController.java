package com.totsp.gwt.yahtzee.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Label;

public class YahtzeeController
{

    List listeners = new ArrayList();
    YahtzeeData data;

    public YahtzeeController()
    {
        this.data = new YahtzeeData();
    }

    public void addChangeListener(final GenericChangeListener listener)
    {
        listeners.add(listener);
    }

    public void reset()
    {
        data = new YahtzeeData();
        for (int i = 0; i < listeners.size(); i++)
        {
            GenericChangeListener listener = (GenericChangeListener) listeners.get(i);
            listener.onChange(data);
        }
    }

    public void displayPossibleScore(int scoreType, Label label)
    {
        // 1-6 simples 1s-6s
        int scoreValue = 0;
        if ((scoreType == 1) || (scoreType == 2) || (scoreType == 3) || (scoreType == 4) || (scoreType == 5)
                || (scoreType == 6))
        {
            scoreValue = getTotalForDieType(scoreType);
        }
        else
        {
            switch (scoreType)
            {
                case 7: {
                    // 3k
                    if (numberOfAnyKindPresent(3))
                    {
                        scoreValue = sumAllDie();
                    }
                    break;
                }
                case 8: {
                    // 4k
                    if (numberOfAnyKindPresent(4))
                    {
                        scoreValue = sumAllDie();
                    }
                    break;
                }
                case 9: {
                    // fh
                    if (fullHousePresent())
                    {
                        scoreValue = 25;
                    }
                    break;
                }
                case 10: {
                    // ss
                    if (smallStraightPresent())
                    {
                        scoreValue = 30;
                    }
                    break;
                }
                case 11: {
                    // ls
                    if (largeStraightPresent())
                    {
                        scoreValue = 40;
                    }
                    break;
                }
                case 12: {
                    // y
                    if (numberOfAnyKindPresent(5))
                    {
                        scoreValue = 50;
                    }
                    break;
                }
                case 13: {
                    // c
                    scoreValue = sumAllDie();
                    break;
                }
            }
        }
        label.setText(String.valueOf(scoreValue));
    }

    public void chooseScore(int scoreType, int score)
    {
        data.scoresSelected++;
        switch (scoreType)
        {
            case 1: {
                data.score1 = score;
                break;
            }
            case 2: {
                data.score2 = score;
                break;
            }
            case 3: {
                data.score3 = score;
                break;
            }
            case 4: {
                data.score4 = score;
                break;
            }
            case 5: {
                data.score5 = score;
                break;
            }
            case 6: {
                data.score6 = score;
                break;
            }
            case 7: {
                data.score7 = score;
                break;
            }
            case 8: {
                data.score8 = score;
                break;
            }
            case 9: {
                data.score9 = score;
                break;
            }
            case 10: {
                data.score10 = score;
                break;
            }
            case 11: {
                data.score11 = score;
                break;
            }
            case 12: {
                data.score12 = score;
                break;
            }
            case 13: {
                data.score13 = score;
                break;
            }
        }
        
        if (data.totalScore == 0)
        {
            data.totalScore = score;
        }
        
        if ((data.score1 + data.score2 + data.score3 + data.score4 + data.score5 + data.score6) >= 63)
        {
            data.upperBonus = 35;
        }
        
        nextTurn();        
    }

    public void processRoll(ButtonDie die1, ButtonDie die2, ButtonDie die3, ButtonDie die4, ButtonDie die5)
    {
        data.currentRoll++;
        if (data.currentRoll <= 3)
        {
            if (!die1.clicked)
            {
                data.die1 = roll();
            }
            if (!die2.clicked)
            {
                data.die2 = roll();
            }
            if (!die3.clicked)
            {
                data.die3 = roll();
            }
            if (!die4.clicked)
            {
                data.die4 = roll();
            }
            if (!die5.clicked)
            {
                data.die5 = roll();
            }
        }

        for (int i = 0; i < listeners.size(); i++)
        {
            GenericChangeListener listener = (GenericChangeListener) listeners.get(i);
            listener.onChange(data);
        }
    }

    public void nextTurn()
    {
        data.die1 = 0;
        data.die2 = 0;
        data.die3 = 0;
        data.die4 = 0;
        data.die5 = 0;
        data.currentRoll = 0;

        for (int i = 0; i < listeners.size(); i++)
        {
            GenericChangeListener listener = (GenericChangeListener) listeners.get(i);
            listener.onChange(data);
        }
    }

    public void setTotalScore(Label label)
    {
        int score = 0;
        score += data.score1;
        score += data.score2;
        score += data.score3;
        score += data.score4;
        score += data.score5;
        score += data.score6;
        score += data.score7;
        score += data.score8;
        score += data.score9;
        score += data.score10;
        score += data.score11;
        score += data.score12;
        score += data.score13;
        score += data.upperBonus;
        label.setText(String.valueOf(score));
    }

    public int roll()
    {
        double i = 0;
        while (i < 1 || i > 6)
        {
            i = Math.random() * 10;
        }
        return (int) Math.round(i);
    }

    // utils to determine scores

    public boolean fullHousePresent()
    {
        boolean result = false;

        int ones = getNumberOfDieForDieType(1);
        int twos = getNumberOfDieForDieType(2);
        int threes = getNumberOfDieForDieType(3);
        int fours = getNumberOfDieForDieType(4);
        int fives = getNumberOfDieForDieType(5);
        int sixes = getNumberOfDieForDieType(6);

        if (ones == 3)
        {
            if (numberOfAnyOtherKindPresent(2, 1))
            {
                result = true;
            }
        }
        else if (twos == 3)
        {
            if (numberOfAnyOtherKindPresent(2, 2))
            {
                result = true;
            }
        }
        else if (threes == 3)
        {
            if (numberOfAnyOtherKindPresent(2, 3))
            {
                result = true;
            }
        }
        else if (fours == 3)
        {
            if (numberOfAnyOtherKindPresent(2, 4))
            {
                result = true;
            }
        }
        else if (fives == 3)
        {
            if (numberOfAnyOtherKindPresent(2, 5))
            {
                result = true;
            }
        }
        else if (sixes == 3)
        {
            if (numberOfAnyOtherKindPresent(2, 6))
            {
                result = true;
            }
        }
        return result;
    }

    public boolean largeStraightPresent()
    {
        boolean result = false;
        int[] dieValues = {data.die1, data.die2, data.die3, data.die4, data.die5};

        // 1,2,3,4,5 version
        if (intInArray(dieValues, 1) && intInArray(dieValues, 2) && intInArray(dieValues, 3)
                && intInArray(dieValues, 4) && intInArray(dieValues, 5))
        {
            result = true;
        }
        // 2,3,4,5,6 version
        else if (intInArray(dieValues, 2) && intInArray(dieValues, 3) && intInArray(dieValues, 4)
                && intInArray(dieValues, 5) && intInArray(dieValues, 6))
        {
            result = true;
        }
        return result;
    }

    public boolean smallStraightPresent()
    {
        boolean result = false;
        int[] dieValues = {data.die1, data.die2, data.die3, data.die4, data.die5};

        // 1,2,3,4 version
        if (intInArray(dieValues, 1) && intInArray(dieValues, 2) && intInArray(dieValues, 3)
                && intInArray(dieValues, 4))
        {
            result = true;
        }
        // 2,3,4,5 version
        else if (intInArray(dieValues, 2) && intInArray(dieValues, 3) && intInArray(dieValues, 4)
                && intInArray(dieValues, 5))
        {
            result = true;
        }
        // 3,4,5,6 version
        else if (intInArray(dieValues, 3) && intInArray(dieValues, 4) && intInArray(dieValues, 5)
                && intInArray(dieValues, 6))
        {
            result = true;
        }

        return result;
    }

    public boolean numberOfAnyOtherKindPresent(int threshold, int excludeNumber)
    {
        boolean result = false;
        if ((excludeNumber != 1) && (getNumberOfDieForDieType(1) >= threshold))
        {
            result = true;
        }
        if ((excludeNumber != 2) && (getNumberOfDieForDieType(2) >= threshold))
        {
            result = true;
        }
        if ((excludeNumber != 3) && (getNumberOfDieForDieType(3) >= threshold))
        {
            result = true;
        }
        if ((excludeNumber != 4) && (getNumberOfDieForDieType(4) >= threshold))
        {
            result = true;
        }
        if ((excludeNumber != 5) && (getNumberOfDieForDieType(5) >= threshold))
        {
            result = true;
        }
        if ((excludeNumber != 6) && (getNumberOfDieForDieType(6) >= threshold))
        {
            result = true;
        }
        return result;
    }

    public boolean numberOfAnyKindPresent(int threshold)
    {
        boolean result = false;
        if (getNumberOfDieForDieType(1) >= threshold)
        {
            result = true;
        }
        else if (getNumberOfDieForDieType(2) >= threshold)
        {
            result = true;
        }
        else if (getNumberOfDieForDieType(3) >= threshold)
        {
            result = true;
        }
        else if (getNumberOfDieForDieType(4) >= threshold)
        {
            result = true;
        }
        else if (getNumberOfDieForDieType(5) >= threshold)
        {
            result = true;
        }
        else if (getNumberOfDieForDieType(6) >= threshold)
        {
            result = true;
        }
        return result;
    }

    public int getNumberOfDieForDieType(int value)
    {
        int result = 0;
        if (data.die1 == value)
        {
            result++;
        }
        if (data.die2 == value)
        {
            result++;
        }
        if (data.die3 == value)
        {
            result++;
        }
        if (data.die4 == value)
        {
            result++;
        }
        if (data.die5 == value)
        {
            result++;
        }
        return result;
    }

    public int getTotalForDieType(int value)
    {
        int result = 0;
        if (data.die1 == value)
        {
            result += data.die1;
        }
        if (data.die2 == value)
        {
            result += data.die2;
        }
        if (data.die3 == value)
        {
            result += data.die3;
        }
        if (data.die4 == value)
        {
            result += data.die4;
        }
        if (data.die5 == value)
        {
            result += data.die5;
        }
        return result;
    }

    public int sumAllDie()
    {
        int result = 0;
        result += data.die1;
        result += data.die2;
        result += data.die3;
        result += data.die4;
        result += data.die5;
        return result;
    }

    public boolean intInArray(int[] array, int value)
    {
        boolean result = false;
        for (int i = 0; i < array.length; i++)
        {
            int j = array[i];
            if (j == value)
            {
                result = true;
                break;
            }
        }
        return result;
    }

}
