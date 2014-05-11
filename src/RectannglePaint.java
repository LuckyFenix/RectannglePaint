import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class RectannglePaint extends JFrame
{
    private PaintPanel panel;
    private Point startPoint;
    private Point finishPoint;

    public RectannglePaint()
    {
        panel = new PaintPanel();
        add(panel);
    }

    public class PaintPanel extends JPanel implements MouseMotionListener, MouseListener
    {
        private boolean paint;
        private ArrayList<Color> colorArray;
        private ArrayList<RoundRectangle2D> rectArray;
        private Color drawColor;
        private Random r = new Random();

        public PaintPanel()
        {
            super();
            rectArray = new ArrayList<RoundRectangle2D>();
            colorArray = new ArrayList<Color>();
            addMouseListener(this);
            addMouseMotionListener(this);
            setDoubleBuffered(true);
        }

        protected void paintComponent(Graphics g)
        {
            super.paintComponents(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(drawColor);
            if (paint)
            {
                RoundRectangle2D drawRect = null;
                if (startPoint.getX() < finishPoint.getX() &&
                        startPoint.getY() < finishPoint.getY())
                    drawRect = new RoundRectangle2D.Double(startPoint.getX(),
                            startPoint.getY(),
                            Math.abs(finishPoint.getX() - startPoint.getX()),
                            Math.abs(finishPoint.getY() - startPoint.getY()),
                            20, 20);
                if (startPoint.getX() > finishPoint.getX() &&
                        startPoint.getY() < finishPoint.getY())
                    drawRect = new RoundRectangle2D.Double(finishPoint.getX(),
                            startPoint.getY(),
                            Math.abs(finishPoint.getX() - startPoint.getX()),
                            Math.abs(finishPoint.getY() - startPoint.getY()),
                            20, 20);
                if (startPoint.getX() < finishPoint.getX() &&
                        startPoint.getY() > finishPoint.getY())
                    drawRect = new RoundRectangle2D.Double(startPoint.getX(),
                            finishPoint.getY(),
                            Math.abs(finishPoint.getX() - startPoint.getX()),
                            Math.abs(finishPoint.getY() - startPoint.getY()),
                            20, 20);
                if (startPoint.getX() > finishPoint.getX() &&
                        startPoint.getY() > finishPoint.getY())
                    drawRect = new RoundRectangle2D.Double(finishPoint.getX(),
                            finishPoint.getY(),
                            Math.abs(finishPoint.getX() - startPoint.getX()),
                            Math.abs(finishPoint.getY() - startPoint.getY()),
                            20, 20);
                if (drawRect != null)
                    g2.draw(drawRect);
            }

            for(int i = 0; i < rectArray.size(); i++)
            {
                g2.setColor(colorArray.get(i));
                g2.draw(rectArray.get(i));
            }
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            finishPoint = e.getPoint();
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            drawColor = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            startPoint = e.getPoint();
            paint = true;
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (startPoint.getX() < finishPoint.getX() &&
                    startPoint.getY() < finishPoint.getY())
                rectArray.add(new RoundRectangle2D.Double((int) startPoint.getX(),
                        (int) startPoint.getY(),
                        (int) Math.abs(finishPoint.getX() - startPoint.getX()),
                        (int) Math.abs(finishPoint.getY() - startPoint.getY()),
                        20, 20));
            if (startPoint.getX() > finishPoint.getX() &&
                    startPoint.getY() < finishPoint.getY())
                rectArray.add(new RoundRectangle2D.Double((int) finishPoint.getX(),
                        (int) startPoint.getY(),
                        (int) Math.abs(finishPoint.getX() - startPoint.getX()),
                        (int) Math.abs(finishPoint.getY() - startPoint.getY()),
                        20, 20));
            if (startPoint.getX() < finishPoint.getX() &&
                    startPoint.getY() > finishPoint.getY())
                rectArray.add(new RoundRectangle2D.Double((int) startPoint.getX(),
                        (int) finishPoint.getY(),
                        (int) Math.abs(finishPoint.getX() - startPoint.getX()),
                        (int) Math.abs(finishPoint.getY() - startPoint.getY()),
                        20, 20));
            if (startPoint.getX() > finishPoint.getX() &&
                    startPoint.getY() > finishPoint.getY())
                rectArray.add(new RoundRectangle2D.Double((int) finishPoint.getX(),
                        (int) finishPoint.getY(),
                        (int) Math.abs(finishPoint.getX() - startPoint.getX()),
                        (int) Math.abs(finishPoint.getY() - startPoint.getY()),
                        20, 20));
            paint = false;
            colorArray.add(drawColor);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public static void main(String[] args)
    {
        RectannglePaint rectannglePaint = new RectannglePaint();
        rectannglePaint.setSize(800, 800);
        rectannglePaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rectannglePaint.setLocationRelativeTo(null);
        rectannglePaint.setVisible(true);
    }
}
