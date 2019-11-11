package Cipher;
import java.io.*;
import java.util.Random;

public class BookCipher {
    private File book;
    private String message;
    private String cryptTxtName;
    private String decodedName;


    public BookCipher(String bookFileName, String messageName, String cryptTxtName, String decodedName, boolean file) throws IOException{
        this.cryptTxtName = cryptTxtName;
        book = new File(bookFileName);
        this.decodedName = decodedName;
        if(file == true) {
            message = readUsingBufferedReader(messageName);
        }
        else{
            message = messageName;
        }

    }

    public String startEncode() throws IOException{
        FileWriter encryptedTxt = new FileWriter(cryptTxtName, false);
        LineNumberReader lnr = new LineNumberReader(new FileReader(book));
        int linesCount = 0;
        while(null != lnr.readLine()) linesCount++;
        Random rndLine = new Random();
        int lineNumber;
        String tempLine;
        int currentChar = 0;
        int sizeMessage = message.length();
        String encryptedString = "";

        while(sizeMessage != currentChar){
            lineNumber = rndLine.nextInt(linesCount) + 1;
            tempLine = findLine(lineNumber);
            if(tempLine.length() == 0)continue;
            char current = message.charAt(currentChar);

            if(current == '\r'){
                encryptedString += '\r';
                currentChar++;
                continue;
            }

            if(current == '\n'){
                encryptedString += '\n';
                currentChar++;
                continue;
            }

            int cryptChar = tempLine.indexOf(current);
            if(cryptChar == -1) continue;

            encryptedString += Integer.toString(lineNumber);
            encryptedString += " ";
            encryptedString += Integer.toString(cryptChar + 1);
            encryptedString += " ";
            currentChar++;
        }
        encryptedTxt.write(encryptedString);
        encryptedTxt.flush();
        encryptedTxt.close();
        return encryptedString;
    }
    public String startDecode() throws IOException{
        String returnDecodedString = "";
        FileWriter  out = new FileWriter(decodedName, false);
        LineNumberReader lnr = new LineNumberReader(new FileReader(cryptTxtName));
        String tempLineBook;
        String readLineCoded = lnr.readLine();
        String decodedString = "";
        while(null != readLineCoded ) {
            String encryptedTxtArray[] = readLineCoded.split(" ");
            for (int i = 0; i < encryptedTxtArray.length - 1; i++) {
                String line = encryptedTxtArray[i];
                int lineInt = Integer.parseInt(line);
                tempLineBook = findLine(lineInt);
                int index = Integer.parseInt(encryptedTxtArray[++i]);
                char tempIndex = tempLineBook.charAt(index - 1);
                decodedString += tempIndex;
            }
            decodedString += '\n';
            out.write(decodedString);
            returnDecodedString += decodedString;
            readLineCoded = lnr.readLine();
            decodedString = "";
        }

        out.flush();
        out.close();
        return returnDecodedString;
    }
    private static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (fileName));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
    private String findLine(int findLine) throws IOException{
        String returnStringLine = null;
        LineNumberReader lnr = new LineNumberReader(new FileReader(book));
        int currentLine = 0;
        while(currentLine != findLine){
            returnStringLine = lnr.readLine();
            currentLine++;
        }
        return returnStringLine;
    }

}
