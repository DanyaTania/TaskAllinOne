//package main.java;

import texthandler.chainparser.LexemeParser;
import texthandler.chainparser.ParagraphParser;
import texthandler.chainparser.SentenceParser;
import texthandler.entity.TextComponent;
import texthandler.entity.TextComposite;
import texthandler.exception.InitializationException;

import java.io.*;
import java.net.*;
import java.security.cert.CertificateRevokedException;
import java.util.*;

import static java.util.Collections.unmodifiableList;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;
import static texthandler.entity.ComponentType.LEXEME;
import static texthandler.entity.ComponentType.TEXT;

/**
 * проект реализует консольный многопользовательский чат.
 * вход в программу запуска сервера - в классе Server.
 * @author izotopraspadov, the tech
 * @version 2.0
 */
/*
class Logout implements Externalizable{
        //Externalizable {
    boolean[] index=null;

   // private String logout;

    public Logout(boolean[] index) {
        //this.logout = logout;
        this.index=new boolean[index.length];
        for (int i=0;i<index.length;i++)
        {this.index[i] = index[i];}
        //this.password = password;
    }

    public  void setIndex(boolean[] index){
        this.index=new boolean[index.length];
        for (int i=0;i<index.length;i++)
        {this.index[i] = index[i];}

    }
    public boolean[] getIndex(){
        return index;
    }
    public  Logout() {
    }

@Override
public String toString() {
       // return  logout;
        return null;}

@Override
public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(index);
        }

@Override
public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
   index = (boolean[]) in.readObject();
        }
        }
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
            boolean notStop=true;
            try {
                while (notStop) {
                    boolean isStart = false;
                    word = in.readLine();//считываем continue
                    if (word.contains("continue")) {

                        word = in.readLine();

                        out.write(word + " Lets go!!!\n");
                        ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("Externals.out"));
                        TaskNumber taskNumber = new TaskNumber();
                        taskNumber.setTask(Integer.parseInt((in2.readObject()).toString()));
                        in2.close();
                        out.write(taskNumber.getTask() + " задание");//в taskNumber номер задания

                        int subTask = taskNumber.getTask();

                        //ParagraphParser paragraphParser;//разбиваем на предложения
                        //  SentenceParser sentenceParser;//разбиваем на слова
                        //  LexemeParser lexemeParser;
                        //    Map<String,Integer> map=new HashMap<String,Integer>();//для хранения частей текста
                        //   ArrayList<TextComponent> arrayList;// анализируемый текст по предложениям в arrayList
                        //    String result="";
                        // Set<String> objectsIds = new HashSet<>();


                        ParagraphParser paragraphParser;
                        paragraphParser = new ParagraphParser();//разбиваем на предложения

                        ArrayList<TextComponent> arrayList;// анализируемый текст, разбитый на предложения, в arrayList
                        arrayList = paragraphParser.parse(Text.toString(), new TextComposite(LEXEME)).getComponents();

 /***************************************************************************************************/

   /* 1 задание из 16     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!**************************             */
  //Найти наибольшее количество предложений текста, в которых есть одинаковые слова


   /***************************************************************************************************/
