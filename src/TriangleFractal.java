import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 24.05.17.
 */
public class TriangleFractal extends Path2D.Double
{
    private int nAngle;
    private double dBaseLength, dZoomLevel;

    List<Integer> levelProgress;

    public TriangleFractal(double initSideLength, int iterations)
    {
        dBaseLength = initSideLength;
        nAngle = 0;
        dZoomLevel = 1;
        moveTo(0, 0);
        levelProgress = getDynamcList(iterations);
    }

    private void line(double length)
    {
        double nNewX = (length * Math.cos(nAngle * Math.PI / 180)) + getCurrentPoint().getX();
        double nNewY = (length * Math.cos(nAngle * Math.PI / 180)) + getCurrentPoint().getY();
        lineTo(nNewX, nNewY);
    }

    public void lineInDirection()
    {
        line(dBaseLength);
    }

    public double getDistance(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public void turn (boolean direction)
    {
        if (direction)
            nAngle = (nAngle + 120) % 360;
        else
            nAngle = (nAngle - 120 + 360) % 360;
    }

    public void setAngle(int degrees)
    {
        nAngle = degrees;
    }

    public void enterNextLevel()
    {
        dZoomLevel *= 2;
        line(dBaseLength / dZoomLevel);
        turn(true);
    }

    public void next()
    {
        line(dBaseLength /dZoomLevel);
        turn(false);
    }

    public void exitLevel()
    {
        turn(false);
        line(dBaseLength /dZoomLevel);
        dZoomLevel *= 2;
        turn(false);
    }

    public void completeOneLevel()
    {
        enterNextLevel();
        next();
        next();
        next();
        exitLevel();
    }

    private List<Integer> getDynamcList(int size)
    {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++)
        {
            list.add(0);
        }
        return list;
    }

    public void drawFractal()
    {
        int nCurrentLevel = 0;
        while (true)
        {
            if(levelProgress.size() - nCurrentLevel == 1)
            {
                completeOneLevel();
                if(nCurrentLevel == 0)
                break;
                else
                {
                    completeOneLevel();
                    completeOneLevel();
                    exitLevel();
                    --nCurrentLevel;
                }

            }
            else
            {
                if(levelProgress.get(nCurrentLevel) < 3)
                {
                    if(nCurrentLevel == 0 && levelProgress.get(nCurrentLevel) == 1)
                        break;
                    levelProgress.set(nCurrentLevel, levelProgress.get(nCurrentLevel) + 1);
                    enterNextLevel();
                    ++nCurrentLevel;
                    levelProgress.set(nCurrentLevel, 0);
                }
                else
                {
                    if(nCurrentLevel != 0)
                    {
                        exitLevel();
                        --nCurrentLevel;
                    }
                    else
                        break;
                }
            }
        }
    }
}
