package synthese.projet.filterapp.filter;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.FloatBuffer;

import synthese.projet.filterapp.*;

public class Convolution1C extends CameraFilter {

    private int program;
    private float mConv[];

    public Convolution1C(Context context, float[] matrix) {
        super(context);

        mConv = matrix;

        program = GLUtils.buildProgram(context, R.raw.vertext, R.raw.convolution1c);
    }

    @Override
    public void onDraw(int cameraTexId, int canvasWidth, int canvasHeight) {
        setupShaderInputs(program,
                new int[]{canvasWidth, canvasHeight},
                new int[]{cameraTexId},
                new int[][]{});

        int matLoc = GLES20.glGetUniformLocation(program, "kernel");
        //GLES20.glUniform3fv(matLoc, 1, FloatBuffer.wrap(mConv));
        GLES20.glUniform3f(matLoc, 1.f, .5f, .5f);


        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}