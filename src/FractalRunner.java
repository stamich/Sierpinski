import javax.swing.*;

/**
 * Created by michal on 24.05.17.
 */
public class FractalRunner
{
    public static void main(String[] args)
    {
        FractalPainter frame = new FractalPainter();

        frame.setSize(800, 800);
        frame.setTitle("Sierpinski's Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
