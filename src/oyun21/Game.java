// Group Members:
// 1. Sueda Esra Akbulut - 220611007
// 2. Ümmügülsün Türkmen - 230611056
// 3. Ayşenur Otaran - 220611034
// 4. Büşra Demirel - 220611029
// 5. Şeyma Akın - 220611012
package oyun21;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import oyun21.Obstacles;

public class Game {
    JButton safeHouseButton, caveButton, forestButton, riverButton, storeButton;
    JFrame frame = new JFrame();
    JLabel label2, label3, label4;
    JTextField text1;
    JButton button;
    Player player = new Player("PlayerName");
    Location location;
    MusicPlayer musicPlayer = new MusicPlayer();
    private JLabel playerInfoLabel; 
    public void logIn() {
        // Müzik çalmayı başlat
    	musicPlayer.loadMusic("src/sound/Mount and Blade Soundtrack - Swadian Hall.wav"); 
        musicPlayer.playMusic();
        label2 = new JLabel("Please login to start the game!");
        label2.setBounds(50, 250, 300, 30);
        label2.setForeground(Color.WHITE); 

        text1 = new JTextField();
        text1.setBounds(200, 300, 100, 30);

        label4 = new JLabel("Nickname: ");
        label4.setBounds(50, 300, 100, 30);
        label4.setForeground(Color.WHITE); 

        label3 = new JLabel("Password: ");
        label3.setBounds(50, 350, 100, 30);
        label3.setForeground(Color.WHITE); 

        JPasswordField password = new JPasswordField();
        password.setBounds(200, 350, 150, 30);

        button = new JButton();
        button.setText("Log in");
        button.setBounds(200, 400, 150, 50);
        button.addActionListener(e -> {
            String pass = new String(password.getPassword());
            label3.setText(label3.getText() + pass);
            String nickname = text1.getText();
            player = new Player(nickname);
            if (nickname.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in both fields!");
            } else {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                frame.dispose();
                
                characterSelectionMenu();
            }
        });

        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/image/Unknown-2.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null); 
        panel.setBounds(0, 0, 1000, 1000);

  
        panel.add(password);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(text1);
        panel.add(button);

        frame.add(panel);

