import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.DoubleToIntFunction;

public class Echoer extends Thread {

    private Socket socket;

    public Echoer(Socket socket){
        this.socket = socket;
        System.out.println("Client connected");
    }

    @Override
    public void run() {

        try{

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            while(true) {
                String echoString = input.readLine();
                System.out.println("Received input: \"" + echoString + "\" on " + Thread.currentThread().getName());
                if(echoString.equals("exit")){
                    System.out.println("Client disconnected");
                    break;
                }

                try{
                    Thread.sleep(10000);
                }catch (InterruptedException e){
                    System.out.println("Thread interrputed: " + Thread.currentThread().getName());
                }

                output.println("Echo from server: " + echoString);
            }
        }catch (IOException e){
            System.out.println("Oops!" + e.getMessage());
        } finally {
            try{
                socket.close();
            }
            catch (IOException e){
                //Oh, well!
            }
        }
    }
}
