package Lab1.colors;

public class CMYK {

    private CMYK() {}

    public static int[] cmykToRgb(double c, double m, double y, double k) {
        int r = (int) (255 * (1 - c) * (1 - k));
        int g = (int) (255 * (1 - m) * (1 - k));
        int b = (int) (255 * (1 - y) * (1 - k));
        return new int[] {r, g, b};
    }

    public static int[] cmykToHls(double c, double m, double y, double k) {
        int[] rgb = cmykToRgb(c, m, y, k);
        return RGB.rgbToHls(rgb[0], rgb[1], rgb[2]);
    }
}
