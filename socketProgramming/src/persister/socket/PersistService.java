package persister.socket;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PersistService {
    // initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private BufferedWriter buffrdWriter = null;
    private File file = null;
    private FileWriter fileWriter = null;

    private int portNum;
    private String outputFilePath = "";
    private static PersistService persistSvcObj = new PersistService();

    private PersistService() {

    }

    public static PersistService getInstance(int inputDataPortNum, String filePath) {
        persistSvcObj.portNum = inputDataPortNum;
        persistSvcObj.outputFilePath = filePath;

        return persistSvcObj;
    }

    public void initSocketConnection() throws IOException {

        server = new ServerSocket(portNum);

        socket = server.accept();

        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void processData() {
        try {
            file = new File(outputFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            buffrdWriter = new BufferedWriter(fileWriter);
            while (in.available() > 0) {
                buffrdWriter.write(in.readUTF() + "\n");
                buffrdWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (buffrdWriter != null)
                    buffrdWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            socket.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }
}
