import java.io.*;
import java.net.Socket;
import java.util.Random;

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
        String str="",str2="", compara="", message="";

        LineNumberReader leitorLinhas = null;
        try {
            leitorLinhas = new LineNumberReader(new FileReader("BDP.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            leitorLinhas.skip(Long.MAX_VALUE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int quantPalavras = leitorLinhas.getLineNumber();
        System.out.println(quantPalavras);
        try {
            leitorLinhas.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] palavras = new String[quantPalavras];

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("BDP.txt"));
            String linhaLida;
            int linha = 0;
            while(((linhaLida = br.readLine()) != null)){
                        palavras[linha] = linhaLida;
                        linha++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Random random = new Random();
        int indiceSorteado = random.nextInt(quantPalavras);
        String sorteada = palavras[indiceSorteado];
        System.out.println(indiceSorteado);
        System.out.println(sorteada);

        message = "A palavra sorteada tem " + sorteada.length() + " letras";
        try {
            dout.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (!str.equals("stop")) {
            compara="";
            try {
                str = din.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int n = sorteada.length();
            if (str.equals(sorteada)) {
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
                    if(str.equals(null))

                    if(str.charAt(i) == sorteada.charAt(i))
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
