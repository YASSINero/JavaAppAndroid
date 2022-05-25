package com.example.javaappandroid;

import java.util.ArrayDeque;
import java.util.Deque;


public class BracesProcess {


    private Deque<Character> charList = new ArrayDeque<Character>();
    private char inputChar;




    int[] BRACKETS;
    {
        BRACKETS = new int[3];
    }

    private final String openBrackets = "({[";
    private final String closeBrackets = ")}]";

    private String Result = "";

   //Input



    public String processBrackets(String code) {

        System.out.println(code);

        for(int i = 0; i<code.length(); i++)
        {
            //inFile>>Char;
            inputChar = code.charAt(i);
            for(int j = 0; j<3; j++)
            {
                if(inputChar == openBrackets.charAt(j))
                {

                    charList.push(inputChar);
                    BRACKETS[j]++;
                    System.out.println("New Bracket open\n");

                }
                else if(inputChar == closeBrackets.charAt(j) && !charList.isEmpty() && charList.getLast() == openBrackets.charAt(j))
                {
                    charList.pop();
                    BRACKETS[j]--;
                    System.out.println("Bracket Closed");
                }
                else if(inputChar == closeBrackets.charAt(j) && charList.isEmpty())
                {
                    BRACKETS[j]++;
                    break;
                }
                else {System.out.println("Will continue\n");
                }
            }

        }

        return checkBracketsSyntax();
    }

    public String checkBracketsSyntax()
    {
        for(int z=0; z < BRACKETS.length; z++)
        {

            switch (z) {
                case 0:
                    if(BRACKETS[z] > 0) {
                        //System.out.println("Syntax Error in bracket : " + ' ' +openBrackets.charAt(z));
                        Result += "Syntax Error in bracket : " + ' ' +openBrackets.charAt(z) + '\n';
                    }
                    else {
                        //System.out.println("Round Brackets well written");
                        Result += "Round Brackets well written\n";
                    }
                    break;

                case 1:
                    if(BRACKETS[z] > 0)	{
                        //System.out.println("Syntax Error in bracket : " + ' ' +openBrackets.charAt(z));
                        Result += "Syntax Error in bracket : " + ' ' +openBrackets.charAt(z) + '\n';
                    }
                    else {
                        //System.out.println("Curly Brackets well written");
                        Result += "Curly Brackets well written\n";
                    }
                    break;

                case 2:
                    if(BRACKETS[z] > 0)	{
                        //System.out.println("Syntax Error in bracket : " + ' ' +openBrackets.charAt(z));
                        Result += "Syntax Error in bracket : " + ' ' +openBrackets.charAt(z) + '\n';
                    }
                    else {
                        //System.out.println("Square Brackets well written");
                        Result += "Square Brackets well written";
                    }
                    break;
                default:
                    System.out.println("Something went wrong");
                    break;
            }



        }
        return Result;
    }


          /*  for(int k= 0; k<3; k++)
            {
                cout << BRACKETS[k] << "\n";
            }

            for(int l = 0; l < 10; l++)
            {
                cout << charsStack[l]<< "\n";
            }*/



//	for(int k = 0; k<sizeof(charsStack); k++)
//					{
//						for(int l = k; l>0; l--)
//						{
//							if((charsStack[k] == ')' || charsStack[k] == '}' || charsStack[k] == ']') && charsStack[l] != charsStack[k])
//							{
//								cout << "Bracket Closed \n";
//							}
//						}
//					}











}
