## RGB to CMYK

To convert RGB (Red, Green, Blue) values to CMYK (Cyan, Magenta, Yellow, Black):

1. Normalize RGB values to the range [0, 1]:
   \[
   r' = \frac{R}{255}, \quad g' = \frac{G}{255}, \quad b' = \frac{B}{255}
   \]

2. Calculate Black (K):
   \[
   K = 1 - \max(r', g', b')
   \]

3. Calculate Cyan (C), Magenta (M), and Yellow (Y):
   \[
   C = \frac{1 - r' - K}{1 - K} \quad \text{(if } K < 1\text{)}
   \]
   \[
   M = \frac{1 - g' - K}{1 - K} \quad \text{(if } K < 1\text{)}
   \]
   \[
   Y = \frac{1 - b' - K}{1 - K} \quad \text{(if } K < 1\text{)}
   \]

## RGB to HSL

To convert RGB values to HSL (Hue, Saturation, Lightness):

1. Normalize RGB values:
   \[
   r' = \frac{R}{255}, \quad g' = \frac{G}{255}, \quad b' = \frac{B}{255}
   \]

2. Find the maximum and minimum values:
   \[
   C_{max} = \max(r', g', b'), \quad C_{min} = \min(r', g', b')
   \]
   \[
   \Delta = C_{max} - C_{min}
   \]

3. Calculate Lightness (L):
   \[
   L = \frac{C_{max} + C_{min}}{2}
   \]

4. Calculate Saturation (S):
   \[
   S = \begin{cases}
   0 & \text{if } \Delta = 0 \\
   \frac{\Delta}{1 - |2L - 1|} & \text{if } \Delta > 0
   \end{cases}
   \]

5. Calculate Hue (H):
   \[
   H = \begin{cases}
   0 & \text{if } \Delta = 0 \\
   60 \times \left(\frac{g' - b'}{\Delta} + (6 \text{ if } g' < b')\right) & \text{if } C_{max} = r' \\
   60 \times \left(\frac{b' - r'}{\Delta} + 2\right) & \text{if } C_{max} = g' \\
   60 \times \left(\frac{r' - g'}{\Delta} + 4\right) & \text{if } C_{max} = b'
   \end{cases}
   \]

## HSL to RGB

To convert HSL back to RGB:

1. Calculate Chroma (C):
   \[
   C = (1 - |2L - 1|) \times S
   \]

2. Calculate X:
   \[
   X = C \times (1 - |(H / 60) \mod 2 - 1|)
   \]

3. Calculate m (match):
   \[
   m = L - \frac{C}{2}
   \]

4. Determine RGB prime values:
   \[
   (r', g', b') = \begin{cases}
   (C, X, 0) & \text{if } 0 \leq H < 60 \\
   (X, C, 0) & \text{if } 60 \leq H < 120 \\
   (0, C, X) & \text{if } 120 \leq H < 180 \\
   (0, X, C) & \text{if } 180 \leq H < 240 \\
   (X, 0, C) & \text{if } 240 \leq H < 300 \\
   (C, 0, X) & \text{if } 300 \leq H < 360
   \end{cases}
   \]

5. Convert to final RGB values:
   \[
   R = (r' + m) \times 255, \quad G = (g' + m) \times 255, \quad B = (b' + m) \times 255
   \]

These formulas provide a systematic approach to converting between the RGB, CMYK, and HSL color models.
