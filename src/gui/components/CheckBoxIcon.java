package gui.components;

import java.awt.*;
import javax.swing.*;

/**
 * Custom checkbox icon for nicer appearance * *
 *
 * <p>Author Calle
 */
public class CheckBoxIcon implements Icon {
  private final boolean selected;
  private final int SIZE = 16;

  public CheckBoxIcon(boolean selected) {
    this.selected = selected;
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(StyleFactory.BG_MEDIUM_COLOR);
    g2d.fillRect(x, y, SIZE, SIZE);
    g2d.setColor(selected ? StyleFactory.ACCENT_COLOR : StyleFactory.BORDER_COLOR);
    g2d.drawRect(x, y, SIZE - 1, SIZE - 1);
    if (selected) {
      g2d.setColor(Color.WHITE);
      g2d.setStroke(new BasicStroke(2));
      g2d.drawLine(x + 3, y + SIZE / 2, x + SIZE / 2, y + SIZE - 4);
      g2d.drawLine(x + SIZE / 2, y + SIZE - 4, x + SIZE - 3, y + 3);
    }
    g2d.dispose();
  }

  @Override
  public int getIconWidth() {
    return SIZE;
  }

  @Override
  public int getIconHeight() {
    return SIZE;
  }
}
