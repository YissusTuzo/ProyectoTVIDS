package mx.uabcs.proyecto;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView misnotas;
    TextView close;
    SharedPreferences sp;

    public ProfileFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        /*TextView txtViewHomeFragment = view.findViewById(R.id.txt_view_home_fragment);
        txtViewHomeFragment.setText("Hello from code");*/
        view.findViewById(R.id.note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NoteActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.horarios).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), horarios.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.calificaciones).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Calificaciones.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.cuenta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp=getContext().getSharedPreferences("MyPrefs",MODE_PRIVATE);
                SharedPreferences.Editor E =sp.edit();
                E.clear();
                E.apply();
                Intent intent = new Intent(view.getContext(), Launcher.class);
                startActivity(intent);
            }
        });

        return view;

    }



}
