package es.ulpgc.IST.infosierrapp.maestrodetalle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import es.ulpgc.IST.infosierrapp.R;


public class ItemPresenter extends FragmentActivity  {

    private EditText nombre;
    private EditText direccion;
    private EditText telefono;
    private EditText email;
    private EditText descripcion;
    private TextView pos;

    private ItemModel model;
    private String action;
    
    private GoogleMap mMap;
    //coordenadas
    private int X;
    private int Y;

    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        setUpMapIfNeeded();

        pos = (TextView)findViewById(R.id.lblPos);
        nombre = (EditText)findViewById(R.id.txtNombre);
        direccion = (EditText)findViewById(R.id.txtDireccion);
        telefono = (EditText)findViewById(R.id.txtTelefono);
        email = (EditText)findViewById(R.id.txtEmail);
        descripcion = (EditText)findViewById(R.id.txtDescripcion);
       



        Intent intent = getIntent();
        model = (ItemModel)intent.getSerializableExtra(Intent.ACTION_EDIT);

        pos.setText(model.getPos());

        nombre.setText(model.getNombre());
        direccion.setText(model.getDireccion());
        telefono.setText(model.getTelefono());
        email.setText(model.getEmail());
        descripcion.setText(model.getDescripcion());
        X=(model.getX());
        Y=(model.getY());


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    
    private void setUpMapIfNeeded() {
    	// Si el nMap esta null entonces es porque no se instancio el mapa.
        if (mMap == null) {
        	// Intenta obtener el mapa del SupportMapFragment. 
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Comprueba si hemos tenido exito en la obtencion del mapa.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(X, Y)).title("Marker"));
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(action, model);
        setResult(RESULT_OK, intent);
        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.menu_save:
//                model.setNombre(nombre.getText().toString());
//                model.setDireccion(direccion.getText().toString());
//                model.setTelefono(telefono.getText().toString());
//                model.setEmail(email.getText().toString());
//                model.setDescripcion(descripcion.getText().toString());
//                action = Intent.ACTION_EDIT;
//                finish();
//                return true;
            case R.id.menu_llamar:
            	Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(model.getTelefono()));
            	startActivity(intent);
            	finish();
            	return true;
//            case R.id.menu_delete:
//                action = Intent.ACTION_DELETE;
//                finish();
//                return true;
            case R.id.menu_back:
                action = Intent.ACTION_DEFAULT;
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}