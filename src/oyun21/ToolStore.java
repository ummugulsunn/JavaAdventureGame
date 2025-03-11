// Group Members:
// 1. Sueda Esra Akbulut - 220611007
// 2. Ümmügülsün Türkmen - 230611056
// 3. Ayşenur Otaran - 220611034
// 4. Büşra Demirel - 220611029
// 5. Şeyma Akın - 220611012
package oyun21;

import javax.swing.*;
import java.awt.*;

public class ToolStore extends JFrame {
    private Player player;
    private Game game;
    private JLabel moneyLabel;
    private Color buttonColor = new Color(70, 130, 180); // Steel blue
    private Color backgroundColor = new Color(96, 190, 214); // Alice blue

    public ToolStore(Player player, Game game) {
        this.player = player;
        this.game = game;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("🏪 Tool Store");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      
        moneyLabel = new JLabel("💰 Your Money: " + player.getMoney() + " coins", SwingConstants.CENTER);
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        moneyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel storeLabel = new JLabel("Welcome to the Tool Store!", SwingConstants.CENTER);
        storeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

 
        JButton weaponButton = createStyledButton("⚔️ Weapon Shop", "View available weapons");
        weaponButton.addActionListener(e -> openWeaponMenu());

        JButton armorButton = createStyledButton("🛡️ Armor Shop", "View available armors");
        armorButton.addActionListener(e -> openArmorMenu());

        buttonPanel.add(weaponButton);
        buttonPanel.add(armorButton);

        mainPanel.add(storeLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(moneyLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(buttonColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setToolTipText(tooltip);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void openWeaponMenu() {
        JDialog weaponDialog = new JDialog(this, "Weapon Shop", true);
        weaponDialog.setSize(400, 500);
        weaponDialog.setLocationRelativeTo(this);
        weaponDialog.setLayout(new BorderLayout());

        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));
        weaponPanel.setBackground(backgroundColor);
        weaponPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Weapons list
        String[][] weapons = {
            {"Pistol", "16", "10"},
            {"Sword", "35", "15"},
            {"Rifle", "55", "20"}
        };

        for (int i = 0; i < weapons.length; i++) {
            final int weaponId = i + 1;
            JPanel itemPanel = createItemPanel(
                weapons[i][0],
                Integer.parseInt(weapons[i][1]),
                Integer.parseInt(weapons[i][2]),
                "Damage",
                e -> purchaseWeapon(weaponId, weaponDialog)
            );
            weaponPanel.add(itemPanel);
            weaponPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JScrollPane scrollPane = new JScrollPane(weaponPanel);
        scrollPane.setBorder(null);
        weaponDialog.add(scrollPane);
        weaponDialog.setVisible(true);
    }

    private void openArmorMenu() {
        JDialog armorDialog = new JDialog(this, "Armor Shop", true);
        armorDialog.setSize(400, 500);
        armorDialog.setLocationRelativeTo(this);
        armorDialog.setLayout(new BorderLayout());

        JPanel armorPanel = new JPanel();
        armorPanel.setLayout(new BoxLayout(armorPanel, BoxLayout.Y_AXIS));
        armorPanel.setBackground(backgroundColor);
        armorPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Armors list
        String[][] armors = {
            {"Light Armor", "15", "1"},
            {"Medium Armor", "25", "3"},
            {"Heavy Armor", "40", "5"}
        };

        for (int i = 0; i < armors.length; i++) {
            final int armorId = i + 1;
            JPanel itemPanel = createItemPanel(
                armors[i][0],
                Integer.parseInt(armors[i][1]),
                Integer.parseInt(armors[i][2]),
                "Defense",
                e -> purchaseArmor(armorId, armorDialog)
            );
            armorPanel.add(itemPanel);
            armorPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JScrollPane scrollPane = new JScrollPane(armorPanel);
        scrollPane.setBorder(null);
        armorDialog.add(scrollPane);
        armorDialog.setVisible(true);
    }

    private JPanel createItemPanel(String name, int price, int stat, String statName, java.awt.event.ActionListener purchaseAction) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 0));
        panel.setBackground(Color.gray);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setMaximumSize(new Dimension(350, 80));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel statsLabel = new JLabel(String.format("<html>Price: %d coins<br>%s: %d</html>", price, statName, stat));
        
        JButton buyButton = createStyledButton("Buy", "Purchase " + name);
        buyButton.addActionListener(purchaseAction);

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(statsLabel, BorderLayout.CENTER);
        panel.add(buyButton, BorderLayout.EAST);

        return panel;
    }

    private void purchaseWeapon(int weaponId, JDialog dialog) {
        String weaponName;
        int price, damage;

        switch (weaponId) {
            case 1:
                weaponName = "Pistol";
                price = 16;
                damage = 10;
                break;
            case 2:
                weaponName = "Sword";
                price = 35;
                damage = 15;
                break;
            case 3:
                weaponName = "Rifle";
                price = 55;
                damage = 20;
                break;
            default:
                return;
        }

        if (player.getMoney() >= price) {
            int choice = JOptionPane.showConfirmDialog(
                dialog,
                String.format("Do you want to buy %s for %d coins?", weaponName, price),
                "Confirm Purchase",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                player.setMoney(player.getMoney() - price);
                player.getInventory().setWeaponName(weaponName);
                player.getInventory().setDamage(damage);
                updateMoneyLabel();
                game.updatePlayerInfo();
                
                JOptionPane.showMessageDialog(
                    dialog,
                    String.format(
                        "<html>Successfully purchased %s!<br>New damage: %d<br>Remaining money: %d coins</html>",
                        weaponName,
                        player.getTotalDamage(),
                        player.getMoney()
                    ),
                    "Purchase Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );
                dialog.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(
                dialog,
                "You don't have enough money!",
                "Insufficient Funds",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void purchaseArmor(int armorId, JDialog dialog) {
        String armorName;
        int price, defense;

        switch (armorId) {
            case 1:
                armorName = "Light Armor";
                price = 15;
                defense = 1;
                break;
            case 2:
                armorName = "Medium Armor";
                price = 25;
                defense = 3;
                break;
            case 3:
                armorName = "Heavy Armor";
                price = 40;
                defense = 5;
                break;
            default:
                return;
        }

        if (player.getMoney() >= price) {
            int choice = JOptionPane.showConfirmDialog(
                dialog,
                String.format("Do you want to buy %s for %d coins?", armorName, price),
                "Confirm Purchase",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                player.setMoney(player.getMoney() - price);
                player.getInventory().setArmorName(armorName);
                player.getInventory().setArmor(defense);
                updateMoneyLabel();
                game.updatePlayerInfo();
                
                JOptionPane.showMessageDialog(
                    dialog,
                    String.format(
                        "<html>Successfully purchased %s!<br>New defense: %d<br>Remaining money: %d coins</html>",
                        armorName,
                        player.getInventory().getArmor(),
                        player.getMoney()
                    ),
                    "Purchase Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );
                dialog.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(
                dialog,
                "You don't have enough money!",
                "Insufficient Funds",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void updateMoneyLabel() {
        moneyLabel.setText("💰 Your Money: " + player.getMoney() + " coins");
    }

}