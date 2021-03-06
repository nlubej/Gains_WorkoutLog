package nlubej.gains.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import nlubej.gains.DataTransferObjects.RoutineDto;
import nlubej.gains.Database.QueryFactory;
import nlubej.gains.R;
import nlubej.gains.Views.Routine;
import nlubej.gains.interfaces.OnItemChanged;

/**
 * Created by nlubej on 22.10.2015.
 */
public class AddRoutineDialog extends DialogFragment implements View.OnClickListener
{

    private QueryFactory db;
    private OnItemChanged<RoutineDto> parent;
    private Context context;
    private int programId;
    private MaterialEditText routine;
    private AlertDialog alertDialog;

    public void SetData(Routine fragment, QueryFactory database)
    {
        this.parent = fragment;
        db = database;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        context = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_edit, null);
        routine = (MaterialEditText) view.findViewById(R.id.name);
        routine.setHint("Legs/Abs");

        Button yes = (Button)view.findViewById(R.id.btn_yes);
        Button no = (Button)view.findViewById(R.id.btn_no);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        programId = getArguments().getInt("PROGRAM_ID");

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();

        return alertDialog;
    }

    @Override
    public void onClick(View v)
    {
        int routineId = 0;

        Boolean wantToCloseDialog = true;
        switch (v.getId())
        {

            case R.id.btn_yes:
                if (routine.getText().toString().compareTo("") != 0)
                {
                    db.Open();
                    routineId = db.InsertRoutine(routine.getText().toString(), programId);
                    db.Close();
                }
                else
                {
                    routine.setError("Write a name");
                    wantToCloseDialog = false;
                }

                if (wantToCloseDialog)
                {
                    parent.OnAdded(new RoutineDto(routineId, routine.getText().toString(), programId));
                    alertDialog.dismiss();
                }
                break;
            case R.id.btn_no:
                alertDialog.dismiss();
                break;
        }
    }
}
