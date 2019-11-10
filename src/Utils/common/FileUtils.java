package Utils.common;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtils {
    private static final Logger log = Logger.getLogger(FileUtils.class);

    public static void WriteToFile(String path,String data){
        try{

            File file =new File(path);

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(path,false);
            fileWritter.write(data);
            fileWritter.close();

            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }
    }


        /**
         * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
         */
        public static void readFileByBytes(String fileName) {
            File file = new File(fileName);
            InputStream in = null;
            try {
                System.out.println("以字节为单位读取文件内容，一次读一个字节：");
                // 一次读一个字节
                in = new FileInputStream(file);
                int tempbyte;
                while ((tempbyte = in.read()) != -1) {
                    System.out.write(tempbyte);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            try {
                System.out.println("以字节为单位读取文件内容，一次读多个字节：");
                // 一次读多个字节
                byte[] tempbytes = new byte[100];
                int byteread = 0;
                in = new FileInputStream(fileName);
                // 读入多个字节到字节数组中，byteread为一次读入的字节数
                while ((byteread = in.read(tempbytes)) != -1) {
                    System.out.write(tempbytes, 0, byteread);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }

        /**
         * 以字符为单位读取文件，常用于读文本，数字等类型的文件
         */
        public static void readFileByChars(String fileName) {
            File file = new File(fileName);
            Reader reader = null;
            try {
                System.out.println("以字符为单位读取文件内容，一次读一个字节：");
                // 一次读一个字符
                reader = new InputStreamReader(new FileInputStream(file));
                int tempchar;
                while ((tempchar = reader.read()) != -1) {
                    // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                    // 但如果这两个字符分开显示时，会换两次行。
                    // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                    if (((char) tempchar) != '\r') {
                        System.out.print((char) tempchar);
                    }
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                System.out.println("以字符为单位读取文件内容，一次读多个字节：");
                // 一次读多个字符
                char[] tempchars = new char[30];
                int charread = 0;
                reader = new InputStreamReader(new FileInputStream(fileName));
                // 读入多个字符到字符数组中，charread为一次读取字符数
                while ((charread = reader.read(tempchars)) != -1) {
                    // 同样屏蔽掉\r不显示
                    if ((charread == tempchars.length)
                            && (tempchars[tempchars.length - 1] != '\r')) {
                        System.out.print(tempchars);
                    } else {
                        for (int i = 0; i < charread; i++) {
                            if (tempchars[i] == '\r') {
                                continue;
                            } else {
                                System.out.print(tempchars[i]);
                            }
                        }
                    }
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }

        /**
         * 以行为单位读取文件，常用于读面向行的格式化文件
         */
        public static String readFileByLines(String fileName) {
            File file = new File(fileName);
            BufferedReader reader = null;
            String result = "";
            try {
                System.out.println("以行为单位读取文件内容，一次读一整行：");
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    // 显示行号
                    //System.out.println("line " + line + ": " + tempString);
                    result = result +"\n"+tempString;
                    line++;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
            return result;
        }

        //创建文件夹
        public static void createFileFloder(String path) {
            File file = new File(path);
            if (!file.exists()) {//如果文件夹不存在
                file.mkdir();//创建文件夹
            }
        }

        //级联删除文件夹
        public static void deleteFile(String path){
            File file = new File(path);
            log.debug("deleting path: "+path);
            if (file == null || !file.exists()){
                log.error(path + "file is not exists!");
                return;
            }
            if(file.isFile()){
                file.delete();
                return;
            }
            //取得这个目录下的所有子文件对象
            File[] files = file.listFiles();
            //遍历该目录下的文件对象
            for (File f: files){
                //打印文件名
                String name = file.getName();
                log.debug("file is "+name);
                //判断子目录是否存在子目录,如果是文件则删除
                if (f.isDirectory()){
                    deleteFile(f.getPath());
                }else {
                    f.delete();
                }
            }
            //删除空文件夹  for循环已经把上一层节点的目录清空。
            file.delete();
        }


    public static void main(String[] args) {
//        String data = "python "+"test1.py\n"+"python "+"test2.py" +
//                "\n";
//        WriteToFile("/Users/sunjack/freemarker/out/main.sh",data);
//        String path = "/Users/sunjack/freemarker/out/main.sh";
//        System.out.println(path.substring(path.lastIndexOf("/")));
//
//        System.out.println(readFileByLines("/Users/sunjack/Desktop/error.log"));

        FileUtils.createFileFloder("/Users/sunjack/test03");
        FileUtils.createFileFloder("/Users/sunjack/test03/test");
        FileUtils.deleteFile("/Users/sunjack/test03");
    }
}
