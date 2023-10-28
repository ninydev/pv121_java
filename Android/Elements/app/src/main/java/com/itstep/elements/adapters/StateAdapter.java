package com.itstep.elements.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.itstep.elements.MainActivity;
import com.itstep.elements.R;
import com.itstep.elements.models.State;

public class StateAdapter extends ArrayAdapter<State>
{
    private LayoutInflater inflater;
    private int layout;
    private List<State> states = new ArrayList<>();



    // resource - можно писать так, что бы не передавать ресурс - а назначать его уже внутри адаптера
    // но это наружит единообразие адаптеров
    public StateAdapter(Context context, int resource, List<State> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    // Но такой стиль нарушает едиообразие
    public StateAdapter(Context context, List<State> states) {
        super(context, R.layout.my_list_state_item, states);

        this.states = states;
        this.layout = R.layout.my_list_state_item;
        this.inflater = LayoutInflater.from(context);
    }



    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);
        TextView capitalView = view.findViewById(R.id.capital);

        State state = states.get(position);

        flagView.setImageResource(state.getFlagResource());
        nameView.setText(state.getName());
        capitalView.setText(state.getCapital());

        return view;
    }


}
