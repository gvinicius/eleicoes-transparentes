package br.ufba.mata62.eleicoestransparentes.ui.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import br.ufba.mata62.eleicoestransparentes.ui.activities.R;

/**
 * Classe que implementa um Dialog(espécie de janela pop-up) das UFS a serem escolhidas
 * @author tiagogoncalves
 *
 */
public class SelectUFDialog extends DialogFragment {

	public static final String[] UFS = { "AC", "AL", "AM", "AP", "BA", "CE",
		"DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI",
		"PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.uf_list_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        ListView languageList = (ListView) v.findViewById(R.id.uf_list);
        UFAdapter languageAdapter = new UFAdapter(SelectUFDialog.this.getActivity(), UFS );
        languageList.setAdapter(languageAdapter);
        
        Button cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SelectUFDialog.this.dismiss();
			}
		});
        
        Button ok = (Button) v.findViewById(R.id.ok);
        ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO BRUXARIA
			}
		});
        
        return v;
    }
}