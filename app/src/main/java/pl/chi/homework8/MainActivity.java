package pl.chi.homework8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    DatabaseManager mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataBase = new DatabaseManager(this);
        etName = findViewById(R.id.etName);
    }

    public void clickButoon(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                addNote();
                break;
            case R.id.btnList:
                showList();
                break;
        }
    }

    private void showList() {
        startActivity(new Intent(this, NoteActivity.class));
    }

    private void addNote() {
       String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Please enter name");
            etName.requestFocus();
            return;
        }

        mDataBase.addNote(name);
        Toast.makeText(this,"Added note", Toast.LENGTH_LONG).show();

        etName.setText("");
    }


}