//switch (subTask){
                        //  case 1: //{
                        if (subTask == 1) {                     //TextParser textParser=new TextParser(Text.toString().split(""));

                            HashSet<TextComponent> differentSentences = new HashSet<>();//множество различных предложений в тексте
                            HashMap<String, Integer> sentencesWithRepeatedWords = new HashMap<>();
                        //    sentencesWithRepeatedWords=null;
                            //  out.write(arrayList.toString());
                            SentenceParser sentenceParser;//разбиваем на слова
                            for (TextComponent sentence : arrayList) {

                                sentenceParser = new SentenceParser();// предложнеия складываем в  HashSet: исключаются дублируюшиеся

                                differentSentences.add(sentence);//sentenceParser.parse(sentence.toString(), new TextComposite(LEXEME)).getComponents().toString());


                            }
                            out.write(differentSentences + "differentSentences\n");
                            //   out.write(differentSentences.toString());
                            sentenceParser = new SentenceParser();


                            //разбиваем на неповторяюшиеся слова, если количество слов во множестве неповторяющихся слов меньше, чем в предложении,
                            //значит в предложении есть неповторяющиеся слова, помечаем эти предложения, сохраняя в значении мэпа число-количество слов в предложении


                            for (Iterator<TextComponent> iter = differentSentences.iterator(); iter.hasNext(); ) {
                                TextComponent sentence = iter.next();

                                ArrayList<TextComponent> wordsInSentence = new ArrayList<>();
                                HashSet<String> differentWordsInSentence = new HashSet<>();
                                wordsInSentence = sentenceParser.parse(sentence.toString(), new TextComposite(TEXT)).getComponents();//слова в одном предложении
                                //если мы их добавим в hashSet, то дублирующиеся в предложении слова "исчезнут"
                                //      out.write(wordsInSentence + "wordsInSentence\n");

                                for (TextComponent words : wordsInSentence) {
                                    differentWordsInSentence.add(words.toString().trim());
                                }
                                //      out.write(differentSentences + "differentSentences\n");

                                //теперь можно сравнить

                                if (differentWordsInSentence.size() < wordsInSentence.size()) {
                                    sentencesWithRepeatedWords.put(wordsInSentence.toString(), wordsInSentence.size());
                                }
                              //  out.write("differentWordsInSentence.size():" + differentWordsInSentence.size() + "wordsInSentence.size()" + wordsInSentence.size() + "\n");
                            }

                            out.write(sentencesWithRepeatedWords+"sentencesWithRepeatedWords\n");

                            int i=sentencesWithRepeatedWords.size()-1;//последний элемент в отсортированном по возрастанию множестве
                 String result = sortByComparator(sentencesWithRepeatedWords).keySet().toArray()[i].toString();

                           result=result.replace("[", "");
                           result = result.replace("]", "");
                            if (sentencesWithRepeatedWords!=null)
                            {out.write("\n Sentence with the largest amount of repeated words :" + result + "\n\n");}
                            else {out.write("We haven't any sentence with the largest amount of repeated words");}
                    //out.write(Text.toString().toUpperCase() + "<>");

                }

     /***************************************************************************************************/

        /* 2 задание из 16     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!**************************             */
      //Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них

     /***************************************************************************************************/


    if (taskNumber.getTask()==2) {
    //    paragraphParser = new ParagraphParser();//разбиваем на предложения
        Map<String,Integer> map=new HashMap<String,Integer>();//для хранения частей текста
      //  ArrayList<TextComponent> arrayList;// анализируемый текст в arrayList
       // arrayList = paragraphParser.parse(Text.toString(), new TextComposite(LEXEME)).getComponents();
        SentenceParser sentenceParser;//разбиваем на слова
        for (TextComponent sentence : arrayList) {
            sentenceParser = new SentenceParser();

            map.put(sentence.toString(), sentenceParser.parse(sentence.toString(), new TextComposite(LEXEME)).getComponents().size());

        }
        String result="";
        result = sortByComparator(map).keySet().toString().replace("[", "");
        result = result.replace("]", "");
        out.write("\n Words in order:"+result+"\n\n");
    }
 /*************************************************************************************************/

 /* 3 задание из 16     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!**************************             */
 //      Найти такое слово в первом предложении, которого нет ни в одном из остальных предложений


 /***************************************************************************************************/

    //case 3: (subTask=2)
    if (taskNumber.getTask()==3) {

       // ParagraphParser paragraphParser;
      //  paragraphParser = new ParagraphParser();//разбиваем на предложения

        //ArrayList<TextComponent> arrayList;// анализируемый текст в arrayList
        //arrayList = paragraphParser.parse(Text.toString(), new TextComposite(LEXEME)).getComponents();


        Iterator<TextComponent> iter = arrayList.iterator();
        SentenceParser sentenceParser;//разбиваем на слова
        sentenceParser = new SentenceParser();

        ArrayList<TextComponent> FirstSentence=new ArrayList<>();
        if (iter.hasNext()) {
            FirstSentence = sentenceParser.parse(iter.next().toString(), new TextComposite(TEXT)).getComponents();
        }


        HashSet<String>  differentWordsInFirstSentence = new HashSet<>();
                for (TextComponent words:FirstSentence){
                differentWordsInFirstSentence.add(words.toString());
                }

       // out.write("\n++" + differentWordsInFirstSentence.toString() + "**\n");
        HashSet<String>  differentWordsInOtherSentences = new HashSet<>();
        while (iter.hasNext()) {
            ArrayList<TextComponent> NextSentence = sentenceParser.parse(iter.next().toString(), new TextComposite(TEXT)).getComponents();
         // в NextSentence очередное предложение
            ArrayList<String> wordsInNextSentence = new ArrayList<>();

            for (TextComponent words : NextSentence) {
                differentWordsInOtherSentences.add(words.toString());
            }
        }



            for (String words : differentWordsInOtherSentences) {

                for (Iterator<String> iter2 = differentWordsInFirstSentence.iterator(); iter2.hasNext();) {

                        if (iter2.next().contains(words))
                        {
                            iter2.remove();
                            break;
                        }

                    }

            }

            String result=differentWordsInFirstSentence.toString().replace("[", "");
        result = result.replace("]", "");


        out.write("\n different words:"+ result+"\n");


    }

  /***************************************************************************************************/

 /* 4 задание из 16     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!**************************             */

 //Во всех вопросительных предложениях текста найти и напечатать без повторений слова заданной длины
   /***************************************************************************************************/

