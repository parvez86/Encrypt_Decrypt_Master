package encryptdecrypt;

import javax.swing.JOptionPane;

public class RC4{
    
    private final byte[] S = new byte[256];
    private final byte[] T = new byte[256];
    private int keylen;
    
    public void Encode(String key, String path){
        System.out.println("Encode RC4 algorithm");
        //calling method for preparing key by Key Scheduling algorithm
        prepareKey(key.getBytes());
        //creating output of the file
        makeOutput(new UtilFile().read(path), path, 1);
    }

    public void Decode(String key, String path){
        System.out.println("Decode RC4 algorithm");
        
        prepareKey(key.getBytes());
        makeOutput(new UtilFile().read(path), path, 2);
    } 
    //*preparing key block where we will apply key scheduling algorithm to the kry and then we will generate the key stream by appying PRGA algorithm*/
    private void prepareKey(byte[] byteKey){
        keylen = byteKey.length;
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
            T[i] = byteKey[i % keylen];
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) & 0xFF;
            S[i] ^= S[j];
            S[j] ^= S[i];
            S[i] ^= S[j];
        }
    }
    //*encrypting or decrypting block where we will xor the key stream with file bytes so that together they can create the desired cipherfile*/
    private void makeOutput(final byte[] plainFile, String pathFile, int tmp){
        final byte[] cipherFile = new byte[plainFile.length];
        int i = 0, j = 0, k, t;
        for (int counter = 0; counter < plainFile.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            S[i] ^= S[j];
            S[j] ^= S[i];
            S[i] ^= S[j];
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            cipherFile[counter] = (byte) (plainFile[counter] ^ k);
        }
        
        new UtilFile().write(cipherFile, pathFile, tmp);//creating file output
    }
}
