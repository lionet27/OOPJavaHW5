package notebook.view;

import java.util.Scanner;

public class DelimeterView {
    
    public static String getDelimeter(){
        Scanner in = new Scanner(System.in);
        System.out.print("Print delimeter: ");
        String delimeter= in.nextLine();
       
        return delimeter;

    }  
        
}
