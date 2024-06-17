/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fees_managemeant_system3;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.apache.derby.iapi.sql.ResultSet;

/**
 *
 * @author Niwash Kumar
 */
public class Add_fees extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new form Add_fees
     */
    public Add_fees() {
        initComponents();
        displayCashFirst();
        fillComboBox();
         int reciept_no=getReceiptNo();
         txt_Receipt_no.setText(Integer.toString(reciept_no));
         ClearFields();
    }
    
    public void displayCashFirst(){
        lbl_DD_no.setVisible(false);
        lbl_cheque_no.setVisible(false);
        lbl_Bank_name.setVisible(false);
        
        
        txt_DD_no.setVisible(false);
        txt_Cheque_no.setVisible(false);
        txt_Bank_Name.setVisible(false);
    }
    
    public boolean validation(){
        if(txt_Recived_from.getText().trim().equals("")){
          JOptionPane.showMessageDialog(this, "Please Enter Reciver From");
          return false;
        }
        
        if(txt_Amount.getText().trim().equals("")|| txt_Amount.getText().trim().matches("[0-9]+")==false ){
          JOptionPane.showMessageDialog(this, "Please Enter Amount in INR");
          return false;
        }
         if(date1.getDate()==null){
          JOptionPane.showMessageDialog(this, "Please Enter Date ");
          return false;
        }
//          if(txt_Total_In_words.getText().trim().equals("")){
//          JOptionPane.showMessageDialog(this, "Please Enter Total Amount in Words");
//          return false;
//        }
          if(txt_year1.getText()==null){
          JOptionPane.showMessageDialog(this, "Please Enter Starting year");
          return false;
        }
           if(txt_year2.getText()==null){
          JOptionPane.showMessageDialog(this, "Please Enter Last year");
          return false;
        }
            
         if(txt_year2.getText().trim().equals("")|| txt_Amount.getText().trim().matches("[0-9]+")==false ){
          JOptionPane.showMessageDialog(this, "Please Enter Roll No.");
          return false;
        } 
         
//         if(txt_Course_name.getText().trim().equals("")){
//          JOptionPane.showMessageDialog(this, "Please Enter Course Name");
//          return false;
//        } 
//          if(txt_cgst.getText().trim().equals("")){
//          JOptionPane.showMessageDialog(this, "Please Enter CGST with 9%");
//          return false;
//        } 
//          
//           if(txt_sgst.getText().trim().equals("")){
//          JOptionPane.showMessageDialog(this, "Please Enter SGST With 9%");
//          return false;
//        } 
               if(jComboBoxPayment_mode.getSelectedItem().toString().equalsIgnoreCase("cheque")){
                  if(txt_Cheque_no.getText().equals("")){
                      JOptionPane.showMessageDialog(this, "Please Enter Cheque Number");
                      return false; 
                  }
                  
                   if(txt_Bank_Name.getText().equals("")){
                      JOptionPane.showMessageDialog(this, "Please Enter  Bank Name");
                      return false; 
                  }
                      
                 }
               
                if(jComboBoxPayment_mode.getSelectedItem().toString().equalsIgnoreCase("DD")){
                  if(txt_DD_no.getText().equals("")){
                      JOptionPane.showMessageDialog(this, "Please Enter DD Number");
                      return false; 
                  }
                  
                   if(txt_Bank_Name.getText().equals("")){
                      JOptionPane.showMessageDialog(this, "Please Enter Bank Name");
                      return false; 
                  }
                      
                 }
                
                if(jComboBoxPayment_mode.getSelectedItem().toString().equalsIgnoreCase("Card")){
//                  if(txt_DD_no.getText().equals("")){
//                      JOptionPane.showMessageDialog(this, "Please Enter DD Number");
//                      return false; 
//                  }
                  
                   if(txt_Bank_Name.getText().equals("")){
                      JOptionPane.showMessageDialog(this, "Please Enter Bank Name");
                      return false; 
                  }
                      
                 }


          
        return true;
        
    }
    
    
    public void fillComboBox(){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_managemeant_system","root","root");
           // String sql="insert into signup values(?,?,?,?,?,?,?)";
           PreparedStatement pst=con.prepareStatement("select Cname from Course");
             java.sql.ResultSet rs=pst.executeQuery();
             while(rs.next()){
                 jComboBoxCourse.addItem(rs.getString("Cname"));
             }        
        }
        catch(Exception e){
           e.printStackTrace();
        }       
    }
    
    
    public int getReceiptNo()
    {
        int reciept_no=0;
        try
        {
            Connection con= DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("select max(reciept_no) from fees_Details");
             java.sql.ResultSet rs=pst.executeQuery();
             if(rs.next()== true)
             {
                 reciept_no=rs.getInt(1);
             }
            
        }
         catch(Exception e)
                     {
                     e.printStackTrace();
                     }
        return reciept_no+1;
    }
    
    
    
    public String insertData(){
        
        String status="";
         
        int recieptNo=Integer.parseInt(txt_Receipt_no.getText());
        String studentName=txt_Recived_from.getText();
        String rollNo=txt_Roll_no1.getText();
        String paymentMode=jComboBoxPayment_mode.getSelectedItem().toString();
        String chequeNo=txt_Cheque_no.getText();
        String bankName=txt_Bank_Name.getText();
        String DDNo=txt_DD_no.getText();
        String courseName=txt_Course_name.getText();
//        String studentName=txt_.getText();
        String gstin=txt_gst_in.getText();
        float totalAmount=Float.parseFloat(txt_total_Amount.getText());
        SimpleDateFormat Format;
        Format = new SimpleDateFormat("YYYY-MM-dd");
        
        String date =Format.format(date1.getDate());
        float initialAmount=Float.parseFloat(txt_Amount.getText());
        float cgst=Float.parseFloat(txt_cgst.getText());
        float sgst=Float.parseFloat(txt_sgst.getText());
        String totalInWoords=txt_Total_In_words.getText();
        String remark=txt_Remark_Area.getText();
        int year1=Integer.parseInt(txt_year1.getText());
        int year2=Integer.parseInt(txt_year2.getText());
               
               
               
        try
        {
            Connection con= DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("insert into  fees_Details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1,recieptNo );
            pst.setString(2,studentName );
            pst.setString(3, rollNo);
            pst.setString(4, paymentMode);
            pst.setString(5, chequeNo);
            pst.setString(6, bankName);
            pst.setString(7, DDNo);
            pst.setString(8, courseName);
            pst.setString(9,gstin);
            pst.setFloat(10,  totalAmount);
            pst.setString(11, date);
            pst.setFloat(12, initialAmount);
            pst.setFloat(13, cgst);
            pst.setFloat(14, sgst);
            pst.setString(15,totalInWoords);
            pst.setString(16, remark);
            pst.setInt(17, year1);
            pst.setInt(18, year2);
            
           int rowCount= pst.executeUpdate();
           if(rowCount==1){
               status="success";
           }else
           {
               status="Failed";
           }
            
            
//             java.sql.ResultSet rs=pst.executeQuery();
            
            
        }
         catch(Exception e)
                     {
                     e.printStackTrace();
                     System.out.println(e);
                     }        
           
        
        return status;
    }
    
    private void ClearFields(){
        txt_Remark_Area.setText(null);
        txt_cgst.setText(null);
        txt_sgst.setText(null);
        txt_year1.setText(null);
        txt_year2.setText(null);
        txt_Total_In_words.setText(null);
        txt_Amount.setText(null);
        date1.setDate(null);
        txt_total_Amount.setText(null);
        txt_DD_no.setText(null);
        txt_Cheque_no.setText(null);
        txt_Bank_Name.setText(null);
        jComboBoxPayment_mode.setSelectedItem(0);
        txt_Roll_no1.setText(null);
        txt_Recived_from.setText(null);
        
        
    }
    
    
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanel_Parent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_DD_no = new javax.swing.JLabel();
        lbl_cheque_no = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();
        txt_gst_in = new javax.swing.JLabel();
        lbl_Bank_name = new javax.swing.JLabel();
        receiptNo = new javax.swing.JLabel();
        txt_Receipt_no = new javax.swing.JTextField();
        txt_DD_no = new javax.swing.JTextField();
        txt_Cheque_no = new javax.swing.JTextField();
        txt_Bank_Name = new javax.swing.JTextField();
        jComboBoxPayment_mode = new javax.swing.JComboBox<>();
        jPanel_chield = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_Total_In_words = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_year2 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_Recived_from = new javax.swing.JTextField();
        txt_Course_name = new javax.swing.JTextField();
        txt_Amount = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        txt_sgst = new javax.swing.JTextField();
        txt_total_Amount = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Remark_Area = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        jComboBoxCourse = new javax.swing.JComboBox<>();
        txt_Roll_no1 = new javax.swing.JTextField();
        txt_year1 = new javax.swing.JTextField();
        btn_Clear = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        date1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1480, 840));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 1080));

        jPanel_Parent.setBackground(new java.awt.Color(0, 153, 153));
        jPanel_Parent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Mode of Payment:");
        jPanel_Parent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 160, 30));

        lbl_DD_no.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_DD_no.setText("DD No:");
        jPanel_Parent.add(lbl_DD_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 140, 30));

        lbl_cheque_no.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_cheque_no.setText("Cheque no:");
        jPanel_Parent.add(lbl_cheque_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 140, 30));

        Date1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Date1.setText("Date:");
        jPanel_Parent.add(Date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 70, 30));

        txt_gst_in.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_gst_in.setText("2701NGKMR462");
        jPanel_Parent.add(txt_gst_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 160, 30));

        lbl_Bank_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_Bank_name.setText("Bank Name :");
        jPanel_Parent.add(lbl_Bank_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 140, 30));

        receiptNo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        receiptNo.setText("Receipt no :  RIT- ");
        jPanel_Parent.add(receiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 160, 30));

        txt_Receipt_no.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jPanel_Parent.add(txt_Receipt_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 200, 30));

        txt_DD_no.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jPanel_Parent.add(txt_DD_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 200, -1));

        txt_Cheque_no.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jPanel_Parent.add(txt_Cheque_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 200, -1));

        txt_Bank_Name.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_Bank_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Bank_NameActionPerformed(evt);
            }
        });
        jPanel_Parent.add(txt_Bank_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 200, -1));

        jComboBoxPayment_mode.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jComboBoxPayment_mode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "Cash", "Card" }));
        jComboBoxPayment_mode.setSelectedIndex(2);
        jComboBoxPayment_mode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPayment_modeActionPerformed(evt);
            }
        });
        jPanel_Parent.add(jComboBoxPayment_mode, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 200, 30));

        jPanel_chield.setBackground(new java.awt.Color(0, 153, 153));
        jPanel_chield.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Recived From:");
        jPanel_chield.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 140, 30));

        txt_Total_In_words.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jPanel_chield.add(txt_Total_In_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 440, 430, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("The following payment in the College office for the year:");
        jPanel_chield.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 470, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Amount (Rs)");
        jPanel_chield.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, 140, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("To");
        jPanel_chield.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 30, 30));

        txt_year2.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year2ActionPerformed(evt);
            }
        });
        jPanel_chield.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 100, -1));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_chield.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 310, 10));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_chield.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1010, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Course :");
        jPanel_chield.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 140, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Sr No ");
        jPanel_chield.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 140, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Total in Words :");
        jPanel_chield.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 180, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Roll No:");
        jPanel_chield.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 120, 80, 30));

        txt_Recived_from.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jPanel_chield.add(txt_Recived_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 360, -1));

        txt_Course_name.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jPanel_chield.add(txt_Course_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 430, -1));

        txt_Amount.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_Amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_AmountActionPerformed(evt);
            }
        });
        jPanel_chield.add(txt_Amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 160, -1));

        txt_cgst.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jPanel_chield.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 290, 160, -1));

        txt_sgst.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jPanel_chield.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, 160, -1));

        txt_total_Amount.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jPanel_chield.add(txt_total_Amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 400, 160, 40));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_chield.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 1010, 10));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Heads ");
        jPanel_chield.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 140, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Receiver Signature");
        jPanel_chield.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 510, 180, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Remark :");
        jPanel_chield.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 100, 30));

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_chield.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, 280, 10));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("SGST 9%");
        jPanel_chield.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 140, 30));

        txt_Remark_Area.setColumns(20);
        txt_Remark_Area.setRows(5);
        jScrollPane1.setViewportView(txt_Remark_Area);

        jPanel_chield.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 420, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("CGST 9%");
        jPanel_chield.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 140, 30));

        btn_print.setText("print");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jPanel_chield.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 580, -1, -1));

        jComboBoxCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCourseActionPerformed(evt);
            }
        });
        jPanel_chield.add(jComboBoxCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 290, 30));

        txt_Roll_no1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_Roll_no1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Roll_no1ActionPerformed(evt);
            }
        });
        jPanel_chield.add(txt_Roll_no1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 150, -1));

        txt_year1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year1ActionPerformed(evt);
            }
        });
        jPanel_chield.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 100, -1));

        btn_Clear.setText("Clear");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });
        jPanel_chield.add(btn_Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 580, -1, -1));

        jPanel_Parent.add(jPanel_chield, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1030, 790));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("GSTIN:    ");
        jPanel_Parent.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 80, 30));
        jPanel_Parent.add(date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 22, 170, 30));

        getContentPane().add(jPanel_Parent, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, -1, 970));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jlebelHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlebelHomeMouseEntered
        // TODO add your handling code here:
        Color clr=new Color(0,153,153);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_jlebelHomeMouseEntered

    private void jlebelHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlebelHomeMouseExited
        // TODO add your handling code here:
       Color clr=new Color(0,51,51);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_jlebelHomeMouseExited

    private void jLabelSearchReacordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearchReacordMouseEntered
        // TODO add your handling code here:
         Color clr=new Color(0,153,153);
        jPanelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_jLabelSearchReacordMouseEntered

    private void jLabelSearchReacordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearchReacordMouseExited
        // TODO add your handling code here:
        Color clr=new Color(0,51,51);
        jPanelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_jLabelSearchReacordMouseExited

    private void jLabelEditCourseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelEditCourseKeyPressed
        // TODO add your handling code here:
