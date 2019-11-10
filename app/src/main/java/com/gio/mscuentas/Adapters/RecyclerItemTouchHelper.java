package com.gio.mscuentas.Adapters;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.View;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemTouchHelperListener listener;


    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs,
                                   RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder viewHolder1) {
        return true;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {

        if (viewHolder != null)
        {
            View foregrountView = ((countAdapter.VHcount) viewHolder).idLayoutitem;
            getDefaultUIUtil().onSelected(foregrountView);
        }
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {

        View foregrountView = ((countAdapter.VHcount)viewHolder).idLayoutitem;

        getDefaultUIUtil().onDrawOver(c,recyclerView, foregrountView , dX, dY,
                actionState,isCurrentlyActive);

    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        View foregrountView = ((countAdapter.VHcount)viewHolder).idLayoutitem;
        getDefaultUIUtil().clearView(foregrountView);
    }


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        View foregrountView = ((countAdapter.VHcount)viewHolder).idLayoutitem;

        getDefaultUIUtil().onDraw(c,recyclerView, foregrountView ,dX,dY,
                actionState,isCurrentlyActive);

    }


    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {


        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwipe(viewHolder,direction, viewHolder.getAdapterPosition());
    }














    public interface RecyclerItemTouchHelperListener
    {
        void onSwipe(RecyclerView.ViewHolder viewHolder,int direction,int position);
    }
}
