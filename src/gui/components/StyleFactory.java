package gui.components;

import java.awt.Color;
import java.awt.Font;

/** Factory class for consistent styling across the application */
public class StyleFactory {
  // Color scheme - Dark red theme
  public static final Color ACCENT_COLOR = new Color(220, 40, 40); // Bright red accent
  public static final Color ACCENT_HOVER_COLOR =
      new Color(240, 60, 60); // Lighter bright red for hover
  public static final Color ACCENT_PRESSED_COLOR =
      new Color(180, 20, 20); // Darker bright red for pressed

  public static final Color BG_DARK_COLOR = new Color(35, 15, 15); // Dark red background
  public static final Color BG_MEDIUM_COLOR = new Color(45, 20, 20); // Medium red background
  public static final Color BG_LIGHT_COLOR = new Color(55, 25, 25); // Light red background

  public static final Color TEXT_COLOR = new Color(230, 230, 230); // Light text
  public static final Color TEXT_SECONDARY_COLOR = new Color(180, 180, 180); // Secondary text
  public static final Color HEADER_COLOR = new Color(255, 70, 70); // Bright red header text

  public static final Color BORDER_COLOR = new Color(30, 65, 50); // Border color
  public static final Color DISABLED_COLOR = new Color(100, 100, 100); // Disabled state color

  // Common fonts
  public static final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 20);
  public static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 14);
  public static final Font LABEL_FONT = new Font("SansSerif", Font.PLAIN, 14);
  public static final Font SMALL_FONT = new Font("SansSerif", Font.PLAIN, 12);

  // Spacing constants
  public static final int PADDING_SMALL = 5;
  public static final int PADDING_MEDIUM = 10;
  public static final int PADDING_LARGE = 15;
}
