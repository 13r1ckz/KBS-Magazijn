import java.sql.*;
import java.util.ArrayList;
public class DBConnect {
    private ArrayList<ArrayList<Integer>> outer = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> inner = new ArrayList<Integer>();
    public DBConnect(int[] aListArray) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/magazijnrobot", "root", "usbw");
            String query="select * FROM product where product_id in (";
            String temp="";
            for(int i=0;i<aListArray.length;i++)
            {
                temp+=",?";
            }
            temp=temp.replaceFirst(",","");
            temp+=")";
            query=query+temp;
            PreparedStatement statement = conn.prepareStatement(query);
            for (int i = 0; i<aListArray.length;i++){
                statement.setInt(i+1, aListArray[i]);
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int productLocationX = rs.getInt("product_locationX");
                int productLocationY = rs.getInt("product_locationY");
                boolean productRetrieved = rs.getBoolean("product_retrieved");
                System.out.format("%s, %s, %s, %s, %s\n", productID, productName, productLocationX, productLocationY, productRetrieved);

                inner.add(productID);
                inner.add(productLocationX);
                inner.add(productLocationY);
                outer.add(inner);
                inner = new ArrayList<Integer>();
            }
            rs.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            System.out.println(e);
        }
    }
    public ArrayList<ArrayList<Integer>> getLocaties() {
        return outer;
    }
}