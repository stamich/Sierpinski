import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

/**
 * Created by michal on 24.05.17.
 */
public class FractalPainter extends JFrame
{
    final private int ITERATIONS = 1;
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        TriangleFractal drawing = new TriangleFractal(getWidth() - 100, ITERATIONS);
        drawing.moveTo(getWidth() - 50, getHeight() - 50);
        drawing.setAngle(180);
        drawing.lineInDirection();
        drawing.turn(true);
        drawing.lineInDirection();
        drawing.turn(true);

        drawing.drawFractal();

        g2.draw(drawing);
    }
}