        frame.setSize(1000, 1000);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    
    public void characterSelectionMenu() {
    	
    	JFrame selectionFrame = new JFrame("Character Selection");
    	selectionFrame.setSize(800, 600);
    	selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 

    	JPanel panel = new JPanel();
    	panel.setBackground(new Color(5, 145, 115)); 

    	
    	panel.setLayout(null);


    	selectionFrame.setContentPane(panel);
    	selectionFrame.setVisible(true);

        JLabel messageLabel = new JLabel("Select your character:");
        messageLabel.setBounds(50, 30, 200, 30);
        ImageIcon samuraiImage = new ImageIcon("src/image/imagesamurai.jpg");

        if (samuraiImage.getIconWidth() == -1) {
            System.out.println("Samurai image not found!");
        } else {
            int buttonWidth = 200;  // Button width
            int buttonHeight = 200; // Button height
            Image resizedSamurai = samuraiImage.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            samuraiImage = new ImageIcon(resizedSamurai);
        }

  
        JLabel samuraiNameLabel = new JLabel("Samurai", SwingConstants.CENTER);
        samuraiNameLabel.setBounds(50, 70, 200, 30); 

        JLabel samuraiImageLabel = new JLabel(samuraiImage);
        samuraiImageLabel.setBounds(50, 100, 200, 200); // Image size and position

        // Select button for Samurai
        JButton samuraiButton = new JButton("Select");
        samuraiButton.setBounds(50, 300, 200, 30); 
        samuraiButton.addActionListener(e -> {
            player.setCharacter("Samurai", 100, 10, 15);
            JOptionPane.showMessageDialog(selectionFrame, "You selected Samurai!");
            selectionFrame.dispose();
            start();
        });

        // Character attributes
        JLabel samuraiStats = new JLabel("<html>Health: 100<br>Attack: 10<br>Defense: 15 <br> Money:15 </html>", SwingConstants.CENTER);
        samuraiStats.setBounds(50, 340, 200, 60); // Stats position

        // Adding Samurai components to the frame
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        selectionFrame.add(samuraiNameLabel);
        selectionFrame.add(samuraiImageLabel);
        selectionFrame.add(samuraiButton);
        selectionFrame.add(samuraiStats);

        ImageIcon archerImage = new ImageIcon("src/image/X6Xv1UPVyQM1TW6nAMtt--0--vlfxo.jpg");
        if (archerImage.getIconWidth() == -1) {
            System.out.println("Archer image not found!");
        } else {
            int buttonWidth = 200;
            int buttonHeight = 200;
            Image resizedArcher = archerImage.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            archerImage = new ImageIcon(resizedArcher);
        }

 
        JLabel archerNameLabel = new JLabel("Archer", SwingConstants.CENTER);
        archerNameLabel.setBounds(300, 70, 200, 30); 
        
        JLabel archerImageLabel = new JLabel(archerImage);
        archerImageLabel.setBounds(300, 100, 200, 200); // Image size and position

        // Archer select button
        JButton archerButton = new JButton("Select");
        archerButton.setBounds(300, 300, 200, 30); // Button position
        archerButton.addActionListener(e -> {
            player.setCharacter("Archer", 80, 18, 20);
            JOptionPane.showMessageDialog(selectionFrame, "You selected Archer!");
            selectionFrame.dispose();
            start();
        });

        // Character attributes
        JLabel archerStats = new JLabel("<html>Health: 80<br>Attack: 18<br>Defense: 20 <br> Money:20 </html>", SwingConstants.CENTER);
        archerStats.setBounds(300, 340, 200, 60); // Stats position
 
        // Adding Archer components to the frame
        selectionFrame.add(archerNameLabel);
        selectionFrame.add(archerImageLabel);
        selectionFrame.add(archerButton);
        selectionFrame.add(archerStats);

        // Knight image and button
        ImageIcon knightImage = new ImageIcon("src/image/imageknight.jpg");
        if (knightImage.getIconWidth() == -1) {
            System.out.println("Knight image not found!");
        } else {
            int buttonWidth = 200;
            int buttonHeight = 200;
            Image resizedKnight = knightImage.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            knightImage = new ImageIcon(resizedKnight);
        }

       
        JLabel knightNameLabel = new JLabel("Knight", SwingConstants.CENTER);
        knightNameLabel.setBounds(550, 70, 200, 30); 
        
        JLabel knightImageLabel = new JLabel(knightImage);
        
        
        knightImageLabel.setBounds(550, 100, 200, 200); // Image size and position

        // Knight select button
        JButton knightButton = new JButton("Select");
        knightButton.setBounds(550, 300, 200, 30); // Button position
        knightButton.addActionListener(e -> {
            player.setCharacter("Knight", 120, 24, 5);
            JOptionPane.showMessageDialog(selectionFrame, "You selected Knight!");
            selectionFrame.dispose();
            start();
        });

     // Character attributes
        JLabel knightStats = new JLabel("<html>Health: 120<br>Attack: 24<br>Defense:  <br> Money:5 </html>", SwingConstants.CENTER);
        knightStats.setBounds(550, 340, 200, 60); // Stats position

        // Adding Knight components to the frame
        selectionFrame.add(knightNameLabel);
        selectionFrame.add(knightImageLabel);
        selectionFrame.add(knightButton);
        selectionFrame.add(knightStats);

        // Adding message label to the frame
        selectionFrame.add(messageLabel);

        // Final frame setup
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectionFrame.setVisible(true);
    }

    public void start() {
        JFrame levelFrame = new JFrame("Select a location: ");
        levelFrame.setSize(1000, 1000);
        levelFrame.setLayout(null);
     
        
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/image/bckgrnd1.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
            
        };
        levelFrame.setContentPane(backgroundPanel);

