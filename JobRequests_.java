/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//package my.UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class JobRequests_ extends javax.swing.JFrame {

    /**
     * Creates new form JobRequests_
     */
    public JobRequests_() {
        setVisible(false);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Enter Job id");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("view requests");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("<<Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton2)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        
public int rowsfound;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        String jid_ = this.jTextField1.getText();
        int jid = Integer.parseInt(jid_);
        String message = "";
        int mid;
        Login l = new Login();
        mid = l.uid;
        if(jid_.length()==0)
            message+="jid cannot be empty";
        try
        {
            //select count(*) from jobs where mid = 101;
           
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con= DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:RDBMS","it20737124","vasavi"); 
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select count(*) from jobs where mid = "+mid +"and jid = "+jid);
                int count =0 ;
                while(rs.next())
                        count+=rs.getInt(1);
                if(count<1)
                    JOptionPane.showMessageDialog(new JFrame(), "This job is not posted by you", "error", JOptionPane.ERROR_MESSAGE);
                else
                {
                    //select * from students where aid in (select aid from jobs_applied where jid = jid)
                    //similar for job_hoppers and display them in tables
                    String sql = "select aid,aname,skills,qualification from students where aid in (select aid from jobs_applied where jid = "+jid+")";
                    PreparedStatement statement = con.prepareStatement(sql);            
                statement.execute();
                rowsfound = statement.executeUpdate();
                System.out.println("Hello");
                if(rowsfound==0) 
               	 {
                        //write logic for data insertion into the database
                        JOptionPane.showMessageDialog(new JFrame(),"No details Found! ", "error", JOptionPane.ERROR_MESSAGE);
                        
                 } 
                else {
        	  st=con.createStatement();
                 String query="select aid,aname,skills,qualification from students where aid in (select aid from jobs_applied where jid = "+jid+")";
                 //statement.setString(1,pname1);
                 System.out.println(query);
                 //PreparedStatement st = con.prepareStatement(query);
                 rs=st.executeQuery(query);
                 //table.setModel(DbUtils.resultSetToTableModel(rs));
                 while(rs.next())
                         {
                           String aid=String.valueOf(rs.getInt("aid"));
                           String aname=rs.getString("aname");
                           String skills=rs.getString("skills");
                           String qualification=rs.getString("qualification");
                          
                           String tbData[]={aid,aname,skills,qualification};
                           DefaultTableModel tblModel=(DefaultTableModel)jTable1.getModel();
                           tblModel.addRow(tbData);
                         }
                 
            System.out.println(query);
                }
                System.out.println("Done");
        	  
        	//close the connection object  
        	con.close(); 
               
                
                
                
                }
                
                
                
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con= DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:RDBMS","it20737124","vasavi"); 
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select count(*) from jobs where mid = "+mid +"and jid = "+jid);
                int count =0 ;
                while(rs.next())
                        count+=rs.getInt(1);
                if(count<1)
                    JOptionPane.showMessageDialog(new JFrame(), "This job is not posted by you", "error", JOptionPane.ERROR_MESSAGE);
                else
                {
                    //select * from students where aid in (select aid from jobs_applied where jid = jid)
                    //similar for job_hoppers and display them in tables
                    String sql = "select aid,aname,skills,qualification from job_hoppers where aid in (select aid from jobs_applied where jid = "+jid+")";
                    PreparedStatement statement = con.prepareStatement(sql);            
                statement.execute();
                rowsfound += statement.executeUpdate();
                System.out.println("Hello");
                if(rowsfound==0) 
               	 {
                        //write logic for data insertion into the database
                        JOptionPane.showMessageDialog(new JFrame(),"No details Found! ", "error", JOptionPane.ERROR_MESSAGE);
                        
                 } 
                else {
        	  st=con.createStatement();
                 String query="select aid,aname,skills,qualification from job_hoppers where aid in (select aid from jobs_applied where jid = "+jid+")";
                 //statement.setString(1,pname1);
                 System.out.println(query);
                 //PreparedStatement st = con.prepareStatement(query);
                 rs=st.executeQuery(query);
                 //table.setModel(DbUtils.resultSetToTableModel(rs));
                 while(rs.next())
                         {
                           String aid=String.valueOf(rs.getInt("aid"));
                           String aname=rs.getString("aname");
                           String skills=rs.getString("skills");
                           String qualification=rs.getString("qualification");
                          
                           String tbData[]={aid,aname,skills,qualification};
                           DefaultTableModel tblModel=(DefaultTableModel)jTable1.getModel();
                           tblModel.addRow(tbData);
                         }
                 
            System.out.println(query);
                }
                System.out.println("Done");
        	  
        	//close the connection object  
        	con.close(); 
               
                
                
                
                }
                
                
                
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
            
        
        
        
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        Manager m = new Manager();
        m.setVisible(true);
        dispose();
    }                                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

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
            java.util.logging.Logger.getLogger(JobRequests_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JobRequests_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JobRequests_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JobRequests_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JobRequests_().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}