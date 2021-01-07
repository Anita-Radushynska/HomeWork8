package pl.chi.homework8;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    ListView listView;
    List<Note> noteList = new ArrayList<Note>();
    DatabaseManager mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_note);

        mDatabase = new DatabaseManager(this);
        listView = findViewById(R.id.listViewNotes);

        loadData();
    }

    private void loadData() {
        Cursor cursor = mDatabase.getAllNotes();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);

                Note note = new Note(id, name);
                noteList.add(note);
            } while (cursor.moveToNext());

            NoteAdapter noteAdapter = new NoteAdapter(this, R.layout.row_note, noteList, mDatabase);
            listView.setAdapter(noteAdapter);
        }
    }
}