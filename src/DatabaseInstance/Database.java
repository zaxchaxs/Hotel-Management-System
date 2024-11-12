/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseInstance;

/**
 *
 * @author USER
 */
import Connections.JDBCConnection;
import DataModels.Room;
import MainMenu.SignIn;
import Sessions.SessionManager;
import DataModels.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

public class Database {
    private Connection connect;
    PreparedStatement pst = null;
    Statement statment = null;
    ResultSet result = null;
    JDBCConnection jdbcConnect = new JDBCConnection();

    public Database() {
        try {
            Class.forName(jdbcConnect.classDriver);
            connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
        } catch (
            ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return connect;
    }
    
    public String getUser(String username, String password) {
        String role = null;
        String query = "SELECT * FROM employee WHERE username=? AND password=?";
        ArrayList<User> result = new ArrayList<User>();
        
        try (PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    
                    role = rs.getString("role");
                    
                    // masukin data user ke sessions manager;
                    User currUser = new User(rs.getString("username"), rs.getString("email"), rs.getString("name"), role, rs.getString("status"), rs.getInt("id"));
                    result.add(currUser);
                    SessionManager.setCurrUser(currUser);
                    return role;
                } else {
                    JOptionPane.showMessageDialog(null, "Username or Password Is Incorrect!");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public DatabaseResultResponse registUser(String email, String name, String username, String password, String role) {
        String query = "insert into users(email, name, username, password, role) values (?, ?, ?, ?, ?)";
        
        try {
            connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            Class.forName(jdbcConnect.classDriver);
            statment = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            // cek email and username
            pst = connect.prepareStatement("select * from users where email=? or username=?");
            pst.setString(1, email);
            pst.setString(2, username);
            result = pst.executeQuery();
            if(result.next()) {
                return new DatabaseResultResponse(400, "failed", null);
            };
            
            // Insert new user
            pst = connect.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, name);
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, "staff");
            pst.executeUpdate();
            
            return new DatabaseResultResponse(200, "success", null);
            
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, e);
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }
    };
    
    public DatabaseResultResponse postUser(String username, String name, String email, String no_phone, String role) {
        String query = "INSERT INTO users (username, name, email, no_phone, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, no_phone);
            pst.setString(5, role);

            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0) {
                return new DatabaseResultResponse(200, "success", null);
            } else {
                return new DatabaseResultResponse(400, "failed", null);
            }
        } catch (SQLException e) {
            System.err.println("Error inserting user: " + e.getMessage());
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }
    }


    public boolean postUser(String username, String name, String email, String no_phone, JComboBox<String> role) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ambilKamar ambilKamar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public class ambilKamar {
        private Connection connect;

        public ambilKamar(Connection connect) {
            this.connect = connect;
        }

        public ResultSet getKamars() {
            String query = "SELECT * FROM kamar";
            ResultSet rs = null;

            try {
                PreparedStatement pst = connect.prepareStatement(query);
                rs = pst.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            }
            return rs;
        }
    }
    
    
    public boolean postKamar(String room_number, String room_type, String price, String status) {
        String query = "INSERT INTO kamar (room_number, room_type, price, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, room_number);
            pst.setString(2, room_type);
            pst.setString(3, price);
            pst.setString(4, status);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public DatabaseResultResponse addRoom(String id, String name, int price, String type) {
      String query = "insert into room values (?, ?, ?, ?, ?)";
      try(PreparedStatement pst = connect.prepareStatement(query)) {
          pst.setString(1, id);
          pst.setString(2, name);
          pst.setInt(3, price);
          pst.setString(4, type);
          pst.setString(5, "empty");
          
          int rowsAffected = pst.executeUpdate();
          if(rowsAffected > 0) {
              return new DatabaseResultResponse(200, "success", null);
          } else {
              return new DatabaseResultResponse(400, "failed", null);
          }
      } catch (SQLException e) {
          e.printStackTrace();
          return new DatabaseResultResponse(500, e.getMessage(), null);
      }
    };
    
    public DatabaseResultResponse deleteRoom(String roomId) {
        String query = "delete from room where id = ?";
        try(PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, roomId);
            
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0) {
                return new DatabaseResultResponse(200, "success", null);
            } else {
                return new DatabaseResultResponse(400, "failed", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }
    };
    
    public DatabaseResultResponse getRooms(String type) {
        String query = "SELECT * from room where type = ?";
        ArrayList<Room> listRoom = new ArrayList<>();
        
        try(Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement statment = connect.prepareStatement(query)) {
            statment.setString(1, type);
            
            try(ResultSet result = statment.executeQuery()) {
                while(result.next()) {
                    String id = result.getString("id");
                    String name = result.getString("name");
                    int price = result.getInt("price");
                    String typeRoom = result.getString("type");
                    String status = result.getString("status");
                    
                    Room room = new Room(id, name, typeRoom, status, price);
                    listRoom.add(room);
                }
            };
            
            return new DatabaseResultResponse(200, "success", listRoom);
        } catch(SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }
    };
    
    public DatabaseResultResponse getStaff(String statusUser) {
        String query = "SELECT id, email, username, name, role, status FROM employee WHERE role = ? AND status = ?";
        ArrayList<User> listStaff = new ArrayList<>();
        
        try(Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement statment = connect.prepareStatement(query)) {
            statment.setString(1, "staff");
            statment.setString(2, statusUser);
            
            try(ResultSet result = statment.executeQuery()) {
                while(result.next()) {
                    int id = result.getInt("id");
                    String email = result.getString("email");
                    String username = result.getString("username");
                    String name = result.getString("name");
                    String role = result.getString("role");
                    String status = result.getString("status");
                    
                    User user = new User(username, email, name, role, status, id);
                    listStaff.add(user);
                }
            };
            
            return new DatabaseResultResponse(200, "success", listStaff);
        } catch(SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }
    };
    
    public DatabaseResultResponse deleteStaff(String username) {
        String query = "delete from employee where username = ?";
        try(PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, username);
            
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0) {
                return new DatabaseResultResponse(200, "success", null);
            } else {
                return new DatabaseResultResponse(400, "failed", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }
    };
    
    public DatabaseResultResponse approveStaff(String username) {
        String query = "update employee set status = ? where username = ?";
        try(PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, "approved");
            pst.setString(2, username);
            
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0) {
                return new DatabaseResultResponse(200, "success", null);
            } else {
                return new DatabaseResultResponse(400, "failed", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }    
    }
    
    public void closeConnection() {
        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
