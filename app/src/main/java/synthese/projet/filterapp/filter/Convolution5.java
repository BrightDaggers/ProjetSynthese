package synthese.projet.filterapp.filter;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.FloatBuffer;

import synthese.projet.filterapp.*;

public class Convolution5 extends CameraFilter {
    private int program;
    private float mConv[];
    private float mFactor;
    private float mOffset;

    public Convolution5(Context context, float[] matrix, float tesselFactor, float offset) {
        super(context);

        mConv = matrix;
        mFactor = tesselFactor;
        mOffset = offset;

        program = GLUtils.buildProgram(context, R.raw.vertext5, R.raw.convolution5);
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

        int matLoc = GLES20.glGetUniformLocation(program, "kernel");
        GLES20.glUniform1fv(matLoc, 25, FloatBuffer.wrap(mConv));


        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
