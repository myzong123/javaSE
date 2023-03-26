package inet;

import com.sun.xml.internal.ws.util.Pool;
import list.MyThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author myz
 * @create 2023/3/22-15:01
 */
public class Server {
    public static List<Socket> sockets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        //
        BufferedReader fbr = new BufferedReader(new FileReader("myio\\user.txt"));
        Map<String,String> map = new HashMap<>();
        String line;
        while((line = fbr.readLine()) != null){
            String[] split = line.split("=");
            map.put(split[0],split[1]);
        }
        fbr.close();
        // 读取客户端数据
        while (true){
            Socket accept = ss.accept();
            System.out.println("有客户端连接....");
            new Thread(new ServerThread(accept,map)).start();
        }
    }
}
