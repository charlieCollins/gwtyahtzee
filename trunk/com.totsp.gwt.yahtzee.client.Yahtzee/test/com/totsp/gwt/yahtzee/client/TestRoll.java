package com.totsp.gwt.yahtzee.client;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestRoll extends TestCase
{
    
    public void testRoll()
    {
        System.out.println("testRoll");
        
        // roll the dice X times and see the distribution
        YahtzeeController c = new YahtzeeController();
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int sample = 10000;
        
        for (int i = 0; i < sample; i++)
        {
            int roll = c.roll();
            switch(roll)
            {
                case 1:
                {
                    count1++;
                    break;
                }
                case 2:
                {
                    count2++;
                    break;
                }
                case 3:
                {
                    count3++;
                    break;
                }
                case 4:
                {
                    count4++;
                    break;
                }
                case 5:
                {
                    count5++;
                    break;
                }
                case 6:
                {
                    count6++;
                    break;
                }                
            }
            
        }
        
        int total = count1 + count2 + count3 + count4 + count5 + count6;
        
        StringBuffer sb = new StringBuffer();
        sb.append("*** dist over " + sample + " *** \n");
        sb.append("1 - " + count1 + "\n");
        sb.append("2 - " + count2 + "\n");
        sb.append("3 - " + count3 + "\n");
        sb.append("4 - " + count4 + "\n");
        sb.append("5 - " + count5 + "\n");
        sb.append("6 - " + count6 + "\n");
        sb.append("total (should match sample) = " + total);
        
        System.out.println(sb.toString());
        
        Assert.assertEquals(total, sample);
        
    }    
    
    
}