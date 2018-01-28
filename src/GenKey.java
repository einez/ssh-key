import com.jcraft.jsch.JSch;
import com.jcraft.jsch.KeyPair;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class GenKey {
    public static void main(String[] args){
        Map<String,String> keys = new HashMap<>();
        int type = KeyPair.RSA;
        JSch jsch = new JSch();
        try {
            KeyPair kpair = KeyPair.genKeyPair(jsch, type,2048);
            //私钥
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//向OutPutStream中写入
            kpair.writePrivateKey(baos);
            String privateKeyString = baos.toString();
            //公钥
            baos = new ByteArrayOutputStream();
            kpair.writePublicKey(baos,"comment");
            String publicKeyString = baos.toString();
            System.out.println("Finger print: " + kpair.getFingerPrint());
            kpair.dispose();
            // 得到公钥字符串
//          String publicKeyString = RSAEncrypt.loadPublicKeyByFile(filePath,filename + ".pub");
//          System.out.println(publicKeyString.length());
            System.out.println(publicKeyString);
            keys.put("publicKey", publicKeyString);
            // 得到私钥字符串
//          String privateKeyString = RSAEncrypt.loadPrivateKeyByFile(filePath,filename);
//          System.out.println(privateKeyString.length());
            System.out.println(privateKeyString);
            keys.put("privateKey", privateKeyString);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
