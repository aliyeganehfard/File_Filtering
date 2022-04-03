import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String soursUrl = "F:\\Java-programming\\java spring\\git\\before.txt";
        String destinationUrl = "F:\\Java-programming\\java spring\\git\\next.txt";
        String type = ".mp4";
        String line;
        long size = 0;
//        Scanner scanner = new Scanner(System.in);
        try {
            File file = new File(soursUrl);
            Scanner reader = new Scanner(file);
            FileWriter writer = new FileWriter(destinationUrl);
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                if (!isUrl(line))
                    continue;
                if (getExtension(line).equals(type)) {
                    writer.append(line).append("\n");
//                    size += getFileSize(Objects.requireNonNull(convertToUrl(line)));
                }
            }
            writer.close();
//            System.out.println(size/1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int getFileSize(URL url) {
        URLConnection conn = null;
        try {
            conn = url.openConnection();
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("HEAD");
            }
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).disconnect();
            }
        }
    }

    public static String getExtension(String url) {
        return url.substring(url.lastIndexOf("."));
    }

    public static boolean isUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static URL convertToUrl(String url) {
        try {
//            new URL(url).toURI();
            return new URL(url);
        } catch (Exception e) {
            return null;
        }
    }
}