//         Color clr=new Color(0,153,153);
//        jPanelEditCourse.setBackground(clr);
    }//GEN-LAST:event_jLabelEditCourseKeyPressed

    private void jLabelEditCourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditCourseMouseEntered
        // TODO add your handling code here:
          Color clr=new Color(0,153,153);
        jPanelEditCourse.setBackground(clr);
    }//GEN-LAST:event_jLabelEditCourseMouseEntered

    private void jLabelEditCourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditCourseMouseExited
        // TODO add your handling code here:
       Color clr=new Color(0,51,51);
        jPanelEditCourse.setBackground(clr);
    }//GEN-LAST:event_jLabelEditCourseMouseExited

    private void jLabelCourseListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCourseListMouseEntered
        // TODO add your handling code here:
          Color clr=new Color(0,153,153);
        jPanelCourseList.setBackground(clr);
    }//GEN-LAST:event_jLabelCourseListMouseEntered

    private void jLabelCourseListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCourseListMouseExited
        // TODO add your handling code here:
          Color clr=new Color(0,51,51);
        jPanelCourseList.setBackground(clr);
    }//GEN-LAST:event_jLabelCourseListMouseExited

    private void jLabelViewAllRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelViewAllRecordMouseEntered
        // TODO add your handling code here:
         Color clr=new Color(0,153,153);
        jPanelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_jLabelViewAllRecordMouseEntered

    private void jLabelViewAllRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelViewAllRecordMouseExited
        // TODO add your handling code here:
         Color clr=new Color(0,51,51);
        jPanelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_jLabelViewAllRecordMouseExited

    private void jLabelBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseEntered
        // TODO add your handling code here:
         Color clr=new Color(0,153,153);
        jPanelBack.setBackground(clr);
    }//GEN-LAST:event_jLabelBackMouseEntered

    private void jLabelBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseExited
        // TODO add your handling code here:
         Color clr=new Color(0,51,51);
        jPanelBack.setBackground(clr);
    }//GEN-LAST:event_jLabelBackMouseExited

    private void jLabelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoutMouseEntered
        // TODO add your handling code here:
         Color clr=new Color(0,153,153);
        jPanelLogout.setBackground(clr);
        
    }//GEN-LAST:event_jLabelLogoutMouseEntered

    private void jLabelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoutMouseExited
        // TODO add your handling code here:
          Color clr=new Color(0,51,51);
        jPanelLogout.setBackground(clr);
    }//GEN-LAST:event_jLabelLogoutMouseExited

    private void txt_Bank_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Bank_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Bank_NameActionPerformed

    private void txt_year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year2ActionPerformed

    private void jComboBoxPayment_modeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPayment_modeActionPerformed
        // TODO add your handling code here:
        
        if(jComboBoxPayment_mode.getSelectedIndex()==0){
            lbl_DD_no.setVisible(true);
             txt_DD_no.setVisible(true);
             
              lbl_cheque_no.setVisible(false);
              txt_Cheque_no.setVisible(false);
              
              lbl_Bank_name.setVisible(true);
             txt_Bank_Name.setVisible(true);
        }
        
         if(jComboBoxPayment_mode.getSelectedIndex()==1){
            lbl_DD_no.setVisible(false);
             txt_DD_no.setVisible(false);
             
              lbl_cheque_no.setVisible(true);
              txt_Cheque_no.setVisible(true);
              
              lbl_Bank_name.setVisible(true);
             txt_Bank_Name.setVisible(true);
        }
         
         if(jComboBoxPayment_mode.getSelectedIndex()==2){
            lbl_DD_no.setVisible(false);
             txt_DD_no.setVisible(false);
             
              lbl_cheque_no.setVisible(false);
              txt_Cheque_no.setVisible(false);
              
              lbl_Bank_name.setVisible(false);
             txt_Bank_Name.setVisible(false);
        }
         
          
         if(jComboBoxPayment_mode.getSelectedIndex()==3){
            lbl_DD_no.setVisible(false);
             txt_DD_no.setVisible(false);
             
              lbl_cheque_no.setVisible(false);
              txt_Cheque_no.setVisible(false);
              
              lbl_Bank_name.setVisible(true);
             txt_Bank_Name.setVisible(true);
        }
    }//GEN-LAST:event_jComboBoxPayment_modeActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        if(validation()==true){
           //JOptionPane.showMessageDialog(this, "Validation Successful");  
           String result=insertData();
           if(result.equals("success")){
               JOptionPane.showMessageDialog(this, "Record  insertion Successful"); 
               PrintRecipt p=new PrintRecipt();
               p.setVisible(true);
               this.dispose();
           }
           else{
               JOptionPane.showMessageDialog(this, "Record insertion Failed"); 
           }
        }
