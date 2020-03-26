package synthese.projet.filterapp;

import android.content.Context;
import android.view.TextureView;

public class MainView extends TextureView {
    MainRenderer mRenderer;

    MainView ( Context context ) {
        super ( context );
        mRenderer = new MainRenderer(context, this);

        this.setSurfaceTextureListener(mRenderer);
    }

    public void onResume() {
        mRenderer.onResume();
    }

    public void onPause() {
        mRenderer.onPause();
    }

    public void setFilter (int filterId) {
        mRenderer.selectFilter(filterId);
    }
}
