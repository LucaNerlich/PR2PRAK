package klausurUebungen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Luca on 24.06.2014.
 */
public class RegExController {

    private String regExPattern;
    private String inputString;
    private Pattern pattern;
    private Matcher matcher;

    public void setRegExPattern(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Please enter your regular expression below!");
            pattern = Pattern.compile(in.readLine());

            match();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void match(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            boolean found = false;
        System.out.println("Thank you. Please enter the String, you are trying to match!");
        matcher = pattern.matcher(in.readLine());

        while(matcher.find()){
            found = true;
            System.out.println("your strings match!");
        }
            if (!found) {
                System.out.printf("No match found\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
