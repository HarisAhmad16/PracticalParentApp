package ca.cmpt276.titanium.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;

import java.util.List;

import ca.cmpt276.titanium.R;
import ca.cmpt276.titanium.model.Child;

/**
 * This is an adapter for the coin flip queue list.
 */
public class CoinFlipQueueAdapter extends ArrayAdapter<Child> {
    private Context context;

    public CoinFlipQueueAdapter(Context context, List<Child> children) {
        super(context, 0, children);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_coin_flip_children_queue, parent, false);
        }

        Child child = getItem(position);

        RoundedBitmapDrawable drawable = child.getPortrait(context.getResources());
        ImageView childQueueIcon = convertView.findViewById(R.id.childQueueIcon);
        childQueueIcon.setImageDrawable(drawable);

        TextView childQueueNameText = convertView.findViewById(R.id.childQueueNameList);
        String childQueueName = child.getName();
        childQueueNameText.setText(childQueueName);

        return convertView;
    }
}