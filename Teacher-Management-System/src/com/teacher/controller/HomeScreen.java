package com.teacher.controller;

import com.teacher.view.AddTeacherScreen;
import com.teacher.view.DeleteTeacherScreen;
import com.teacher.view.DisplayTeacherScreen;
import com.teacher.view.UpdateTeacherScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent; // Needed for Ctrl/Alt masks
import java.awt.event.KeyEvent;   // Needed for VK codes

public class HomeScreen extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu menuTeacher;
    // Declaring JMenuItems for Add, Update, Delete, Display, and Exit
    private JMenuItem menuItemAddTeacher;
    private JMenuItem menuItemUpdateTeacher;
    private JMenuItem menuItemDeleteTeacher;
    private JMenuItem menuItemDisplayTeacher;
    private JMenuItem menuItemExit;

    private JLabel welcomeLabel;
    private JPanel contentPanel; // Panel to hold main content

    // Removed the individual JButton fields as the menu items will handle the primary actions now

    public HomeScreen() {
        setTitle("Teacher Management System - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700); // Larger size for more content
        setLocationRelativeTo(null); // Center the window

        // Set a modern Look and Feel (e.g., Nimbus)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Could not set Nimbus Look and Feel. Using default.");
        }

        // Use BorderLayout for the main frame structure
        setLayout(new BorderLayout());

        // --- Menu Bar ---
        menuBar = new JMenuBar();
        setJMenuBar(menuBar); // Set as the frame's menu bar

        menuTeacher = new JMenu("Teacher");
        menuTeacher.setMnemonic('T'); // Alt + T to open the menu
        menuBar.add(menuTeacher);

        // Menu Items for Add, Update, Delete, Display Teacher
        // Using a helper method to create items without icons but with accelerators
        menuItemAddTeacher = createMenuItem("Add Teacher", KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK);
        menuTeacher.add(menuItemAddTeacher);

        menuItemUpdateTeacher = createMenuItem("Update Teacher", KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK);
        menuTeacher.add(menuItemUpdateTeacher);

        menuItemDeleteTeacher = createMenuItem("Delete Teacher", KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK);
        menuTeacher.add(menuItemDeleteTeacher);

        menuItemDisplayTeacher = createMenuItem("Display Teacher", KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK); // Changed to P for 'Show' or 'Present' to avoid conflict if 'D' is needed elsewhere
        menuTeacher.add(menuItemDisplayTeacher);

        menuTeacher.addSeparator(); // A visual separator in the menu

        // Exit Menu Item
        menuItemExit = createMenuItem("Exit", KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK); // Alt + X
        menuTeacher.add(menuItemExit);

        // --- Main Content Panel (CENTER of BorderLayout) ---
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout()); // Use BorderLayout for the content panel
        contentPanel.setBackground(new Color(240, 248, 255)); // AliceBlue for a soft background
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        welcomeLabel = new JLabel("Welcome to the Teacher Management System!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 32)); // Modern font
        welcomeLabel.setForeground(new Color(0, 102, 153)); // Darker blue color
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); // Padding around text
        contentPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Placeholder for the main content area (no images)
        JLabel centerContentPlaceholder = new JLabel("Use the 'Teacher' menu to perform actions.", JLabel.CENTER);
        centerContentPlaceholder.setFont(new Font("Segoe UI", Font.ITALIC, 20));
        centerContentPlaceholder.setForeground(Color.GRAY);
        contentPanel.add(centerContentPlaceholder, BorderLayout.CENTER);


        // --- Removed the "Quick Actions" dashboard panel as primary actions are in the menu ---
        // If you still want secondary buttons, you can re-add this panel
        // and its buttons here. For now, it's removed as requested implicitly.


        add(contentPanel, BorderLayout.CENTER); // Add the content panel to the frame's center

        // Optional: A status bar at the bottom
        JLabel statusBar = new JLabel("  Ready. Teacher Management System v1.0", JLabel.LEFT);
        statusBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        statusBar.setFont(new Font("Arial", Font.PLAIN, 12));
        statusBar.setForeground(Color.DARK_GRAY);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Helper method to create a JMenuItem without an icon, but with an ActionListener, mnemonic, and accelerator.
     * @param text The display text for the menu item.
     * @param mnemonic The KeyEvent.VK_ code for the mnemonic (e.g., KeyEvent.VK_A).
     * @param acceleratorMask The InputEvent.CTRL_DOWN_MASK, InputEvent.ALT_DOWN_MASK, etc.
     * @return The configured JMenuItem.
     */
    private JMenuItem createMenuItem(String text, int mnemonic, int acceleratorMask) {
        JMenuItem item = new JMenuItem(text);
        item.addActionListener(this); // HomeScreen implements ActionListener
        item.setMnemonic(mnemonic); // Mnemonic (e.g., Alt+T then A for Add)
        item.setAccelerator(KeyStroke.getKeyStroke(mnemonic, acceleratorMask)); // Accelerator (e.g., Ctrl+A)
        return item;
    }

    // The createDashboardButton helper method is removed as there are no dashboard buttons in this version.
    // private JButton createDashboardButton(String text) { /* ... */ }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Use getActionCommand() to identify which menu item was clicked
        String command = e.getActionCommand();

        if (command.equals("Add Teacher")) {
            JOptionPane.showMessageDialog(this, "You selected Add Teacher from the menu!");
            new AddTeacherScreen(); // Uncomment and replace with your actual screen
        } else if (command.equals("Update Teacher")) {
            JOptionPane.showMessageDialog(this, "You selected Update Teacher from the menu!");
            new UpdateTeacherScreen(); // Uncomment and replace with your actual screen
        } else if (command.equals("Delete Teacher")) {
            JOptionPane.showMessageDialog(this, "You selected Delete Teacher from the menu!");
            new DeleteTeacherScreen(); // Uncomment and replace with your actual screen
        } else if (command.equals("Display Teacher")) {
            JOptionPane.showMessageDialog(this, "You selected Display Teacher from the menu!");
            new DisplayTeacherScreen(); // Uncomment and replace with your actual screen
        } else if (command.equals("Exit")) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Application", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeScreen::new);
    }
}