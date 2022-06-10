package telas;

import conexoes.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import objetos.Cliente;

public class cadastroCliente extends javax.swing.JFrame {

    MySQL conectar = new MySQL(); //acessar os métodos de conexao com o banco
    Cliente novoCliente = new Cliente(); //acessar os atributos da classe cliente

    public cadastroCliente() {
        initComponents();

        setLocationRelativeTo(null);

        Date data = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Ldata.setText(formato.format(data));
        Ldata1.setText(formato.format(data));

        Timer timer = new Timer(1000, new hora());
        timer.start();
    }

    private void cadastraCliente(Cliente novoCliente) {
        this.conectar.conectaBanco();

        novoCliente.setNome(campoNome.getText());
        novoCliente.setCpf(campoCpf.getText());
        novoCliente.setCep(campoCep.getText());
        novoCliente.setEmail(campoEmail.getText());
        novoCliente.setCidade(campoCidade.getText());
        novoCliente.setRua(campoRua.getText());
        novoCliente.setNumero(campoNumero.getText());
        novoCliente.setComplemento(campoComplemento.getText());
        novoCliente.setPontoReferencia(campoPR.getText());
        novoCliente.setTelefone(campoTelefone.getText());
        novoCliente.setEstado((String) campoEstado.getSelectedItem());
        novoCliente.setBairro(campoBairro.getText());
        novoCliente.setDataNasc(campoDataNasc.getText());

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
                    + "estado,"
                    + "bairro,"
                    + "dataNasc"
                    + ") VALUES ("
                    + "'" + novoCliente.getNome() + "',"
                    + "'" + novoCliente.getCpf() + "',"
                    + "'" + novoCliente.getCep() + "',"
                    + "'" + novoCliente.getEmail() + "',"
                    + "'" + novoCliente.getCidade() + "',"
                    + "'" + novoCliente.getRua() + "',"
                    + "'" + novoCliente.getNumero() + "',"
                    + "'" + novoCliente.getComplemento() + "',"
                    + "'" + novoCliente.getPontoReferencia() + "',"
                    + "'" + novoCliente.getTelefone() + "',"
                    + "'" + novoCliente.getEstado() + "' ,"
                    + "'" + novoCliente.getBairro() + "' ,"
                    + "'" + novoCliente.getDataNasc() + "' "
                    + ");");

        } catch (Exception e) {

            System.out.println("Erro ao cadastrar cliente " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente");

        } finally {
            this.conectar.fechaBanco();

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso",
                    "Successfully", JOptionPane.INFORMATION_MESSAGE);

            LimparCliente();

        }

    }

    private void buscaCliente(Cliente novoCliente) {
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
                    + "estado,"
                    + "bairro,"
                    + "dataNasc"
                    + " FROM"
                    + " Clientes"
                    + " WHERE"
                    + " cpf = '" + consultaCpf + "'"
                    + ";"
            );

            while (this.conectar.getResultSet().next()) {
                novoCliente.setNome(this.conectar.getResultSet().getString(1));
                novoCliente.setCpf(this.conectar.getResultSet().getString(2));
                novoCliente.setCep(this.conectar.getResultSet().getString(3));
                novoCliente.setEmail(this.conectar.getResultSet().getString(4));
                novoCliente.setCidade(this.conectar.getResultSet().getString(5));
                novoCliente.setRua(this.conectar.getResultSet().getString(6));
                novoCliente.setNumero(this.conectar.getResultSet().getString(7));
                novoCliente.setComplemento(this.conectar.getResultSet().getString(8));
                novoCliente.setPontoReferencia(this.conectar.getResultSet().getString(9));
                novoCliente.setTelefone(this.conectar.getResultSet().getString(10));
                novoCliente.setEstado(this.conectar.getResultSet().getString(11));
                novoCliente.setBairro(this.conectar.getResultSet().getString(12));
                novoCliente.setDataNasc(this.conectar.getResultSet().getString(13));

            }

            if (novoCliente.getCpf().equals("")) {
                JOptionPane.showMessageDialog(this, "CPF não cadastrado e/ou inválido",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "CPF não cadastrado e/ou inválido",
                    "Erro", JOptionPane.ERROR_MESSAGE);

        } finally {
            campoNome1.setText(novoCliente.getNome());
            campoCpf1.setText(novoCliente.getCpf());
            campoCep1.setText(novoCliente.getCep());
            campoEmail1.setText(novoCliente.getEmail());
            campoCidade1.setText(novoCliente.getCidade());
            campoRua1.setText(novoCliente.getRua());
            campoNumero1.setText(novoCliente.getNumero());
            campoComplemento1.setText(novoCliente.getComplemento());
            campoPR1.setText(novoCliente.getPontoReferencia());
            campoTelefone1.setText(novoCliente.getTelefone());
            campoEstado1.setText(novoCliente.getEstado());
            campoBairro1.setText(novoCliente.getBairro());
            campoDataNasc1.setText(novoCliente.getDataNasc());

            this.conectar.fechaBanco();
        }
    }

    private void deletaCadastro(Cliente novoCliente) {
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

            JOptionPane.showMessageDialog(this, "Cliente deletado com sucesso",
                    "Successfully", JOptionPane.INFORMATION_MESSAGE);

            LimparCliente1();
        }

    }

    public void atualizaCadastro(Cliente novoCliente) {
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
                    + "pontoReferencia = '" + campoPR1.getText() + "', "
                    + "telefone = '" + campoTelefone1.getText() + "', "
                    + "estado = '" + campoEstado1.getText() + "' ,"
                    + "bairro = '" + campoBairro1.getText() + "' ,"
                    + "dataNasc = '" + campoDataNasc1.getText() + "' "
                    + " WHERE "
                    + "cpf = '" + consultaCpf + "'"
                    + ";"
            );
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente");
        } finally {
            this.conectar.fechaBanco();

            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso",
                    "Successfully", JOptionPane.INFORMATION_MESSAGE);
            LimparCliente1();

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Ldata = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        Lhora = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        campoCpf = new javax.swing.JFormattedTextField();
        campoTelefone = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoDataNasc = new javax.swing.JFormattedTextField();
        campoEmail = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        campoRua = new javax.swing.JTextField();
        campoBairro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        campoPR = new javax.swing.JTextArea();
        campoNumero = new javax.swing.JTextField();
        campoComplemento = new javax.swing.JTextField();
        campoCidade = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        campoEstado = new javax.swing.JComboBox<>();
        campoCep = new javax.swing.JFormattedTextField();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        Ldata1 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        Lhora1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel61 = new javax.swing.JLabel();
        consultaCpf = new javax.swing.JFormattedTextField();
        consultaCadastro = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        campoNome1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        campoDataNasc1 = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        campoCpf1 = new javax.swing.JFormattedTextField();
        campoEmail1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        campoTelefone1 = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        campoRua1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        campoNumero1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        campoComplemento1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        campoBairro1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        campoCep1 = new javax.swing.JFormattedTextField();
        jLabel30 = new javax.swing.JLabel();
        campoCidade1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        campoPR1 = new javax.swing.JTextArea();
        campoEstado1 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel11.setBackground(new java.awt.Color(253, 168, 105));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(181, 181, 181));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ldata.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Ldata.setForeground(new java.awt.Color(0, 0, 0));
        Ldata.setText("00-00-0000");
        jPanel4.add(Ldata, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 68, -1, -1));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("DIA:");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 68, -1, -1));

        Lhora.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Lhora.setForeground(new java.awt.Color(0, 0, 0));
        Lhora.setText("00:00:00");
        jPanel4.add(Lhora, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 68, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/kkkj.jpg"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel8.setText("jLabel1");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jPanel11.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 110));

        jPanel9.setBackground(new java.awt.Color(206, 206, 206));
        jPanel9.setPreferredSize(new java.awt.Dimension(179, 747));

        jButton2.setText("TELA INICIAL");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("VOLTAR");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setText("GERENTE");
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("USER: ADMIN");

        jToggleButton1.setText("SAIR");
        jToggleButton1.setBorder(null);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 294, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jPanel11.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 750));

        jPanel7.setBackground(new java.awt.Color(253, 168, 105));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Pessoais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(750, 253));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nome completo");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 32, -1, -1));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("CPF");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 96, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Telefone");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 163, -1, -1));
        jPanel7.add(campoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 54, 300, 30));

        try {
            campoCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel7.add(campoCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 115, 300, 30));

        try {
            campoTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel7.add(campoTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 185, 300, 30));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Data de nascimento");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 32, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("E-mail");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 93, -1, -1));

        try {
            campoDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel7.add(campoDataNasc, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 54, 113, 30));
        jPanel7.add(campoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 115, 300, 30));

        jPanel11.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 766, -1));

        jPanel8.setBackground(new java.awt.Color(253, 168, 105));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logradouro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel8.setRequestFocusEnabled(false);
        jPanel8.setVerifyInputWhenFocusTarget(false);
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ponto de referência");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 139, -1, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Rua");
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 24, -1, -1));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Bairro");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 82, -1, -1));
        jPanel8.add(campoRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 46, 300, 30));
        jPanel8.add(campoBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 103, 300, 30));

        campoPR.setColumns(20);
        campoPR.setRows(5);
        jScrollPane2.setViewportView(campoPR);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 161, 300, 50));
        jPanel8.add(campoNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 46, 73, 30));
        jPanel8.add(campoComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 46, 300, 30));
        jPanel8.add(campoCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(521, 103, 150, 30));

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Nº");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 24, -1, -1));

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Complemento");
        jPanel8.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 24, -1, -1));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("CEP");
        jPanel8.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 82, -1, -1));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Cidade");
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(521, 82, -1, -1));

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Estado");
        jPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 82, -1, -1));

        campoEstado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        jPanel8.add(campoEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 103, 61, 30));

        try {
            campoCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel8.add(campoCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 103, 150, 30));

        jPanel11.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 766, 230));

        jButton8.setForeground(new java.awt.Color(0, 0, 0));
        jButton8.setText("LIMPAR");
        jButton8.setBorder(null);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 640, 112, 80));

        jButton1.setBackground(new java.awt.Color(253, 168, 105));
        jButton1.setBorder(null);
        jPanel11.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 640, 112, 80));

        jButton15.setForeground(new java.awt.Color(0, 0, 0));
        jButton15.setText("CADASTRAR");
        jButton15.setBorder(null);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 640, 112, 80));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("CADASTRO DE CLIENTE", jPanel5);

        jPanel6.setBackground(new java.awt.Color(253, 168, 105));

        jPanel10.setBackground(new java.awt.Color(181, 181, 181));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ldata1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Ldata1.setForeground(new java.awt.Color(0, 0, 0));
        Ldata1.setText("00-00-0000");
        jPanel10.add(Ldata1, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 68, -1, -1));

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("DIA:");
        jPanel10.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 68, -1, -1));

        Lhora1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Lhora1.setForeground(new java.awt.Color(0, 0, 0));
        Lhora1.setText("00:00:00");
        jPanel10.add(Lhora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 68, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/kkkj.jpg"))); // NOI18N
        jPanel10.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel13.setText("jLabel1");
        jPanel10.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jPanel12.setBackground(new java.awt.Color(206, 206, 206));

        jButton4.setText("TELA INICIAL");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("VOLTAR");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setText("GERENTE");
        jButton9.setBorder(null);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("USER: ADMIN");

        jToggleButton2.setText("SAIR");
        jToggleButton2.setBorder(null);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("CPF");

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

        consultaCadastro.setText("BUSCAR");
        consultaCadastro.setBorder(null);
        consultaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaCadastroActionPerformed(evt);
            }
        });

        jButton11.setText("LIMPAR");
        jButton11.setBorder(null);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(253, 168, 105));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados pessoais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(662, 215));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nome completo");
        jPanel13.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 24, -1, -1));
        jPanel13.add(campoNome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 46, 300, 30));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Data de nascimento");
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 24, -1, -1));

        try {
            campoDataNasc1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel13.add(campoDataNasc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 46, 113, 30));

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("CPF");
        jPanel13.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 88, -1, -1));

        campoCpf1.setEditable(false);
        campoCpf1.setBackground(java.awt.Color.lightGray);
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
        jPanel13.add(campoCpf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 107, 300, 30));
        jPanel13.add(campoEmail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 107, 300, 30));

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("E-mail");
        jPanel13.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 85, -1, -1));

        try {
            campoTelefone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel13.add(campoTelefone1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 177, 300, 30));

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Telefone");
        jPanel13.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 155, -1, -1));

        jPanel14.setBackground(new java.awt.Color(253, 168, 105));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logradouro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Rua");

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Nº");

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Complemento");

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Bairro");

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("CEP");

        try {
            campoCep1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Cidade");

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Estado");

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Ponto de referência");

        campoPR1.setColumns(20);
        campoPR1.setRows(5);
        jScrollPane3.setViewportView(campoPR1);

        campoEstado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEstado1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(332, 332, 332)
                        .addComponent(jLabel26)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel27))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(campoRua1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(campoNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(campoComplemento1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(319, 319, 319)
                        .addComponent(jLabel29)
                        .addGap(133, 133, 133)
                        .addComponent(jLabel30)
                        .addGap(123, 123, 123)
                        .addComponent(jLabel31))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(campoBairro1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(campoCep1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(campoCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoEstado1))
                    .addComponent(jLabel34)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addGap(6, 6, 6)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoRua1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoComplemento1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addGap(5, 5, 5)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoBairro1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCep1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel34)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jButton13.setForeground(new java.awt.Color(0, 0, 0));
        jButton13.setText("ATUALIZAR");
        jButton13.setBorder(null);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setForeground(new java.awt.Color(0, 0, 0));
        jButton14.setText("DELETAR");
        jButton14.setBorder(null);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton16.setForeground(new java.awt.Color(0, 0, 0));
        jButton16.setText("LIMPAR");
        jButton16.setBorder(null);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel61)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(consultaCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(consultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consultaCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consultaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("BUSCA DE CLIENTE", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Gerente gerente = new Gerente();
        gerente.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        LimparCliente();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Gerente gerente = new Gerente();
        gerente.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void consultaCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consultaCpfActionPerformed

    private void consultaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaCadastroActionPerformed
        buscaCliente(novoCliente);
    }//GEN-LAST:event_consultaCadastroActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        consultaCpf.setText("");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Deseja atualizar este cliente?", "Atualizar",
                JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
            this.atualizaCadastro(novoCliente);
        }


    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        LimparCliente1();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        cadastraCliente(novoCliente);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void campoEstado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEstado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEstado1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Deseja deletar este cliente?", "Deletar",
                JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
            this.deletaCadastro(novoCliente);
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void campoCpf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCpf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCpf1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cadastroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ldata;
    private javax.swing.JLabel Ldata1;
    private javax.swing.JLabel Lhora;
    private javax.swing.JLabel Lhora1;
    private javax.swing.JTextField campoBairro;
    private javax.swing.JTextField campoBairro1;
    private javax.swing.JFormattedTextField campoCep;
    private javax.swing.JFormattedTextField campoCep1;
    private javax.swing.JTextField campoCidade;
    private javax.swing.JTextField campoCidade1;
    private javax.swing.JTextField campoComplemento;
    private javax.swing.JTextField campoComplemento1;
    private javax.swing.JFormattedTextField campoCpf;
    private javax.swing.JFormattedTextField campoCpf1;
    private javax.swing.JFormattedTextField campoDataNasc;
    private javax.swing.JFormattedTextField campoDataNasc1;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables

    public void LimparCliente() {
        campoEstado.setSelectedIndex(0);
        campoNome.setText("");
        campoDataNasc.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoTelefone.setText("");
        campoRua.setText("");
        campoNumero.setText("");
        campoComplemento.setText("");
        campoBairro.setText("");
        campoCep.setText("");
        campoCidade.setText("");
        campoPR.setText("");
    }

    public void LimparCliente1() {
        campoEstado1.setText("");
        campoNome1.setText("");
        campoDataNasc1.setText("");
        campoCpf1.setText("");
        campoEmail1.setText("");
        campoTelefone1.setText("");
        campoRua1.setText("");
        campoNumero1.setText("");
        campoComplemento1.setText("");
        campoBairro1.setText("");
        campoCep1.setText("");
        campoCidade1.setText("");
        campoPR1.setText("");
    }

    class hora implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            Calendar now = Calendar.getInstance();
            Lhora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
            Lhora1.setText(String.format("%1$tH:%1$tM:%1$tS", now));

        }
    }
}
