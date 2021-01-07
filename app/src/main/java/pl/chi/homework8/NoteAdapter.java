package pl.chi.homework8;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NoteAdapter extends ArrayAdapter {

    Context ctx;
    List<Note> noteList;
    int layoutRes;
    DatabaseManager mDatabase;
    ListView listView;


    public NoteAdapter(@NonNull Context context, int resource,
                       List<Note> noteList, DatabaseManager mDatabase) {
        super(context, resource, noteList);
        ctx = context;
        layoutRes = resource;
        this.noteList = noteList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(layoutRes, null);

        TextView tvId = view.findViewById(R.id.tvId);
        TextView tvName = view.findViewById(R.id.tvName);

        Note note = noteList.get(position);

        tvId.setText(String.valueOf(note.getId()));
        tvName.setText(note.getName());

        view.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote(note);

            }
        });
        return view;
    }

    private void deleteNote(Note note) {
        mDatabase.deleteNote(note.getId());
        noteList.remove(note);
        notifyDataSetChanged();

    }
}
