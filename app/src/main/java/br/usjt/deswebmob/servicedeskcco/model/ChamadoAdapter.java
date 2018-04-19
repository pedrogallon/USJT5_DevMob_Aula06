package br.usjt.deswebmob.servicedeskcco.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.usjt.deswebmob.servicedeskcco.R;
import br.usjt.deswebmob.servicedeskcco.controller.MainActivity;

/**
 * @author pedrogallon
 */
public class ChamadoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Chamado> chamados;
    private ArrayList<Fila> filas;

    public ChamadoAdapter(Context context, ArrayList<Chamado> chamados) {
        this.context = context;
        this.chamados = chamados;
        this.filas = MainActivity._filas;
    }

    @Override
    public int getCount() {
        return chamados.size();
    }

    @Override
    public Object getItem(int position) {
        return chamados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_chamado, parent, false);

            ImageView imagem = view.findViewById(R.id.imagem_fila);
            TextView numero = view.findViewById(R.id.numero_status_chamado);
            TextView datas = view.findViewById(R.id.abertura_fechamento_chamado);
            TextView descricao = view.findViewById(R.id.descricao_chamado);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.setNumero(numero);
            viewHolder.setDatas(datas);
            viewHolder.setDescricao(descricao);
            viewHolder.setImagem(imagem);
            view.setTag(viewHolder);
        }

        Chamado chamado = chamados.get(position);

        ViewHolder viewHolder = (ViewHolder)view.getTag();

        try {
            viewHolder.getImagem().setImageBitmap(filas.get(Fila.getFila(filas, chamado.getFila().getId())).getImagem());
        } catch(Exception e){
            viewHolder.getImagem().setImageDrawable(context.getDrawable(R.drawable.ic_not_found));
        }
        DateFormat formatter = new SimpleDateFormat(Chamado.DATE_PATTERN);
        viewHolder.getNumero().setText(String.format("numero: %d - status:%s", chamado.getNumero(), chamado.getStatus()));
        viewHolder.getDatas().setText(String.format("abertura: %s - fechamento: %s",
                formatter.format(chamado.getDataAbertura()),
                chamado.getDataFechamento() == null?" ":formatter.format(chamado.getDataFechamento())));
        viewHolder.getDescricao().setText(chamado.getDescricao());

        return view;
    }
}
