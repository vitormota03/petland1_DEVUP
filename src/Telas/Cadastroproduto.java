package telas;

import conexoes.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import objetos.Produto;

public class Cadastroproduto extends javax.swing.JFrame {

    MySQL conectar = new MySQL();
    Produto novoProduto = new Produto();

    public Cadastroproduto() {
        initComponents();

        setLocationRelativeTo(null);

        Date data = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Ldata.setText(formato.format(data));
        Ldata1.setText(formato.format(data));

        Timer timer = new Timer(1000, new hora());
        timer.start();

    }

    private void cadastraProduto(Produto novoProduto) {
        this.conectar.conectaBanco();

        novoProduto.setNomeProduto(campoNome.getText());
        novoProduto.setDescricao(campoDescricao.getText());
        novoProduto.setCategoria(campoCategoria.getText());
        novoProduto.setFornecedor(campoFornecedor.getText());
        novoProduto.setValidade(campoValidade.getText());
        novoProduto.setPrecoCusto(Float.parseFloat(campoCusto.getText()));
        novoProduto.setPrecoVenda(Float.parseFloat(campoVenda.getText()));
        novoProduto.setTaxa(Float.parseFloat(campoTaxa.getText()));

        try {

            this.conectar.insertSQL("INSERT INTO Produtos ("
                    + "nome,"
                    + "descricao,"
                    + "categoria,"
                    + "fornecedor,"
                    + "validade,"
                    + "custo,"
                    + "venda,"
                    + "taxa"
                    + ") VALUES ("
                    + "'" + novoProduto.getNomeProduto() + "',"
                    + "'" + novoProduto.getDescricao() + "',"
                    + "'" + novoProduto.getCategoria() + "',"
                    + "'" + novoProduto.getFornecedor() + "',"
                    + "'" + novoProduto.getValidade() + "',"
                    + "'" + novoProduto.getPrecoCusto() + "',"
                    + "'" + novoProduto.getPrecoVenda() + "', "
                    + "'" + novoProduto.getTaxa() + "' "
                    + ");");

        } catch (Exception e) {

            System.out.println("Erro ao cadastrar produto " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto");

        } finally {
            this.conectar.fechaBanco();
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso",
                    "Successfully", JOptionPane.INFORMATION_MESSAGE);

            limpaProduto();
        }
    }

