package inet;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author myz
 * @create 2023/3/23-16:18
 */
public class ClientThread implements Runnable{
    private BufferedReader br;

    public ClientThread(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        while(true){
            try {
                String str = br.readLine();
                System.out.println(str);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
