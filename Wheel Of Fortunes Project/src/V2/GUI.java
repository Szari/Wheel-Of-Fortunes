package V2;

public class GUI extends javax.swing.JFrame {

    public GUI() {
        initComponents();
        resetComponents();
        loginPanel.setVisible(true);
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        nickname.setVisible(true);
        startButton.setVisible(true);
        exitButton.setEnabled(false);
        errorLobby1.setVisible(false);
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
        exitButton = new javax.swing.JButton();
        leaveButton = new javax.swing.JButton();
        waitingPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        loginInLobby = new javax.swing.JLabel();
        nrPokoju = new javax.swing.JTextField();
        roomButton = new javax.swing.JButton();
        pokoje = new javax.swing.JTextArea();
        errorLobby = new javax.swing.JLabel();
        errorLobby1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Wheel Of Fortunes");

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
                .addGap(204, 204, 204)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLogin)
                    .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                        .addComponent(nickname)))
                .addContainerGap(302, Short.MAX_VALUE))
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
        sendText.setEnabled(false);

        infoLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        stawka.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        stawka.setText("Stawka: ");

        stawkaL.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N

        odgadywane.setText("Już odgadywane: ");

        newGame.setText("Nowa gra");

        exitButton.setText("Exit");

        leaveButton.setText("Back to Lobby");

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
                                .addComponent(stawkaL, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(tekstZgadujacego, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newGame))))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(odgadywane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(litery)))
                .addContainerGap(211, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(leaveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap())
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(leaveButton))
                .addGap(102, 102, 102)
                .addComponent(haslo)
                .addGap(18, 18, 18)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(odgadywane)
                    .addComponent(litery))
                .addGap(33, 33, 33)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player2)
                    .addComponent(player3))
                .addGap(18, 18, 18)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p2)
                    .addComponent(pp2)
                    .addComponent(p3)
                    .addComponent(pp3))
                .addGap(51, 51, 51)
                .addComponent(infoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stawka, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stawkaL, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        waitingPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        loginLabel.setText("Twój login:");

        nrPokoju.setToolTipText("Wpisz numer pokoju");

        roomButton.setText("Wybierz pokój");
        roomButton.setActionCommand("");

        pokoje.setEditable(false);
        pokoje.setColumns(20);
        pokoje.setRows(5);

        errorLobby.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        errorLobby.setForeground(new java.awt.Color(255, 0, 0));
        errorLobby.setText("Wybierz odpowiedni numer pokoju, jeden z pokazanych powyżej.");

        errorLobby1.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        errorLobby1.setForeground(new java.awt.Color(255, 0, 0));
        errorLobby1.setText("Odszedł jeden z graczy z pokoju :/");

        javax.swing.GroupLayout waitingPanelLayout = new javax.swing.GroupLayout(waitingPanel);
        waitingPanel.setLayout(waitingPanelLayout);
        waitingPanelLayout.setHorizontalGroup(
            waitingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waitingPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(waitingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(waitingPanelLayout.createSequentialGroup()
                        .addGroup(waitingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(waitingPanelLayout.createSequentialGroup()
                                .addComponent(nrPokoju, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pokoje, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(loginLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginInLobby)
                        .addGap(246, 246, 246))
                    .addGroup(waitingPanelLayout.createSequentialGroup()
                        .addGroup(waitingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorLobby)
                            .addGroup(waitingPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(errorLobby1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        waitingPanelLayout.setVerticalGroup(
            waitingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waitingPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(waitingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(loginInLobby)
                    .addComponent(pokoje, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(waitingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nrPokoju, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLobby)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLobby1)
                .addContainerGap(373, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(waitingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(waitingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
    
    public final void resetComponents(){
        errorLogin.setVisible(false);
        gamePanel.setVisible(false);
        haslo.setVisible(false);
        infoLabel.setVisible(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        litery.setVisible(false);
        loginInLobby.setVisible(false);
        loginLabel.setVisible(false);
        loginPanel.setVisible(false);
        newGame.setVisible(false);
        nickname.setVisible(false);
        nrPokoju.setVisible(false);
        odgadywane.setVisible(false);
        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
        player1.setVisible(false);
        player2.setVisible(false);
        player3.setVisible(false);
        pokoje.setVisible(false);
        pp1.setVisible(false);
        pp2.setVisible(false);
        pp3.setVisible(false);
        roomButton.setVisible(false);
        sendText.setVisible(false);
        startButton.setVisible(false);
        stawka.setVisible(false);
        stawkaL.setVisible(false);
        tekstZgadujacego.setVisible(false);
        waitingPanel.setVisible(false);
        errorLobby.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel errorLobby;
    public javax.swing.JLabel errorLobby1;
    public javax.swing.JLabel errorLogin;
    public javax.swing.JButton exitButton;
    public javax.swing.JPanel gamePanel;
    public javax.swing.JLabel haslo;
    public javax.swing.JLabel infoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JButton leaveButton;
    public javax.swing.JLabel litery;
    public javax.swing.JLabel loginInLobby;
    public javax.swing.JLabel loginLabel;
    public javax.swing.JPanel loginPanel;
    public javax.swing.JButton newGame;
    public javax.swing.JTextField nickname;
    public javax.swing.JTextField nrPokoju;
    public javax.swing.JLabel odgadywane;
    public javax.swing.JLabel p1;
    public javax.swing.JLabel p2;
    public javax.swing.JLabel p3;
    public javax.swing.JLabel player1;
    public javax.swing.JLabel player2;
    public javax.swing.JLabel player3;
    public javax.swing.JTextArea pokoje;
    public javax.swing.JLabel pp1;
    public javax.swing.JLabel pp2;
    public javax.swing.JLabel pp3;
    public javax.swing.JButton roomButton;
    public javax.swing.JButton sendText;
    public javax.swing.JButton startButton;
    public javax.swing.JLabel stawka;
    public javax.swing.JLabel stawkaL;
    public javax.swing.JTextField tekstZgadujacego;
    public javax.swing.JPanel waitingPanel;
    // End of variables declaration//GEN-END:variables
}
