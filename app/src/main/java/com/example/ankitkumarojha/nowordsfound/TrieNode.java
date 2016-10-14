package com.example.ankitkumarojha.nowordsfound;

import android.util.Log;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by ankitkumarojha on 10/14/2016.
 */

public class TrieNode {
    public HashMap<String,TrieNode> children;
    public boolean isEndOfWord;

    TrieNode()
    {
        children = new HashMap<>();
        isEndOfWord = false;
    }

    public void add(String word)
    {
        final String Trie_Node_Add ="TrieNode-add";
        Log.d(Trie_Node_Add,"Method called");
        Log.d(Trie_Node_Add,"method called");
        TrieNode currentNode = this;
        String tempString = "";
        for(int i=0;i<word.length();i++)
        {
            tempString += word.charAt(i);
            if(!currentNode.children.containsKey(tempString))
            {
                TrieNode newNode = new TrieNode();
                currentNode.children.put(tempString,newNode);
                Log.d(Trie_Node_Add,"new node created-"+tempString);
            }
            else
            {
                Log.d(Trie_Node_Add,"node exist-"+tempString);
            }
        }
        currentNode.isEndOfWord=true;
        Log.d(Trie_Node_Add,"isEndOfWord set True");
    }


    public String getRandomWordFromTrie()
    {
        final String GET_RANDOM_WORD_FROM_TRIE="Word From Trie";
        final int NUMBER_OF_CHARACTERS = 26;
        Log.d(GET_RANDOM_WORD_FROM_TRIE,"Method Called");
        Random random = new Random();
        TrieNode currentNode = this;
        String tempString = "";
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while(!currentNode.isEndOfWord)
        {
            String tempAlphabet =alphabet;
            int indexSize=NUMBER_OF_CHARACTERS;
            int randomIndex=-1;
            while(!currentNode.children.containsKey(tempString))
            {
                randomIndex = random.nextInt(indexSize);
                tempString+=tempAlphabet.charAt(randomIndex);
                tempAlphabet=tempAlphabet.substring(0,randomIndex)+tempAlphabet.substring(randomIndex+1);
                indexSize--;
            }
            Log.d(GET_RANDOM_WORD_FROM_TRIE,"IN loop TempString="+tempString);
            currentNode=currentNode.children.get(tempString);
        }
        Log.d(GET_RANDOM_WORD_FROM_TRIE,"Method Ends");
        return tempString;
    }

    public boolean checkIsWordFromTrie(String word)
    {
        final String CHECK_IS_WORD_FROM_TRIE="iswordfromtrie";
        Log.d(CHECK_IS_WORD_FROM_TRIE,"method called");
        TrieNode currentNode = this;
        String tempString = "";
        for(int i=0;i<word.length();i++)
        {
            tempString += word.charAt(i);
            if(!currentNode.children.containsKey(tempString))
            {
                Log.d(CHECK_IS_WORD_FROM_TRIE,"Returning False");
                return false;
            }
            Log.d(CHECK_IS_WORD_FROM_TRIE,"Continuing Loop");
            currentNode=currentNode.children.get(tempString);
        }
        Log.d(CHECK_IS_WORD_FROM_TRIE,"Returning boolean"+currentNode.isEndOfWord);
        if(currentNode.isEndOfWord)
            return true;
        return false;
    }



}
