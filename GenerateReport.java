/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fees_managemeant_system3;
import java.util.*;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Niwash Kumar
 */
public class GenerateReport extends javax.swing.JFrame {

    /**
     * Creates new form GenerateReport
     */
     DefaultTableModel model;
    public GenerateReport() {
        initComponents();
       // setRecordTable();
        fillComboBox();
    }
    
    
    
    
        public void fillComboBox(){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_managemeant_system","root","root");
           // String sql="insert into signup values(?,?,?,?,?,?,?)";
           PreparedStatement pst=con.prepareStatement("select Course_name from fees_details");
             java.sql.ResultSet rs=pst.executeQuery();
             while(rs.next()){
                combo_SelectCourse.addItem(rs.getString("Course_name"));
             }        
        }
        catch(Exception e){
           e.printStackTrace();
        }       
    }

//       public void setRecordTable(){
//        try{
//            Connection con= DBConnection.getConnection();
//            PreparedStatement pst=con.prepareStatement("select* from fees_Details ");
//             java.sql.ResultSet rs=pst.executeQuery();
//            // rs.next();
//            while(rs.next()){
//                String Receipt=rs.getString("reciept_no");
//                String Roll_no=rs.getString("roll_no");
//                String Student_name=rs.getString("student_name"); 
//                String course_name=rs.getString("course_name");
//               // String payment_mode=rs.getString("payment_mode");
//                Float Amount=rs.getFloat("total_amount");
//                 String Remark=rs.getString("remark");
//                 
//                 
//                 Object[] obj={ Receipt,Roll_no,Student_name,course_name, Amount,Remark};
//                 model=(DefaultTableModel)tbl_studentData.getModel();
//                 model.addRow(obj);                
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        
//    }
//       
     public void setRecordTable() {
         String cname=combo_SelectCourse.getSelectedItem().toString();
         SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
         String fromDate=dateFormat.format(txt_dateChooser1.getDate());
         String toDate=dateFormat.format(txt_datechooser2.getDate());
         
         Float Total_amount=0.0f;
         
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from fees_details where date between ? and ? and course_name=? ");
            pst.setString(1, fromDate);
            pst.setString(2, toDate);
            pst.setString(3, cname);
            java.sql.ResultSet rs = pst.executeQuery();
            // rs.next();
            while (rs.next()) {
               // int course_ID = rs.getInt("Id");
                String receiptNo = rs.getString("reciept_no");
                String roll_no = rs.getString("roll_no");
                String StudentName = rs.getString("student_name");
                 String course_Taken = rs.getString("course_name");
                 float amount=rs.getFloat("Total_amount");
                  String remarkd = rs.getString("remark");
                
                
                  Total_amount=Total_amount+amount;
                  
                Object[] obj = {receiptNo, roll_no, StudentName,course_Taken, amount,remarkd};
                model = (DefaultTableModel)tbl_studentData.getModel();
                model.addRow(obj);
            }
            lbl_CourseSelect.setText(cname);
            lbl_TotalAmountCollected.setText( Total_amount.toString());
            lbl_Total_AmountInWords.setText(NumberToWordsConverter.convert( Total_amount.intValue()));
            
        }     
        catch (Exception e) {
            e.printStackTrace();
        }

    }
     
      public void clearTable() {
        DefaultTableModel model = (DefaultTableModel)tbl_studentData.getModel();
        model.setRowCount(1);
    }
      
      
      public void exportToExcel(){
          XSSFWorkbook wb=new XSSFWorkbook();
          XSSFSheet ws=wb.createSheet();
          
        DefaultTableModel model = (DefaultTableModel)tbl_studentData.getModel();
        TreeMap<String,Object[]> map=new TreeMap<>();
        
        
        
        
        
        
        map.put("0",new Object[]{model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),
        model.getColumnName(3),model.getColumnName(4),model.getColumnName(5)});
        
        
        for(int i=1;i<model.getRowCount();i++){
            map.put(Integer.toString(i),new Object[]{model.getValueAt(i, 0),model.getValueAt(i, 1),model.getValueAt(i, 2),
            model.getValueAt(i, 3),model.getValueAt(i, 4),model.getValueAt(i, 5)});
        }
        
