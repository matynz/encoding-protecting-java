/*
 * TeoriadealInfoView.java
 */
package teoriadealinfo;

import org.jdesktop.application.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.io.File;

import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * The application's main frame.
 */
public class TeoriadealInfoView extends FrameView {

    SistemaTeoInfo sis;

    public TeoriadealInfoView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
            //statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
//        statusAnimationLabel.setIcon(idleIcon);
//        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
//                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
//                    statusAnimationLabel.setIcon(idleIcon);
//                    progressBar.setVisible(false);
//                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
//                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(false);
//                    progressBar.setValue(value);
                }
            }
        });
        sis = new SistemaTeoInfo();

    }

///         FUNCIONES
//    public void setNuevoAmbiente()
//    {
//        arbol=new Arbol();
//        archivo=new Archivo(new File(""));
//        tabla=new Tabla(archivo, arbol);
//        huffman=new Huffman(archivo, arbol);
//    }
    public void Clear_table() {


        for (int aux = jtblCodigo.getRowCount() - 1; aux >= 0; aux--) {

            jtblCodigo.setValueAt("", aux, 0);
            jtblCodigo.setValueAt("", aux, 1);
            jtblCodigo.setValueAt("", aux, 2);
        }
        jtxtTexComprimido.setText("");
//        if(tabla!=null)
//        tabla.getTabla().clear();
//        if(huffman!=null){
//        huffman.map_codigo.clear();
//        huffman.map_car.clear();}
//        if(archivo!=null)
//        archivo.renglones.clear();

    }

    @SuppressWarnings("empty-statement")
    public void llenarTabla() {

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableModel temp;
        int aux = 0;
        for (NodoCodigo n : sis.getTabla()) {

            if (aux < 10) {
                jtblCodigo.setValueAt(n.str_carc, aux, 0);
                jtblCodigo.getColumnModel().getColumn(0).setCellRenderer(tcr);
                jtblCodigo.setValueAt(n.int_frecuencia, aux, 1);
                jtblCodigo.getColumnModel().getColumn(1).setCellRenderer(tcr);
                jtblCodigo.setValueAt(n.str_codigo, aux, 2);
                jtblCodigo.getColumnModel().getColumn(2).setCellRenderer(tcr);
            } else {
                temp = (DefaultTableModel) jtblCodigo.getModel();
                Object nuevo[] = {n.str_carc, n.int_frecuencia, n.str_codigo};
                temp.addRow(nuevo);

            }
            aux++;
        }
    }

