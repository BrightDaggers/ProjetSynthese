package synthese.projet.filterapp.filter;


import android.content.Context;

import java.nio.FloatBuffer;

public class FilterFactory {
    Context mContext;


    public FilterFactory (Context context) {
        mContext = context;
    }

    public CameraFilter readFilter (String file) {
        String[] lines = file.split("\n");

        if (lines[0].toLowerCase().substring(0,2).equals("bw")) {
            return readBW(lines);
        }
        else if (lines[0].toLowerCase().substring(0,5).equals("color")) {
            return readColor(lines);
        }

        return null;
    }

    private CameraFilter readColor(String[] lines) {
        if (lines.length<3) {
            String[] nums = lines[1].split(" ");
            float[] color = {
                    Float.valueOf(nums[0]),
                    Float.valueOf(nums[1]),
                    Float.valueOf(nums[2]),
            };

            return new Convolution1C(mContext, color);
        }
        else {
            float[] red = new float[9];
            for (int i=0; i<3; i++) {
                String[] s = lines[i+1].split(" ");
                for (int j=0; j<3; j++) {
                    red[i*3 + j] = Float.valueOf(s[j]);
                }
            }
            float[] green = new float[9];
            for (int i=0; i<3; i++) {
                String[] s = lines[i+4].split(" ");
                for (int j=0; j<3; j++) {
                    green[i*3 + j] = Float.valueOf(s[j]);
                }
            }
            float[] blue = new float[9];
            for (int i=0; i<3; i++) {
                String[] s = lines[i+7].split(" ");
                for (int j=0; j<3; j++) {
                    blue[i*3 + j] = Float.valueOf(s[j]);
                }
            }
            float factor = 1.f;
            float offset = 0.f;

            return new Convolution3C(mContext, red, green, blue, factor, offset);
        }
    }

    private CameraFilter readBW (String[] lines) {
        int n = lines[1].split(" ").length;

        switch(n) {
            case 1:
                return readBW1(lines);
            case 3:
                return readBW3(lines);
            case 5:
                return readBW5(lines);
        }
        return null;
    }

    private CameraFilter readBW5(String[] lines) {
        float[] conv = new float[25];
        float tot = 0.f;
        float fact = 0.f;
        float offset = 0.f;

        for (int i=0; i<5; i++) {
            String[] s = lines[i+1].split(" ");
            for (int j=0; j<5; j++) {
                conv[i*5 + j] = Float.valueOf(s[j]);
                tot += conv[i*5 + j];
            }
        }

        if (lines.length>6) {
            offset = Float.valueOf(lines[6]);
            if (lines.length > 7) {
                fact = Float.valueOf(lines[7]);

                if (lines.length > 8 && lines[8].toLowerCase().equals("true") && tot != 0.f) {
                    for (int i = 0; i < 25; i++)
                        conv[i] /= tot;
                }
            }
        }

        return new Convolution5(mContext, conv, fact, offset);
    }

    private CameraFilter readBW3(String[] lines) {
        float[] conv = new float[9];
        float tot = 0.f;
        float fact = 0.f;
        float offset = 0.f;

        for (int i=0; i<3; i++) {
            String[] s = lines[i+1].split(" ");
            for (int j=0; j<3; j++) {
                conv[i*3 + j] = Float.valueOf(s[j]);
                tot += conv[i*3 + j];
            }
        }

        if (lines.length>4) {
            offset = Float.valueOf(lines[4]);

            if (lines.length > 5) {
                fact = Float.valueOf(lines[5]);
                if (lines.length > 6 && lines[6].toLowerCase().equals("true") && tot != 0.f) {
                    for (int i = 0; i < 9; i++)
                        conv[i] /= tot;
                }
            }
        }

        return new Convolution3(mContext, conv, fact, offset);
    }

    private CameraFilter readBW1(String[] lines) {
        float[] conv = {Float.valueOf(lines[1])};
        return new Convolution1(mContext, conv);
    }
}
