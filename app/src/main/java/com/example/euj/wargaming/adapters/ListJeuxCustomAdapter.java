package com.example.euj.wargaming.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.euj.wargaming.R;
import com.example.euj.wargaming.entites.ListJeux;

import com.example.euj.wargaming.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListJeuxCustomAdapter extends ArrayAdapter<ListJeux> {
	
	  private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContext;

	  public ListJeuxCustomAdapter(Context context, int resourceId, List<ListJeux> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContext = context;
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	  }
	  
	  //ViewHolder Design Pattern
	  static class ViewHolder {
		    public TextView textTitle, DescText;
		    public ImageView image;
		  }
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  View rowView = convertView;

		  ViewHolder holder = new ViewHolder();
		  //Réutiliser les Views
		  if (rowView == null) {
			rowView = inflater.inflate(resourceId, parent, false);
			  //Configuration du ViewHolder
			  holder.image = (ImageView) rowView.findViewById(R.id.imgArticle);
			  holder.textTitle = (TextView) rowView.findViewById(R.id.titreArticle);
		  		Utils.changeFont(mContext.getAssets(), holder.textTitle);
			  holder.DescText = (TextView) rowView.findViewById(R.id.descArticle);
		  rowView.setTag(holder);
		  }else {
			  //Affecter les données aux Views
			  holder = (ViewHolder) rowView.getTag();
		  }
		  ListJeux article = getItem(position);
		  
		  holder.textTitle.setText(article.getName());
		  holder.DescText.setText(article.getLevel());
		  Picasso.with(mContext).load(article.getImage())
				  .into(holder.image);

		  return rowView;
	  }

	}