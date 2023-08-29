package org.itstep.myClassWork.august29;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class August29Files implements Runnable
{
    @Override
    public void run() {
        saveObj();
        loadObj();
    }

    private void saveObj () {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat")))
        {
            Person p = new Person("Sam", 33);
            oos.writeObject(p);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void loadObj() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat")))
        {
            Person p=(Person)ois.readObject();
            System.out.printf("Name: %s \t Age: %d \n", p.getName(), p.getAge());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void writeFile(){
        try(FileWriter writer = new FileWriter("notes.txt", false))
        {
            // запись всей строки
            String text = "Hello World!";
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.flush();

            writer.write(" File Open ");
            writer.flush(); // Будет вызван в момент закрытия файла
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    private void readFileByChar() {
        try(FileReader reader = new FileReader("notes.txt"))
        {
            // читаем посимвольно
            int c;
            StringBuilder sb = new StringBuilder();
            while((c=reader.read())!=-1){
                System.out.print((char)c);
                sb.append((char) c);
            }
            System.out.println(sb.toString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void writeFileOutputStream() {
        String text = "Hello world!"; // строка для записи

        try(FileOutputStream fos=new FileOutputStream("notes.txt"))
        {
            // перевод строки в байты
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
            System.out.println("The file has been written");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void readFileInputStreamToBuffer() {
        try(FileInputStream fin=new FileInputStream("notes.txt"))
        {
            // Лучший вариант чтения файла с помощью потока - это подготовить буфер соответствующего размера
            // Например - получить размер файла - и создать для него буффер в оперативной памяти
            File file = new File("notes.txt"); // Укажите путь к вашему файлу
            int fileSizeBytes = (int) file.length();
            byte[] buffer = new byte[fileSizeBytes];

            int count;
            while((count=fin.read(buffer))!=-1){
//                System.out.println(count);
//                buffer[count] = '\n';
//                for(int i=0; i<count;i++){
//                    System.out.print((char)buffer[i]);
//                }
            }
            String str = new String(buffer, StandardCharsets.UTF_8);
            System.out.println(str);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    private void readFileInputStream() {
        try(FileInputStream fin=new FileInputStream("notes.txt"))
        {
            int i;
            while((i=fin.read())!=-1){
                System.out.print((char)i);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


}
