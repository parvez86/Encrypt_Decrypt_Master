package encryptdecrypt;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
public class UtilFile{
    
    public byte[] read(String aInputFileName){
        File file = new File(aInputFileName);
        byte[] result = new byte[(int)file.length()];
        try {
            InputStream input = null;
            try {
                int totalBytesRead = 0;
                input = new BufferedInputStream(new FileInputStream(file));
                while(totalBytesRead < result.length){
                    int bytesRemaining = result.length - totalBytesRead;
                    //input.read() returns -1, 0, or more :
                    int bytesRead = input.read(result, totalBytesRead, bytesRemaining); 
                    if (bytesRead > 0){
                        totalBytesRead = totalBytesRead + bytesRead;
                    }
                }
            }
            finally {
                input.close();
            }
        }
        catch (FileNotFoundException ex) {
        }
        catch (IOException ex) {
        }
        return result;
    }


    public void write(byte[] aInput, String aOutputFileName, int tmp){
        try {
            OutputStream output = null;
            try {
                output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
                output.write(aInput);
                if (tmp==1) {
                    JOptionPane.showMessageDialog(null, "Your file is successfully encoded", "Encode Status", JOptionPane.INFORMATION_MESSAGE);
                   }
                else if (tmp == 2){
                    JOptionPane.showMessageDialog(null, "Your file is successfully decoded", "Decode Status", JOptionPane.INFORMATION_MESSAGE);
                   }
            }
            finally {
                output.close();
            }
        }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Error Message", "File not found!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Error Message", "I/O error occurs!", JOptionPane.ERROR_MESSAGE);
        }
    }  
}
