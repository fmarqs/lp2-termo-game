import java.io.*;
import java.net.Socket;

public class IsPalindrome implements Runnable {

    private Socket socket;
    public IsPalindrome(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

        System.out.println("Teste " + socket);

        DataInputStream din= null;
        try {
            din = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataOutputStream dout= null;
        try {
            dout = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str="",str2="",b="teste", compara="";

        while (!str.equals("stop")) {
            compara="";
            try {
                str = din.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int n = str.length();
            if (str.equals(b)) {
                str2="Voce acertou!";
                try {
                    dout.writeUTF(str2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    dout.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                for (int i = 0; i < n; i++) {
                    if(str.charAt(i) == b.charAt(i))
                        compara = compara + str.charAt(i);
                    else
                        compara = compara + '_';

                }
                str2 = compara;
                try {
                    dout.writeUTF(str2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    dout.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
