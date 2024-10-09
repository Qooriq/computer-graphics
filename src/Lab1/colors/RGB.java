package Lab1.colors;

public class RGB {

    private RGB() {}

    public static double[] rgbToCmyk(int r, int g, int b) {
        double k = Math.min(1 - r / 255.0, Math.min( 1 - g / 255.0, 1 -  b / 255.0));
        double c = (1 - r / 255.0 - k) / (1 - k);
        double m = (1 - g / 255.0 - k) / (1 - k);
        double y = (1 - b / 255.0 - k) / (1 - k);
        return new double[] {c, m, y, k};
    }

    public static double[] rgbToHls(int r, int g, int b) {
        double rTemp = r / 255.0;
        double gTemp = g / 255.0;
        double bTemp = b / 255.0;
        double cMax = Math.max(rTemp, Math.max(gTemp, bTemp));
        double cMin = Math.min(rTemp, Math.min(gTemp, bTemp));
        double delta = cMax - cMin;
        double hue;
        double saturation;
        double luminance = (cMax + cMin) / 2;
        if (delta == 0.0) {
            hue = 0;
            saturation = 0;
        } else {
            saturation = delta / (1 - Math.abs(2 * luminance - 1));
            if (cMax == rTemp) {
                hue = 60 * ((gTemp - bTemp) / delta  + (gTemp < bTemp ? 6 : 0));
            } else if (cMax == gTemp) {
                hue = 60 * ((bTemp - rTemp) / delta  + 2);
            } else {
                hue = 60 * ((rTemp - gTemp) / delta  + 4);
            }
        }
        return new double[]{hue, luminance, saturation};
    }
}
