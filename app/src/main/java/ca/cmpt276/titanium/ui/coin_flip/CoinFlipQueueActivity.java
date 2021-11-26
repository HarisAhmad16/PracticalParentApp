package ca.cmpt276.titanium.ui.coin_flip;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;

import java.util.Objects;

import ca.cmpt276.titanium.R;
import ca.cmpt276.titanium.model.Child;
import ca.cmpt276.titanium.model.Children;
import ca.cmpt276.titanium.model.ChildrenQueue;
import ca.cmpt276.titanium.model.CoinFlipHistory;

/**
 * This activity represents the coin flip queue.
 */
public class CoinFlipQueueActivity extends AppCompatActivity {
    private Children children;
    private ChildrenQueue childrenQueue;
    private CoinFlipHistory coinFlipHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip_queue);
        setTitle(R.string.toolbar_coin_flip_history_view_queue);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        children = Children.getInstance(this);
        childrenQueue = ChildrenQueue.getInstance(this);
        coinFlipHistory = CoinFlipHistory.getInstance(this);

        displayQueue();
        updateGUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coinflipqueue, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayQueue();
        updateGUI();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.changeChildButton) {
            startActivity(new Intent(this, ChangeChildActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void displayQueue() {
        if (children.getChildren().size() == 0) {
            findViewById(R.id.childrenQueueListText).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.childrenQueueListText).setVisibility(View.INVISIBLE);
        }

        ListView childrenListView = findViewById(R.id.childrenQueueList);
        CoinFlipQueueAdapter adapter = new CoinFlipQueueAdapter(this, childrenQueue.getChildrenQueue());
        childrenListView.setAdapter(adapter);
    }

    private void updateGUI() {
        ImageView currentChildIcon = findViewById(R.id.currentChildTurnIcon);
        TextView currentChildTurnName = findViewById(R.id.currentChildTurnText);

        Child currentChildTurn = childrenQueue.getChild(coinFlipHistory.getNextPickerUniqueID());
        if (currentChildTurn != null) {
            currentChildTurnName.setText(currentChildTurn.getName());
            RoundedBitmapDrawable drawable = children.getChild(coinFlipHistory.getNextPickerUniqueID()).getPortrait(getResources());
            currentChildIcon.setImageDrawable(drawable);
        } else {
            currentChildTurnName.setText(R.string.empty_state_no_children);
            currentChildIcon.setImageResource(R.drawable.ic_default_portrait_green);
        }
    }
}