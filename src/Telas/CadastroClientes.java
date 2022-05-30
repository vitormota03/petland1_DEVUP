package Telas;

import conexoes.MySQL;
import javax.swing.JOptionPane;
import Objetos.Cadastro;

public class CadastroClientes extends javax.swing.JFrame {

    MySQL conectar = new MySQL(); //acessar os métodos de conexao com o banco
    Cadastro novoCadastro = new Cadastro(); //acessar os atributos da classe cliente

    public CadastroClientes() {
        initComponents();
    }

    private void cadastraCliente(Cadastro novoCadastro) {
        this.conectar.conectaBanco();

        novoCadastro.setNome(campoNome.getText());

        novoCadastro.setCpf(campoCpf.getText());
        novoCadastro.setCep(campoCep.getText());
        novoCadastro.setEmail(campoEmail.getText());
        novoCadastro.setCidade(campoCidade.getText());
        novoCadastro.setRua(campoRua.getText());
        novoCadastro.setNumero(campoNumero.getText());
        novoCadastro.setComplemento(campoComplemento.getText());
        novoCadastro.setPontoReferencia(campoPR.getText());
        novoCadastro.setTelefone(campoTelefone.getText());
        novoCadastro.setEstado((String) campoEstado.getSelectedItem());

        try {

            this.conectar.insertSQL("INSERT INTO Clientes ("
                    + "nome,"
                    + "cpf,"
                    + "cep,"
                    + "email,"
                    + "cidade,"
                    + "rua,"
                    + "numero,"
                    + "complemento,"
                    + "pontoReferencia,"
                    + "telefone,"
                    + "estado"
                    + ") VALUES ("
                    + "'" + novoCadastro.getNome() + "',"
                    + "'" + novoCadastro.getCpf() + "',"
                    + "'" + novoCadastro.getCep() + "',"
                    + "'" + novoCadastro.getEmail() + "',"
                    + "'" + novoCadastro.getCidade() + "',"
                    + "'" + novoCadastro.getRua() + "',"
                    + "'" + novoCadastro.getNumero() + "',"
                    + "'" + novoCadastro.getComplemento() + "',"
                    + "'" + novoCadastro.getPontoReferencia() + "',"
                    + "'" + novoCadastro.getTelefone() + "',"
                    + "'" + novoCadastro.getEstado() + "'"
                    + ");");

        } catch (Exception e) {

            System.out.println("Erro ao cadastrar cliente " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente");

        } finally {
            this.conectar.fechaBanco();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");

            limparCamposCadastro();
            limparCamposCadastro();

        }

    }

    private void buscaCadastro(Cadastro novoCadastro) {
        this.conectar.conectaBanco();

        String consultaCpf = this.consultaCpf.getText();

        try {
            this.conectar.executarSQL(
                    "SELECT "
                    + "nome,"
                    + "cpf,"
                    + "cep,"
                    + "email,"
                    + "cidade,"
                    + "rua,"
                    + "numero,"
                    + "complemento,"
                    + "pontoReferencia,"
                    + "telefone,"
                    + "estado"
                    + " FROM"
                    + " Clientes"
                    + " WHERE"
                    + " cpf = '" + consultaCpf + "'"
                    + ";"
            );

            while (this.conectar.getResultSet().next()) {
                novoCadastro.setNome(this.conectar.getResultSet().getString(1));
                novoCadastro.setCpf(this.conectar.getResultSet().getString(2));
                novoCadastro.setCep(this.conectar.getResultSet().getString(3));
                novoCadastro.setEmail(this.conectar.getResultSet().getString(4));
                novoCadastro.setCidade(this.conectar.getResultSet().getString(5));
                novoCadastro.setRua(this.conectar.getResultSet().getString(6));
                novoCadastro.setNumero(this.conectar.getResultSet().getString(7));
                novoCadastro.setComplemento(this.conectar.getResultSet().getString(8));
                novoCadastro.setPontoReferencia(this.conectar.getResultSet().getString(9));
                novoCadastro.setTelefone(this.conectar.getResultSet().getString(10));
                novoCadastro.setEstado(this.conectar.getResultSet().getString(11));

            }

            if (novoCadastro.getNome() == "") {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar cliente " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente");

        } finally {
            campoNome1.setText(novoCadastro.getNome());
            campoCpf1.setText(novoCadastro.getCpf());
            campoCep1.setText(novoCadastro.getCep());
            campoEmail1.setText(novoCadastro.getEmail());
            campoCidade1.setText(novoCadastro.getCidade());
            campoRua1.setText(novoCadastro.getRua());
            campoNumero1.setText(novoCadastro.getNumero());
            campoComplemento1.setText(novoCadastro.getComplemento());
            campoPR1.setText(novoCadastro.getPontoReferencia());
            campoTelefone1.setText(novoCadastro.getTelefone());
            campoEstado1.setText(novoCadastro.getEstado());

            this.conectar.fechaBanco();
        }
    }

    private void deletaCadastro(Cadastro novoCadastro) {
        this.conectar.conectaBanco();

        String consultaCpf = this.consultaCpf.getText();

        try {
            this.conectar.updateSQL(
                    "DELETE FROM Clientes "
                    + " WHERE "
                    + "cpf = '" + consultaCpf + "'"
                    + ";"
            );

        } catch (Exception e) {
            System.out.println("Erro ao deletar cliente " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar cliente");
        } finally {
            this.conectar.fechaBanco();
            limparCamposBusca();
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso");
        }

    }

    public void atualizaCadastro(Cadastro novoCadastro) {
        this.conectar.conectaBanco();

        String consultaCpf = this.consultaCpf.getText();

        try {
            this.conectar.updateSQL(
                    "UPDATE Clientes SET "
                    + "nome = '" + campoEstado1.getText() + "',"
                    + "cpf = '" + campoCpf1.getText() + "',"
                    + "cep = '" + campoCep1.getText() + "',"
                    + "email = '" + campoEmail1.getText() + "',"
                    + "cidade = '" + campoCidade1.getText() + "',"
                    + "rua = '" + campoRua1.getText() + "', "
                    + "numero = '" + campoNumero1.getText() + "', "
                    + "complemento = '" + campoComplemento1.getText() + "', "
                    + "pontoReferencia = '" + campoPR1.getText() + "' "
                    + "telefone = '" + campoTelefone1.getText() + "' "
                    + "estado = '" + campoEstado1.getText() + "' "
                    + " WHERE "
                    + "cpf = '" + consultaCpf + "'"
                    + ";"
            );
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente");
        } finally {
            this.conectar.fechaBanco();
            limparCamposBusca();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso");
        }
    }

    private void limparCamposCadastro() {
        campoNome.setText("");
        campoCpf.setText("");
        campoCep.setText("");
        campoEmail.setText("");
        campoCidade.setText("");
        campoRua.setText("");
        campoNumero.setText("");
        campoComplemento.setText("");
        campoPR.setText("");
        campoTelefone.setText("");
        campoEstado.setSelectedIndex(0);
    }

    private void limparCamposBusca() {
        campoNome.setText("");
        campoEmail.setText("");
        campoCidade.setText("");
        campoCep.setText("");
        campoRua.setText("");
        campoNumero.setText("");
        campoPR.setText("");
        campoCpf.setText("");
        campoTelefone.setText("");
        campoComplemento.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDialog1 = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        jColorChooser1 = new javax.swing.JColorChooser();
        jOptionPane1 = new javax.swing.JOptionPane();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollBar1 = new javax.swing.JScrollBar();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        jScrollPane5 = new javax.swing.JScrollPane();
        jEditorPane2 = new javax.swing.JEditorPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        campoComplemento = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        campoRua = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        campoPR = new javax.swing.JTextArea();
        jButton8 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton9 = new javax.swing.JButton();
        campoCpf = new javax.swing.JFormattedTextField();
        campoTelefone = new javax.swing.JFormattedTextField();
        campoCep = new javax.swing.JFormattedTextField();
        campoEstado = new javax.swing.JComboBox<>();
        campoNumero = new javax.swing.JTextField();
        campoCidade = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        consultaCadastro = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        consultaCpf = new javax.swing.JFormattedTextField();
        jLabel61 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        campoEstado1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        campoCpf1 = new javax.swing.JFormattedTextField();
        jLabel32 = new javax.swing.JLabel();
        campoEmail1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        campoTelefone1 = new javax.swing.JFormattedTextField();
        jLabel34 = new javax.swing.JLabel();
        campoCep1 = new javax.swing.JFormattedTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        campoCidade1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        campoComplemento1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        campoRua1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        campoNumero1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        campoPR1 = new javax.swing.JTextArea();
        jLabel43 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        campoNome1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTree1);

        jScrollPane2.setViewportView(jEditorPane1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jScrollPane4.setViewportView(jTree2);

        jScrollPane5.setViewportView(jEditorPane2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable2);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jList1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel20.setText("jLabel20");

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(254, 134, 47));

        jPanel8.setBackground(new java.awt.Color(181, 181, 181));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/logopet.jpg"))); // NOI18N
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setText("NOME");

        campoNome.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setText("CPF");

        campoEmail.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        campoEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmailActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel22.setText("E-MAIL");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel23.setText("TELEFONE (com ddd)");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel24.setText("CEP");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel25.setText("ESTADO");

        campoComplemento.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel27.setText("COMPLEMENTO (ex: casa)");

        campoRua.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel29.setText("RUA");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel30.setText("Nº");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel26.setText("PONTO DE REFERÊNCIA (opcional)");

        campoPR.setColumns(20);
        campoPR.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        campoPR.setRows(5);
        jScrollPane8.setViewportView(campoPR);

        jButton8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton8.setText("LIMPAR ");
        jButton8.setBorder(null);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jToggleButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jToggleButton1.setText("CANCELAR");
        jToggleButton1.setBorder(null);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(207, 182, 174));
        jButton9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(60, 63, 65));
        jButton9.setText("CADASTRAR CLIENTE");
        jButton9.setBorder(new javax.swing.border.MatteBorder(null));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        try {
            campoCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCpfActionPerformed(evt);
            }
        });

        try {
            campoTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            campoCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        campoEstado.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        campoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        campoEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEstadoActionPerformed(evt);
            }
        });

        campoNumero.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        campoCidade.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel28.setText("CIDADE");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel19))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel22))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoCep, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel29)
                        .addGap(241, 241, 241)
                        .addComponent(jLabel30))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(campoRua, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(campoCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(campoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)
                            .addComponent(campoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel23)))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel24))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(campoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel25)))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(campoCep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel28)
                        .addGap(6, 6, 6)
                        .addComponent(campoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(campoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(campoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel26))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("CADASTAR CLIENTE", jPanel1);

        jPanel10.setBackground(new java.awt.Color(254, 134, 47));
        jPanel10.setForeground(new java.awt.Color(60, 63, 65));

        jPanel11.setBackground(new java.awt.Color(181, 181, 181));
        jPanel11.setPreferredSize(new java.awt.Dimension(484, 161));

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/logopet.jpg"))); // NOI18N
        jLabel35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        consultaCadastro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        consultaCadastro.setText("BUSCAR");
        consultaCadastro.setBorder(null);
        consultaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaCadastroActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setText("LIMPAR");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        try {
            consultaCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        consultaCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaCpfActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel61.setText("CPF");

        jPanel14.setBackground(new java.awt.Color(254, 134, 47));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMAÇÕES "));

        campoEstado1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel31.setText("NOME");

        try {
            campoCpf1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCpf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCpf1ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel32.setText("CPF");

        campoEmail1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        campoEmail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmail1ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel33.setText("E-MAIL");

        try {
            campoTelefone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel34.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel34.setText("TELEFONE (com ddd)");

        try {
            campoCep1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel37.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel37.setText("CEP");

        jLabel38.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel38.setText("ESTADO");

        campoCidade1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel39.setText("CIDADE");

        campoComplemento1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel40.setText("COMPLEMENTO (ex: casa)");

        campoRua1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel41.setText("RUA");

        campoNumero1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel42.setText("Nº");

        campoPR1.setColumns(20);
        campoPR1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        campoPR1.setRows(5);
        jScrollPane9.setViewportView(campoPR1);

        jLabel43.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel43.setText("PONTO DE REFERÊNCIA (opcional)");

        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setText("ATUALIZAR");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton4.setText("DELETAR");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        campoNome1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(campoEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel31)
                            .addGap(331, 331, 331)
                            .addComponent(jLabel32))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGap(385, 385, 385)
                            .addComponent(campoCpf1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel33)
                            .addGap(323, 323, 323)
                            .addComponent(jLabel34))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel38)))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoCep1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campoCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(17, 17, 17)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel40)
                                .addComponent(campoComplemento1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(jLabel41)
                                    .addGap(241, 241, 241)
                                    .addComponent(jLabel42)
                                    .addGap(80, 80, 80)
                                    .addComponent(jLabel43))
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(campoRua1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(campoNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(19, 19, 19)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(campoNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(campoEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel31)
                        .addComponent(jLabel32))
                    .addGap(6, 6, 6)
                    .addComponent(campoCpf1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel33)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel34)))
                    .addGap(5, 5, 5)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(campoEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel37))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(campoTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel38)))
                    .addGap(5, 5, 5)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(campoCep1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel39)
                            .addGap(6, 6, 6)
                            .addComponent(campoCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel40)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(campoComplemento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel41)
                        .addComponent(jLabel42)
                        .addComponent(jLabel43))
                    .addGap(8, 8, 8)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(campoRua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(consultaCpf))
                        .addGap(18, 18, 18)
                        .addComponent(consultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(consultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(consultaCpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("CONSULTAR CADASTRO DE CLIENTE ", jPanel6);

        jPanel12.setBackground(java.awt.Color.lightGray);
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(181, 181, 181));

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/logopet.jpg"))); // NOI18N
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 170));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cadastro (2).png"))); // NOI18N
        jPanel12.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/entregavel (2).png"))); // NOI18N
        jPanel12.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/carrinho-de-compras (2).png"))); // NOI18N
        jPanel12.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("VENDAS - CARRINHO");
        jPanel12.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("ESTOQUE - PRODUTOS");
        jPanel12.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, -1, -1));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel18.setText("CADASTROS");
        jPanel12.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, -1, -1));

        jButton5.setBackground(new java.awt.Color(254, 134, 47));
        jButton5.setForeground(new java.awt.Color(254, 134, 47));
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 410, 190));

        jButton6.setBackground(new java.awt.Color(254, 134, 47));
        jButton6.setForeground(new java.awt.Color(254, 134, 47));
        jButton6.setBorder(null);
        jPanel12.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 270, 390));

        jButton7.setBackground(new java.awt.Color(254, 134, 47));
        jButton7.setForeground(new java.awt.Color(254, 134, 47));
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, 410, 190));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("MENU", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 788, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
        CadastroProdutos cadastroProdutos = new CadastroProdutos();
        cadastroProdutos.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        dispose();
        CadastroClientes cadastros = new CadastroClientes();
        cadastros.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void campoEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEmailActionPerformed

    private void campoEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEstadoActionPerformed

    private void consultaCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consultaCpfActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        consultaCpf.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        deletaCadastro(novoCadastro);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void campoCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCpfActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        cadastraCliente(novoCadastro);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        campoNome.setText("");
        campoEmail.setText("");
        campoCep.setText("");
        campoRua.setText("");
        campoNumero.setText("");
        campoPR.setText("");
        campoCpf.setText("");
        campoTelefone.setText("");
        campoComplemento.setText("");

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void consultaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaCadastroActionPerformed
        buscaCadastro(novoCadastro);
    }//GEN-LAST:event_consultaCadastroActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        atualizaCadastro(novoCadastro);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void campoCpf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCpf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCpf1ActionPerformed

    private void campoEmail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmail1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEmail1ActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroClientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroClientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroClientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroClientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField campoCep;
    private javax.swing.JFormattedTextField campoCep1;
    private javax.swing.JTextField campoCidade;
    private javax.swing.JTextField campoCidade1;
    private javax.swing.JTextField campoComplemento;
    private javax.swing.JTextField campoComplemento1;
    private javax.swing.JFormattedTextField campoCpf;
    private javax.swing.JFormattedTextField campoCpf1;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoEmail1;
    private javax.swing.JComboBox<String> campoEstado;
    private javax.swing.JTextField campoEstado1;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNome1;
    private javax.swing.JTextField campoNumero;
    private javax.swing.JTextField campoNumero1;
    private javax.swing.JTextArea campoPR;
    private javax.swing.JTextArea campoPR1;
    private javax.swing.JTextField campoRua;
    private javax.swing.JTextField campoRua1;
    private javax.swing.JFormattedTextField campoTelefone;
    private javax.swing.JFormattedTextField campoTelefone1;
    private javax.swing.JButton consultaCadastro;
    private javax.swing.JFormattedTextField consultaCpf;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JEditorPane jEditorPane2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree jTree2;
    // End of variables declaration//GEN-END:variables

}