        levelFrame.setVisible(true);
    
        backgroundPanel.setLayout(null);

    
        if (playerInfoLabel == null) {
            playerInfoLabel = new JLabel();
            playerInfoLabel.setBounds(20, 20, 600, 30); 
            playerInfoLabel.setForeground(Color.BLACK);
            levelFrame.add(playerInfoLabel);
        }
        updatePlayerInfo(); 
        JButton saveGameButton = new JButton("Save Game");
        saveGameButton.setBounds(50, 500, 150, 50);
        saveGameButton.addActionListener(e -> {
            saveGame();
            JOptionPane.showMessageDialog(null, "Game saved successfully!");
        });
        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setBounds(220, 500, 150, 50);
        loadGameButton.addActionListener(e -> {
            loadGame();
            JOptionPane.showMessageDialog(null, "Game loaded successfully!");
        });
        backgroundPanel.add(loadGameButton);
        backgroundPanel.add(saveGameButton);
     // Safe House Button
        JButton safeHouseButton = new JButton("Safe House");
        safeHouseButton.setBounds(50, 150, 150, 50);
        safeHouseButton.setEnabled(true);
        safeHouseButton.addActionListener(e -> startLocation(new SafeHouse(player)));
        JLabel safeHouseLabel = new JLabel("It's your own place and no enemies.");
        safeHouseLabel.setBounds(220, 165, 400, 30);
        backgroundPanel.add(safeHouseButton);
        backgroundPanel.add(safeHouseLabel);

        // Cave Buttons and Difficulty
        JButton caveEasyButton = new JButton("Cave (Easy)");
        caveEasyButton.setBounds(50, 220, 150, 50);
        caveEasyButton.addActionListener(e -> {
            Obstacles.setDifficulty(GameDifficulty.EASY);
            startLocation(new Cave(player, this));
        });
        JButton caveHardButton = new JButton("Cave (Hard)");
        caveHardButton.setBounds(220, 220, 150, 50);
        caveHardButton.addActionListener(e -> {
            Obstacles.setDifficulty(GameDifficulty.HARD);
            startLocation(new Cave(player, this));
        });
        JLabel caveLabel = new JLabel("Level 1: Zombies may appear");
        caveLabel.setBounds(400, 235, 300, 30);
        backgroundPanel.add(caveEasyButton);
        backgroundPanel.add(caveHardButton);
        backgroundPanel.add(caveLabel);

        // Forest Buttons and Difficulty
        JButton forestEasyButton = new JButton("Forest (Easy)");
        forestEasyButton.setBounds(50, 290, 150, 50);
        forestEasyButton.addActionListener(e -> {
            Obstacles.setDifficulty(GameDifficulty.EASY);
            startLocation(new Forest(player, this));
        });
        JButton forestHardButton = new JButton("Forest (Hard)");
        forestHardButton.setBounds(220, 290, 150, 50);
        forestHardButton.addActionListener(e -> {
            Obstacles.setDifficulty(GameDifficulty.HARD);
            startLocation(new Forest(player, this));
        });
        JLabel forestLabel = new JLabel("Level 2: Vampires may appear");
        forestLabel.setBounds(400, 305, 300, 30);
        backgroundPanel.add(forestEasyButton);
        backgroundPanel.add(forestHardButton);
        backgroundPanel.add(forestLabel);

        // River Buttons and Difficulty
        JButton riverEasyButton = new JButton("River (Easy)");
        riverEasyButton.setBounds(50, 360, 150, 50);
        riverEasyButton.addActionListener(e -> {
            Obstacles.setDifficulty(GameDifficulty.EASY);
            startLocation(new River(player, this));
        });
        JButton riverHardButton = new JButton("River (Hard)");
        riverHardButton.setBounds(220, 360, 150, 50);
        riverHardButton.addActionListener(e -> {
            Obstacles.setDifficulty(GameDifficulty.HARD);
            startLocation(new River(player, this));
        });
        JLabel riverLabel = new JLabel("Level 3: Bears may appear");
        riverLabel.setBounds(400, 375, 300, 30);
        backgroundPanel.add(riverEasyButton);
        backgroundPanel.add(riverHardButton);
        backgroundPanel.add(riverLabel);

