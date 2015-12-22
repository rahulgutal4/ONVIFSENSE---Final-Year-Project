/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CloudPack;

import LibPack.DeviceDetails;
import LibPack.MyImage;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "MyService")
public class MyService {

    Connection con;

    @WebMethod(operationName = "StreamVideo")
    public MyImage StreamVideo(@WebParam(name = "eIn") int eIn) {
        initDatabase();
        MyImage mi = new MyImage();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try {
            String ssql = "select * from deviceDetails where id ='" + eIn + "'";
            System.out.println(ssql);
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery(ssql);
            while (rs.next()) {
                mi.service = rs.getString(5);
            }
            
            ssql = "select * from userdata where devID =" + eIn;
            stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            rs = stmt.executeQuery(ssql);
            if (rs.next()) {
                mi.ww = rs.getInt(4);
                mi.hh = rs.getInt(5);
                long DB = rs.getLong(6);
                long curr = Calendar.getInstance().getTimeInMillis();
                if (curr - DB > 5000) {
                    mi.State = "InActive";
                } else {
                    mi.State = "Active";
                }
                Blob b = rs.getBlob(7);
                mi.img = new int[mi.ww * mi.hh];
                byte[] temp;
                temp = b.getBytes(1, (int) b.length());
//                for (int i = 0, j = 0; i < temp.length; i += 3, j++) {
//                    int col = ((temp[i] << 16) | (temp[i + 1] << 8) | (temp[i + 2]));
//                    mi.img[j] = col;
//                }
                int cnt = 0;
                int k = 0;
                for (int y = 0; y < mi.hh; y++) {
                    for (int x = 0; x < mi.ww; x++) {
                        int col = (temp[cnt] & 0xff) << 16;//getting R
                        cnt++;
                        col |= (temp[cnt] & 0xff) << 8;//appending G
                        cnt++;//first assigning to col n then shifting is performed
                        col |= (temp[cnt] & 0xff);//appending B
                        cnt++;
                        mi.img[k] = col;
                        k++;
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return mi;
    }

    @WebMethod(operationName = "GetAllDevices")
    public ArrayList<DeviceDetails> GetAllDevices() {
        initDatabase();
        ArrayList<DeviceDetails> allDevices = new ArrayList<DeviceDetails>();
        DeviceDetails singleDevice;

        try {
            String ssql = "select * from userdata where state = 1";
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery(ssql);
            while (rs.next()) {
                singleDevice = new DeviceDetails();
                singleDevice.DeviceName = rs.getString(3);
                singleDevice.deviceID = rs.getInt(2);
                allDevices.add(singleDevice);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return allDevices;
    }

    @WebMethod(operationName = "GetDevicesByDeviceName")
    public ArrayList<DeviceDetails> GetDevicesByDeviceName(String eIn) {
        initDatabase();
        ArrayList<DeviceDetails> allDevices = new ArrayList<DeviceDetails>();
        DeviceDetails singleDevice;
        try {
            String ssql = "select * from userdata where state = 1 and deviceName ='" + eIn + "'";
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery(ssql);
            while (rs.next()) {
                singleDevice = new DeviceDetails();
                singleDevice.DeviceName = rs.getString(3);
                singleDevice.deviceID = rs.getInt(2);
                allDevices.add(singleDevice);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return allDevices;
    }

    @WebMethod(operationName = "GetDevicesByDeviceType")
    public ArrayList<DeviceDetails> GetDevicesByDeviceType(String eIn) {
        initDatabase();
        ArrayList<DeviceDetails> allDevices = new ArrayList<DeviceDetails>();
        DeviceDetails singleDevice;
        ArrayList<String> tempDevices = new ArrayList<String>();
        try {
            String ssql = "select * from deviceDetails where devType ='" + eIn + "'";
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery(ssql);
            while (rs.next()) {
                tempDevices.add(rs.getString(2));
            }
            for (int i = 0; i < tempDevices.size(); i++) {
                ssql = "select * from userdata where state = 1 and deviceName='" + tempDevices.get(i) + "'";
                stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
                rs = stmt.executeQuery(ssql);
                while (rs.next()) {
                    singleDevice = new DeviceDetails();
                    singleDevice.DeviceName = rs.getString(3);
                    singleDevice.deviceID = rs.getInt(2);
                    allDevices.add(singleDevice);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return allDevices;
    }

    @WebMethod(operationName = "GetDevicesByServiceType")
    public ArrayList<DeviceDetails> GetDevicesByServiceType(String eIn) {
        initDatabase();
        ArrayList<DeviceDetails> allDevices = new ArrayList<DeviceDetails>();
        DeviceDetails singleDevice;
        ArrayList<String> tempDevices = new ArrayList<String>();
        System.out.println("In: " + eIn);
        StringTokenizer st = new StringTokenizer(eIn, ",");
        String type = "", serv = "";
        while (st.hasMoreTokens()) {
            type = st.nextToken();
            serv = st.nextToken();
        }
        try {
            String ssql = "select * from deviceDetails where serviceType ='" + serv + "' and devtype = '" + type + "'";
            if (type.equals("SELECT") || serv.equals("SELECT")) {
                ssql = "select * from deviceDetails where serviceType ='" + serv + "' or devtype = '" + type + "'";
            }
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery(ssql);
            while (rs.next()) {
                tempDevices.add(rs.getString(2));
            }
            for (int i = 0; i < tempDevices.size(); i++) {
                ssql = "select * from userdata where state = 1 and deviceName='" + tempDevices.get(i) + "'";
                stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
                rs = stmt.executeQuery(ssql);
                while (rs.next()) {
                    singleDevice = new DeviceDetails();
                    singleDevice.DeviceName = rs.getString(3);
                    singleDevice.deviceID = rs.getInt(2);
                    allDevices.add(singleDevice);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return allDevices;
    }

    private void initDatabase() {

        String connection = "jdbc:mysql://localhost/8838DB";
        String user = "root";
        String password = "pass";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(connection, user, password);
            new JavaLib.LoadForm();
//            System.out.println("Database Connection OK");
        } catch (Exception e) {
            System.out.println("Error opening database : " + e);
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SignUp")
    public String SignUp(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "email") String email, @WebParam(name = "mob") String mob) {
        initDatabase();
        try {
            String ssql = "insert into client values('" + username + "','";
            ssql += password + "','" + mob + "','" + email + "')";
            System.out.println(ssql);
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.executeUpdate(ssql);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "CheckUID")
    public String CheckUID(@WebParam(name = "eIn") String eIn) {
        initDatabase();
        try {
            String ssql = "select *  from client where ClientId ='" + eIn + "'";
            System.out.println(ssql);
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery(ssql);
            if (rs.next()) {
                return "UID(UN-AVAILABLE)";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "UID(AVAILABLE)";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        initDatabase();
        try {
            String ssql = "select *  from client where ClientId ='" + username + "' and password='" + password + "'";
            System.out.println(ssql);
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery(ssql);
            if (rs.next()) {
                return "Login Successful";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "Login Unsuccessful";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getDeviceDetails")
    public DeviceDetails getDeviceDetails(@WebParam(name = "eIn") int eIn) {
        initDatabase();
        DeviceDetails dd = new DeviceDetails();
        try {
            String ssql = "select *  from deviceDetails where Id =" + eIn;
            System.out.println(ssql);
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery(ssql);
            if (rs.next()) {
                dd.deviceID = rs.getInt(1);
                dd.DeviceName = rs.getString(2);
                dd.type = rs.getString(3);
                dd.location = rs.getString(4);
                dd.service = rs.getString(5);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return dd;
    }
}
