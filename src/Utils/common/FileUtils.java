package Utils.common;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtils {


    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 100 * 1000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码

    /**
     *        上传文件到服务器
     *
     * @param file
     *            需要上传的文件
     * @param RequestURL
     *            文件服务器的rul
     * @return 返回响应的内容
     *
     */
    public static String uploadFile(File file, String RequestURL) throws IOException {
        String result = null;
        String BOUNDARY = "letv"; // 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型

        try {
            URL  url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("Charset", CHARSET); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
            if (file != null) {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                DataOutputStream dos = new DataOutputStream(
                        conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
                        + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/ctet-stream" + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024 * 1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();

                // if(res==200)
                // {

                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
                result = new String(result.getBytes("iso8859-1"), "utf-8");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

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
                showAvailableBytes(in);
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

        /**
         * 随机读取文件内容
         */
        public static void readFileByRandomAccess(String fileName) {
            RandomAccessFile randomFile = null;
            try {
                System.out.println("随机读取一段文件内容：");
                // 打开一个随机访问文件流，按只读方式
                randomFile = new RandomAccessFile(fileName, "r");
                // 文件长度，字节数
                long fileLength = randomFile.length();
                // 读文件的起始位置
                int beginIndex = (fileLength > 4) ? 4 : 0;
                // 将读文件的开始位置移到beginIndex位置。
                randomFile.seek(beginIndex);
                byte[] bytes = new byte[10];
                int byteread = 0;
                // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
                // 将一次读取的字节数赋给byteread
                while ((byteread = randomFile.read(bytes)) != -1) {
                    System.out.write(bytes, 0, byteread);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (randomFile != null) {
                    try {
                        randomFile.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }

        /**
         * 显示输入流中还剩的字节数
         */
        private static void showAvailableBytes(InputStream in) {
            try {
                System.out.println("当前字节输入流中的字节数为:" + in.available());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    public static void main(String[] args) {
        String data = "python "+"test1.py\n"+"python "+"test2.py" +
                "\n";
        WriteToFile("/Users/sunjack/freemarker/out/main.sh",data);
        String path = "/Users/sunjack/freemarker/out/main.sh";
        System.out.println(path.substring(path.lastIndexOf("/")));

        System.out.println(readFileByLines("/Users/sunjack/Desktop/error.log"));
    }
}
