package calculator;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    TextField answerBox, equationBox;
    
    ArrayList<String> equation = new ArrayList<String>();

    @FXML
    private void initialize(){
        answerBox.setEditable(false);
        equationBox.setEditable(false);  
    }

    @FXML
    private void refresh(){
        String tempString = "";
        for(int ctr = 0; ctr<equation.size(); ctr++){
            tempString = tempString.concat(equation.get(ctr));
        }
        equationBox.setText(tempString);
    }

    private void addNumericalValue(String num){
        
        if(equation.size()>0){
            if (equation.get(equation.size()-1).matches("^\\d+\\.?\\d*$")) {
                equation.set(equation.size()-1, equation.get((equation.size()-1))+num);
                refresh();
            }
            else {
                equation.add(num);
                refresh();
            }
        } else {
            equation.add(num);
            refresh();
        }
    }

    private void addOperator(String operator){
        if(equation.size()>0&&equation.get(equation.size()-1).matches("^\\d+\\.?\\d*$")){
            equation.add(operator);
            refresh();
        }
    }

    @FXML
    private void equalKeyPressed(){
        System.out.println(equation);
        if(equation.size()==0) {
            answerBox.setText("0");
        } else if(equation.size()==1){
            answerBox.setText(equation.get(0));
            refresh();
        } else if(equation.size()>2&&equation.get(equation.size()-1).matches("^\\d+\\.?\\d*$")) {
            double tempNum1, tempNum2, tempAnswer = 0;
            String tempOperator;
            int arraySize = equation.size();
            for(int ctr = 0; ctr < arraySize-2; ctr+=2){
                tempNum1 = Double.parseDouble(equation.get(0));
                tempOperator = equation.get(1);
                tempNum2 = Double.parseDouble(equation.get(2));
                
                if (tempOperator.equals("+")) {
                    tempAnswer = tempNum1 + tempNum2;
                } else if(tempOperator.equals("-")) {
                    tempAnswer = tempNum1 - tempNum2;
                } else if(tempOperator.equals("x")) {
                    tempAnswer = tempNum1 * tempNum2;
                } else if(tempOperator.equals("÷")) {
                    tempAnswer = tempNum1 / tempNum2;
                }

                equation.remove(2);
                equation.remove(1);
                equation.set(0, ""+tempAnswer);
            }
            if (Double.parseDouble(equation.get(0))%1==0) {
                answerBox.setText(""+Math.round(Double.parseDouble(equation.get(0)))); //removes the .0 at the end of a whole number
            } else {
                answerBox.setText(equation.get(0));
            }
            
        } else {
            answerBox.setText("Ensure the last input is a number");
        }
    }

    @FXML
    private void oneKeyPressed(){
        addNumericalValue("1");
    }

    @FXML
    private void twoKeyPressed(){
        addNumericalValue("2");
    }
    
    @FXML
    private void threeKeyPressed(){
        addNumericalValue("3");
    }

    @FXML
    private void fourKeyPressed(){
        addNumericalValue("4");
    }

    @FXML
    private void fiveKeyPressed(){
        addNumericalValue("5");
    }

    @FXML
    private void sixKeyPressed(){
        addNumericalValue("6");
    }
    
    @FXML
    private void sevenKeyPressed(){
        addNumericalValue("7");
    }

    @FXML
    private void eightKeyPressed(){
        addNumericalValue("8");
    }

    @FXML
    private void nineKeyPressed(){
        addNumericalValue("9");
    }

    @FXML
    private void zeroKeyPressed(){
        addNumericalValue("0");
    }

    @FXML
    private void decimalKeyPressed(){
        if (equation.size()>0&&equation.get(equation.size()-1).matches("^\\d+.?d*$")) {
            addNumericalValue(".");
        }
        
    }

    @FXML
    private void clearKeyPressed(){
        equation.clear();
        answerBox.setText("");
        refresh();
    }

    @FXML
    private void sumKeyPressed(){
        addOperator("+");
    }

    @FXML
    private void subtractKeyPressed(){
        addOperator("-");
    }

    @FXML
    private void multiplyKeyPressed(){
        addOperator("x");
    }

    @FXML
    private void divideKeyPressed(){
        addOperator("÷");
    }

}
