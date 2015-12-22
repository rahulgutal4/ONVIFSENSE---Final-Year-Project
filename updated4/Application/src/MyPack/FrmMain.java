package MyPack;

import JMyron.JMyron;
import JavaLib.LoadForm;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FrmMain extends javax.swing.JFrame implements MouseListener, MouseMotionListener {

    public BufferedImage thumbCam, thumbImage;
    public Graphics2D g2DImage;
    public ImageIcon iiImage;
    java.util.Timer t1;
    MyTimerTask tt;
    public boolean running;
    public JMyron m; //a camera object
    public int cw, ch; // camera dimensions
    public int ww, hh; // required dimensions
    public int frameRate; //fps
    FrmSettings parent;
    Connection con;
    boolean connect = false;
    Point initialClick;

    public FrmMain(FrmSettings parent) {
        this.parent = parent;
        initComponents();
        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth() / 2, sd.height / 2 - this.getHeight() / 2);
        initDatabase();

        if (parent.res == 0) {
            ww = 320;
            hh = 240;

        } else if (parent.res == 1) {
            ww = 400;
            hh = 300;
        } else {
            ww = 640;
            hh = 480;
        }
        parent.ImageTransform(ww, hh);
        byte[] myArrray;
        int count = 0;
        myArrray = new byte[ww * hh * 3];
        //To upload default image (snapshot)
        for (int i = 0; i < hh; i++) {
            for (int j = 0; j < ww; j++) {
                int col = parent.scSmall.getRGB(j, i);
                int r = (col >> 16) & 0xff;
                myArrray[count] = (byte) r;
                count++;
                int g = (col >> 8) & 0xff;
                myArrray[count] = (byte) g;
                count++;
                int b = (col) & 0xff;
                myArrray[count] = (byte) b;
                count++;
            }
        }
        if (!parent.currDevice.service.equals("Snapshot")) {
            cw = 400;
            ch = 300;
            System.out.println("Initializing Webcam, w:" + cw + ", h:" + ch);
            m = new JMyron();//make a new instance of the object
            m.start(cw, ch);//start a capture at 320x240
            m.findGlobs(0);//disable the intelligence to speed up frame rate
            cw = m.getForcedWidth();
            ch = m.getForcedHeight();
            System.out.println("Forced Dimensions, w:" + cw + ", h:" + ch);

            m.stop();

            // Reinitializing with required dimensions
            System.out.println("Re-Initializing Webcam, w:" + cw + ", h:" + ch);
            m = new JMyron();//make a new instance of the object
            m.start(cw, ch);//start a capture at camera dimensions
            m.findGlobs(0);//disable the intelligence to speed up frame rate


            thumbCam = new BufferedImage(cw, ch, BufferedImage.TYPE_INT_RGB);
            thumbImage = new BufferedImage(ww, hh, BufferedImage.TYPE_INT_RGB);
            g2DImage = thumbImage.createGraphics();
            iiImage = new ImageIcon(thumbImage);
            jLabelFeed.setIcon(iiImage);

            running = false;

            t1 = new java.util.Timer();
            tt = new MyTimerTask();
            t1.schedule(tt, 100, 100);
        }


        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            String ssql = "select * from userdata where devID=" + parent.devID;
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery(ssql);
            if (rs.next()) {
                ssql = "update userdata set width = " + ww + ", height =" + hh + ",mydate = '" + sdf.format(cal.getTime()) + "',image  =?  where devID=" + parent.devID;
                PreparedStatement pre;
                ByteArrayInputStream bis = new ByteArrayInputStream(myArrray);
                pre = con.prepareStatement(ssql);
                pre.setBinaryStream(1, bis, (long) (myArrray.length));
                pre.executeUpdate();
                System.out.println("New Value: Updated");
            } else {
                ssql = "insert into userdata(devID,deviceName,width,height,mydate,image,state) values(?,?,?,?,?,?,0)";
                PreparedStatement pre;
                ByteArrayInputStream bis = new ByteArrayInputStream(myArrray);
                pre = con.prepareStatement(ssql);
                pre.setInt(1, parent.devID);
                pre.setString(2, parent.deviceName);
                pre.setInt(3, ww);
                pre.setInt(4, hh);
                pre.setString(5, sdf.format(cal.getTime()));
                pre.setBinaryStream(6, bis, (long) (myArrray.length));
                pre.executeUpdate();
                System.out.println("New Value: inserted");
            }
        } catch (Exception e) {
            System.out.println("ERROR IN UPDATING IMAGE" + e);
            e.printStackTrace();
        }
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                // get location of Window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void initDatabase() {

        String connection = "jdbc:mysql://localhost/8838DB";
        String user = "root";
        String password = "pass";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(connection, user, password);
