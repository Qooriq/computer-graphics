package Lab1.colors;

public class HLS {

    private HLS() {
    }

    public static int[] hlsToCmyk(double h, double l, double s) {
        int[] rgb = HLS.hlsToRgb(h, l, s);
        return RGB.rgbToCmyk(rgb[0], rgb[1], rgb[2]);
    }

    public static int[] hlsToRgb(double h, double l, double s) {
        int r, g, b;
        double c = (1 - Math.abs(2 * l - 1)) * s;
        double x = c * (1 - Math.abs(h / 60 % 2 - 1));
        double m = l - c / 2;
        double rs = c, gs = 0, bs = x;
        if (h < 60) {
            rs = c;
            gs = x;
            bs = 0;
        } else if (h < 120) {
            rs = x;
            gs = c;
            bs = 0;
        } else if (h < 180) {
            rs = 0;
            gs = c;
            bs = x;
        } else if (h < 240) {
            rs = 0;
            gs = x;
            bs = c;
        } else if (h < 300) {
            rs = x;
            gs = 0;
            bs = c;
        }
        r = (int) ((rs + m) * 255);
        g = (int) ((gs + m) * 255);
        b = (int) ((bs + m) * 255);

        return  new int[] {r, g, b};
    }
}
