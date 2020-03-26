package synthese.projet.filterapp.filter;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.FloatBuffer;

import synthese.projet.filterapp.*;

public class Convolution3C extends CameraFilter {
    private int program;
    private float mRed[];
    private float mGreen[];
    private float mBlue[];
    private float mFactor;
    private float mOffset;

    public Convolution3C(Context context, float[] red, float[] green, float[] blue, float tesselFactor, float offset) {
        super(context);

        mRed = red;
        mGreen = green;
        mBlue = blue;
        mFactor = tesselFactor;
        mOffset = offset;

        program = GLUtils.buildProgram(context, R.raw.vertext3, R.raw.convolution3c);
    }

    @Override
    public void onDraw(int cameraTexId, int canvasWidth, int canvasHeight) {
        setupShaderInputs(program,
                new int[]{canvasWidth, canvasHeight},
                new int[]{cameraTexId},
                new int[][]{});

        float texelWidth = mFactor / ((float) canvasWidth);
        float texelHeight = mFactor / ((float) canvasWidth);
        int tWidthLoc = GLES20.glGetUniformLocation(program, "texelWidth");
        int tHeightLoc = GLES20.glGetUniformLocation(program, "texelHeight");
        GLES20.glUniform1f(tWidthLoc, texelWidth);
        GLES20.glUniform1f(tHeightLoc, texelHeight);

        int matLoc = GLES20.glGetUniformLocation(program, "red");
        GLES20.glUniform1fv(matLoc, 9, FloatBuffer.wrap(mRed));
        matLoc = GLES20.glGetUniformLocation(program, "green");
        GLES20.glUniform1fv(matLoc, 9, FloatBuffer.wrap(mGreen));
        matLoc = GLES20.glGetUniformLocation(program, "blue");
        GLES20.glUniform1fv(matLoc, 9, FloatBuffer.wrap(mBlue));

        int offLoc = GLES20.glGetUniformLocation(program, "offset");
        GLES20.glUniform1f(offLoc, mOffset);


        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