    private void buscaProduto(Produto novoProduto) {

        this.conectar.conectaBanco();

        String consultaProduto = this.consultaProduto.getText();

        try {
            this.conectar.executarSQL(
                    "SELECT "
                    + "nome,"
                    + "descricao,"
                    + "categoria,"
                    + "fornecedor,"
                    + "validade,"
                    + "custo,"
                    + "venda,"
                    + "taxa"
                    + " FROM"
                    + " Produtos"
                    + " WHERE"
                    + " nome = '" + consultaProduto + "'"
                    + ";"
            );

            while (this.conectar.getResultSet().next()) {
                novoProduto.setNomeProduto(this.conectar.getResultSet().getString(1));
                novoProduto.setDescricao(this.conectar.getResultSet().getString(2));
                novoProduto.setCategoria(this.conectar.getResultSet().getString(3));
                novoProduto.setFornecedor(this.conectar.getResultSet().getString(4));
                novoProduto.setValidade(this.conectar.getResultSet().getString(5));
                novoProduto.setPrecoCusto(this.conectar.getResultSet().getFloat(6));
                novoProduto.setPrecoVenda(this.conectar.getResultSet().getFloat(7));
                novoProduto.setTaxa(this.conectar.getResultSet().getFloat(8));
            }

            if (novoProduto.getNomeProduto().equals("")) {
                JOptionPane.showMessageDialog(this, "Produto não cadastrado e/ou inválido",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Produto não cadastrado e/ou inválido",
                    "Erro", JOptionPane.ERROR_MESSAGE);

        } finally {
            campoNome1.setText(novoProduto.getNomeProduto());
            campoDescricao1.setText(novoProduto.getDescricao());
            campoCategoria1.setText(novoProduto.getCategoria());
            campoFornecedor1.setText(novoProduto.getFornecedor());
            campoValidade1.setText(novoProduto.getValidade());
            campoCusto1.setText(Double.toString(novoProduto.getPrecoCusto()));
            campoVenda1.setText(Double.toString(novoProduto.getPrecoVenda()));
            campoTaxa1.setText(Double.toString(novoProduto.getTaxa()));

            this.conectar.fechaBanco();
        }
    }

    private void deletaProduto(Produto novoProduto) {
        this.conectar.conectaBanco();

        String consultaProduto = this.consultaProduto.getText();

        try {
            this.conectar.updateSQL(
                    "DELETE FROM Produtos"
                    + " WHERE "
                    + "nome = '" + consultaProduto + "'"
                    + ";"
            );

        } catch (Exception e) {
            System.out.println("Erro ao deletar produto " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar produto");
        } finally {
            this.conectar.fechaBanco();

            JOptionPane.showMessageDialog(this, "Produto deletado com sucesso",
                    "Successfully", JOptionPane.INFORMATION_MESSAGE);

            limpaProduto1();
        }

    }

    public void atualizaProduto(Produto novoProduto) {
        this.conectar.conectaBanco();

        String consultaProduto = this.consultaProduto.getText();

        try {
            this.conectar.updateSQL(
                    "UPDATE Produtos SET "
                    + "nome = '" + campoNome1.getText() + "',"
                    + "descricao = '" + campoDescricao1.getText() + "',"
                    + "categoria = '" + campoCategoria1.getText() + "',"
                    + "fornecedor = '" + campoFornecedor1.getText() + "',"
                    + "validade = '" + campoValidade1.getText() + "',"
                    + "custo = '" + campoCusto1.getText() + "', "
                    + "venda = '" + campoVenda1.getText() + "', "
                    + "taxa = '" + campoTaxa1.getText() + "' "
                    + " WHERE "
                    + "nome = '" + consultaProduto + "'"
                    + ";"
            );
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto");
        } finally {
            this.conectar.fechaBanco();

          
            JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso",
                    "Successfully", JOptionPane.INFORMATION_MESSAGE);
            limpaProduto1();

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        Ldata1 = new javax.swing.JLabel();
        Lhora1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        campoValidade = new javax.swing.JFormattedTextField();
        campoDescricao = new javax.swing.JTextField();
        campoCategoria = new javax.swing.JTextField();
        campoFornecedor = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        campoCusto = new javax.swing.JTextField();
        campoVenda = new javax.swing.JTextField();
        campoTaxa = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        Ldata = new javax.swing.JLabel();
        Lhora = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
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
        campoNome1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoValidade1 = new javax.swing.JFormattedTextField();
        campoDescricao1 = new javax.swing.JTextField();
        campoCategoria1 = new javax.swing.JTextField();
        campoFornecedor1 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        campoCusto1 = new javax.swing.JTextField();
        campoVenda1 = new javax.swing.JTextField();
        campoTaxa1 = new javax.swing.JTextField();
        consultaCadastro1 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        consultaProduto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(253, 168, 105));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(181, 181, 181));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/kkkj.jpg"))); // NOI18N
        jLabel34.setText("jLabel33");

        Ldata1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Ldata1.setForeground(new java.awt.Color(0, 0, 0));
        Ldata1.setText("00-00-0000");

        Lhora1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Lhora1.setForeground(new java.awt.Color(0, 0, 0));
        Lhora1.setText("00:00:00");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("DIA:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ldata1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                .addComponent(Lhora1)
                .addGap(80, 80, 80))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ldata1)
                            .addComponent(Lhora1)
                            .addComponent(jLabel22)))
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 110));

        jPanel10.setBackground(new java.awt.Color(206, 206, 206));

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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("USER: ADMIN");

        jToggleButton2.setText("SAIR");
        jToggleButton2.setBorder(null);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        jPanel11.setBackground(new java.awt.Color(253, 168, 105));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel11.setPreferredSize(new java.awt.Dimension(750, 253));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nome");

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Descrição");

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Categoria");

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Data de validade");

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Fornecedor");

        campoValidade.setBorder(null);
        try {
            campoValidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("  ##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(307, 307, 307)
                                .addComponent(jLabel17))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(campoValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(campoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(campoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel17))
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9))
                .addGap(3, 3, 3)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 766, 240));

        jPanel12.setBackground(new java.awt.Color(253, 168, 105));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel12.setRequestFocusEnabled(false);
        jPanel12.setVerifyInputWhenFocusTarget(false);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Valor de venda");

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Valor de custo");

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Taxa");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(campoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(campoTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(campoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(450, 450, 450))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel18)
                .addGap(6, 6, 6)
                .addComponent(campoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel19)
                .addGap(5, 5, 5)
                .addComponent(campoTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 330, 220));

        jButton10.setForeground(new java.awt.Color(0, 0, 0));
        jButton10.setText("CADASTRAR");
        jButton10.setBorder(null);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 510, 112, 80));

        jButton13.setForeground(new java.awt.Color(0, 0, 0));
        jButton13.setText("LIMPAR");
        jButton13.setBorder(null);
        jPanel1.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, 112, 80));

        jTabbedPane1.addTab("CADASTRO DE PRODUTOS", jPanel1);

        jPanel3.setBackground(new java.awt.Color(253, 168, 105));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(181, 181, 181));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/kkkj.jpg"))); // NOI18N
        jLabel33.setText("jLabel33");

        Ldata.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Ldata.setForeground(new java.awt.Color(0, 0, 0));
        Ldata.setText("00-00-0000");

        Lhora.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Lhora.setForeground(new java.awt.Color(0, 0, 0));
        Lhora.setText("00:00:00");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("DIA:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ldata)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                .addComponent(Lhora)
                .addGap(80, 80, 80))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Ldata)
                            .addComponent(Lhora)
                            .addComponent(jLabel21)))
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 110));

        jPanel9.setBackground(new java.awt.Color(206, 206, 206));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        jPanel7.setBackground(new java.awt.Color(253, 168, 105));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(750, 253));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nome");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Descrição");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Categoria");

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Data de validade");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Fornecedor");

        campoValidade1.setBorder(null);
        try {
            campoValidade1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("  ##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(307, 307, 307)
                                .addComponent(jLabel14))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(campoNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(campoValidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(campoDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(campoFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(campoCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel14))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoValidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addGap(3, 3, 3)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 766, 240));

        jPanel8.setBackground(new java.awt.Color(253, 168, 105));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel8.setRequestFocusEnabled(false);
        jPanel8.setVerifyInputWhenFocusTarget(false);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Valor de venda");

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Valor de custo");

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Taxa");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(campoCusto1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(campoTaxa1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(campoVenda1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(450, 450, 450))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel15)
                .addGap(6, 6, 6)
                .addComponent(campoCusto1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel16)
                .addGap(5, 5, 5)
                .addComponent(campoTaxa1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoVenda1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 330, 220));

        consultaCadastro1.setText("BUSCAR");
        consultaCadastro1.setBorder(null);
        consultaCadastro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaCadastro1ActionPerformed(evt);
            }
        });
        jPanel3.add(consultaCadastro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, 112, 45));

        jButton16.setText("LIMPAR");
        jButton16.setBorder(null);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, 112, 45));

        jButton14.setForeground(new java.awt.Color(0, 0, 0));
        jButton14.setText("ATUALIZAR");
        jButton14.setBorder(null);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 570, 112, 80));

        jButton15.setForeground(new java.awt.Color(0, 0, 0));
        jButton15.setText("DELETAR");
        jButton15.setBorder(null);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 570, 112, 80));

        jButton17.setForeground(new java.awt.Color(0, 0, 0));
        jButton17.setText("LIMPAR");
        jButton17.setBorder(null);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 570, 112, 80));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Produto");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));
        jPanel3.add(consultaProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 300, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("BUSCA DE PRODUTOS", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 973, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Gerente gerente = new Gerente();
        gerente.setVisible(true);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Gerente gerente = new Gerente();
        gerente.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void consultaCadastro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaCadastro1ActionPerformed
        buscaProduto(novoProduto);
    }//GEN-LAST:event_consultaCadastro1ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        consultaProduto.setText("");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Deseja atualizar este produto?", "Atualizar",
                JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
            this.atualizaProduto(novoProduto);
        }


    }//GEN-LAST:event_jButton14ActionPerformed

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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        cadastraProduto(novoProduto);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        if (JOptionPane.showConfirmDialog(this, "Deseja deletar este produto?", "Deletar",
                JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
            this.deletaProduto(novoProduto);
        }


    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        limpaProduto1();
    }//GEN-LAST:event_jButton17ActionPerformed

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
            java.util.logging.Logger.getLogger(Cadastroproduto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastroproduto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastroproduto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastroproduto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadastroproduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ldata;
    private javax.swing.JLabel Ldata1;
    private javax.swing.JLabel Lhora;
    private javax.swing.JLabel Lhora1;
    private javax.swing.JTextField campoCategoria;
    private javax.swing.JTextField campoCategoria1;
    private javax.swing.JTextField campoCusto;
    private javax.swing.JTextField campoCusto1;
    private javax.swing.JTextField campoDescricao;
    private javax.swing.JTextField campoDescricao1;
    private javax.swing.JTextField campoFornecedor;
    private javax.swing.JTextField campoFornecedor1;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNome1;
    private javax.swing.JTextField campoTaxa;
    private javax.swing.JTextField campoTaxa1;
    private javax.swing.JFormattedTextField campoValidade;
    private javax.swing.JFormattedTextField campoValidade1;
    private javax.swing.JTextField campoVenda;
    private javax.swing.JTextField campoVenda1;
    private javax.swing.JButton consultaCadastro1;
    private javax.swing.JTextField consultaProduto;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables

    public void limpaProduto() {
        campoNome.setText("");
        campoValidade.setText("");
        campoDescricao.setText("");
        campoFornecedor.setText("");
        campoCategoria.setText("");
        campoCusto.setText("");
        campoVenda.setText("");
        campoTaxa.setText("");
    }

    public void limpaProduto1() {
        campoNome1.setText("");
        campoValidade1.setText("");
        campoDescricao1.setText("");
        campoFornecedor1.setText("");
        campoCategoria1.setText("");
        campoCusto1.setText("");
        campoVenda1.setText("");
        campoTaxa1.setText("");

    }

    class hora implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            Calendar now = Calendar.getInstance();
            Lhora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
            Lhora1.setText(String.format("%1$tH:%1$tM:%1$tS", now));

        }

    }
}
