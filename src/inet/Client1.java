package inet;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author myz
 * @create 2023/3/22-15:00
 */
public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",10000);
        System.out.println("服务器连接成功");
        System.out.println("==========欢迎来到黑马聊天室===========");
        System.out.println("1登录");
        System.out.println("2注册");
        System.out.println("请输入你的选择：");
        OutputStream os = s.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        Scanner sc = new Scanner(System.in);
        while (true){
            String str = sc.nextLine();
            switch (str){
                case "1":
                    System.out.println("登录");
                    System.out.print("请输入用户名:");
                    String name = sc.nextLine();
                    System.out.print("请输入密码:");
                    String password = sc.nextLine();
                    bw.write("1"+"="+name+"="+password);
                    bw.newLine();
                    bw.flush();

                    String message = br.readLine();
                    System.out.println(message);
                    if("登录成功".equals(message)){
                        new Thread(new ClientThread(br)).start();
                        while(true){
                            System.out.println("请输入消息:");
                            String s1 = sc.nextLine();
                            bw.write(s1);
                            bw.newLine();
                            bw.flush();
                        }
                    }
                    break;
                case "2":
                    System.out.println("注册");
                    System.out.print("请注册用户名:");
                    String username = sc.nextLine();
                    System.out.print("请输入注册密码:");
                    String pwd = sc.nextLine();
                    bw.write("2"+"="+username+"="+pwd);
                    bw.newLine();
                    bw.flush();

                    String regist = br.readLine();
                    System.out.println(regist);
                    break;
                default:
                    System.out.println("输入有误请重新输入");
            }
        }
    }
}
