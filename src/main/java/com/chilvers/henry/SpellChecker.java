package com.chilvers.henry;

/**
 * Component to spell check a given word.
 *
 * Created by Henry on 9/25/2016.
 */

class SpellChecker {
    static Boolean isWord(String word) {
        return word.length() % 2 == 0;
        //return true;
    }
}