///         FIN FUNCIONES
////        BOTONES    
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = TeoriadealInfoApp.getApplication().getMainFrame();
            aboutBox = new TeoriadealInfoAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        TeoriadealInfoApp.getApplication().show(aboutBox);
    }

    /**
     * This method is called f boolean fin = false;rom within the constructor to
     * initialize the form. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jbtnCargar_tabla = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblCodigo = new javax.swing.JTable();
        jbtnGenerar_codificacion = new javax.swing.JButton();
        jlbl_Aceptar = new javax.swing.JLabel();
        jbtnGuardar_t = new javax.swing.JButton();
        jlblAceptar_g = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jtxtDir = new javax.swing.JTextField();
        jbtnBuscar = new javax.swing.JButton();
        jbtnCargar_texto = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jbtnDeco = new javax.swing.JButton();
        jbtnDesHamin = new javax.swing.JButton();
        jbtnDescomprimir = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jbtnGuardar_archivo = new javax.swing.JButton();
        jbtnInErrores = new javax.swing.JButton();
        jbtnHaminizar = new javax.swing.JButton();
        jbtnComprimirArchivo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtTextosincom = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtTexComprimido = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mainPanelKeyPressed(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(teoriadealinfo.TeoriadealInfoApp.class).getContext().getResourceMap(TeoriadealInfoView.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel1.border.titleFont"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel4.border.titleFont"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jbtnCargar_tabla.setFont(resourceMap.getFont("jbtnguardart.font")); // NOI18N
        jbtnCargar_tabla.setText(resourceMap.getString("jbtnCargar_tabla.text")); // NOI18N
        jbtnCargar_tabla.setEnabled(false);
        jbtnCargar_tabla.setName("jbtnCargar_tabla"); // NOI18N
        jbtnCargar_tabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCargar_tablaActionPerformed(evt);
            }
        });

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jtblCodigo.setFont(resourceMap.getFont("jtblCodigo.font")); // NOI18N
        jtblCodigo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                " Simbolo", "Frecuencia", "     Codigo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblCodigo.setName("jtblCodigo"); // NOI18N
        jtblCodigo.setSelectionBackground(resourceMap.getColor("jtblCodigo.selectionBackground")); // NOI18N
        jScrollPane3.setViewportView(jtblCodigo);
        jtblCodigo.getColumnModel().getColumn(0).setResizable(false);
        jtblCodigo.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtblCodigo.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jtblCodigo.columnModel.title0")); // NOI18N
        jtblCodigo.getColumnModel().getColumn(1).setResizable(false);
        jtblCodigo.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jtblCodigo.columnModel.title2")); // NOI18N
        jtblCodigo.getColumnModel().getColumn(2).setResizable(false);
        jtblCodigo.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jtblCodigo.columnModel.title1")); // NOI18N

        jbtnGenerar_codificacion.setFont(resourceMap.getFont("jbtnGenerar_codificacion.font")); // NOI18N
        jbtnGenerar_codificacion.setText(resourceMap.getString("jbtnGenerar_codificacion.text")); // NOI18N
        jbtnGenerar_codificacion.setEnabled(false);
        jbtnGenerar_codificacion.setName("jbtnGenerar_codificacion"); // NOI18N
        jbtnGenerar_codificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGenerar_codificacionActionPerformed(evt);
            }
        });

        jlbl_Aceptar.setIcon(resourceMap.getIcon("jlbl_Aceptar.icon")); // NOI18N
        jlbl_Aceptar.setText(resourceMap.getString("jlbl_Aceptar.text")); // NOI18N
        jlbl_Aceptar.setEnabled(false);
        jlbl_Aceptar.setName("jlbl_Aceptar"); // NOI18N

        jbtnGuardar_t.setFont(resourceMap.getFont("jbtnGuardar_t.font")); // NOI18N
        jbtnGuardar_t.setText(resourceMap.getString("jbtnGuardar_t.text")); // NOI18N
        jbtnGuardar_t.setEnabled(false);
        jbtnGuardar_t.setName("jbtnGuardar_t"); // NOI18N
        jbtnGuardar_t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardar_tActionPerformed(evt);
            }
        });

        jlblAceptar_g.setIcon(resourceMap.getIcon("jlblAceptar_g.icon")); // NOI18N
        jlblAceptar_g.setText(resourceMap.getString("jlblAceptar_g.text")); // NOI18N
        jlblAceptar_g.setEnabled(false);
        jlblAceptar_g.setName("jlblAceptar_g"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnGuardar_t, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(jbtnCargar_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblAceptar_g))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jbtnGenerar_codificacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlbl_Aceptar)))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlbl_Aceptar)
                            .addComponent(jbtnGenerar_codificacion))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jbtnCargar_tabla)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnGuardar_t))
                            .addComponent(jlblAceptar_g)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel5.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel5.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel5.border.titleFont"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jtxtDir.setBackground(resourceMap.getColor("jtxtDir.background")); // NOI18N
        jtxtDir.setEditable(false);
        jtxtDir.setFont(resourceMap.getFont("jtxtDir.font")); // NOI18N
        jtxtDir.setName("jtxtDir"); // NOI18N
        jtxtDir.setSelectionColor(resourceMap.getColor("jtxtDir.selectionColor")); // NOI18N

        jbtnBuscar.setFont(resourceMap.getFont("jbtnBuscar.font")); // NOI18N
        jbtnBuscar.setText(resourceMap.getString("jbtnBuscar.text")); // NOI18N
        jbtnBuscar.setName("jbtnBuscar"); // NOI18N
        jbtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarActionPerformed(evt);
            }
        });

        jbtnCargar_texto.setFont(resourceMap.getFont("jbtnBuscar.font")); // NOI18N
        jbtnCargar_texto.setText(resourceMap.getString("jbtnCargar_texto.text")); // NOI18N
        jbtnCargar_texto.setEnabled(false);
        jbtnCargar_texto.setName("jbtnCargar_texto"); // NOI18N
        jbtnCargar_texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCargar_textoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnCargar_texto)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jbtnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtDir, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnBuscar)
                    .addComponent(jtxtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnCargar_texto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel6.border.titleFont"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N

        jPanel7.setBackground(resourceMap.getColor("jPanel7.background")); // NOI18N
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel7.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel7.border.titleFont"))); // NOI18N
        jPanel7.setName("jPanel7"); // NOI18N

        jbtnDeco.setFont(resourceMap.getFont("jbtnDeco.font")); // NOI18N
        jbtnDeco.setText(resourceMap.getString("jbtnDeco.text")); // NOI18N
        jbtnDeco.setName("jbtnDeco"); // NOI18N
        jbtnDeco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDecoActionPerformed(evt);
            }
        });

        jbtnDesHamin.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jbtnDesHamin.setText(resourceMap.getString("jbtnDesHamin.text")); // NOI18N
        jbtnDesHamin.setEnabled(false);
        jbtnDesHamin.setName("jbtnDesHamin"); // NOI18N
        jbtnDesHamin.setPreferredSize(new java.awt.Dimension(151, 23));
        jbtnDesHamin.setPressedIcon(resourceMap.getIcon("jbtnDesHamin.pressedIcon")); // NOI18N
        jbtnDesHamin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDesHaminActionPerformed(evt);
            }
        });

        jbtnDescomprimir.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jbtnDescomprimir.setText(resourceMap.getString("jbtnDescomprimir.text")); // NOI18N
        jbtnDescomprimir.setEnabled(false);
        jbtnDescomprimir.setName("jbtnDescomprimir"); // NOI18N
        jbtnDescomprimir.setPreferredSize(new java.awt.Dimension(151, 23));
        jbtnDescomprimir.setPressedIcon(resourceMap.getIcon("jbtnDescomprimir.pressedIcon")); // NOI18N
        jbtnDescomprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDescomprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnDeco, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnDesHamin, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnDescomprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnDeco)
                .addGap(18, 18, 18)
                .addComponent(jbtnDesHamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnDescomprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(resourceMap.getColor("jPanel8.background")); // NOI18N
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel8.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel8.border.titleFont"))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jbtnGuardar_archivo.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jbtnGuardar_archivo.setText(resourceMap.getString("jbtnGuardar_archivo.text")); // NOI18N
        jbtnGuardar_archivo.setEnabled(false);
        jbtnGuardar_archivo.setName("jbtnGuardar_archivo"); // NOI18N
        jbtnGuardar_archivo.setPreferredSize(new java.awt.Dimension(151, 23));
        jbtnGuardar_archivo.setRequestFocusEnabled(false);
        jbtnGuardar_archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardar_archivoActionPerformed(evt);
            }
        });

        jbtnInErrores.setFont(resourceMap.getFont("jbtnInErrores.font")); // NOI18N
        jbtnInErrores.setText(resourceMap.getString("jbtnInErrores.text")); // NOI18N
        jbtnInErrores.setEnabled(false);
        jbtnInErrores.setName("jbtnInErrores"); // NOI18N
        jbtnInErrores.setPreferredSize(new java.awt.Dimension(151, 23));
        jbtnInErrores.setRequestFocusEnabled(false);
        jbtnInErrores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnInErroresActionPerformed(evt);
            }
        });

        jbtnHaminizar.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jbtnHaminizar.setText(resourceMap.getString("jbtnHaminizar.text")); // NOI18N
        jbtnHaminizar.setEnabled(false);
        jbtnHaminizar.setName("jbtnHaminizar"); // NOI18N
        jbtnHaminizar.setPreferredSize(new java.awt.Dimension(151, 23));
        jbtnHaminizar.setRequestFocusEnabled(false);
        jbtnHaminizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHaminizarActionPerformed(evt);
            }
        });

        jbtnComprimirArchivo.setFont(resourceMap.getFont("jbtnComprimirArchivo.font")); // NOI18N
        jbtnComprimirArchivo.setText(resourceMap.getString("jbtnComprimirArchivo.text")); // NOI18N
        jbtnComprimirArchivo.setEnabled(false);
        jbtnComprimirArchivo.setName("jbtnComprimirArchivo"); // NOI18N
        jbtnComprimirArchivo.setPreferredSize(new java.awt.Dimension(151, 23));
        jbtnComprimirArchivo.setRequestFocusEnabled(false);
        jbtnComprimirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnComprimirArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jbtnHaminizar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnInErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jbtnGuardar_archivo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jbtnComprimirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnComprimirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnInErrores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnHaminizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnGuardar_archivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel2.border.titleFont"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jtxtTextosincom.setEditable(false);
        jtxtTextosincom.setName("jtxtTextosincom"); // NOI18N
        jtxtTextosincom.setSelectionColor(resourceMap.getColor("jtxtTextosincom.selectionColor")); // NOI18N
        jScrollPane1.setViewportView(jtxtTextosincom);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel3.border.titleFont"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jtxtTexComprimido.setEditable(false);
        jtxtTexComprimido.setName("jtxtTexComprimido"); // NOI18N
        jtxtTexComprimido.setSelectionColor(resourceMap.getColor("jtxtTexComprimido.selectionColor")); // NOI18N
        jScrollPane2.setViewportView(jtxtTexComprimido);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(530, 530, 530)
                        .addComponent(jLabel1)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenu1.add(jMenuItem1);

        jPopupMenu1.add(jMenu1);

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarActionPerformed

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto", "txt");
        JFileChooser FCnuevo = new JFileChooser();
        FCnuevo.setFileFilter(filtro);
        FCnuevo.setDialogTitle("Abrir archivo...");
        File ruta = new File("C:/Users/Mauricio/Documents");
        FCnuevo.setCurrentDirectory(ruta);
        int intresult = FCnuevo.showOpenDialog(null);

        if (intresult == JFileChooser.APPROVE_OPTION) {
            if (jbtnGenerar_codificacion.isEnabled()) {
                Clear_table();
                jtxtTexComprimido.setText("");
                jtxtTextosincom.setText("");
                jbtnCargar_tabla.setEnabled(false);
                jbtnGenerar_codificacion.setEnabled(false);
                jbtnGuardar_t.setEnabled(false);
                jlblAceptar_g.setEnabled(false);
                jlbl_Aceptar.setEnabled(false);
            }
            sis.setASCIIPath(FCnuevo.getSelectedFile());
            jtxtDir.setText(String.valueOf(FCnuevo.getSelectedFile()));
            jbtnCargar_texto.setEnabled(true);

        }
    // TODO add your handling code here:
}//GEN-LAST:event_jbtnBuscarActionPerformed

    private void jbtnCargar_textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCargar_textoActionPerformed
        String texto = sis.getASCIIText();
        jtxtTextosincom.setText(texto);
        jbtnGenerar_codificacion.setEnabled(true);
        jbtnCargar_texto.setEnabled(false);
}//GEN-LAST:event_jbtnCargar_textoActionPerformed

private void jbtnGenerar_codificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGenerar_codificacionActionPerformed
    sis.codeTabla();
    jlbl_Aceptar.setEnabled(true);
    jbtnCargar_tabla.setEnabled(true);

}//GEN-LAST:event_jbtnGenerar_codificacionActionPerformed

private void jbtnCargar_tablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCargar_tablaActionPerformed
    llenarTabla();
    jbtnGuardar_t.setEnabled(true);
}//GEN-LAST:event_jbtnCargar_tablaActionPerformed

private void jbtnGuardar_tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardar_tActionPerformed
    JFileChooser FCnuevo = new JFileChooser();
    FCnuevo.setDialogTitle("Guardar archivo...");
    File ruta = new File("C:/Users/Mauricio/Documents");
    FCnuevo.setCurrentDirectory(ruta);
    int intresult = FCnuevo.showSaveDialog(null);

    if (intresult == JFileChooser.APPROVE_OPTION) {
        sis.guardarTabla(FCnuevo.getSelectedFile().toString());
        jlblAceptar_g.setEnabled(true);
        jbtnComprimirArchivo.setEnabled(true);
    }

}//GEN-LAST:event_jbtnGuardar_tActionPerformed

private void mainPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mainPanelKeyPressed

    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        jPopupMenu1.setVisible(true);
    }
}//GEN-LAST:event_mainPanelKeyPressed

private void jbtnComprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    sis.comprimirArchivo();
    jbtnHaminizar.setEnabled(true);
    jbtnGuardar_archivo.setEnabled(true);
}//GEN-LAST:event_jButton1ActionPerformed

private void jbtnHaminizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHaminizarActionPerformed

    // Agregar cartel para seleecinar el tamaño(1024, 512,256)
    Tam_bloque tpe_new = new Tam_bloque(null, true);
    tpe_new.setLocationRelativeTo(null);
    tpe_new.setVisible(true);
    int dato = tpe_new.intValor;

    sis.hamminizar(dato);
    jbtnInErrores.setEnabled(true);
// TODO add your handling code here:
}//GEN-LAST:event_jbtnHaminizarActionPerformed

private void jbtnInErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInErroresActionPerformed

    sis.insertarRuido();
// TODO add your handling code here:
}//GEN-LAST:event_jbtnInErroresActionPerformed

private void jbtnGuardar_archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardar_archivoActionPerformed

    sis.escribirArchivo();
// TODO add your handling code here:
}//GEN-LAST:event_jbtnGuardar_archivoActionPerformed

private void jbtnDecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDecoActionPerformed

    JFileChooser FCnuevo = new JFileChooser();
    FCnuevo.setDialogTitle("Abrir archivo...");
    File ruta = new File("C:/Users/Mauricio/Documents");
    FCnuevo.setCurrentDirectory(ruta);
    int intresult = FCnuevo.showOpenDialog(null);

    if (intresult == JFileChooser.APPROVE_OPTION) {
        Clear_table();
        sis.setPathDescomprimir(FCnuevo.getSelectedFile().toString());
        jbtnDesHamin.setEnabled(true);
        jbtnDescomprimir.setEnabled(true);

    }
// TODO add your handling code here:
}//GEN-LAST:event_jbtnDecoActionPerformed

private void jbtnDescomprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDescomprimirActionPerformed

    sis.descomprimirArchivo();
    String texto = sis.getTextoDescomprimido();
    jtxtTexComprimido.setText(texto);

// TODO add your handling code here:
}//GEN-LAST:event_jbtnDescomprimirActionPerformed

private void jbtnDesHaminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDesHaminActionPerformed

    sis.desHamminizar();
    // TODO add your handling code here:
}//GEN-LAST:event_jbtnDesHaminActionPerformed

private void jbtnComprimirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnComprimirArchivoActionPerformed

    sis.comprimirArchivo();
    jbtnHaminizar.setEnabled(true);
    jbtnGuardar_archivo.setEnabled(true);
    // TODO add your handling code here:
}//GEN-LAST:event_jbtnComprimirArchivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnCargar_tabla;
    private javax.swing.JButton jbtnCargar_texto;
    private javax.swing.JButton jbtnComprimirArchivo;
    private javax.swing.JButton jbtnDeco;
    private javax.swing.JButton jbtnDesHamin;
    private javax.swing.JButton jbtnDescomprimir;
    private javax.swing.JButton jbtnGenerar_codificacion;
    private javax.swing.JButton jbtnGuardar_archivo;
    private javax.swing.JButton jbtnGuardar_t;
    private javax.swing.JButton jbtnHaminizar;
    private javax.swing.JButton jbtnInErrores;
    private javax.swing.JLabel jlblAceptar_g;
    private javax.swing.JLabel jlbl_Aceptar;
    private javax.swing.JTable jtblCodigo;
    private javax.swing.JTextField jtxtDir;
    private javax.swing.JTextPane jtxtTexComprimido;
    private javax.swing.JTextPane jtxtTextosincom;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
