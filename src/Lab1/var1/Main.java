package Lab1.var1;

import Lab1.colors.CMYK;
import Lab1.colors.HLS;
import Lab1.colors.RGB;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private JTextField rField, gField, bField;
    private JTextField cField, mField, yField, kField;
    private JTextField hField, lField, sField;
    private JColorChooser colorChooser;

    public Main() {
        setTitle("Color Converter");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JPanel rgbPanel = new JPanel();
        rgbPanel.add(new JLabel("RGB:"));
        rField = createTextField();
        rField.setText("0");
        gField = createTextField();
        gField.setText("0");
        bField = createTextField();
        bField.setText("0");
        rgbPanel.add(rField);
        rgbPanel.add(gField);
        rgbPanel.add(bField);

        JPanel cmykPanel = new JPanel();
        cmykPanel.add(new JLabel("CMYK:"));
        cField = createTextField();
        cField.setText("0.0");
        mField = createTextField();
        mField.setText("0.0");
        yField = createTextField();
        yField.setText("0.0");
        kField = createTextField();
        kField.setText("0.0");
        cmykPanel.add(cField);
        cmykPanel.add(mField);
        cmykPanel.add(yField);
        cmykPanel.add(kField);

        JPanel hlsPanel = new JPanel();
        hlsPanel.add(new JLabel("HLS:"));
        hField = createTextField();
        hField.setText("0.0");
        lField = createTextField();
        lField.setText("0.0");
        sField = createTextField();
        sField.setText("0.0");
        hlsPanel.add(hField);
        hlsPanel.add(lField);
        hlsPanel.add(sField);

        colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(e -> updateFromColorChooser());

        add(rgbPanel);
        add(cmykPanel);
        add(hlsPanel);
        add(colorChooser);

        // Event Listeners
        rField.addActionListener(e -> updateFromRGB());
        gField.addActionListener(e -> updateFromRGB());
        bField.addActionListener(e -> updateFromRGB());

        cField.addActionListener(e -> updateFromCMYK());
        mField.addActionListener(e -> updateFromCMYK());
        yField.addActionListener(e -> updateFromCMYK());
        kField.addActionListener(e -> updateFromCMYK());

        hField.addActionListener(e -> updateFromHLS());
        lField.addActionListener(e -> updateFromHLS());
        sField.addActionListener(e -> updateFromHLS());
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(5);
        textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }

    private void updateFromColorChooser() {
        Color color = colorChooser.getColor();
        updateRGBFields(color);
        updateCMYKFields(color);
        updateHLSFields(color);
    }

    private void updateFromRGB() {
        try {
            int r = Integer.parseInt(rField.getText());
            int g = Integer.parseInt(gField.getText());
            int b = Integer.parseInt(bField.getText());
            Color color = new Color(r, g, b);
            colorChooser.setColor(color);
            updateCMYKFields(color);
            updateHLSFields(color);
        } catch (NumberFormatException ignored) {
        }
    }

    private void updateFromHLS() {
        try {
            double h = Double.parseDouble(hField.getText());
            double l = Double.parseDouble(lField.getText());
            double s = Double.parseDouble(sField.getText());
            int[] rgb = HLS.hlsToRgb(h, l / 100.0, s / 100.0);
            Color color = new Color(rgb[0], rgb[1], rgb[2]);
            colorChooser.setColor(color);
            updateCMYKFields(color);
            updateRGBFields(color);
        } catch (NumberFormatException ignored) {
        }
    }

    private void updateFromCMYK() {
        try {
            double c = Double.parseDouble(cField.getText());
            double m = Double.parseDouble(mField.getText());
            double y = Double.parseDouble(yField.getText());
            double k = Double.parseDouble(kField.getText());
            if (c > 100 || c < 0 || y > 100 || y < 0 || k > 100 || k < 0 || m > 100 || m < 0) {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int[] rgb = CMYK.cmykToRgb(c / 100.0, m / 100.0, y / 100.0, k / 100.0);
                Color color = new Color(rgb[0], rgb[1], rgb[2]);
                colorChooser.setColor(color);
                updateHLSFields(color);
                updateRGBFields(color);
                updateCMYKFields(color);
            }

        } catch (NumberFormatException ignored) {
        }
    }

    private void updateRGBFields(Color color) {
        rField.setText(String.valueOf(color.getRed()));
        gField.setText(String.valueOf(color.getGreen()));
        bField.setText(String.valueOf(color.getBlue()));
    }

    private void updateCMYKFields(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        int[] cmyk = RGB.rgbToCmyk(r, g, b);

        cField.setText(String.format("%.2f", cmyk[0] * 100.0));
        mField.setText(String.format("%.2f", cmyk[1] * 100.0));
        yField.setText(String.format("%.2f", cmyk[2] * 100.0));
        kField.setText(String.format("%.2f", cmyk[3] * 100.0));

    }

    private void updateHLSFields(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int[] hls = RGB.rgbToHls(r, g, b);
        hField.setText(String.format("%.2f", hls[0]));
        lField.setText(String.format("%.2f", hls[1] * 100.0));
        sField.setText(String.format("%.2f", hls[2] * 100.0));
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}