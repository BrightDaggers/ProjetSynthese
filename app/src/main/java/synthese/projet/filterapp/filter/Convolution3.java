package synthese.projet.filterapp.filter;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.FloatBuffer;

import synthese.projet.filterapp.*;

public class Convolution3 extends CameraFilter {
    private int program;
    private float mConv[];
    private float mFactor;
    private float mOffset;

    public Convolution3(Context context, float[] matrix, float tesselFactor, float offset) {
        super(context);

        mConv = matrix;
        mFactor = tesselFactor;
        mOffset = offset;

        program = GLUtils.buildProgram(context, R.raw.vertext3, R.raw.convolution3);
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

        int matLoc = GLES20.glGetUniformLocation(program, "convo");
        GLES20.glUniformMatrix3fv(matLoc, 1, true, FloatBuffer.wrap(mConv));

        int offLoc = GLES20.glGetUniformLocation(program, "offset");
        GLES20.glUniform1f(offLoc, mOffset);


        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
