package com.bp.pruebanfc;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Extraer datos de un tag NFC. */
        final String nfcAction = NfcAdapter.ACTION_NDEF_DISCOVERED;
        /* Pasa un intent a la actividad con los datos alojados en el tag. */
        String action = getIntent().getAction();

        if (action.equals(nfcAction)) {
            //Recupera la tabla de mensajes transmitidos por NFC.
            Parcelable[] nfcTagMsg = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            //Recupera los distintos campos del mensaje.
            for (Parcelable nfcTag : nfcTagMsg) {
                NdefMessage msg = (NdefMessage) nfcTag;
                NdefRecord[] records = msg.getRecords();

                for (NdefRecord record : records) {
                    //Realizar el procesamiento sobre los distintos campos del mensaje.
                }
            }
        }

        //Comprueba si el adaptador NFC esta habiltado.
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null) { //El dispositivo no dispone de NFC
            Toast.makeText(this, "Este dispositivo no dispone de NFC.", Toast.LENGTH_LONG).show();
        }

        if (!nfcAdapter.isEnabled()) { //NFC habilitado.
            Toast.makeText(this, "NFC activado", Toast.LENGTH_LONG).show();
        } else { //NFC deshabilitado.
            Toast.makeText(this, "NFC desactivado", Toast.LENGTH_LONG).show();
        }


        nfcAdapter.setNdefPushMessageCallback(new NfcAdapter.CreateNdefMessageCallback() {

            @Override
            public NdefMessage createNdefMessage(NfcEvent event) {
                //Crear mensaje Ndef
                return null;
            }
        }, this);

        /* ANDROID BEAM: Sirve para compartir y transmitir datos usando NFC. Para ello
        * utilizar el método invokeBeam de la clase NfcAdapter. */
    }
}
