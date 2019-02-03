package com.lang.ayu.earthquake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
        public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
            super(context, 0, earthquakes);
        }
          String offsetLoc;
          String primaryLoc;
          private static final String Location_Separator = " of ";
    @Override
    public View getView(int postion, View convertView , ViewGroup parent) {
        View lisItemView = convertView;
        if (lisItemView == null) {
            lisItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(postion);

        TextView magnitudeView = (TextView) lisItemView.findViewById(R.id.magnitude);
        String formattedmag = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText(formattedmag);
        GradientDrawable magCircle = (GradientDrawable)magnitudeView.getBackground();
        int magColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magCircle.setColor(magColor);

        TextView offsetView = (TextView) lisItemView.findViewById(R.id.offset);
        TextView primaryView = (TextView) lisItemView.findViewById(R.id.primary);
        String location = currentEarthquake.getLocation();
        if (location.contains(Location_Separator)) {
            String[] parts = location.split(Location_Separator);
            offsetLoc = parts[0] + Location_Separator;
            primaryLoc = parts[1];
        } else {
            offsetLoc = getContext().getString(R.string.near);
            primaryLoc = location;
        }
        offsetView.setText(offsetLoc);
        primaryView.setText(primaryLoc);

        TextView dateView = (TextView) lisItemView.findViewById(R.id.date);
        Date dateObject = new Date(currentEarthquake.getTimeInSeconds());
        String dateToDisplay = formatDate(dateObject);
        dateView.setText(dateToDisplay);

        TextView timeView = (TextView) lisItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);
        return lisItemView;
      }

      private String formatMagnitude(double mag)
      {
          DecimalFormat magFormat = new DecimalFormat("0.0");
          return magFormat.format(mag);
      }

      private String formatDate(Date dateObject)
      {
          SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd , yyyy");
          return dateFormat.format(dateObject);
      }

      private String formatTime(Date dateObject)
      {
          SimpleDateFormat timeFormat =new SimpleDateFormat("h:mm a");
          return timeFormat.format(dateObject);
      }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.mag1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.mag2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.mag3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.mag4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.mag5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.mag6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.mag7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.mag8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.mag9;
                break;
            default:
                magnitudeColorResourceId = R.color.mag10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
    }

