package Lab1.colors;

public class HLS {

    private HLS() {
    }

    public static double[] hlsToCmyk(double h, double l, double s) {
        int[] rgb = HLS.hlsToRgb(h, l, s);
        return RGB.rgbToCmyk(rgb[0], rgb[1], rgb[2]);
    }

    public static int[] hlsToRgb(double h, double l, double s) {
        double r, g, b;

        if (s == 0) {
            r = g = b = l;
        } else {
            double m2 = (l < 0.5) ? l * (1 + s) : l + s - (l * s);
            double m1 = 2 * l - m2;
            r = hueToRgb(m1, m2, h + 120);
            g = hueToRgb(m1, m2, h);
            b = hueToRgb(m1, m2, h - 120);
        }

        return new int[]{(int) (r * 255), (int) (g * 255), (int) (b * 255)};
    }

    private static double hueToRgb(double p, double q, double t) {
        if (t < 0) t += 360;
        if (t > 360) t -= 360;
        if (t < 60) return p + (q - p) * t / 60;
        if (t < 180) return q;
        if (t < 240) return p + (q - p) * (240 - t) / 60;
        return p;
    }
}
