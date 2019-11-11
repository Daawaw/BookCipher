import Cipher.*;

import java.io.*;

public class Main {

    public static void main(String[] args) {
	String bookFileName = "src/Lovecraft2.txt";
	String coded = "src/coded.txt";
	String message = "src/message.txt";
	//String message = "src/Lovecraft2.txt";
	String decoded = "src/decoded.txt";

	try {
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//String res = reader.readLine();
        //BookCipher cipher = new BookCipher(bookFileName, res, coded, decoded, false);
		BookCipher cipher = new BookCipher(bookFileName, message, coded, decoded, true);
        String encodeResult = cipher.startEncode();
        String decodeResult = cipher.startDecode();
        System.out.println("Encode Result: \n"+encodeResult);
        System.out.println("Decode Result: \n" + decodeResult);
        //reader.close();
    }
	catch(IOException ex){
	    System.out.println(ex);
    }
    }
}