//         if(validation()==true){
//           JOptionPane.showMessageDialog(this, "Validation Successful");  
//        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void txt_AmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_AmountActionPerformed
        // TODO add your handling code here:
        Float amnt = Float.parseFloat(txt_Amount.getText());
        
        Float cgst=(float)(amnt * 0.09);
         Float sgst=(float)(amnt * 0.09);
         
         
         txt_cgst.setText(cgst.toString());
          txt_sgst.setText(sgst.toString());
          
          float total=amnt+ cgst+sgst;
          
          txt_total_Amount.setText(Float.toString(total));
          txt_Total_In_words.setText(NumberToWordsConverter.convert((int) total)+" only");
        
    }//GEN-LAST:event_txt_AmountActionPerformed

    private void jComboBoxCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCourseActionPerformed
        // TODO add your handling code here:
        
        txt_Course_name.setText(jComboBoxCourse.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBoxCourseActionPerformed

    private void txt_Roll_no1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Roll_no1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Roll_no1ActionPerformed

    private void txt_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year1ActionPerformed

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        // TODO add your handling code here:
        ClearFields();
    }//GEN-LAST:event_btn_ClearActionPerformed

    private void jLabelSearchReacordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearchReacordMouseClicked
        // TODO add your handling code here:
        SearchRecord sr=new SearchRecord();
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelSearchReacordMouseClicked

    private void jlebelHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlebelHomeMouseClicked
        // TODO add your handling code here:
        home h=new home();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlebelHomeMouseClicked

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
            java.util.logging.Logger.getLogger(Add_fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_fees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date1;
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_print;
    private com.toedter.calendar.JDateChooser date1;
    private javax.swing.JComboBox<String> jComboBoxCourse;
    private javax.swing.JComboBox<String> jComboBoxPayment_mode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelCourseList;
    private javax.swing.JLabel jLabelEditCourse;
    private javax.swing.JLabel jLabelLogout;
    private javax.swing.JLabel jLabelSearchReacord;
    private javax.swing.JLabel jLabelViewAllRecord;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBack;
    private javax.swing.JPanel jPanelCourseList;
    private javax.swing.JPanel jPanelEditCourse;
    private javax.swing.JPanel jPanelLogout;
    private javax.swing.JPanel jPanelSearchRecord;
    private javax.swing.JPanel jPanelViewAllRecord;
    private javax.swing.JPanel jPanel_Parent;
    private javax.swing.JPanel jPanel_chield;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel jlebelHome;
    private javax.swing.JLabel lbl_Bank_name;
    private javax.swing.JLabel lbl_DD_no;
    private javax.swing.JLabel lbl_cheque_no;
    private javax.swing.JPanel panelHome;
    private javax.swing.JLabel receiptNo;
    private javax.swing.JTextField txt_Amount;
    private javax.swing.JTextField txt_Bank_Name;
    private javax.swing.JTextField txt_Cheque_no;
    private javax.swing.JTextField txt_Course_name;
    private javax.swing.JTextField txt_DD_no;
    private javax.swing.JTextField txt_Receipt_no;
    private javax.swing.JTextField txt_Recived_from;
    private javax.swing.JTextArea txt_Remark_Area;
    private javax.swing.JTextField txt_Roll_no1;
    private javax.swing.JTextField txt_Total_In_words;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JLabel txt_gst_in;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total_Amount;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables
}