//       for(Map.Entry<String,Object[]>entry :map.entrySet()){
//           String key=entry.getKey();
//           Object[] value=entry.getValue();
//           System.out.println(Arrays.toString(value));
//       }
        Set<String> id=map.keySet();
        XSSFRow fRow;
        int rowId=0;
        for(String key:id){
            fRow=ws.createRow(rowId++);
            Object[] value= map.get(key);
            int cellId=0;
            
            for(Object object: value){
               XSSFCell cell=fRow.createCell(cellId++);
               cell.setCellValue(object.toString());
            }
            
        }
        try(FileOutputStream fos=new  FileOutputStream(new File(txt_fillPath.getText()))){
           
           wb.write(fos);
           JOptionPane.showMessageDialog(this, "File exported successfully :"+txt_fillPath.getText());
        }
        catch(Exception e){
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
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        combo_SelectCourse = new javax.swing.JComboBox<>();
        txt_datechooser2 = new com.toedter.calendar.JDateChooser();
        txt_dateChooser1 = new com.toedter.calendar.JDateChooser();
        btn_Export_to_Excel = new javax.swing.JButton();
        txt_fillPath = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_studentData = new javax.swing.JTable();
        btn_Submit = new javax.swing.JButton();
        btn_Print = new javax.swing.JButton();
        btn_Browse = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbl_CourseSelect = new javax.swing.JLabel();
        lbl_TotalAmountCollected = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_Total_AmountInWords = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        jPanel1.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 270, 80));

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

        jPanel1.add(jPanelSearchRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 270, 80));

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

        jPanel1.add(jPanelEditCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 270, 80));

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

        jPanel1.add(jPanelCourseList, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 270, 70));

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

        jPanel1.add(jPanelViewAllRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 270, 80));

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

        jPanel1.add(jPanelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 270, 80));

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

        jPanel1.add(jPanelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 690, 270, 80));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("             View Report");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 230, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 240, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 1080));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Select Date:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 120, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("From date:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 110, 40));

        combo_SelectCourse.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jPanel2.add(combo_SelectCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 220, 40));
        jPanel2.add(txt_datechooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 180, 30));
        jPanel2.add(txt_dateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 180, 30));

        btn_Export_to_Excel.setBackground(new java.awt.Color(0, 51, 51));
        btn_Export_to_Excel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btn_Export_to_Excel.setForeground(new java.awt.Color(255, 255, 255));
        btn_Export_to_Excel.setText("Export to Excel");
        btn_Export_to_Excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Export_to_ExcelActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Export_to_Excel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 210, -1));
        jPanel2.add(txt_fillPath, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 420, 30));

        tbl_studentData.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbl_studentData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Receipt No.", "Roll No.", "Student Name.", "Course Taken", "Amount.", "Remark"
            }
        ));
        jScrollPane2.setViewportView(tbl_studentData);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 980, 560));

        btn_Submit.setBackground(new java.awt.Color(0, 51, 51));
        btn_Submit.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btn_Submit.setForeground(new java.awt.Color(255, 255, 255));
        btn_Submit.setText("Submit");
        btn_Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SubmitActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 110, -1));

        btn_Print.setBackground(new java.awt.Color(0, 51, 51));
        btn_Print.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btn_Print.setForeground(new java.awt.Color(255, 255, 255));
        btn_Print.setText("Print");
        btn_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 110, -1));

        btn_Browse.setBackground(new java.awt.Color(0, 51, 51));
        btn_Browse.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btn_Browse.setForeground(new java.awt.Color(255, 255, 255));
        btn_Browse.setText("Browse");
        btn_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BrowseActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, 110, -1));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_CourseSelect.setBackground(new java.awt.Color(255, 255, 255));
        lbl_CourseSelect.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        lbl_CourseSelect.setForeground(new java.awt.Color(204, 0, 51));
        lbl_CourseSelect.setText("Course Selected:");
        jPanel3.add(lbl_CourseSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 140, 40));

        lbl_TotalAmountCollected.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        lbl_TotalAmountCollected.setForeground(new java.awt.Color(204, 0, 51));
        lbl_TotalAmountCollected.setText("Total Amount Collected:");
        jPanel3.add(lbl_TotalAmountCollected, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 210, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Course Selected:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 40));

        lbl_Total_AmountInWords.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        lbl_Total_AmountInWords.setForeground(new java.awt.Color(204, 0, 51));
        lbl_Total_AmountInWords.setText("                                         Total Amount In Words:");
        jPanel3.add(lbl_Total_AmountInWords, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 460, 40));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Total Amount Collected:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 210, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Total Amount In Words:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 210, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 490, 200));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("To Date:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 90, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Select Course:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 140, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 1250, 1080));

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

    private void jLabelSearchReacordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearchReacordMouseClicked
        // TODO add your handling code here:

        SearchRecord sr = new SearchRecord();
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelSearchReacordMouseClicked

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

    private void jLabelEditCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditCourseMouseClicked
        // TODO add your handling code here:

        EditCourse ec=new EditCourse();
        ec.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabelEditCourseMouseClicked

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

    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
        // TODO add your handling code here:
        
        
         SimpleDateFormat Date_Format = new SimpleDateFormat("YYYY-MM-dd"); 
        String datefrom=  Date_Format.format(txt_dateChooser1.getDate());
      String dateto=  Date_Format.format(txt_datechooser2.getDate());
       
        MessageFormat header=new MessageFormat("Report From "+datefrom+" To " +dateto);
MessageFormat footer=new MessageFormat("page{0,number,integer}");
        try 
        
        {
           tbl_studentData.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        } 
        
        catch (Exception e) {
            e.getMessage();
        } 
    }//GEN-LAST:event_btn_PrintActionPerformed

    private void btn_SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SubmitActionPerformed
        // TODO add your handling code here:
        clearTable();
        setRecordTable() ;
    }//GEN-LAST:event_btn_SubmitActionPerformed

    private void btn_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BrowseActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChoose=new JFileChooser();
        fileChoose.showOpenDialog(this);
        
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String date=dateFormat.format(new Date());
        try{
           File f=fileChoose.getSelectedFile();
           String path=f.getAbsolutePath();
           
           path=path+"_"+date+".xlsx";
           txt_fillPath.setText(path);
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        
    }//GEN-LAST:event_btn_BrowseActionPerformed

    private void btn_Export_to_ExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Export_to_ExcelActionPerformed
        // TODO add your handling code here:
        
        exportToExcel();
    }//GEN-LAST:event_btn_Export_to_ExcelActionPerformed

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
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Browse;
    private javax.swing.JButton btn_Export_to_Excel;
    private javax.swing.JButton btn_Print;
    private javax.swing.JButton btn_Submit;
    private javax.swing.JComboBox<String> combo_SelectCourse;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelCourseList;
    private javax.swing.JLabel jLabelEditCourse;
    private javax.swing.JLabel jLabelLogout;
    private javax.swing.JLabel jLabelSearchReacord;
    private javax.swing.JLabel jLabelViewAllRecord;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelBack;
    private javax.swing.JPanel jPanelCourseList;
    private javax.swing.JPanel jPanelEditCourse;
    private javax.swing.JPanel jPanelLogout;
    private javax.swing.JPanel jPanelSearchRecord;
    private javax.swing.JPanel jPanelViewAllRecord;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlebelHome;
    private javax.swing.JLabel lbl_CourseSelect;
    private javax.swing.JLabel lbl_TotalAmountCollected;
    private javax.swing.JLabel lbl_Total_AmountInWords;
    private javax.swing.JPanel panelHome;
    private javax.swing.JTable tbl_studentData;
    private com.toedter.calendar.JDateChooser txt_dateChooser1;
    private com.toedter.calendar.JDateChooser txt_datechooser2;
    private javax.swing.JTextField txt_fillPath;
    // End of variables declaration//GEN-END:variables
}
