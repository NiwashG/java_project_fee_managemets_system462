/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fees_managemeant_system3;

import java.awt.Color;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Niwash Kumar
 */
public class EditCourse extends javax.swing.JFrame {

    /**
     * Creates new form EditCourse
     */
    DefaultTableModel model;

    public EditCourse() {
        initComponents();
        setRecordTable();
    }

    public void setRecordTable() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select* from course ");
            java.sql.ResultSet rs = pst.executeQuery();
            // rs.next();
            while (rs.next()) {
                int course_ID = rs.getInt("Id");
                String course_name = rs.getString("CNAME");
                String Cousr_cost = rs.getString("COST");
                Object[] obj = {course_ID, course_name, Cousr_cost};
                model = (DefaultTableModel) tbl_CourseList.getModel();
                model.addRow(obj);
            }
        }     
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_CourseList.getModel();
        model.setRowCount(0);
    }

    public void addCourse(int id, String cname, double cost) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into course values(?,?,?)");
            pst.setInt(1, id);
            pst.setString(2, cname);
            pst.setDouble(3, cost);

            int rowCount = pst.executeUpdate();
            if (rowCount == 1) {
                JOptionPane.showMessageDialog(this, "course added succesfully");
                clearTable();
                setRecordTable();
            } else {
                JOptionPane.showMessageDialog(this, "course insertion failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "course insertion failed");
            e.printStackTrace();
        }

    }

    public void updateCourse(int id, String cname, double cost) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("update  course set cname=?,cost=?  where id=?");

            pst.setString(1, cname);
            pst.setDouble(2, cost);
            pst.setInt(3, id);

            int rowCount = pst.executeUpdate();
            if (rowCount == 1) {
                JOptionPane.showMessageDialog(this, "course Updated succesfully");
                clearTable();
                setRecordTable();
            } else {
                JOptionPane.showMessageDialog(this, "course Updation failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "course Updation failed");
            e.printStackTrace();
        }

    }

    public void deleteCourse(int id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from  course where id=?");

//            pst.setString(1, cname);
//            pst.setDouble(2,cost);
            pst.setInt(1, id);

            int rowCount = pst.executeUpdate();
            if (rowCount == 1) {
                JOptionPane.showMessageDialog(this, "course Deleted succesfully");
                clearTable();
                setRecordTable();
            } else {
                JOptionPane.showMessageDialog(this, "course Deletion  failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "course Deletion  failed");
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_CourseList = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_Course_Price = new javax.swing.JTextField();
        txt_course_id = new javax.swing.JTextField();
        txt_course_name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        jlebelHome = new javax.swing.JLabel();
        jPanelSearchRecord = new javax.swing.JPanel();
        jLabelSearchReacord = new javax.swing.JLabel();
        jPanelEditCourse = new javax.swing.JPanel();
        jLabelEditCourse = new javax.swing.JLabel();
        jPanelCourseList = new javax.swing.JPanel();
        jLabelCourseList = new javax.swing.JLabel();
        jPanelViewAllRecord = new javax.swing.JPanel();
        jLabelViewAllRecord = new javax.swing.JLabel();
        jPanelBack = new javax.swing.JPanel();
        jLabelBack = new javax.swing.JLabel();
        jPanelLogout = new javax.swing.JPanel();
        jLabelLogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1560, 950));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_CourseList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        tbl_CourseList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Id", "Course Name", "Course Price"
            }
        ));
        tbl_CourseList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CourseListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_CourseList);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 610, 430));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EditCourse");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 6, 180, 40));

        txt_Course_Price.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel2.add(txt_Course_Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 200, 40));

        txt_course_id.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_course_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_course_idKeyReleased(evt);
            }
        });
        jPanel2.add(txt_course_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 200, 40));

        txt_course_name.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel2.add(txt_course_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 200, 40));

        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 19)); // NOI18N
        jLabel2.setText("Course Price:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 140, 40));

        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 19)); // NOI18N
        jLabel3.setText("Course ID:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 140, 40));

        jLabel4.setFont(new java.awt.Font("SansSerif", 3, 19)); // NOI18N
        jLabel4.setText("Course Name:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 140, 40));

        jButton4.setBackground(new java.awt.Color(153, 0, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("DELETE");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 720, 120, 50));

        jButton5.setBackground(new java.awt.Color(0, 51, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("ADD");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 720, 120, 50));

        jButton6.setBackground(new java.awt.Color(0, 51, 51));
        jButton6.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("UPDATE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 720, 120, 50));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 230, -1));

        jLabel5.setBackground(new java.awt.Color(0, 51, 51));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_managemeant_system3/images/back1.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 60, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 1060));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHome.setBackground(new java.awt.Color(0, 51, 51));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        panelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlebelHome.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jlebelHome.setForeground(new java.awt.Color(255, 255, 255));
        jlebelHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_managemeant_system3/images/home.png"))); // NOI18N
        jlebelHome.setText("      Home");
        jlebelHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlebelHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlebelHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlebelHomeMouseExited(evt);
            }
        });
        panelHome.add(jlebelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 250, 60));

        jPanel1.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 270, 80));

        jPanelSearchRecord.setBackground(new java.awt.Color(0, 51, 51));
        jPanelSearchRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        jPanelSearchRecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSearchReacord.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        jLabelSearchReacord.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSearchReacord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_managemeant_system3/images/search2.png"))); // NOI18N
        jLabelSearchReacord.setText("Search Record");
        jLabelSearchReacord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSearchReacordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelSearchReacordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelSearchReacordMouseExited(evt);
            }
        });
        jPanelSearchRecord.add(jLabelSearchReacord, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 70));

        jPanel1.add(jPanelSearchRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 270, 80));

        jPanelEditCourse.setBackground(new java.awt.Color(0, 51, 51));
        jPanelEditCourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        jPanelEditCourse.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelEditCourse.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        jLabelEditCourse.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEditCourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_managemeant_system3/images/edit2.png"))); // NOI18N
        jLabelEditCourse.setText("Edit Course");
        jLabelEditCourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEditCourseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelEditCourseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelEditCourseMouseExited(evt);
            }
        });
        jLabelEditCourse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelEditCourseKeyPressed(evt);
            }
        });
        jPanelEditCourse.add(jLabelEditCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 240, 60));

        jPanel1.add(jPanelEditCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 270, 80));

        jPanelCourseList.setBackground(new java.awt.Color(0, 51, 51));
        jPanelCourseList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        jPanelCourseList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCourseList.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        jLabelCourseList.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCourseList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_managemeant_system3/images/list.png"))); // NOI18N
        jLabelCourseList.setText("Course List");
        jLabelCourseList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCourseListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCourseListMouseExited(evt);
            }
        });
        jPanelCourseList.add(jLabelCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 60));

        jPanel1.add(jPanelCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 270, 70));

        jPanelViewAllRecord.setBackground(new java.awt.Color(0, 51, 51));
        jPanelViewAllRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        jPanelViewAllRecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelViewAllRecord.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        jLabelViewAllRecord.setForeground(new java.awt.Color(255, 255, 255));
        jLabelViewAllRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_managemeant_system3/images/view all record.png"))); // NOI18N
        jLabelViewAllRecord.setText("View All Record");
        jLabelViewAllRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelViewAllRecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelViewAllRecordMouseExited(evt);
            }
        });
        jPanelViewAllRecord.add(jLabelViewAllRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 260, 60));

        jPanel1.add(jPanelViewAllRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 270, 80));

        jPanelBack.setBackground(new java.awt.Color(0, 51, 51));
        jPanelBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        jPanelBack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBack.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        jLabelBack.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_managemeant_system3/images/left-arrow.png"))); // NOI18N
        jLabelBack.setText("     Back");
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelBackMouseExited(evt);
            }
        });
        jPanelBack.add(jLabelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 60));

        jPanel1.add(jPanelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 270, 80));

        jPanelLogout.setBackground(new java.awt.Color(0, 51, 51));
        jPanelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        jPanelLogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLogout.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabelLogout.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_managemeant_system3/images/logout.png"))); // NOI18N
        jLabelLogout.setText("       Logout");
        jLabelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelLogoutMouseExited(evt);
            }
        });
        jPanelLogout.add(jLabelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, 60));

        jPanel1.add(jPanelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 630, 270, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 410, 1080));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jlebelHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlebelHomeMouseClicked
        // TODO add your handling code here:
        home h = new home();
        h.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jlebelHomeMouseClicked

    private void jlebelHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlebelHomeMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_jlebelHomeMouseEntered

    private void jlebelHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlebelHomeMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 51, 51);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_jlebelHomeMouseExited

    private void jLabelSearchReacordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearchReacordMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        jPanelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_jLabelSearchReacordMouseEntered

    private void jLabelSearchReacordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearchReacordMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 51, 51);
        jPanelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_jLabelSearchReacordMouseExited

    private void jLabelEditCourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditCourseMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        jPanelEditCourse.setBackground(clr);
    }//GEN-LAST:event_jLabelEditCourseMouseEntered

    private void jLabelEditCourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditCourseMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 51, 51);
        jPanelEditCourse.setBackground(clr);
    }//GEN-LAST:event_jLabelEditCourseMouseExited

    private void jLabelEditCourseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelEditCourseKeyPressed
        // TODO add your handling code here:
        //         Color clr=new Color(0,153,153);
        //        jPanelEditCourse.setBackground(clr);
    }//GEN-LAST:event_jLabelEditCourseKeyPressed

    private void jLabelCourseListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCourseListMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        jPanelCourseList.setBackground(clr);
    }//GEN-LAST:event_jLabelCourseListMouseEntered

    private void jLabelCourseListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCourseListMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 51, 51);
        jPanelCourseList.setBackground(clr);
    }//GEN-LAST:event_jLabelCourseListMouseExited

    private void jLabelViewAllRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelViewAllRecordMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        jPanelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_jLabelViewAllRecordMouseEntered

    private void jLabelViewAllRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelViewAllRecordMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 51, 51);
        jPanelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_jLabelViewAllRecordMouseExited

    private void jLabelBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        jPanelBack.setBackground(clr);
    }//GEN-LAST:event_jLabelBackMouseEntered

    private void jLabelBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 51, 51);
        jPanelBack.setBackground(clr);
    }//GEN-LAST:event_jLabelBackMouseExited

    private void jLabelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoutMouseEntered
        // TODO add your handling code here:
        Color clr = new Color(0, 153, 153);
        jPanelLogout.setBackground(clr);

    }//GEN-LAST:event_jLabelLogoutMouseEntered

    private void jLabelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoutMouseExited
        // TODO add your handling code here:
        Color clr = new Color(0, 51, 51);
        jPanelLogout.setBackground(clr);
    }//GEN-LAST:event_jLabelLogoutMouseExited

    private void txt_course_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_course_idKeyReleased
        // TODO add your handling code here:
//         String SearchString=txt_course_id.getText();
//        Search(SearchString);


    }//GEN-LAST:event_txt_course_idKeyReleased

    private void tbl_CourseListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CourseListMouseClicked
        // TODO add your handling code here:
        int rowNo = tbl_CourseList.getSelectedRow();
        TableModel model = tbl_CourseList.getModel();

        txt_course_id.setText(model.getValueAt(rowNo, 0).toString());
        txt_course_name.setText((String) model.getValueAt(rowNo, 1));
        txt_Course_Price.setText(model.getValueAt(rowNo, 2).toString());


    }//GEN-LAST:event_tbl_CourseListMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        int id = Integer.parseInt(txt_course_id.getText());
        String cname = txt_course_name.getText();
        double cost = Double.parseDouble(txt_Course_Price.getText());

        addCourse(id, cname, cost);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(txt_course_id.getText());
        String cname = txt_course_name.getText();
        double cost = Double.parseDouble(txt_Course_Price.getText());

        updateCourse(id, cname, cost);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(txt_course_id.getText());
//        String cname=txt_course_name.getText();
//        double cost=Double.parseDouble(txt_course_id.getText())

        deleteCourse(id);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabelSearchReacordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearchReacordMouseClicked
        // TODO add your handling code here:

        SearchRecord sr = new SearchRecord();
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelSearchReacordMouseClicked

    private void jLabelEditCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditCourseMouseClicked
        // TODO add your handling code here:
        
        EditCourse ec=new EditCourse();
        ec.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabelEditCourseMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        home h=new home();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditCourse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelCourseList;
    private javax.swing.JLabel jLabelEditCourse;
    private javax.swing.JLabel jLabelLogout;
    private javax.swing.JLabel jLabelSearchReacord;
    private javax.swing.JLabel jLabelViewAllRecord;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBack;
    private javax.swing.JPanel jPanelCourseList;
    private javax.swing.JPanel jPanelEditCourse;
    private javax.swing.JPanel jPanelLogout;
    private javax.swing.JPanel jPanelSearchRecord;
    private javax.swing.JPanel jPanelViewAllRecord;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlebelHome;
    private javax.swing.JPanel panelHome;
    private javax.swing.JTable tbl_CourseList;
    private javax.swing.JTextField txt_Course_Price;
    private javax.swing.JTextField txt_course_id;
    private javax.swing.JTextField txt_course_name;
    // End of variables declaration//GEN-END:variables
}