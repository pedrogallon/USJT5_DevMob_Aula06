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
import java.util.ArrayList;

import br.usjt.deswebmob.servicedeskcco.R;
/**
 * @author pedrogallon
 */
public class ChamadoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Chamado> chamados;
//    private ArrayList<Fila> filas;

    public ChamadoAdapter(Context context, ArrayList<Chamado> chamados) {
        this.context = context;
        this.chamados = chamados;
//        try {
//            this.filas = ChamadoNetwork.getFilas(null, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

            ImageView imagem = (ImageView) view.findViewById(R.id.imagem_fila);
            TextView numero = (TextView) view.findViewById(R.id.numero_status_chamado);
            TextView datas = (TextView) view.findViewById(R.id.abertura_fechamento_chamado);
            TextView descricao = (TextView) view.findViewById(R.id.descricao_chamado);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.setNumero(numero);
            viewHolder.setDatas(datas);
            viewHolder.setDescricao(descricao);
            viewHolder.setImagem(imagem);
            view.setTag(viewHolder);
        }

        Chamado chamado = chamados.get(position);

        ViewHolder viewHolder = (ViewHolder)view.getTag();


        int id = context.getResources().getIdentifier(chamado.getFila().getFigura(), "drawable", context.getPackageName());
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),id);
        viewHolder.getImagem().setImageBitmap(icon);
        viewHolder.getNumero().setText(String.format("numero: %d - status:%s", chamado.getNumero(), chamado.getStatus()));
        viewHolder.getDatas().setText(String.format("aberture: %tD - fechamento: %tD",
                chamado.getDataAbertura(), chamado.getDataFechamento()));
        viewHolder.getDescricao().setText(chamado.getDescricao());

        return view;
    }

//    private int getFila(int id){
//        for (int i = 0; i<filas.size(); i++){
//            if(filas.get(i).getId() == id)
//                return i;
//        }
//        return -1;
//    }
}
