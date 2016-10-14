package com.example.ankitkumarojha.nowordsfound;

import java.util.ArrayList;
import java.util.Random;


public class PopulateWords {

    char arr[][] = new char[10][10];
    String[] st;


    public PopulateWords(ArrayList<String> list) {
        int k = 0;
        for (String input : list) {
            st[k++] = input;
        }
    }

    public void vertical() {

        int words = 0;
        for (int i = 0; i < st.length; i++) {

            Random random = new Random();

            int randx = random.nextInt(15 - st[i].length());
            int randy = random.nextInt(10), k = 0;

            k = 0;
            for (int x = randx; x < 10; x++) {

                if (words == 4)
                    break;
                if (k == st[i].length()) {
                    words++;
                    break;
                }

                arr[x][randy] = st[i].charAt(k++);
            }
        }


    }
    public void horizontal() {

        String str = "";
        ArrayList<Integer> location = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            str = "";
            for (int x = 0; x < 10; x++) {
                if (arr[i][x] == '\u0000')
                    str += '_';
                else
                    str += arr[i][x];
            }

            for (int j = 0; j < st.length; j++) {


                if (location.contains(j))
                    continue;

                String newstate = st[j];
                Boolean flag = false;
                int overlaps = 0;

                while (!flag) {

                    overlaps = 0;
                    for (int z = 0; z < newstate.length(); z++) {

                        if (newstate.length() > 10)
                            break;
                        if (str.charAt(z) == '_' || str.charAt(z) == newstate.charAt(z) || (str.charAt(z) == '_' && newstate.charAt(z) == ' '))
                            ;
                        else
                            overlaps++;

                    }
                    if (overlaps > 0)
                        break;
                    else {
                        location.add(j);
                        for (int q = 0; q < newstate.length(); q++) {
                            if (q > 9)
                                break;
                            arr[i][q] = newstate.charAt(q);
                        }
                    }

                    newstate = " " + newstate;

                    if (newstate.length() > 10)
                        break;

                }
            }
        }
    }

    public void addrandomCharaters()  {

        Random rand=new Random();
        char random_char;

        for(int i=0;i<10;i++)  {

            for(int j=0;j<10;j++)  {

                if(arr[i][j] == ' ' || arr[i][j] == '_')  {

                    random_char=(char)(rand.nextInt((26) + 65));
                    arr[i][j]=random_char;
                }

            }
        }

    }

}