        // Tool Store Button
        JButton storeButton = new JButton("Tool Store");
        storeButton.setBounds(50, 430, 150, 50);
        storeButton.addActionListener(e -> {
            ToolStore toolStore = new ToolStore(player, this);
            toolStore.setVisible(true);
        });
        backgroundPanel.add(storeButton);

     
    }
        
    public void updatePlayerInfo() {
        if (playerInfoLabel != null) {
           
            String playerInfo = "<html>" +
                                "Character: " + player.getName() + "<br>" +
                                "Health: " + player.getHealthy() + "<br>" +
                                "Attack: " + player.getTotalDamage() + "<br>" +
                                "Defense: " + player.getInventory().getArmor() + "<br>" +
                                "Money: $" + player.getMoney() + "<br>" +
                                "Score: " + player.getScore() + "<br>" +
                                "Inventory: " + 
                                (player.getInventory().getItems().isEmpty() ? "Empty" : player.getInventory().getItems()) +
                                "</html>";

         
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BorderLayout());
            infoPanel.setBorder(BorderFactory.createTitledBorder("Player Information"));
            infoPanel.setPreferredSize(new Dimension(200, 300)); // Genişlik ve yükseklik ayarı
            infoPanel.add(new JLabel(playerInfo), BorderLayout.NORTH);

      
            JScrollPane scrollPane = new JScrollPane(infoPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    
            playerInfoLabel.removeAll();
            playerInfoLabel.setLayout(new BorderLayout());
            playerInfoLabel.add(scrollPane, BorderLayout.CENTER);
            playerInfoLabel.setBounds(750, 20, 200, 400); 
            playerInfoLabel.revalidate();
            playerInfoLabel.repaint();
        }
    }
    public void saveGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Game");
        
        int userSelection = fileChooser.showSaveDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File saveFile = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
                GameState gameState = new GameState(player, caveCompleted, forestCompleted, riverCompleted);
                oos.writeObject(gameState);
                System.out.println("Game saved successfully to: " + saveFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Error saving game: " + e.getMessage());
            }
        } else {
            System.out.println("Save operation cancelled.");
        }
    }

    public void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Game");
        
        int userSelection = fileChooser.showOpenDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File loadFile = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(loadFile))) {
                GameState gameState = (GameState) ois.readObject();

         
                this.player = gameState.getPlayer();
                this.caveCompleted = gameState.isCaveCompleted();
                this.forestCompleted = gameState.isForestCompleted();
                this.riverCompleted = gameState.isRiverCompleted();

       
                updatePlayerInfo();
                unlockNextLevel();

                System.out.println("Game loaded successfully from: " + loadFile.getAbsolutePath());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading game: " + e.getMessage());
            }
        } else {
            System.out.println("Load operation cancelled.");
        }
    }
    
    

    private boolean caveCompleted = false;
    private boolean forestCompleted = false;
    private boolean riverCompleted = false;
    public void startLocation(Location selectedLocation) {
        this.location = selectedLocation;

        boolean locationCompleted = location.getLoc(); 

        if (!locationCompleted) {
            JOptionPane.showMessageDialog(frame, "Game Over! You failed to complete the location.");
            return;
        }

        updatePlayerInfo();
        unlockNextLevel();
    }
    
    private void unlockNextLevel() {
        if (caveCompleted && !forestCompleted) {
            forestButton.setEnabled(true);
        } 
        if (forestCompleted && !riverCompleted) {
            riverButton.setEnabled(true);
        }
    }



    }
