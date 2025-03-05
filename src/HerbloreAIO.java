import gui.components.CustomButton;
import gui.components.CustomScrollBarUI;
import gui.components.StyleFactory;
import gui.components.ToggleSwitch;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HerbloreAIO {
  private JFrame guiFrame;
  private boolean isScriptRunning = false;
  private boolean isDynamicMouseEnabled = false;

  private static JComboBox<String> getStringJComboBox() {
    String[] potionOptions = {
      "Attack potion",
      "Strength potion",
      "Defense potion",
      "Ranging potion",
      "Magic potion",
      "Prayer potion",
      "Super attack potion",
      "Super strength potion",
      "Super defense potion",
      "Super restore potion",
      "Super energy potion",
      "Saradomin brew",
      "Antifire potion",
      "Super antifire potion",
      "Extended antifire potion",
      "Extended super antifire potion"
    };
    JComboBox<String> potionDropdown = new JComboBox<>(potionOptions);
    return potionDropdown;
  }

  private void initGUI() {
    SwingUtilities.invokeLater(
        () -> {
          guiFrame =
              new JFrame("AIO Herblore Bot") {
                @Override
                public Insets getInsets() {
                  return new Insets(2, 2, 2, 2);
                }
              };
          guiFrame.setSize(340, 220);
          guiFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
          guiFrame.addWindowListener(
              new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                  isScriptRunning = false;
                  stop();
                  guiFrame.dispose();
                }
              });

          // Set up the content pane
          Container contentPane = guiFrame.getContentPane();
          contentPane.setBackground(StyleFactory.BG_DARK_COLOR);
          contentPane.setLayout(new BorderLayout());

          // Create main panel
          JPanel mainPanel = new JPanel();
          mainPanel.setBackground(StyleFactory.BG_DARK_COLOR);
          mainPanel.setLayout(null); // Keep null layout for exact positioning

          // Header
          JLabel titleLabel = new JLabel("AIO Herblore Bot", JLabel.CENTER);
          titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
          titleLabel.setForeground(StyleFactory.TEXT_COLOR);
          titleLabel.setBounds(10, 5, 320, 25);
          mainPanel.add(titleLabel);

          // Potion Dropdown
          JLabel potionLabel = new JLabel("Select Potion Type:");
          potionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
          potionLabel.setForeground(StyleFactory.TEXT_COLOR);
          potionLabel.setBounds(10, 40, 120, 20);
          mainPanel.add(potionLabel);

          JComboBox<String> potionDropdown = getStringJComboBox();
          styleComboBox(potionDropdown);
          potionDropdown.setBounds(130, 40, 190, 24);
          mainPanel.add(potionDropdown);

          // Dynamic Mouse Toggle
          JLabel mouseLabel = new JLabel("Dynamic Mouse:");
          mouseLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
          mouseLabel.setForeground(StyleFactory.TEXT_COLOR);
          mouseLabel.setBounds(10, 75, 120, 20);
          mainPanel.add(mouseLabel);

          ToggleSwitch dynamicMouseToggle = new ToggleSwitch();
          dynamicMouseToggle.setPreferredSize(new Dimension(50, 24));
          dynamicMouseToggle.setBounds(130, 75, 50, 24);
          dynamicMouseToggle.addChangeListener(
              e -> isDynamicMouseEnabled = dynamicMouseToggle.isSelected());
          mainPanel.add(dynamicMouseToggle);

          // Start Button
          CustomButton startButton =
              new CustomButton("Start", CustomButton.ButtonStyle.PILL)
                  .setColors(
                      StyleFactory.ACCENT_COLOR,
                      StyleFactory.ACCENT_HOVER_COLOR,
                      StyleFactory.ACCENT_PRESSED_COLOR)
                  .setTextColor(Color.WHITE);
          startButton.setBounds(20, 115, 70, 30);
          startButton.addActionListener(
              e -> {
                String selectedPotion = (String) potionDropdown.getSelectedItem();
                log(selectedPotion + " crafting selected!");
                isDynamicMouseEnabled = dynamicMouseToggle.isSelected();
                log("Dynamic Mouse Enabled: " + isDynamicMouseEnabled);
                log("Crafting started!");
                isScriptRunning = true;

                if (isDynamicMouseEnabled) {
                  startMouseSpeedThread();
                }
              });
          mainPanel.add(startButton);

          // Discord Button
          CustomButton discordButton =
              new CustomButton("Discord", CustomButton.ButtonStyle.FLAT)
                  .setColors(
                      new Color(114, 137, 218), // Discord color
                      new Color(134, 157, 238), // Lighter discord
                      new Color(94, 117, 198) // Darker discord
                      )
                  .setTextColor(Color.WHITE);
          discordButton.setBounds(100, 115, 70, 30);
          discordButton.addActionListener(
              e -> {
                try {
                  Desktop.getDesktop().browse(new java.net.URI("https://discord.gg/KcKRsG8p2s"));
                } catch (Exception ex) {
                  log("Failed to open Discord link: " + ex.getMessage());
                }
              });
          mainPanel.add(discordButton);

          // Report Bug Button
          CustomButton reportBugButton =
              new CustomButton("Report Bug", CustomButton.ButtonStyle.FLAT)
                  .setColors(
                      StyleFactory.BG_MEDIUM_COLOR,
                      StyleFactory.BG_LIGHT_COLOR,
                      StyleFactory.BG_DARK_COLOR)
                  .setTextColor(StyleFactory.TEXT_COLOR);
          reportBugButton.setBounds(180, 115, 75, 30);
          reportBugButton.addActionListener(
              e -> {
                JFrame bugReportFrame = new JFrame("Bug Report");
                bugReportFrame.setSize(400, 300);
                bugReportFrame.setLayout(new BorderLayout());

                // Create styled content panel
                JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
                contentPanel.setBackground(StyleFactory.BG_DARK_COLOR);
                contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

                JLabel titleReportLabel = new JLabel("Describe the issue:");
                titleReportLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
                titleReportLabel.setForeground(StyleFactory.TEXT_COLOR);
                contentPanel.add(titleReportLabel, BorderLayout.NORTH);

                JTextArea bugTextArea = new JTextArea();
                bugTextArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
                bugTextArea.setLineWrap(true);
                bugTextArea.setWrapStyleWord(true);
                bugTextArea.setBackground(StyleFactory.BG_MEDIUM_COLOR);
                bugTextArea.setForeground(StyleFactory.TEXT_COLOR);
                bugTextArea.setBorder(new EmptyBorder(8, 8, 8, 8));

                JScrollPane scrollPane = new JScrollPane(bugTextArea);
                scrollPane.setBorder(BorderFactory.createEmptyBorder());
                scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
                contentPanel.add(scrollPane, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.setBackground(StyleFactory.BG_DARK_COLOR);

                CustomButton sendButton =
                    new CustomButton("Send", CustomButton.ButtonStyle.PILL)
                        .setColors(
                            StyleFactory.ACCENT_COLOR,
                            StyleFactory.ACCENT_HOVER_COLOR,
                            StyleFactory.ACCENT_PRESSED_COLOR)
                        .setTextColor(Color.WHITE);

                sendButton.addActionListener(
                    ev -> {
                      String bugReportContent = bugTextArea.getText();
                      if (!bugReportContent.isEmpty()) {
                        try {
                          HttpURLConnection connection = getHttpURLConnection();

                          String payload =
                              "{\"content\": \"" + bugReportContent.replace("\"", "\\\"") + "\"}";
                          try (java.io.OutputStream os = connection.getOutputStream()) {
                            os.write(payload.getBytes());
                            os.flush();
                          }

                          if (connection.getResponseCode() >= 200
                              && connection.getResponseCode() <= 299) {
                            JOptionPane.showMessageDialog(
                                bugReportFrame, "Bug report sent successfully!");
                            bugReportFrame.dispose();
                          } else {
                            JOptionPane.showMessageDialog(
                                bugReportFrame,
                                "Failed to send the bug report. HTTP Response Code: "
                                    + connection.getResponseCode());
                          }
                        } catch (Exception ex) {
                          JOptionPane.showMessageDialog(
                              bugReportFrame, "An error occurred: " + ex.getMessage());
                        }
                      } else {
                        JOptionPane.showMessageDialog(
                            bugReportFrame, "Bug report cannot be empty.");
                      }
                    });

                buttonPanel.add(sendButton);
                contentPanel.add(buttonPanel, BorderLayout.SOUTH);

                bugReportFrame.setContentPane(contentPanel);
                bugReportFrame.setLocationRelativeTo(guiFrame);
                bugReportFrame.setVisible(true);
              });
          mainPanel.add(reportBugButton);

          // Stop Button
          CustomButton stopButton =
              new CustomButton("Stop", CustomButton.ButtonStyle.PILL)
                  .setColors(new Color(160, 30, 30), new Color(180, 40, 40), new Color(140, 20, 20))
                  .setTextColor(Color.WHITE);
          stopButton.setBounds(265, 115, 60, 30);
          stopButton.addActionListener(
              e -> {
                log("Crafting stopped!");
                isScriptRunning = false;
                stop();
              });
          mainPanel.add(stopButton);

          // Status line
          JPanel statusPanel = new JPanel(new BorderLayout());
          statusPanel.setBackground(StyleFactory.BG_MEDIUM_COLOR);
          statusPanel.setBorder(new EmptyBorder(5, 6, 3, 6));

          JLabel statusLabel = new JLabel("Ready to start");
          statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
          statusLabel.setForeground(StyleFactory.TEXT_COLOR);
          statusPanel.add(statusLabel, BorderLayout.WEST);

          contentPane.add(mainPanel, BorderLayout.CENTER);
          contentPane.add(statusPanel, BorderLayout.SOUTH);

          guiFrame.setLocationRelativeTo(null);
          guiFrame.setVisible(true);
        });
  }

  /** Style a combo box */
  private void styleComboBox(JComboBox<String> comboBox) {
    comboBox.setBackground(StyleFactory.BG_MEDIUM_COLOR);
    comboBox.setForeground(StyleFactory.TEXT_COLOR);
    comboBox.setFont(new Font("SansSerif", Font.PLAIN, 12));

    comboBox.setRenderer(
        new DefaultListCellRenderer() {
          @Override
          public Component getListCellRendererComponent(
              JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (isSelected) {
              setBackground(StyleFactory.ACCENT_COLOR);
              setForeground(Color.WHITE);
            } else {
              setBackground(StyleFactory.BG_MEDIUM_COLOR);
              setForeground(StyleFactory.TEXT_COLOR);
            }

            setBorder(new EmptyBorder(3, 5, 3, 5));
            return this;
          }
        });
  }

  public static HttpURLConnection getHttpURLConnection() throws IOException {
    String webhookURL =
        "https://discord.com/api/webhooks/1318655450262011975/nrgLGz2Rc9ddh_YrEMce7_DGowd-FIC6-VsLjXaEkG73h9iwWHMo8A4Bg69Lf_xg8eyy";
    java.net.URL url = new java.net.URL(webhookURL);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setDoOutput(true);
    connection.setRequestProperty(
        "User-Agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
    return connection;
  }

  private void startMouseSpeedThread() {
    log("Starting dynamic mouse speed thread");
  }

  private void log(String message) {
    System.out.println("[HerbloreBot] " + message);
  }

  private void stop() {
    log("Bot stopped");
  }
}
