package es.correointeligente.cipostal.cimobile.Holders;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

import es.correointeligente.cipostal.cimobile.Activities.DetalleNotificacionActivity;
import es.correointeligente.cipostal.cimobile.Activities.ListaNotificacionesActivity;
import es.correointeligente.cipostal.cimobile.Activities.NuevaNotificacionActivity;
import es.correointeligente.cipostal.cimobile.Model.Notificacion;
import es.correointeligente.cipostal.cimobile.R;
import es.correointeligente.cipostal.cimobile.Util.Util;


public class NotificacionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView referencia, referenciaSCB, nombre, direccion, resultado1, resultado2;
    CheckBox marcada;
    LinearLayout ll_contentItems;
    TableLayout tbl_resultados;
    CardView cardView;
    Notificacion notificacion;
    Context context;
    ListaNotificacionesActivity listaNotificacionesActivity;

    public NotificacionViewHolder(View itemView) {
        super(itemView);
    }

    public NotificacionViewHolder(View itemView, Context context, ListaNotificacionesActivity listaNotificacionesActivity) {
        super(itemView);


        this.context = context;
        this.listaNotificacionesActivity = listaNotificacionesActivity;

        referencia = (TextView) itemView.findViewById(R.id.textView_cardView_referencia);
        referenciaSCB = (TextView) itemView.findViewById(R.id.textView_cardView_referenciaSCB);
        nombre = (TextView) itemView.findViewById(R.id.textView_cardView_nombre);
        direccion = (TextView) itemView.findViewById(R.id.textView_cardView_direccion);
        resultado1 = (TextView) itemView.findViewById(R.id.textView_cardView_resutado_1);
        resultado2 = (TextView) itemView.findViewById(R.id.textView_cardView_resutado_2);
        marcada = (CheckBox) itemView.findViewById(R.id.checkBox_cardView_marcada);
        ll_contentItems = (LinearLayout) itemView.findViewById(R.id.linearLayout_cardView_layout);
        cardView = (CardView) itemView.findViewById(R.id.cardView_notificacion);
        tbl_resultados = (TableLayout) itemView.findViewById(R.id.tableLayout_cardView_resultados);

        itemView.setOnClickListener(this);
        marcada.setOnClickListener(this);
    }


    public void bindData(Notificacion notificacion) {

        this.notificacion = notificacion;

        String textoResultado1 = StringUtils.defaultIfBlank(notificacion.getResultado1(), "") + " " + StringUtils.defaultIfBlank(notificacion.getDescResultado1(), "");
        String textoResultado2 = StringUtils.defaultIfBlank(notificacion.getResultado2(), "") + " " + StringUtils.defaultIfBlank(notificacion.getDescResultado2(), "");

        referencia.setText(notificacion.getReferencia());
        referenciaSCB.setText(notificacion.getReferenciaSCB());
        nombre.setText(notificacion.getNombre());
        direccion.setText(notificacion.getDireccion());
        resultado1.setText(textoResultado1.toUpperCase());
        resultado2.setText(textoResultado2.toUpperCase());
        marcada.setChecked(notificacion.getMarcada());
        cardView.setCardBackgroundColor(ContextCompat.getColor(context, notificacion.getBackgroundColor()));
        cardView.setUseCompatPadding(true);
        cardView.setCardElevation(10);
        cardView.setRadius(10);
    }

    @Override
    public void onClick(View view) {

        Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(20);

        if (view.getId() == R.id.checkBox_cardView_marcada) {
            notificacion.setMarcada(((CheckBox) view).isChecked());
            notificacion.setTimestampMarcada(null);
            if(notificacion.getMarcada()) {
                Long timestamp = Calendar.getInstance().getTimeInMillis();
                notificacion.setTimestampMarcada(timestamp.toString());
            }

            // Se llama a la actividad principal para persistir los datos
            listaNotificacionesActivity.actualizarNotificacionMarcada(notificacion);

        } else {
            // Dependiendo si es una notificacion a gestionar o una notificacion ya gestionada se llama a una pantalla u otra
            Intent intent = null;
            Integer request = null;

            // NO LISTA
            if (!notificacion.getEsLista()) {
                // Requiere segunda visita y no la hay
                if ((notificacion.getSegundoIntento() != null && notificacion.getSegundoIntento() && notificacion.getResultado2() == null) ||
                    // Requiere primera vista y no la hay
                   ((notificacion.getSegundoIntento() == null || !notificacion.getSegundoIntento())) && notificacion.getResultado1() == null) {
                    intent = new Intent(context, NuevaNotificacionActivity.class);
                    request = Util.REQUEST_CODE_NOTIFICATION_RESULT;
                } else {
                    intent = new Intent(context, DetalleNotificacionActivity.class);
                    request = Util.REQUEST_CODE_NOTIFICATION_DELETE_RESULT;
                }
            // Lista
            }else {
                // Certificada
               if (notificacion.getEsCertificado()){
                  if  (notificacion.getSegundoIntento()) {
                      intent = new Intent(context, NuevaNotificacionActivity.class);
                      request = Util.REQUEST_CODE_NOTIFICATION_DELETE_RESULT;
                  } else {
                      intent = new Intent(context, DetalleNotificacionActivity.class);
                      request = Util.REQUEST_CODE_NOTIFICATION_DELETE_RESULT;
                  }
               // No certificada
               } else {
                   // Acabada
                   if (notificacion.getResultado2() != null && !notificacion.getResultado2().isEmpty()) {
                       intent = new Intent(context, DetalleNotificacionActivity.class);
                       request = Util.REQUEST_CODE_NOTIFICATION_DELETE_RESULT;
                       // No acabada
                   }  else {
                    intent = new Intent(context, NuevaNotificacionActivity.class);
                    request = Util.REQUEST_CODE_NOTIFICATION_DELETE_RESULT;
                }
               }
            }

                intent.putExtra("idNotificacion", notificacion.getId());
                intent.putExtra("posicionAdapter", getAdapterPosition());
                listaNotificacionesActivity.startActivityForResult(intent, request);
            }
        }


    }
