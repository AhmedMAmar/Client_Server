package first;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    private static final String server_ip = "127.0.0.1";
    private static final int server_port = 9090;
    //private static final String serverResponse = null;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton chercherButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTable table1;


    public Client() {

        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String Mt = textField1.getText();
                String Prenom = textField2.getText();
                String nom = textField3.getText();

                int Matricule = Integer.parseInt(Mt);

                try {
                    Socket socket = new Socket(server_ip, server_port);
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    while (true) {
                        System.out.println("> ");
                        String cmd = key.readLine();
                        if (cmd.equals("arreter")) break;
                        String serverResponse = input.readLine();
                        System.out.println("server says: " + serverResponse);

                        socket.close();
                        System.exit(0);

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});
        chercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Socket socket = new Socket(server_ip, server_port);
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    while (true) {
                        //System.out.println("> ");
                        //String cmd = key.readLine();
                        //if (cmd.equals("arreter")) break;
                        String serverResponse = input.readLine();
                        System.out.println("server says: " + serverResponse);

                        socket.close();
                        System.exit(0);

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().tabbedPane1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);




        }


        /*
        String serR = input.readLine();
        JOptionPane.showMessageDialog(null ,serR);
        socket.close();
        System.exit(0);
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().tabbedPane1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/

}