//            System.out.println("Database Connection OK");
        } catch (Exception e) {
            System.out.println("Error opening database : " + e);
        }
    }

    class MyTimerTask extends TimerTask {

        public MyTimerTask() {
            ;
        }

        public void run() {
            if (!running) {
                return;
            }
            updateImage();
        }
    }

    public void updateImage() {

        byte[] myArrray;
        m.update();//update the camera view
        int[] img = m.image(); //get the normal image of the camera
        thumbCam.setRGB(0, 0, cw, ch, img, 0, cw);
        g2DImage.drawImage(thumbCam, 0, 0, ww, hh, null);
        jLabelFeed.repaint();
        int count = 0;
        myArrray = new byte[ww * hh * 3];
        for (int i = 0; i < hh; i++) {
            for (int j = 0; j < ww; j++) {
                int col = thumbImage.getRGB(j, i);
                int r = (col >> 16) & 0xff;
                myArrray[count] = (byte) r;
                count++;
                int g = (col >> 8) & 0xff;
                myArrray[count] = (byte) g;
                count++;
                int b = (col) & 0xff;
                myArrray[count] = (byte) b;
                count++;
            }
        }
        if (connect) {
            Calendar cal = Calendar.getInstance();//for time
            try {
                String ssql = "update userdata set width = " + ww + ", height =" + hh + ",mydate = " + cal.getTimeInMillis() + ",image  =?  where devID=" + parent.devID;
                PreparedStatement pre;
                ByteArrayInputStream bis = new ByteArrayInputStream(myArrray);
                initDatabase();
                pre = con.prepareStatement(ssql);
                pre.setBinaryStream(1, bis, (int) (myArrray.length));
                pre.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error: " + e);
                e.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelFeed = new javax.swing.JLabel();
        jBLoadNew = new javax.swing.JButton();
        jBLoadNew1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();

        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 8, true));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelFeed.setBackground(new java.awt.Color(255, 255, 255));
        jLabelFeed.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFeed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagePack/Back320x240.PNG"))); // NOI18N
        jLabelFeed.setMaximumSize(new java.awt.Dimension(640, 480));
        jLabelFeed.setMinimumSize(new java.awt.Dimension(640, 480));
        jLabelFeed.setPreferredSize(new java.awt.Dimension(640, 480));

        jBLoadNew.setBackground(new java.awt.Color(0, 153, 153));
        jBLoadNew.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jBLoadNew.setText("Start Feed");
        jBLoadNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoadNewActionPerformed(evt);
            }
        });

        jBLoadNew1.setBackground(new java.awt.Color(0, 153, 153));
        jBLoadNew1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jBLoadNew1.setText("Stop Feed");
        jBLoadNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoadNew1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jBLoadNew, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jBLoadNew1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3Layout.createSequentialGroup()
                        .add(jLabelFeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 640, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelFeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 480, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jBLoadNew, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jBLoadNew1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(new java.awt.Component[] {jBLoadNew, jBLoadNew1}, org.jdesktop.layout.GroupLayout.VERTICAL);

        new LoadForm();

        jButton3.setBackground(new java.awt.Color(0, 153, 153));
        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jButton3.setText("BACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jToggleButton1.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jToggleButton1);
        jToggleButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jToggleButton1.setText("CONNECT");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jToggleButton2);
        jToggleButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jToggleButton2.setText("DISCONNECT");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VIDEO FEED");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                .add(jToggleButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(54, 54, 54)
                                .add(jToggleButton2)))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(0, 34, Short.MAX_VALUE)
                        .add(jButton3))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jToggleButton1)
                            .add(jToggleButton2))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (connect) {
            JOptionPane.showMessageDialog(jPanel1, "Please Disconnect First and Try Again!!!");
            return;
        }
        this.setVisible(false);
        new FrmSettings().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jBLoadNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLoadNew1ActionPerformed
        running = false;
    }//GEN-LAST:event_jBLoadNew1ActionPerformed

    private void jBLoadNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLoadNewActionPerformed
        if (parent.currDevice.service.equals("Snapshot")) {
            jLabelFeed.setIcon(new ImageIcon(parent.scSmall));
            return;
        }
        if (parent.flag) {
            jLabelFeed.setIcon(new ImageIcon(parent.scSmall));
            return;
        }

       running = true;

    }//GEN-LAST:event_jBLoadNewActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        connect = true;
        try {
            String ssql = "update userdata set state = 1 where devID =" + parent.devID;
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.executeUpdate(ssql);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        connect = false;
        try {
            String ssql = "update userdata set state = 0 where devID =" + parent.devID;
            Statement stmt = con.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.executeUpdate(ssql);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBLoadNew;
    private javax.swing.JButton jBLoadNew1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelFeed;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
