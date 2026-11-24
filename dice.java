import java.util.Random;

public class dice {
    private int[] results={1,1,1,1,1};
    private Random rand=new Random();
    private int yahtzees=0;

    public void diceRoll(boolean[] toRoll){ //Rolls only dice labeled as "true" withing the array of booleans (EX: if value 1 is "true", reroll the first value of the results array).
        if(toRoll.length==5){
            for(int c=1;c<=5;c++){
                boolean roll=toRoll[c-1]; //Obtains the boolean for whether or not the current result should be rerolled.
                if(roll){
                    results[c-1]=rand.nextInt(1,6);
                }
            }
        }
    }

    public int[] getResults(){ //Grants easy access to the results array.
        return results;
    }

    public String getIcon(int val){ //Grants easy access to dice emojis (⚀⚁⚂⚃⚄⚅).
        if(val==1){
            return "⚀";
        }else if(val==2){
            return "⚁";
        }else if(val==3){
            return "⚂";
        }else if(val==4){
            return "⚃";
        }else if(val==5){
            return "⚄";
        }else if(val==6){
            return "⚅";
        }else{
            return "?";
        }
    }

    public void displayResults(){ //Displays the current dice results on the terminal.
        String msg="Results:";
        for(int c=1;c<=5;c++){
            int val=results[c-1];
            msg=msg+" "+getIcon(val);
        }
    }

    public int checkDice(String outcome){
        if(outcome=="threeOfAKind" || outcome=="fourOfAKind" || outcome=="yahtzee"){ //Checks how many dice you have of the same kind.
            int amount=1;
            for(int c=1;c<=5;c++){ //Goes through each result.
                for(int v=1;v<=5;v++){ //Takes each result and compares it the other result.
                    if(v!=c && results[v-1]==results[c-1]){
                        amount+=1; //If the results match, we uptick the amount by 1.
                    }
                }
            }

            if(outcome=="threeOfAKind" && amount==3){ //Checks the outcome and the amount to ensure they match.
                return results[0]+results[1]+results[2]+results[3]+results[4]; //Calculates the sum of all the results.
            }else if(outcome=="fourOfAKind" && amount==4){
                return results[0]+results[1]+results[2]+results[3]+results[4]; //Calculates the sum of all the results.
            }else if(outcome=="yahtzee" && amount==5){
                yahtzees+=1;
                if(yahtzees>1){
                    return 100;
                }else{
                    return 50;
                }
            }else{
                return 0; //If the outcome is invalid, we return 0 as this means they're discarding this part of the score card.
            }
        }else if(outcome=="ones" || outcome=="twos" || outcome=="threes" || outcome=="fours" || outcome=="fives" || outcome=="sixes"){
            int target=1; //Figures out which number we're looking to add up.
            if(outcome=="ones"){
                target=1;
            }else if(outcome=="twos"){
                target=2;
            }else if(outcome=="threes"){
                target=3;
            }else if(outcome=="fours"){
                target=4;
            }else if(outcome=="fives"){
                target=5;
            }else if(outcome=="sixes"){
                target=6;
            }

            int total=0; //The sum of all the valid dice.
            for(int c=1;c<=5;c++){
                if(results[c-1]==target){ //Figures out if the value is equivalent to the target value.
                    total+=target; //Adds to the total.
                }
            }

            return total;
        }else if(outcome=="Chance"){
            return results[0]+results[1]+results[2]+results[3]+results[4]; //Returns the sum of all the results.
        }else{
            return 0;
        }
    }

}