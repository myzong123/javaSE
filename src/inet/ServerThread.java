package inet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import static inet.Server.sockets;

/**
 * @author myz
 * @create 2023/3/23-15:38
 */
public class ServerThread implements Runnable{
    private Socket accept;
    private Map<String,String> map;
    public ServerThread(Socket accept, Map<String,String> map){
        this.accept = accept;
        this.map = map;
    }
    @Override
    public void run() {
        try {
            InputStream is = accept.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String message = br.readLine();
            System.out.println("读取到客户信息");
            String[] split = message.split("=");
            // 登录
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            if("1".equals(split[0])){
                if(map.containsKey(split[1])){
                    if(map.get(split[1]).equals(split[2])){
                        bw.write("登录成功");
                        bw.newLine();
                        bw.flush();
                        sockets.add(accept);
                        while(true){
                            String str = br.readLine();
                            System.out.println(split[1]+"发来消息:"+str);
                            // bw.write(split[1]+"发来消息:"+str);
                            // bw.newLine();
                            // bw.flush();
                            //群发
                            for(Socket s : sockets){
                                BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                                bw1.write(split[1]+"发来消息:"+str);
                                bw1.newLine();
                                bw1.flush();
                            }
                        }
                    }else{
                        bw.write("密码错误");
                        bw.newLine();
                        bw.flush();
                    }
                }else {
                    bw.write("用户名不存在");
                    bw.newLine();
                    bw.flush();
                }

            }
            // 注册
            if("2".equals(split[0])){
                if (map.containsKey(split[1])){
                    bw.write("用户名已存在");
                    bw.newLine();
                    bw.flush();
                }else{
                    FileOutputStream fos = new FileOutputStream("myio\\user.txt",true);
                    fos.write("\r\n".getBytes());
                    fos.write((split[1]+"="+split[2]).getBytes());
                    fos.close();
                    bw.write("注册成功");
                    bw.newLine();
                    bw.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
