package es.correointeligente.cipostal.cimobile.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import es.correointeligente.cipostal.cimobile.R;
import es.correointeligente.cipostal.cimobile.Util.BaseActivity;
import es.correointeligente.cipostal.cimobile.Util.DBHelper;
import es.correointeligente.cipostal.cimobile.Util.Util;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    Toolbar mToolbar;
    Button mNuevaNotificacion;
    Button mCargarReparto;
    Button mCerrarSesion;
    Button mResumenReparto;
    Button mResetearAPP;
    DBHelper dbHelper;
    String usuarioFTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.loadLayoutCurrentSession();

        mNuevaNotificacion = (Button) findViewById(R.id.button_nueva_notificacion);
        mNuevaNotificacion.setOnClickListener(this);

        mCargarReparto = (Button) findViewById(R.id.button_cargar_reparto);
        mCargarReparto.setOnClickListener(this);

        mCerrarSesion = (Button) findViewById(R.id.button_cerrar_sesion);
        mCerrarSesion.setOnClickListener(this);

        mResumenReparto = (Button) findViewById(R.id.button_resumen_reparto);
        mResumenReparto.setOnClickListener(this);

        mResetearAPP = (Button) findViewById(R.id.button_resetear_app);
        mResetearAPP.setOnClickListener(this);
        usuarioFTP = Util.obtenerValorPreferencia(Util.CLAVE_PREFERENCIAS_FTP_USER, getBaseContext(), String.class.getSimpleName());

        if (usuarioFTP.contains("ultimaversion")){
            mResetearAPP.setVisibility(View.VISIBLE);
        } else {
            mResetearAPP.setVisibility(View.INVISIBLE);
        }



        // Obtenemos la instancia del helper de la base de datos
        dbHelper = new DBHelper(this);

    }

    @Override
    protected int getLayoutResocurce() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        v.vibrate(20);

        Intent i = null;
        switch (view.getId()) {

            case R.id.button_cargar_reparto:
                i = new Intent(getBaseContext(), CargarRepartoActivity.class);
                startActivity(i);
                break;

            case R.id.button_resumen_reparto:
                i = new Intent(getBaseContext(), ResumenRepartoActivity.class);
                startActivity(i);
                break;

            case R.id.button_nueva_notificacion:
                if(!Util.existeFirmaNotificador(obtenerCodigoNotificador())) {
                    // Si no hay firma del notificador mandamos que firme
                    i = new Intent(getBaseContext(), FirmaNotificadorActivity.class);
                } else {
                    // Si ya hay firma entonces mostramos la lista de notificaciones para empezar el reparto
                    i = new Intent(getBaseContext(), ListaNotificacionesActivity.class);
                }
                startActivity(i);
                break;

            case R.id.button_cerrar_sesion:
                this.cerrarSesion();
                break;

            case R.id.button_resetear_app:
                usuarioFTP = Util.obtenerValorPreferencia(Util.CLAVE_PREFERENCIAS_FTP_USER, getBaseContext(), String.class.getSimpleName());
                if (usuarioFTP.contains("ultimaversion")){
                    mResetearAPP.setVisibility(View.VISIBLE);
                } else {
                    mResetearAPP.setVisibility(View.INVISIBLE);
                }
                if (usuarioFTP.contains("ultimaversion")){
                    this.avisoRegenerarBDA();
                } else {
                    break;
                }

            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_configuracion_aplicacion, menu);
        return true;
    }

    // Logica de la barra de herramientas
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_preferences:
                Intent i = new Intent(getBaseContext(), PreferenciasActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.cerrarSesion();
    }

    /**
     * Método privado que muestra un dialogo donde se pide confirmación para cerrar la sesión
     */
    private void cerrarSesion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_logout);
        builder.setMessage(R.string.detalle_logout);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor e = sp.edit();
                e.clear();
                e.commit();
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int wich) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    /**
     * Método privado que muestra un dialogo donde se pide confirmación para regenerar la base de datos
     */
    private void avisoRegenerarBDA() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_regenerarBDA);
        builder.setMessage(R.string.detalle_regenerar_bda);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.regeneraBDA();
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int wich) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }
}
