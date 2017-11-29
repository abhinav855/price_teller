
package price_teller;


import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abhinav
 */
public final class Main_window extends javax.swing.JFrame {

   /* static void main() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    /**
     * Creates new form Main_window
     */
    public Main_window() {
        initComponents();
        getConnection();
        show_products_in_JTable();
        
    }
    String ImgPath=null;
    int pos=0;
   public Connection getConnection() 
    {
        Connection con=null;
        try
        {
             Class.forName("com.mysql.jdbc.Driver").newInstance();
            con =DriverManager.getConnection("jdbc:mysql://localhost/products_db","root","123456");
           //JOptionPane.showMessageDialog(null,"Connection Established");
           
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Connection Error");
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
         return con;
    }
    //check inpts
    public boolean checkInputs()
    {
        if(txt_name.getText() == null || txt_price.getText()== null || btn_date.getDate() == null)
        {
            return false;
        }
        else
        {
            try
            {
                Float.parseFloat(txt_price.getText());
                return true;
            }
            catch(Exception e)
            {
                return false;
            }
        }
        
    }
 //Resize Image
    public ImageIcon ResizeImage(String imagePath, byte[] pic)
    {
         ImageIcon myImage=null;
        
        
        if(imagePath != null)
        {
            myImage= new ImageIcon(imagePath);
        }
        else
        {
            myImage =new ImageIcon(pic);
        }
        Image img=myImage.getImage();
        Image img2=img.getScaledInstance(lbl_image.getWidth(),lbl_image.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image=new ImageIcon(img2);
        return image;
        
    }
    
    //Display data in JTable
        // 1. Fill array list with the data
                
    public ArrayList<Product> getProductList()
    {
            ArrayList<Product> productList=new ArrayList<Product>();
            Connection con=getConnection();
            String query= "SELECT * FROM products";
             Statement st;
            ResultSet rs;
            
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            Product product;
               
           while(rs.next())
           {
               product=new Product(rs.getInt("id"), rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getString("add_date"),rs.getBytes("image"));
               productList.add(product);
           }
        
        } catch (SQLException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }
    
    // 2- populate JTable
    
    public void show_products_in_JTable()
    {
        
        ArrayList<Product> list=getProductList();
        DefaultTableModel model=(DefaultTableModel)JTable_Products.getModel();
        model.setRowCount(0);
        Object[] row=new Object[4];
        for(int i=0; i<list.size(); i++)
        {
            row[0]=list.get(i).getId();
            row[1]=list.get(i).getName();
            row[2]=list.get(i).getPrice();
            row[3]=list.get(i).getDate();
            
            model.addRow(row);
        }
    }
    public void ShowItem(int index)
    {
        txt_id.setText(Integer.toString(getProductList().get(index).getId()));
        txt_name.setText(getProductList().get(index).getName());
        txt_price.setText(Float.toString(getProductList().get(index).getPrice()));
        try{
        Date addDate=null;
        addDate=new SimpleDateFormat("dd-MM-yyyy").parse((String)getProductList().get(index).getDate());
           btn_date.setDate(addDate);
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        
        lbl_image.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        btn_choose_image = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        btn_previous = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_date = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Image:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("ID:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Add Date:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Price:");

        txt_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_name.setPreferredSize(new java.awt.Dimension(59, 50));
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        txt_id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_id.setEnabled(false);
        txt_id.setPreferredSize(new java.awt.Dimension(59, 50));
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        txt_price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_price.setPreferredSize(new java.awt.Dimension(59, 50));

        lbl_image.setBackground(new java.awt.Color(51, 255, 255));
        lbl_image.setForeground(new java.awt.Color(0, 204, 204));
        lbl_image.setOpaque(true);

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Add Date"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        btn_choose_image.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_choose_image.setText("Choose Image");
        btn_choose_image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_choose_imageActionPerformed(evt);
            }
        });

        btn_insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_insert.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abhinav\\Documents\\NetBeansProjects\\price_teller\\must_have_icon_set\\Add\\Add_16x16.png")); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.setIconTextGap(20);
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abhinav\\Documents\\NetBeansProjects\\price_teller\\1497485749_Update.png")); // NOI18N
        btn_update.setText("Update");
        btn_update.setIconTextGap(20);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abhinav\\Documents\\NetBeansProjects\\price_teller\\must_have_icon_set\\Delete\\Delete_16x16.png")); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.setIconTextGap(20);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_first.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_first.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abhinav\\Documents\\NetBeansProjects\\price_teller\\fatcow-hosting-icons-900\\16x16_0760\\resultset_first.png")); // NOI18N
        btn_first.setText("First");
        btn_first.setIconTextGap(20);
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_last.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_last.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abhinav\\Documents\\NetBeansProjects\\price_teller\\fatcow-hosting-icons-900\\16x16_0760\\resultset_last.png")); // NOI18N
        btn_last.setText("Last");
        btn_last.setIconTextGap(20);
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        btn_previous.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_previous.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abhinav\\Documents\\NetBeansProjects\\price_teller\\must_have_icon_set\\Previous\\Previous_16x16.png")); // NOI18N
        btn_previous.setText("Previous");
        btn_previous.setIconTextGap(20);
        btn_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_previousActionPerformed(evt);
            }
        });

        btn_next.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_next.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abhinav\\Documents\\NetBeansProjects\\price_teller\\must_have_icon_set\\Next\\Next_16x16.png")); // NOI18N
        btn_next.setText("Next");
        btn_next.setIconTextGap(20);
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_choose_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_id, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                    .addComponent(btn_date, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btn_insert)
                .addGap(18, 18, 18)
                .addComponent(btn_update)
                .addGap(18, 18, 18)
                .addComponent(btn_delete)
                .addGap(18, 18, 18)
                .addComponent(btn_first)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_next)
                .addGap(18, 18, 18)
                .addComponent(btn_previous)
                .addGap(18, 18, 18)
                .addComponent(btn_last)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_choose_image))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if(checkInputs() && txt_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps=null;
            Connection con=getConnection();
            if(ImgPath == null)
            {
                try {
                         UpdateQuery="UPDATE products SET name = ? , price = ? , add_date=? WHERE id=?";
                         ps=con.prepareStatement(UpdateQuery);
                          ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    
                    
                    SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy");
                    String addDate=dateformat.format(btn_date.getDate());
                    ps.setString(3, addDate);
                    ps.setInt(4, Integer.parseInt(txt_id.getText()));
                    
                    ps.executeUpdate();
                     show_products_in_JTable();
                    JOptionPane.showMessageDialog(null,"Databse Updated");
                } catch (SQLException ex) {
                    Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                try {
                    //update with image
                     
                    InputStream img=new FileInputStream(new File(ImgPath));
                    UpdateQuery="UPDATE products SET name = ? , price = ?  ,add_date=? , image = ? WHERE id=?";
                    ps=con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    
                    
                    SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy");
                    String addDate=dateformat.format(btn_date.getDate());
                    ps.setString(3, addDate);
                    ps.setBlob(4, img);
                    ps.setInt(5, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                     show_products_in_JTable();
                    JOptionPane.showMessageDialog(null,"Databse Updated");
                } catch (FileNotFoundException | SQLException ex)
                {
                    Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"One or more fields are empty or wrong");
           
        }

    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if(!txt_id.getText().equals(""))
        {
            try {
                Connection con=getConnection();
                PreparedStatement ps=con.prepareStatement("DELETE FROM products WHERE id=?");
                int id=Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                 show_products_in_JTable();
                JOptionPane.showMessageDialog(null,"Produc Deleted");
            } catch (SQLException ex) {
                Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
                  
                JOptionPane.showMessageDialog(null,"Produc Not Deleted");
            }
        }
        else
        {
                 JOptionPane.showMessageDialog(null,"Produc Not Deleted: No product id");
        }
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos=0;
        ShowItem(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        pos=getProductList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_previousActionPerformed
        pos--;
        if(pos < 0) 
        {
            pos=0;
        }
        ShowItem(pos);
    }//GEN-LAST:event_btn_previousActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;
        if(pos >=getProductList().size())
        {
             pos=getProductList().size()-1;
        }
        ShowItem(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_choose_imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_choose_imageActionPerformed
      JFileChooser file=new JFileChooser();
      file.setCurrentDirectory(new File(System.getProperty("user.home")));
      
        FileNameExtensionFilter filter=new FileNameExtensionFilter("*.images","jpg","png");
        file.addChoosableFileFilter(filter);
        int result=file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile=file.getSelectedFile();
            String path=selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path ,null));
            ImgPath=path;
        }
        else
        {
            System.out.println("No file selected");
        }
    }//GEN-LAST:event_btn_choose_imageActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
            if(checkInputs() && ImgPath != null)
            {
                
                try
                {
                    Connection con=getConnection();
                    PreparedStatement ps=con.prepareStatement("Insert into products (name,price,add_date,image) values(?,?,?,?)");
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    
                    
                    SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy");
                    String addDate=dateformat.format(btn_date.getDate());
                    ps.setString(3, addDate);
                    InputStream img1=new FileInputStream(new File(ImgPath));
                    ps.setBlob(4, img1);
                    ps.executeUpdate();
                    show_products_in_JTable();
                    JOptionPane.showMessageDialog(null,"Data inserted");
                }
                catch(SQLException e)
                {
                    e.printStackTrace();  
                    JOptionPane.showMessageDialog(null,e.getMessage());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "One or More fields are Empty");
            }
         /*   System.out.println("Name: "+txt_name.getText());
            System.out.println("Price: "+txt_price.getText());
            System.out.println("Image: "+ImgPath);*/
    }//GEN-LAST:event_btn_insertActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
                int Index=JTable_Products.getSelectedRow();
                ShowItem(Index);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

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
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main_window().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Products;
    private javax.swing.JButton btn_choose_image;
    private com.toedter.calendar.JDateChooser btn_date;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_previous;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
