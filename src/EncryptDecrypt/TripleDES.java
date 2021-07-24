package encryptdecrypt;

import javax.swing.JOptionPane;

public class TripleDES{
    

    private static int[] Table_IP = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 
                                     36,28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 
                                     16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 
                                     53,  45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 
                                    };
    private static int[] Table_FP = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47,
                                     15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21,
                                     61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51,19, 59, 27, 34,
                                     2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 
                                    };
    private static int[] Table_P = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 
                                    26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4,
                                    25
                                   };
    private static int[] Table_Expand = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8,
                                         9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 
                                         21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };

    private static int[][][] Table_SBox = {
        { 		{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
        { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
        { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
        { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } 
        },
        { 		{ 15, 1, 8, 14, 6, 11, 3, 2, 9, 7, 2, 13, 12, 0, 5, 10 },
        { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
        { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
        { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } 
        },
        { 		{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
        { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
        { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
        { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } 
        },
        { 		{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
        { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
        { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
        { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } 
        },
        { 		{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
        { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
        { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
        { 11, 8, 12, 7, 1, 14, 2, 12, 6, 15, 0, 9, 10, 4, 5, 3 } 
        },
        { 		{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
        { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
        { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
        { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }

        },
        { 		{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
        { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
        { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
        { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }

        },
        { 		{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
        { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
        { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
        { 2, 1, 14, 7, 4, 10, 18, 13, 15, 12, 9, 0, 3, 5, 6, 11 }

        } };

    //const for key preparation
    //permutation 64 -> 56 bit
    private static int[] Table_P1 = { 
        15, 7, 57, 49, 41, 33, 25, 17,26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 9, 
        1, 58, 50, 42, 34, 11, 3, 60,52, 44, 36, 63, 45, 37, 29, 21, 13, 5, 28, 
        20, 12, 4,62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 55, 47, 39, 31, 23
    };
    //permutation 56 -> 48 bit
    private static int[] Table_P2 = { 23, 19, 12, 4, 26, 8, 16, 14, 17, 3, 28, 
                                     15, 6, 21, 10, 39, 56, 34, 53, 46, 42, 50, 36, 40, 51, 45, 33, 20, 13, 2,
                                     41, 52, 30, 48, 44, 49,  29, 32, 1, 5, 7, 27, 31, 37, 11, 24, 47, 55
                                    };
    //shift value for each position
    private static int[] Table_Rotation = { 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 
                                           1, 1, 2, 2 
                                          };

    //string key
    static String[] TripleDESKey=new String[3];
    //byte key
    private static byte[][] desKey0;
    private static byte[][] desKey1;
    private static byte[][] desKey2;
    
    
    public void Encode(String key, String path){
        System.out.println("Encode 3DES algorithm");
        
        //generate key0, key1, key2
        TripleDESKey[0]=new String(key);
        desKey0 = prepareKey(key.getBytes());

        String keyReverse = new StringBuffer(key).reverse().toString();
        TripleDESKey[1]=new String(keyReverse);
        desKey1 = prepareKey(keyReverse.getBytes());

        String keySwap = key.substring(key.length()/2,key.length())+
            key.substring(0,key.length()/2);
        TripleDESKey[2]=new String(keySwap);
        desKey2 = prepareKey(keySwap.getBytes());
        
        makeOutput(new UtilFile().read(path),path,true, 1);
//        JOptionPane.showMessageDialog(null, "Your file is encoded successfully", "Encode Status", JOptionPane.INFORMATION_MESSAGE);
    }

    public void Decode(String key, String path){
        System.out.println("Decode 3DES algorithm");
        
        //generate key0, key1, key2
        TripleDESKey[0]=new String(key);
        desKey0 = prepareKey(key.getBytes());

        String keyReverse = new StringBuffer(key).reverse().toString();
        TripleDESKey[1]=new String(keyReverse);
        desKey1 = prepareKey(keyReverse.getBytes());

        String keySwap = key.substring(key.length()/2,key.length())+
            key.substring(0,key.length()/2);
        TripleDESKey[2]=new String(keySwap);
        desKey2 = prepareKey(keySwap.getBytes());
        
        makeOutput(new UtilFile().read(path),path,false, 2);
//        JOptionPane.showMessageDialog(null, "Your file is decoded successfully", "Decode Status", JOptionPane.INFORMATION_MESSAGE);
    } 
    
    private byte[][] prepareKey(byte[] byteKey){
        byte[][] desTmp = new byte[16][];
        // PC1 permutation -> 56
        byte[] tmpK = P_Func(byteKey, Table_P1);

        // first 28 bits
        byte[] F = Get_Bits(tmpK, 0, 28);
        // second 28 bits
        byte[] S = Get_Bits(tmpK, 28, 28);

        // generate 16 subkeys
        for (int i = 0; i < 16; i++) {

            //left rotation bit
            F = LR_Bits(F, 28, Table_Rotation[i]);
            S = LR_Bits(S, 28, Table_Rotation[i]);

            //bond both 28 bits array
            byte[] sub56key = Bond_Bits(F, 28, S, 28);

            //PC2 permutation -> 48
            desTmp[i] = P_Func(sub56key, Table_P2);
        }

        return desTmp;
    }

    private void makeOutput(final byte[] dataFile, String pathFile,boolean encode, int tmpk){

            byte[] B_64bits = new byte[8];

            if(encode){
                //add padding if file is not 64 bit %
                int i, lenght=0;
                lenght = 8 - dataFile.length % 8;

                byte[] padding = new byte[1];
                padding = new byte[lenght];
                padding[0] = (byte) 0x80;
                for (i = 1; i < lenght; i++)
                    padding[i] =(byte) 0x0f;

                byte[] cipherFile = new byte[dataFile.length + lenght];

                int count = 0;
                for (i = 0; i < dataFile.length + lenght; i++) {
                    //encrypt bloc if have 64 bits
                    if (i > 0 && i % 8 == 0) {
                        B_64bits = elaborate64bits(B_64bits,desKey0, true);						
                        B_64bits = elaborate64bits(B_64bits,desKey1, false);
                        B_64bits = elaborate64bits(B_64bits,desKey2, true);

                        System.arraycopy(B_64bits, 0, cipherFile, i - 8, B_64bits.length);
                    }
                    //filling the block
                    if (i < dataFile.length)
                        B_64bits[i % 8] = dataFile[i];
                    else{														
                        B_64bits[i % 8] = padding[count % 8];
                        count++;
                    }
                }
                //encrypt last block
                if(B_64bits.length == 8){
                    B_64bits = elaborate64bits(B_64bits,desKey0, true);						
                    B_64bits = elaborate64bits(B_64bits,desKey1, false);
                    B_64bits = elaborate64bits(B_64bits,desKey2, true);
                    
                    System.arraycopy(B_64bits, 0, cipherFile, i - 8, B_64bits.length);
                }

                new UtilFile().write(cipherFile,pathFile, tmpk);
            } else {
                int i;
                byte[] plainFile = new byte[dataFile.length];

                for (i = 0; i < dataFile.length; i++) {
                    if (i > 0 && i % 8 == 0) {
                        B_64bits = elaborate64bits(B_64bits,desKey2, false);						
                        B_64bits = elaborate64bits(B_64bits,desKey1, true);
                        B_64bits = elaborate64bits(B_64bits,desKey0, false);
                        
                        System.arraycopy(B_64bits, 0, plainFile, i - 8, B_64bits.length);
                    }
                    if (i < dataFile.length)
                        B_64bits[i % 8] = dataFile[i];
                }
                B_64bits = elaborate64bits(B_64bits,desKey2, false);						
                B_64bits = elaborate64bits(B_64bits,desKey1, true);
                B_64bits = elaborate64bits(B_64bits,desKey0, false);
                
                System.arraycopy(B_64bits, 0, plainFile, i - 8, B_64bits.length);

                //remove padding
                int count = 0;
                i = plainFile.length - 1;
                while (plainFile[i] ==(byte) 0x0f) {
                    count++;
                    i--;
                }

                byte[] tmp = new byte[plainFile.length - count - 1];
                System.arraycopy(plainFile, 0, tmp, 0, tmp.length);
   

                new UtilFile().write(tmp,pathFile,tmpk);
            }
    }
        
    private static byte[] elaborate64bits(byte[] array,byte[][] key, boolean encode) {
        byte[] tmp = new byte[array.length];
        byte[] first32bits = new byte[array.length / 2];
        byte[] second32bits = new byte[array.length / 2];

        tmp = P_Func(array, Table_IP);

        second32bits = Get_Bits(tmp, 0, Table_IP.length/2);
        first32bits = Get_Bits(tmp, Table_IP.length/2, Table_IP.length/2);

        for (int i = 0; i < 16; i++) {
            byte[] tmpR = first32bits;
            if(encode)
                first32bits = F_Func(first32bits, key[15-i]);
            else
                first32bits = F_Func(first32bits, key[i]);

            first32bits = XOR_Func(second32bits, first32bits);											
            second32bits = tmpR;													
        }

        tmp = Bond_Bits(first32bits, Table_IP.length/2, second32bits, Table_IP.length/2);

        tmp = P_Func(tmp, Table_FP);
        return tmp;
    }
    
    private static byte[] F_Func(byte[] a, byte[] b) {
        byte[] tmp;
        tmp = P_Func(a, Table_Expand);
        tmp = XOR_Func(tmp, b);
        tmp = S_Func(tmp);
        tmp = P_Func(tmp, Table_P);
        return tmp;
    }

    private static byte[] S_Func(byte[] array) {
        array = Sunder_Bytes(array, 6);
        byte[] out = new byte[array.length / 2];					
        int halfByte = 0;											
        for (int b = 0; b < array.length; b++) {
            byte valByte = array[b];
            int r = 2 * (valByte >> 7 & 0x0001) + (valByte >> 2 & 0x0001);														
            int c = valByte >> 3 & 0x000F;
            int val = Table_SBox[b][r][c];
            if (b % 2 == 0)
                halfByte = val;
            else
                out[b / 2] = (byte) (16 * halfByte + val);
        }
        return out;
    }



    // util bit
    ///////////
    private static byte[] P_Func(byte[] input, int[] table) {
        int nrBytes = (table.length - 1) / 8 + 1;
        byte[] out = new byte[nrBytes];
        for (int i = 0; i < table.length; i++) {
            int val = Get_Bit(input, table[i] - 1);
            Set_Bit(out, i, val);
        }
        return out;
    }

    private static byte[] Get_Bits(byte[] input, int pos, int n) {
        int num = (n - 1) / 8 + 1;							
        byte[] out = new byte[num];						
        for (int i = 0; i < n; i++) {						
            int val = Get_Bit(input, pos + i);					
            Set_Bit(out, i, val);
        }
        return out;
    }

    private static void Set_Bit(byte[] data, int pos, int val) {
        int posB = pos / 8;										
        int posb = pos % 8;										
        byte tmpB = data[posB];									
        tmpB = (byte) (((0xFF7F >> posb) & tmpB) & 0x00FF);		
        byte newByte = (byte) ((val << (8 - (posb + 1))) | tmpB);
        data[posB] = newByte;
    }

    private static int Get_Bit(byte[] data, int pos) {
        int posB = pos / 8;
        int posb = pos % 8;										
        byte tmpB = data[posB];
        int bit = tmpB >> (8 - (posb + 1)) & 0x0001;
        return bit;
    }

    private static byte[] LR_Bits(byte[] input, int len, int pas) {
        int num = (len - 1) / 8 + 1;
        byte[] out = new byte[num];
        for (int i = 0; i < len; i++) {
            int val = Get_Bit(input, (i + pas) % len);
            Set_Bit(out, i, val);
        }
        return out;
    }

    private static byte[] Bond_Bits(byte[] a, int aLen, byte[] b, int bLen) {
        int num = (aLen + bLen - 1) / 8 + 1;
        byte[] out = new byte[num];
        int j = 0;
        for (int i = 0; i < aLen; i++) {
            int val = Get_Bit(a, i);
            Set_Bit(out, j, val);
            j++;
        }
        for (int i = 0; i < bLen; i++) {
            int val = Get_Bit(b, i);
            Set_Bit(out, j, val);
            j++;
        }
        return out;
    }

    private static byte[] Sunder_Bytes(byte[] array, int len) {
        int num = (8 * array.length - 1) / len + 1;	
        byte[] out = new byte[num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < len; j++) {
                int val = Get_Bit(array, len * i + j);
                Set_Bit(out, 8 * i + j, val);
            }
        }
        return out;
    }

    private static byte[] XOR_Func(byte[] a, byte[] b) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ b[i]);
        }
        return out;
    }
}
