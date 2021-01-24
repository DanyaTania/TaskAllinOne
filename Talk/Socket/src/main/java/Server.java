//package main.java;

import com.example.dto.TextReader;
import texthandler.chainparser.TextParser;
import texthandler.exception.InitializationException;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.isNumber;

/**
 * проект реализует консольный многопользовательский чат.
 * вход в программу запуска сервера - в классе Server.
 * @author izotopraspadov, the tech
 * @version 2.0
 */



class ServerSomthing extends Thread {
    public static Object Text;
    //public     String Text="";
static class TextString{
    private String text;

    public void setText(String text){
        this.text=text;
    }
    public  String getText(){
        return text;
    }

}

    static class TaskNumber {
        private static int task;
        public void setTask(int n){
            this.task=n;
        }
        public static int getTask(){
            return task;
        }
    }
    //String taskNumber="";
    
    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток завписи в сокет

    /**
     * для общения с клиентом необходим сокет (адресные данные)
     * @param socket
     * @throws IOException
     */
    
    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        // если потоку ввода/вывода приведут к генерированию искдючения, оно проброситься дальше
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Server.getStory().printStory(out); // поток вывода передаётся для передачи истории последних 10
        // сооюбщений новому поключению
        start(); // вызываем run()
    }
    @Override
    public void run() {
        String word;
        String answer = null;
        try {
            // первое сообщение отправленное сюда - это yes /no
            word = in.readLine();  word = in.readLine();
            int n=0;
           if (isNumber(word)) {
               n=Integer.parseInt(word);}
            out.write(" \n");

            out.flush();
            try {int i=0;
                word = in.readLine();
                n=0;
                if (isNumber(word)) {n=Integer.parseInt(word);}
//                out.write(word + "+2\n");
                while (!isNumber(word)){

                word = in.readLine(); out.write(word + i+"\n");
i++;
                out.flush();
                if (word.contains("2")){ out.write(word + "!!!\n");}}
            }catch (IOException ignored) {}

            try {
                out.write(word + "\n");

                out.flush(); // flush() нужен для выталкивания оставшихся данных
                // если такие есть, и очистки потока для дьнейших нужд
            } catch (IOException ignored) {}
            try {
                while (true) {boolean isStart=false;
                    word = in.readLine();//считываем continue
                  if (word.contains("continue")){

                  word = in.readLine();




                  out.write(word+" Lets go!!!\n");
                      ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("Externals.out"));
                      TaskNumber taskNumber = new TaskNumber();
                      taskNumber.setTask(Integer.parseInt((in2.readObject()).toString()));
                      in2.close();
                      out.write(taskNumber.getTask() +" задание");//в taskNumber номер задания
                    if(taskNumber.getTask()==3) {
                         //TextParser textParser=new TextParser(Text.toString().split(""));
                         out.write(Text.toString().toUpperCase()+"<>");
                     }

                  isStart=true;  }

                        if (isStart) {

                            out.write(" \n");
                        }
                        //}
                    if(word.equals("stop")) {
                        this.downService(); //
                        break; // если пришла пустая строка - выходим из цикла прослушки
                    }
                    System.out.println("Echoing: " + word);
                    Server.getStory().addStoryEl(word);
                    for (ServerSomthing vr : Server.serverList) {
                        vr.send(word); // отослать принятое сообщение с привязанного клиента всем остальным влючая его
                    }
                }
            } catch (NullPointerException ignored) {} catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            this.downService();
        }
    }
    
    /**
     * отсылка одного сообщения клиенту по указанному потоку
     * @param msg
     */
    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
        
    }
    
    /**
     * закрытие сервера
     * прерывание себя как нити и удаление из списка нитей
     */
    private void downService() {
            try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerSomthing vr : Server.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }
}

/**
 * класс хранящий в ссылочном приватном
 * списке информацию о последних 10 (или меньше) сообщениях
 */

class Story {
    
    private static LinkedList<String> story = new LinkedList<>();

    public static void getLastAnswer() {
    }

    /**
     * добавить новый элемент в список
     * @param el
     */
    
    public void addStoryEl(String el) {
        // если сообщений больше 3, удаляем первое и добавляем новое
        // иначе просто добавить
        if (story.size() >= 3) {
            story.removeFirst();
            story.add(el);
        } else {
            story.add(el);
        }
    }
    

    public static void getLastAnswer(BufferedWriter writer){
        if(story.size() > 0) {
            String vr=story.getLast();
            try {
            writer.write(story.getFirst()+" answer "+vr+"<<<"+story.getLast());
                writer.flush();

    } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /**
     * отсылаем последовательно каждое сообщение из списка
     * в поток вывода данному клиенту (новому подключению)
     * @param writer
     */
    public static void printStory(BufferedWriter writer) {
        if(story.size() > 0) {
            try {
                writer.write("History messages" + "\n");
                for (String vr : story) {
                    writer.write(vr + "\n");
                }
                writer.write("/...." + "\n");
                writer.flush();
            } catch (IOException ignored) {}
            
        }
        
    }
}

public class Server {
    private final static String path = "E://new_doc//Java//Talk//Socket//src//main//resources";
    private final static String file="resource.txt";
    public static final int PORT = 8080;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>(); // список всех нитей - экземпляров
    // сервера, слушающих каждый своего клиента
    private static Story story; // история переписки
    private final static String pathFile = "E://new_doc//Java//Talk//Socket//src//main//resources//resource.txt";
    private String taskNumber;

    /**
     * @param args
     * @throws IOException
     */
    
    public static void main(String[] args) throws IOException, InitializationException {
        ServerSocket server = new ServerSocket(PORT);
        setStory(new Story());
        System.out.println("Server Started");
        //TextReader textReader=new TextReader(path,file);
        texthandler.reader.TextReader textReader=new texthandler.reader.TextReader();
        ServerSomthing.TextString textString=new ServerSomthing.TextString();
               textString.setText(textReader.readText(pathFile));

        //List<String> allLines = textReader.takeAll();// в allLines все строки из текста
       // System.out.println(allLines);
        ServerSomthing.Text=textString.getText();
        System.out.println(textString.getText());

      if (       TaskNumber.getTask()==3) {System.out.println("3");}
      else{System.out.println("33");}

        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket)); // добавить новое соединенние в список
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }

    public static Story getStory() {
        return story;
    }

    public static void setStory(Story story) {
        Server.story = story;
    }
}