package Lab1.var2.swing;

import Lab1.colors.CMYK;
import Lab1.colors.HLS;
import Lab1.colors.RGB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {

    private JPanel rgbPanel = new JPanel();
    private JPanel cmykPanel = new JPanel();
    private JPanel hlsPanel = new JPanel();
    private JPanel colorPanel = new JPanel();

    private final JLabel rgbLabel = new JLabel("RGB");
    private JTextArea rField = new JTextArea("0", 1, 1);
    private JTextArea gField = new JTextArea("0", 1, 1);
    private JTextArea bField = new JTextArea( "0", 1, 1);
    private JSlider rSlider = new JSlider(0, 255, 0);
    private JSlider gSlider = new JSlider(0, 255, 0);
    private JSlider bSlider = new JSlider(0, 255, 0);
    private final JButton setRGBButton = new JButton("Set RGB");

    private final JLabel cmykLabel = new JLabel("CMYK");
    private JTextArea cField = new JTextArea("0", 1, 1);
    private JTextArea mField = new JTextArea("0", 1, 1);
    private JTextArea yField = new JTextArea("0", 1, 1);
    private JTextArea kField = new JTextArea("1.0", 1, 1);
    private JSlider cSlider = new JSlider(0, 100, 0);
    private JSlider mSlider = new JSlider(0, 100, 0);
    private JSlider ySlider = new JSlider(0, 100, 0);
    private JSlider kSlider = new JSlider(0, 100, 0);
    private final JButton setCMYKButton = new JButton("Set CMYK");

    private final JLabel hlsLabel = new JLabel("HLS");
    private JTextArea hField = new JTextArea("0", 1, 1);
    private JTextArea lField = new JTextArea("0", 1, 1);
    private JTextArea sField = new JTextArea("0", 1, 1);
    private JSlider hSlider = new JSlider(0, 360, 0);
    private JSlider lSlider = new JSlider(0, 100, 0);
    private JSlider sSlider = new JSlider(0, 100, 0);
    private final JButton setHLSButton = new JButton("Set HSL");


    int rCount = 0;
    int cCount = 0;
    int hCount = 0;

    public Window() {
        setTitle("Color Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(5, 1));

        rgbPanel.add(rgbLabel);
        rgbPanel.add(rField);
        rField.setBounds(40, 20, 50, 25);
        rgbPanel.add(gField);
        gField.setBounds(40, 20, 50, 25);
        rgbPanel.add(bField);
        bField.setBounds(40, 20, 50, 25);
        rgbPanel.add(rSlider);
        rSlider.addChangeListener(e -> {
            rField.setText(String.valueOf(rSlider.getValue()));
            updateFromRgb(++rCount);
            --rCount;
        });
        rgbPanel.add(gSlider);
        gSlider.addChangeListener(e -> {
            gField.setText(String.valueOf(gSlider.getValue()));
            updateFromRgb(++rCount);
            --rCount;
        });
        rgbPanel.add(bSlider);
        bSlider.addChangeListener(e -> {
            bField.setText(String.valueOf(bSlider.getValue()));
            updateFromRgb(++rCount);
            --rCount;
        });
        rgbPanel.add(setRGBButton);
        setRGBButton.addActionListener(new RGBListener());
        add(rgbPanel);

        cmykPanel.add(cmykLabel);
        cmykPanel.add(cField);
        cField.setBounds(40, 20, 50, 25);
        cmykPanel.add(mField);
        mField.setBounds(40, 20, 50, 25);
        cmykPanel.add(yField);
        yField.setBounds(40, 20, 50, 25);
        cmykPanel.add(kField);
        kField.setBounds(40, 20, 50, 25);
        cmykPanel.add(cSlider);
        cSlider.addChangeListener(e -> {
            cField.setText(String.valueOf(cSlider.getValue()));
            updateFromCmyk(++cCount);
            --cCount;
        });

        cmykPanel.add(mSlider);
        mSlider.addChangeListener(e -> {
            mField.setText(String.valueOf(mSlider.getValue()));
            updateFromCmyk(++cCount);
            --cCount;
        });
        cmykPanel.add(ySlider);
        ySlider.addChangeListener(e -> {
            yField.setText(String.valueOf(ySlider.getValue()));
            updateFromCmyk(++cCount);
            --cCount;
        });
        cmykPanel.add(kSlider);
        kSlider.addChangeListener(e -> {
            kField.setText(String.valueOf(kSlider.getValue()));
            updateFromCmyk(++cCount);
            --cCount;
        });
        cmykPanel.add(setCMYKButton);
        setCMYKButton.addActionListener(new CMYKListener());
        add(cmykPanel);


        hlsPanel.add(hlsLabel);
        hlsPanel.add(hField);
        hField.setBounds(40, 20, 50, 25);
        hlsPanel.add(lField);
        lField.setBounds(40, 20, 50, 25);
        hlsPanel.add(sField);
        sField.setBounds(40, 20, 50, 25);
        hlsPanel.add(hSlider);
        hSlider.addChangeListener(e -> {
            hField.setText(String.valueOf(hSlider.getValue()));
            updateFromHLS(++hCount);
            --hCount;
        });
        hlsPanel.add(lSlider);
        lSlider.addChangeListener(e -> {
            lField.setText(String.valueOf(lSlider.getValue()));
            updateFromHLS(++hCount);
            --hCount;
        });
        hlsPanel.add(sSlider);
        sSlider.addChangeListener(e -> {
            sField.setText(String.valueOf(sSlider.getValue()));
            updateFromHLS(++hCount);
            --hCount;
        });
        hlsPanel.add(setHLSButton);
        setHLSButton.addActionListener(new HLSListener());
        add(hlsPanel);

        add(new JLabel("Color"));
        colorPanel.setBackground(new Color(0, 0, 0));
        add(colorPanel);

        setVisible(true);
    }

    private void updateFromRgb(int i) {
        if (i <= 1) {
            int r = rSlider.getValue();
            int g = gSlider.getValue();
            int b = bSlider.getValue();

            // Update CMYK
            int[] cmyk = RGB.rgbToCmyk(r, g, b);
            cField.setText(String.valueOf(cmyk[0]));
            cSlider.setValue(cmyk[0]);
            mField.setText(String.valueOf(cmyk[1]));
            mSlider.setValue(cmyk[1]);
            yField.setText(String.valueOf(cmyk[2]));
            ySlider.setValue(cmyk[2]);
            kField.setText(String.valueOf(cmyk[3]));
            kSlider.setValue(cmyk[3]);

            // Update HLS
            int[] hls = RGB.rgbToHls(r, g, b);
            hField.setText(String.valueOf(hls[0]));
            hSlider.setValue(hls[0]);
            sField.setText(String.valueOf(hls[1]));
            sSlider.setValue(hls[1]);
            lField.setText(String.valueOf(hls[2]));
            lSlider.setValue(hls[2]);

            colorPanel.setBackground(new Color(r, g, b));
        }
    }

    private void updateFromCmyk(int i) {
        if (i <= 1) {
            double c = cSlider.getValue() / 100.0;
            double m = mSlider.getValue() / 100.0;
            double y = ySlider.getValue() / 100.0;
            double k = kSlider.getValue() / 100.0;

            // Update RGB
            int[] rgb = CMYK.cmykToRgb(c, m, y, k);
            rSlider.setValue(rgb[0]);
            gSlider.setValue(rgb[1]);
            bSlider.setValue(rgb[2]);

            // Update HLS
            int[] hls = CMYK.cmykToHls(c, m, y, k);
            hField.setText(String.valueOf(hls[0]));
            sField.setText(String.valueOf(hls[1]));
            lField.setText(String.valueOf(hls[2]));

            colorPanel.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
        }

    }

    private void updateFromHLS(int i) {
        if (i <= 1) {
            double h = hSlider.getValue();
            double l = lSlider.getValue() / 100.0;
            double s = sSlider.getValue() / 100.0;

            // Update RGB
            int[] rgb = HLS.hlsToRgb(h, l, s);
            rSlider.setValue(rgb[0]);
            gSlider.setValue(rgb[1]);
            bSlider.setValue(rgb[2]);

            // Update HLS
            int[] cmyk = HLS.hlsToCmyk(h, l, s);
            cField.setText(String.valueOf(cmyk[0]));
            mField.setText(String.valueOf(cmyk[1]));
            yField.setText(String.valueOf(cmyk[2]));
            kField.setText(String.valueOf(cmyk[3]));

            colorPanel.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
        }
    }

    private class RGBListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rSlider.setValue(Integer.parseInt(rField.getText()));
            gSlider.setValue(Integer.parseInt(gField.getText()));
            bSlider.setValue(Integer.parseInt(bField.getText()));
        }
    }

    private class CMYKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cSlider.setValue((int) (Double.parseDouble(cField.getText())));
            mSlider.setValue((int) (Double.parseDouble(mField.getText())));
            ySlider.setValue((int) (Double.parseDouble(yField.getText())));
            kSlider.setValue((int) (Double.parseDouble(kField.getText())));
        }
    }

    private class HLSListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            hSlider.setValue((int) (Double.parseDouble(hField.getText())));
            lSlider.setValue((int) (Double.parseDouble(lField.getText())));
            sSlider.setValue((int) (Double.parseDouble(sField.getText())));
        }
    }
}
