package es.iesnervion.android.ignacio.notificaciones;

import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Notificacion extends FragmentActivity {
	
private Button btnNotificacion, btnAlerta, btnConfirmacion, btnSeleccion, btnPersonal, btnNotificacionDescarga; 
	
	private static final int NOTIF_ALERTA_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notificacion);
		btnNotificacion = (Button)findViewById(R.id.btnEnviarNoti);
		btnNotificacionDescarga = (Button)findViewById(R.id.btnEnviarNotiDescarga);
		btnAlerta = (Button)findViewById(R.id.btnAlerta);
		btnConfirmacion = (Button)findViewById(R.id.btnConfirmacion);
		btnSeleccion = (Button)findViewById(R.id.btnSeleccion);
		btnPersonal = (Button)findViewById(R.id.btnPersonal);
		
		/* NOTIFICACI�N	*/
        btnNotificacion.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				NotificationCompat.Builder builder =
					    new NotificationCompat.Builder(Notificacion.this)
					        .setSmallIcon(android.R.drawable.stat_sys_warning)
					        .setLargeIcon((((BitmapDrawable)getResources()
					            .getDrawable(R.drawable.ic_launcher)).getBitmap()))
					        .setContentTitle("Atenci�n")
					        .setContentText("Ejemplo de notificaci�n.")
					        .setContentInfo("1")
					        .setTicker("Notificaci�n nueva");
				
				Intent notIntent =
					    new Intent(Notificacion.this, Notificacion.class);
					 
					PendingIntent contIntent =
					    PendingIntent.getActivity(
					    		Notificacion.this, 0, notIntent, 0);
					 
					builder.setContentIntent(contIntent);
					
					
					NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
					mNotificationManager.notify(NOTIF_ALERTA_ID, builder.build());
			}
        });
        
        /* NOTIFICACI�N DESCARGA	*/
        btnNotificacionDescarga.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final NotificationManager mNotifyManager =
				        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				final NotificationCompat.Builder builder =
					    new NotificationCompat.Builder(Notificacion.this);
				builder.setContentTitle("Descargar")
				    .setContentText("En proceso")
				    .setSmallIcon(R.drawable.ic_launcher);
				new Thread(
				    new Runnable() {
				        @Override
				        public void run() {
				            int incr;
				            for (incr = 0; incr <= 100; incr+=5) {
				            	//builder.setProgress(100, incr, false);
				            	builder.setProgress(100, incr, true);
				            	mNotifyManager.notify(0, builder.build());
				                        try {
				                            Thread.sleep(3000);
				                        } catch (InterruptedException e) {
				                            Log.d("DESCARGA", "sleep failure");
				                        }
				            }

				            builder.setContentText("Descarga completada")
				                    .setProgress(0,0,false);
				            mNotifyManager.notify(NOTIF_ALERTA_ID, builder.build());
				        }
				    }
				).start();
			}
        });
        
       
        
        /* DI�LOGO ALERTA	*/
        btnAlerta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                   DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager, "tagAlerta");
            }
        });
        
        /* DI�LOGO CONFIRMACI�N	*/
        btnConfirmacion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
			    DialogoConfirmacion dialogo = new DialogoConfirmacion();
			    dialogo.show(fragmentManager, "tagConfirmacion");
			}
		});
        
        /* DI�LOGO SELECCI�N	*/
        btnSeleccion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
			    DialogoSeleccion dialogo = new DialogoSeleccion();
			    dialogo.show(fragmentManager, "tagSeleccion");
			}
		});
        
        /* DI�LOGO PERSONAL	*/
        btnPersonal.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
			    DialogoPersonal dialogo = new DialogoPersonal();
			    dialogo.show(fragmentManager, "tagPersonalizado");
			}
		});
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notificacion, menu);
		return true;
	}

}
