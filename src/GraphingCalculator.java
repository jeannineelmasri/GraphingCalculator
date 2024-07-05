import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Jeannine Elmasri
 */
public class GraphingCalculator {

    private JFrame frame;
    private JTextField slope;
    private JTextField offsetText;

    /**
     * Launches the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GraphingCalculator window = new GraphingCalculator();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Creates the application.
     */
    public GraphingCalculator() {
        initialize();
    }

    /**
     * Initializes the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 435, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g);
            }
        };
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 136, 400, 400);
        frame.getContentPane().add(panel);

        JLabel slopeText = new JLabel("Scalar (m)");
        slopeText.setBounds(10, 21, 62, 14);
        frame.getContentPane().add(slopeText);

        slope = new JTextField();
        slope.setBounds(67, 18, 90, 25);
        frame.getContentPane().add(slope);
        slope.setColumns(10);

        JLabel offset = new JLabel("Offset (b)");
        offset.setBounds(226, 15, 90, 25);
        frame.getContentPane().add(offset);

        offsetText = new JTextField();
        offsetText.setColumns(10);
        offsetText.setBounds(281, 18, 90, 25);
        frame.getContentPane().add(offsetText);

        JLabel statusUpdate = new JLabel("Status:");
        statusUpdate.setBounds(20, 540, 385, 14);
        frame.getContentPane().add(statusUpdate);

        JRadioButton buttonX = new JRadioButton("X");
        buttonX.setBounds(10, 54, 100, 25);
        frame.getContentPane().add(buttonX);

        JRadioButton buttonX2 = new JRadioButton("X^2");
        buttonX2.setBounds(10, 80, 100, 25);
        frame.getContentPane().add(buttonX2);

        JRadioButton buttonLog = new JRadioButton("Log(x)");
        buttonLog.setBounds(140, 80, 100, 25);
        frame.getContentPane().add(buttonLog);

        JRadioButton buttonX3 = new JRadioButton("X^3");
        buttonX3.setBounds(10, 110, 100, 25);
        frame.getContentPane().add(buttonX3);

        JRadioButton rdbtnSqrt = new JRadioButton("Sqrt(x)");
        rdbtnSqrt.setBounds(139, 106, 100, 25);
        frame.getContentPane().add(rdbtnSqrt);


        JButton graphButton = new JButton("Graph");
        graphButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initial(panel.getGraphics());
                statusUpdate.setText("Status: ");
                if(isNumeric(slope.getText())){
                    if(isNumeric(offsetText.getText())) {
                        if(buttonX.isSelected()) {
                            drawX(panel.getGraphics());
                        }else if(buttonX2.isSelected()) {
                            drawX2(panel.getGraphics());
                        }else if (buttonX3.isSelected()) {
                            drawX3(panel.getGraphics());
                        }else if(buttonLog.isSelected()) {
                            drawLogX(panel.getGraphics());
                        }else if(rdbtnSqrt.isSelected()) {
                            drawSqrtX(panel.getGraphics());
                        }else {
                            statusUpdate.setText("Status: Need to choose a graph");
                        }
                    }else {
                        statusUpdate.setText("Status: The text you inputed is not a double or integer.");
                    }
                }else {
                    statusUpdate.setText("Status: The scalar/slope text you inputed is not a double or integer. ");
                }
            }
        });
        graphButton.setBounds(250, 70, 100, 40);
        frame.getContentPane().add(graphButton);
    }


    public void initial(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 400, 400);
        drawGrid(g);
    }

    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("x", 20, 190);
        g.drawString("y", 180, 20);
        g.drawString("1.0", 210, 105);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(195, 300, 205, 300);
        g.drawLine(195, 100, 205, 100);
        g.drawLine(100, 195, 100, 205);
        g.drawLine(300, 195, 300, 205);
    }


    /**
     * @param g
     */
    public void drawX(Graphics g) {
        double dx = 0.001;
        double m = Double.parseDouble(slope.getText());
        double b = Double.parseDouble(offsetText.getText());
        for (double x=-2; x<=2; x+=dx) {
            double y = (m*x) + b;
            int cX = (int)(100.0*x)+200;
            int cY = (int)(200-(100.0*y));
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(cX,cY,cX, cY);
            g.setColor(Color.RED);
            g.drawLine(cX, cY, cX, cY);;
        }
    }



    public void drawX2(Graphics g) {
        double dx = 0.00001;
        double m = Double.parseDouble(slope.getText());
        double b = Double.parseDouble(offsetText.getText());
        for (double x=-2; x<=2; x+=dx) {

            double y = (m*(Math.pow(x,2))) + b;
            int cX = (int)(100.0*x)+200;
            int cY = (int)(200-(100.0*y));
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(cX,cY,cX, cY);
            g.setColor(Color.RED);
            g.drawLine(cX, cY, cX, cY);
        }
    }


    public void drawX3(Graphics g) {
        double dx = 0.00001;
        double m = Double.parseDouble(slope.getText());
        double b = Double.parseDouble(offsetText.getText());
        for (double x=-2.0; x<=2.0; x+=dx) {
            double y = (m*Math.pow(x, 3.0)) + b;
            int cX = (int)(100.0*x)+200;
            int cY = (int)(200-(100.0*y));
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(cX,cY,cX, cY);
            g.setColor(Color.RED);
            g.drawLine(cX, cY, cX, cY);
        }
    }


    public void drawLogX(Graphics g) {
        double dx = 0.00001;
        double m = Double.parseDouble(slope.getText());
        double b = Double.parseDouble(offsetText.getText());
        for (double x=0; x<=2; x+=dx) {
            double y = (m*Math.log(x)) + b;
            int cX = (int)(100.0*x)+200;
            int cY = (int)(200-(100.0*y));
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));
            g2.drawLine(cX,cY,cX, cY);
            g.setColor(Color.RED);
            g.drawLine(cX, cY, cX, cY);
        }
    }


    public void drawSqrtX(Graphics g) {
        double dx = 0.00001;
        double m = Double.parseDouble(slope.getText());
        double b = Double.parseDouble(offsetText.getText());
        for (double x=0; x<=2; x+=dx) {
            double y = (m*Math.sqrt(x))+b;
            int cX = (int)(100.0*x)+200;
            int cY = (int)(200-(100.0*y));
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(cX,cY,cX, cY);
            g.setColor(Color.RED);
            g.drawLine(cX, cY, cX, cY);
        }
    }
}

