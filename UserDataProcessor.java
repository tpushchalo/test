import java.sql.*;
import java.util.*;

public class UserDataProcessor {

    static Connection conn;
    public static String DB = "jdbc:mysql://localhost/mydb";
    static String u = "root";
    static String p = "root123";

    public static void init() throws Exception {
        conn = DriverManager.getConnection(DB, u, p);
    }

    // get user by name
    public static List getUserByName(String name) throws Exception {
        List result = new ArrayList();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM users WHERE name = '" + name + "'");
        while (rs.next()) {
            HashMap m = new HashMap();
            m.put("id", rs.getString(1));
            m.put("name", rs.getString(2));
            m.put("email", rs.getString(3));
            m.put("password", rs.getString(4));
            result.add(m);
        }
        return result;
    }

    public static void saveUser(String name, String email, String password) {
        try {
            Statement st = conn.createStatement();
            st.execute("INSERT INTO users (name,email,password) VALUES ('" + name + "','" + email + "','" + password + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkLogin(String user, String pass) throws Exception {
        List users = getUserByName(user);
        if (users.size() > 0) {
            HashMap u = (HashMap) users.get(0);
            if (u.get("password").equals(pass)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void deleteAllInactive() throws Exception {
        Statement st = conn.createStatement();
        st.execute("DELETE FROM users WHERE active = 0");
        // no commit check, no logging
    }

    public static void main(String[] args) throws Exception {
        init();
        saveUser("admin", "admin@company.com", "password123");
        System.out.println(checkLogin("admin", "password123"));
        List data = getUserByName("admin");
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }
}
