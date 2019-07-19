package org.citra.citra_android.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.citra.citra_android.R;
import org.citra.citra_android.model.Game;
import org.citra.citra_android.utils.PicassoUtils;
import org.citra.citra_android.viewholders.TvGameViewHolder;

/**
 * The Leanback library / docs call this a Presenter, but it works very
 * similarly to a RecyclerView.Adapter.
 */
public final class GameRowPresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        // Create a new view.
        ImageCardView gameCard = new ImageCardView(parent.getContext());

        gameCard.setMainImageAdjustViewBounds(true);
        gameCard.setMainImageDimensions(48, 48);
        gameCard.setMainImageScaleType(ImageView.ScaleType.CENTER_CROP);

        gameCard.setFocusable(true);
        gameCard.setFocusableInTouchMode(true);

        // Use that view to create a ViewHolder.
        return new TvGameViewHolder(gameCard);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        TvGameViewHolder holder = (TvGameViewHolder) viewHolder;
        Game game = (Game) item;

        String screenPath = game.getScreenshotPath();

        holder.imageScreenshot.setImageDrawable(null);
        PicassoUtils.loadGameBanner(holder.imageScreenshot, screenPath, game.getPath());

        holder.cardParent.setTitleText(game.getTitle());
        holder.cardParent.setContentText(game.getCompany());

        // TODO These shouldn't be necessary once the move to a DB-based model is complete.
        holder.gameId = game.getGameId();
        holder.path = game.getPath();
        holder.title = game.getTitle();
        holder.description = game.getDescription();
        holder.country = game.getCountry();
        holder.company = game.getCompany();
        holder.screenshotPath = game.getScreenshotPath();

        // Set the platform-dependent background color of the card
        Context context = holder.cardParent.getContext();
        Drawable background = ContextCompat.getDrawable(context, R.drawable.tv_card_background_gamecube);
        holder.cardParent.setInfoAreaBackground(background);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        // no op
    }
}
