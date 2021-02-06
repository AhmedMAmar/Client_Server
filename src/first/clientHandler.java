package first;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;


public class clientHandler implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ObjectOutputStream oout;

    public clientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream() , true);

    }


    @Override
    public void run() {
        try {
            ArrayList<String> result = new ArrayList<>();

            while (this.in.ready()) {
                result.add(this.in.readLine());
            }


            String myUrl = "jdbc:mysql://localhost:3388/mulitreademployee";
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
                if (result.size()==3) {
                    java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3388/mulitreademployee","root","");
                    PreparedStatement Pstatement=connection.prepareStatement("insert into users (matricule ,nom ,prenom) values(?,?,?)");
                    Pstatement.setString(1,result.get(0));
                    Pstatement.setString(2,result.get(1));
                    Pstatement.setString(3,result.get(3));
                    Pstatement.execute();
                    /*
                    String SQL = "select * from users";
                    PreparedStatement stmt = null;
                    ResultSet rs = stmt.executeQuery(SQL);
                    String[] columnNames = {"matricule", "nom" , "prenom"};
                    String m = "",n = "" ,p="" ;
                    while(rs.next()) {
                        m = rs.getString("matricule");
                        n = rs.getString("nom");
                        p = rs.getString("prenom");
                        Object[][]data = {{m,n,p}};
                        //jTable1 = new JTable(data, columnNames);

                    }*/
                    out.println("nouveau empolye a ete inserer");
                } else if(result.size()==1){
                    String query = "delete from users where matricule = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, 3);

                    // execute the preparedstatement
                    preparedStmt.execute();

                    conn.close();
                    out.println("operation completer");
                }
            } catch (IOException | SQLException ioException) {
            ioException.printStackTrace();
        }
    }
    }