if (taskNumber.getTask()==4) {
    HashSet<String> differentSentencesWithQuestionSign = new HashSet<>();

    //  out.write(arrayList.toString());
    SentenceParser sentenceParser;//разбиваем на слова
    for (TextComponent sentence : arrayList) {
        if (sentence.toString().contains("?")) {
            sentenceParser = new SentenceParser();//вопросительные несовпадающие предложнеия складываем в мэп: предложение+ его длина (количество слов)

            differentSentencesWithQuestionSign.add(sentence.toString());//sentenceParser.parse(sentence.toString(), new TextComposite(LEXEME)).getComponents().toString());

        }
    }
    out.write(differentSentencesWithQuestionSign.toString());
   sentenceParser = new SentenceParser();


    //разбиваем на неповторяюшиеся слова и складываем в мэп: слово его, длина
    int lengthOfWord = 7;//задаем длинну слов, которые нужно вывести !!!!!!!!! оформить, как запрос со стророны клиента

    HashMap<String,Integer> differentWords = new HashMap<>();

    for (Iterator<String> iter = differentSentencesWithQuestionSign.iterator(); iter.hasNext(); ) {
         String st = iter.next().toString();

        ArrayList<TextComponent> wordsInSentence = new ArrayList<>();

        wordsInSentence =  sentenceParser.parse(st, new TextComposite(TEXT)).getComponents();

        for (TextComponent words: wordsInSentence) {
            out.write("\nqqq "+words+" zz \n"+words.toString().length());

                differentWords.put(words.toString(),words.toString().length()-1);
              //  out.write(differentWords.toString());


        }

    }
    out.write("\njjj" + differentWords.toString() + "\n");
    String result = sortByComparator(differentWords).keySet().toString().replace("[", "");
    result = result.replace("]", "");
    out.write("\n Words in order with lengths:"+result+"\n\n");
}
/***************************************************************************************************/

/* 5 задание из 16     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!**************************             */

 //В каждом предложении текста поменять местами первое слово с последним, не изменяя длины предложения
 /***************************************************************************************************/

 if (taskNumber.getTask()==5) {


      ArrayList<TextComponent> arrayList2=new ArrayList<>();// анализируемые слова из предложения в arrayList2
     // arrayList = paragraphParser.parse(Text.toString(), new TextComposite(LEXEME)).getComponents();
     SentenceParser sentenceParser;//разбиваем на слова
     for (TextComponent sentence : arrayList) {
         sentenceParser = new SentenceParser();//в листе sentenceParser слова
         arrayList2=sentenceParser.parse(sentence.toString(), new TextComposite(LEXEME)).getComponents();
         String sentenceReverced=arrayList2.toArray()[arrayList2.size()-1].toString();
         for(int i=1;i<arrayList2.size()-1;i++){
             sentenceReverced+=arrayList2.toArray()[i].toString();
                      }
         sentenceReverced+=arrayList2.toArray()[0].toString();
         /*String temp; //v меняем 1 и последнее слово в предложении
         temp=arrayList2.toArray()[0].toString();
         out.write(temp+"temp\n");
         arrayList2.toArray()[arrayList2.size()-1]=arrayList2.toArray()[0].toString();
         out.write(arrayList2.toArray()[arrayList2.size()-1]+"temp\n");
         arrayList2.toArray()[0]=temp;
     String sentenceReverced= arrayList2.toString();*/
     out.write(sentenceReverced+"dfd\n");
     }


    // out.write(arrayList + " ap\n");
 }
   /***************************************************************************************************/
  /*  case 6:
     /*   paragraphParser=new ParagraphParser();
        LexemeAlphabeticalPrinter lexemeAlphabeticalPrinter=new LexemeAlphabeticalPrinter();
lexemeAlphabeticalPrinter.lexemeAlphabeticalPrinter(paragraphParser.parse(Text.toString(), new TextComposite(LEXEME)));
*/
 /*   case  7:
        lexemeParser=new LexemeParser();
        ArrayList<TextComponent> wordsOfFirstSentence2=lexemeParser.parse(Text.toString(), new TextComposite(TEXT)).getComponents();
      //  out.write(wordsOfFirstSentence2.toString());


            default:
                */
                out.write("\n********************** \n Should you continue? (yes/no)\n*************\n");


       // notStop=false;

       // }


                      }

                    out.write(word+"<><><><><>");
                    if (word.contains("yes")) {
/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        /*Logout logout=new Logout("yes");//result синтаксичесого анализа можно передать
                        ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("Externals.out"));
                        out2.writeObject(logout);

                        out2.close();
*/

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

    public static int[] compareTwoLists(ArrayList list, ArrayList list2){
        int[] res=new int[list2.size()];
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.size(); j++){
                if(list.get(i).equals(list2.get(j)) == true){
                    res[j]=Integer.MAX_VALUE;
                    //System.out.println("Элемент " + i +
                      //      " первого массива равен элементу " + j + " второго массива.");
                }else{ if (res[j]!=Integer.MAX_VALUE) res[j]=j;
                    continue;

                    //System.out.println("Элемент " + i +
                      //      " первого массива не равен элементу " + j + " второго массива.");
                }
            }
        }
    return res;}
    private Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

            List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(
                    unsortMap.entrySet());

            Collections.sort(list, new Comparator<Map.Entry<String, Integer>> () {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });

            HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
            for (Map.Entry<String, Integer> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
            return sortedMap;
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
    private final static String pathFile = path+"//"+file;//E://new_doc//Java//Talk//Socket//src//main//resources//resource.txt";
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