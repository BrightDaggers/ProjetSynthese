package synthese.projet.filterapp.filter;

import android.content.Context;
import android.opengl.GLES20;

import synthese.projet.filterapp.*;

public class Convolution1 extends CameraFilter {

    private int program;
    private float mConv[];

    public Convolution1(Context context, float[] matrix) {
        super(context);

        mConv = matrix;

        program = GLUtils.buildProgram(context, R.raw.vertext, R.raw.convolution1);
    }

    @Override
    public void onDraw(int cameraTexId, int canvasWidth, int canvasHeight) {
        setupShaderInputs(program,
                new int[]{canvasWidth, canvasHeight},
                new int[]{cameraTexId},
                new int[][]{});

        int matLoc = GLES20.glGetUniformLocation(program, "kernel");
        GLES20.glUniform1f(matLoc, mConv[0]);


        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
