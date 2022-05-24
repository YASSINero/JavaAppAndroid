package com.example.javaappandroid;

import java.text.MessageFormat;

public class BracesProcess {

    private char[] charsStack;


    {
        charsStack = new char[100];
    }

    int[] BRACKETS;
    {
        BRACKETS = new int[3];
    }

    char inputChar;

    private final String openBrackets = "({[";
    private final String closeBrackets = ")}]";

    int indexa;
    private String Result = "";

    private void push(char pushed)
    {
        indexa++;
        charsStack[indexa] = pushed;
    }

    private void pop()
    {
        charsStack[indexa] = 0;
        indexa--;
    }

    private char top()
    {
        return charsStack[indexa];
    }

    private boolean stackEmpty()
    {
        return indexa >= 1;
    }







   //Input



    public String processBrackets(String code) {

        for(int i = 0; i<code.length(); i++)
        {
            //inFile>>Char;
            inputChar = code.charAt(i);
            for(int j = 0; j<3; j++)
            {
                if(inputChar == openBrackets.charAt(j))
                {

                    push(openBrackets.charAt(j));
                    BRACKETS[j]++;
                    System.out.println("New Bracket open\n");

                }
                else if(inputChar == closeBrackets.charAt(j) && top() == openBrackets.charAt(j))
                {

                    pop();
                    BRACKETS[j]--;
                    System.out.println("Bracket closed\n");
                }
                else {System.out.println("Brackets Closing in wrong order\n");}
            }
            for(int z=0; z < BRACKETS.length;)
            {

                    if(BRACKETS[z] == 0 && z == 0) {
                        System.out.println("round Brackets well written\n");
                        z++;
                    }
                    else if(BRACKETS[z] == 0 && z == 1){
                        System.out.println("Curly Brackets well written\n");
                        z++;
                    }
                    else if(BRACKETS[z] == 0 && z == 2){
                        System.out.println("Square Brackets well written\n");
                        Result ="Brackets well written";
                        z++;

                    }
                    else {
                        System.out.println("Syntax Error in brackets\n");
                        return Result = MessageFormat.format("Syntax Error in bracket : {0}\n", openBrackets.charAt(z));
                    }

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
