package edu.gatech.oad.antlab.person;
import java.util.*;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string
 *
 *  @author Jordan
 *  @version 1.1
 */
public class Person1 {
  /** Holds the persons real name */
  private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
  public Person1(String pname) {
    name = pname;
  }
  	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
    char[] ch = input.toCharArray();
    char[] ch2 = ch;
    int len = ch.length;
    for(int i = 0; i < len - 2; i++) {
      ch[i] = ch2[i + 2];
    }
    ch[len - 2] = ch2[0];
    ch[len - 1] = ch2[1];
    String str = String.valueOf(ch);
	  //Person 1 put your implementation here
	  return str;
	}

	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}

}
