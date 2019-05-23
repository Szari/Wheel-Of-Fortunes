public class GUI extends javax.swing.JFrame {

    public GUI() {
        initComponents();
        gamePanel.setVisible(false);
        p1.setVisible(false);
        pp1.setVisible(false);
        p2.setVisible(false);
        pp2.setVisible(false);
        p3.setVisible(false);
        pp3.setVisible(false);
        haslo.setVisible(false);
        sendText.setEnabled(false);
        stawka.setVisible(false);
        odgadywane.setVisible(false);
        newGame.setVisible(false);
        errorLogin.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nickname = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        errorLogin = new javax.swing.JLabel();
        gamePanel = new javax.swing.JPanel();
        haslo = new javax.swing.JLabel();
        player1 = new javax.swing.JLabel();
        player2 = new javax.swing.JLabel();
        player3 = new javax.swing.JLabel();
        p2 = new javax.swing.JLabel();
        pp2 = new javax.swing.JLabel();
        p1 = new javax.swing.JLabel();
        pp1 = new javax.swing.JLabel();
        p3 = new javax.swing.JLabel();
        pp3 = new javax.swing.JLabel();
        tekstZgadujacego = new javax.swing.JTextField();
        sendText = new javax.swing.JButton();
        infoLabel = new javax.swing.JLabel();
        stawka = new javax.swing.JLabel();
        stawkaL = new javax.swing.JLabel();
        odgadywane = new javax.swing.JLabel();
        litery = new javax.swing.JLabel();
        newGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wheel of Fortunes");
        setLocation(new java.awt.Point(300, 100));
        setMinimumSize(new java.awt.Dimension(900, 600));

        jLabel1.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Wheel of Fortunes");

        jLabel2.setText("Podaj nickname: ");

        startButton.setText("Start");

        errorLogin.setForeground(new java.awt.Color(255, 0, 0));
        errorLogin.setText("Nickname powinien być dłuższy niż 3 znaki ");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(nickname))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(334, Short.MAX_VALUE)
                .addComponent(errorLogin)
                .addGap(319, 319, 319))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel1)
                .addGap(154, 154, 154)
                .addComponent(errorLogin)
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        haslo.setFont(new java.awt.Font("Palatino Linotype", 0, 24)); // NOI18N
        haslo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        haslo.setText("HASŁO DO ODGADNIECIA");

        player1.setText("Player1");

        p2.setText("Punkty:");

        pp2.setText("0");

        p1.setText("Punkty:");

        pp1.setText("0");

        p3.setText("Punkty:");

        pp3.setText("0");

        tekstZgadujacego.setToolTipText("");

        sendText.setText("Guess");

        infoLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        stawka.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        stawka.setText("Stawka: ");

        stawkaL.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N

        odgadywane.setText("Już odgadywane: ");

        newGame.setText("Nowa gra");

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(haslo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addComponent(p2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pp2))
                    .addComponent(player2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(player3)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addComponent(p3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pp3)))
                .addGap(25, 25, 25))
            .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(gamePanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(p1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pp1))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, gamePanelLayout.createSequentialGroup()
                            .addGap(211, 211, 211)
                            .addComponent(player1)))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(stawka)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stawkaL))
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(tekstZgadujacego, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sendText)
                                .addGap(18, 18, 18)
                                .addComponent(newGame))))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(odgadywane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(litery)))
                .addContainerGap(195, Short.MAX_VALUE))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(haslo)
                .addGap(18, 18, 18)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(odgadywane)
                    .addComponent(litery))
                .addGap(33, 33, 33)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player2)
                    .addComponent(player3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p2)
                    .addComponent(pp2)
                    .addComponent(p3)
                    .addComponent(pp3))
                .addGap(51, 51, 51)
                .addComponent(infoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stawka, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stawkaL))
                .addGap(34, 34, 34)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tekstZgadujacego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendText)
                    .addComponent(newGame))
                .addGap(4, 4, 4)
                .addComponent(player1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p1)
                    .addComponent(pp1))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel errorLogin;
    public javax.swing.JPanel gamePanel;
    public javax.swing.JLabel haslo;
    public javax.swing.JLabel infoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel litery;
    public javax.swing.JPanel loginPanel;
    public javax.swing.JButton newGame;
    public javax.swing.JTextField nickname;
    public javax.swing.JLabel odgadywane;
    public javax.swing.JLabel p1;
    public javax.swing.JLabel p2;
    public javax.swing.JLabel p3;
    public javax.swing.JLabel player1;
    public javax.swing.JLabel player2;
    public javax.swing.JLabel player3;
    public javax.swing.JLabel pp1;
    public javax.swing.JLabel pp2;
    public javax.swing.JLabel pp3;
    public javax.swing.JButton sendText;
    public javax.swing.JButton startButton;
    public javax.swing.JLabel stawka;
    public javax.swing.JLabel stawkaL;
    public javax.swing.JTextField tekstZgadujacego;
    // End of variables declaration//GEN-END:variables
}
