import java.awt.*;
import javax.swing.*;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.PaintListener;

@ScriptManifest(
        name = "XPEHerblore",
        author = "Calle",
        version = 1.0,
        description = "All-in-one herblore script with modern UI",
        category = Category.HERBLORE)
public class BotExecute extends AbstractScript implements PaintListener {
    private HerbloreAIO herbloreGUI;

    @Override
    public void onStart() {
        // Just create and show the GUI
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                log("Failed to set look and feel");
            }

            herbloreGUI = new HerbloreAIO();
            try {
                java.lang.reflect.Method method = HerbloreAIO.class.getDeclaredMethod("initGUI");
                method.setAccessible(true);
                method.invoke(herbloreGUI);
            } catch (Exception e) {
                log("Error creating GUI: " + e.getMessage());
            }
        });
    }

    @Override
    public int onLoop() {
        return 1000;
    }

    @Override
    public void onExit() {
    }

    @Override
    public void onPaint(Graphics g) {
    }
}