/*****************************************************************************
 *  Copyright 2011 Twisted Apps LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 */

package com.twsitedapps.homemanager;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*****************************************************************************
 * QuickSelectArrayAdapter - ArrayAdapter<AppInfo> - Adapter to show home apps 
 * currently installed within a listview. 
 * 
 * @author Russell T Mackler
 * @version 1.0
 * @since 1.0.1.8
 */
public class QuickSelectArrayAdapter extends ArrayAdapter<AppInfo>
{
    private static final String            DEBUG_TAG = QuickSelectArrayAdapter.class.getSimpleName();

    // The activity using this adapter
    private final Activity                 context;

    // The ArrayList of home apps
    private final ArrayList<AppInfo>       listAppInfo;

    /*****************************************************************************
     * QuickSelectArrayAdapter - ArrayAdapter<AppInfo> - Adapter to show home apps 
     * currently installed within a listview. 
     * 
     */
    public QuickSelectArrayAdapter( Activity context, ArrayList<AppInfo> listAppInfo )
    {
        super( context, R.layout.quickselectrow, listAppInfo );
        this.context = context;
        this.listAppInfo = listAppInfo;
    }


    /*
     * (non-Javadoc)
     * @see android.widget.ArrayAdapter#getCount()
     */
    @Override public int getCount()
    {
        return this.listAppInfo.size(); // Set via getCacheImages method
    }


    /*
     * (non-Javadoc)
     * @see android.widget.ArrayAdapter#getItem(int)
     */
    @Override public AppInfo getItem( int position )
    {
        return this.listAppInfo.get( position );
    }


    /*****************************************************************************
     * ViewHolder - Used for UI performance
     *            
     */
    static class ViewHolder
    {
        // The icon associated with the application
        public ImageView iconDrawable;
        
        // The name of the application
        public TextView  txt_appName;
        
        // The memory of the application
        public TextView txt_memoryName;
        
        // The Row RelativeLayout
        public LinearLayout ll;
        
        public TextView txt_default;
    } // End ViewHolder


    /*
     * (non-Javadoc)
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override public View getView( int position, View convertView, ViewGroup parent )
    {
        // ViewHolder will buffer the individual fields of the quick select row layout
        ViewHolder holder;

        // Recycle existing view if passed as parameter
        // This will save memory and time on Android
        // This only works if the base layout for all classes are the same
        View rowView = convertView;
        if( rowView == null )
        {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate( R.layout.quickselectrow, null, true );
            holder = new ViewHolder();
            holder.txt_appName = (TextView) rowView.findViewById( R.id.txt_app );
            holder.txt_memoryName = (TextView) rowView.findViewById( R.id.txt_memory );
            holder.iconDrawable = (ImageView) rowView.findViewById( R.id.appIconImageView );
            holder.ll = (LinearLayout) rowView.findViewById( R.id.mainlinearlayout );
            holder.txt_default = (TextView) rowView.findViewById( R.id.txt_default );
            rowView.setTag( holder );
        }
        else
        {
            holder = (ViewHolder) rowView.getTag();
        }

        // Only if the list of apps is not null display the contents
        if( listAppInfo != null )
        {
            try
            {                
                // Display app values set
                holder.txt_appName.setText( listAppInfo.get( position ).getappName().trim() );
                holder.txt_memoryName.setText( listAppInfo.get( position ).getappMemory() );

                // Set the image if it was cached
                holder.iconDrawable.setImageDrawable( listAppInfo.get( position ).geticonDrawable() );
                
                // If this is the default application
                if( listAppInfo.get( position ).getIsDefault() )
                {
                    if ( StaticConfig.theme == StaticConfig.BLACK )
                    {
                        holder.ll.setBackgroundColor( Color.WHITE );
                        holder.txt_appName.setTextColor( Color.BLACK );
                        holder.txt_memoryName.setTextColor( Color.RED );
                    }
                    else if( StaticConfig.theme == StaticConfig.WHITE )
                    {
                        holder.ll.setBackgroundColor( Color.BLACK );
                        holder.txt_appName.setTextColor( Color.WHITE );
                        holder.txt_memoryName.setTextColor( Color.YELLOW );
                    }
                    else if( StaticConfig.theme == StaticConfig.GREY )
                    {
                        holder.ll.setBackgroundColor( Color.BLACK );
                        holder.txt_appName.setTextColor( Color.DKGRAY );
                        holder.txt_memoryName.setTextColor( Color.YELLOW );
                    }
                    else if( StaticConfig.theme == StaticConfig.CYAN )
                    {
                        holder.ll.setBackgroundColor( Color.BLACK );
                        holder.txt_appName.setTextColor( Color.CYAN );
                        holder.txt_memoryName.setTextColor( Color.CYAN );
                    }
                    else if( StaticConfig.theme == StaticConfig.GREEN )
                    {
                        holder.ll.setBackgroundColor( Color.BLACK );
                        holder.txt_appName.setTextColor( Color.GREEN );
                        holder.txt_memoryName.setTextColor( Color.YELLOW );
                    }
                    else if( StaticConfig.theme == StaticConfig.MAGENTA )
                    {
                        holder.ll.setBackgroundColor( Color.BLACK );
                        holder.txt_appName.setTextColor( Color.MAGENTA );
                        holder.txt_memoryName.setTextColor( Color.YELLOW );
                    }
                }
                else
                {
                    if ( StaticConfig.theme == StaticConfig.BLACK )
                    {
                        holder.ll.setBackgroundColor( Color.BLACK );
                        holder.txt_appName.setTextColor( Color.WHITE );
                        holder.txt_memoryName.setTextColor( Color.YELLOW );
                    }
                    else if( StaticConfig.theme == StaticConfig.WHITE )
                    {
                        holder.ll.setBackgroundColor( Color.WHITE );
                        holder.txt_appName.setTextColor( Color.BLACK );
                        holder.txt_memoryName.setTextColor( Color.RED );
                    }
                    else if( StaticConfig.theme == StaticConfig.GREY )
                    {
                        holder.ll.setBackgroundColor( Color.DKGRAY );
                        holder.txt_appName.setTextColor( Color.BLACK );
                        holder.txt_memoryName.setTextColor( Color.CYAN );
                    }
                    else if( StaticConfig.theme == StaticConfig.CYAN )
                    {
                        holder.ll.setBackgroundColor( Color.CYAN );
                        holder.txt_appName.setTextColor( Color.BLACK );
                        holder.txt_memoryName.setTextColor( Color.RED );
                    }
                    else if( StaticConfig.theme == StaticConfig.GREEN )
                    {
                        holder.ll.setBackgroundColor( Color.GREEN );
                        holder.txt_appName.setTextColor( Color.BLACK );
                        holder.txt_memoryName.setTextColor( Color.BLUE );
                    }
                    else if( StaticConfig.theme == StaticConfig.MAGENTA )
                    {
                        holder.txt_default.setText( "" );
                        holder.ll.setBackgroundColor( Color.MAGENTA );
                        holder.txt_appName.setTextColor( Color.BLACK );
                        holder.txt_memoryName.setTextColor( Color.WHITE );
                    }
                }
            }
            catch( OutOfMemoryError e )
            {
                Log.e( DEBUG_TAG, "getView : OutOfMemoryError : cacher image" );
                e.printStackTrace();
            }
            catch( IndexOutOfBoundsException e )
            {
                Log.e( DEBUG_TAG, "getView : IndexOutOfBoundsException : cacher image" );
                e.printStackTrace();
            }
            catch( NullPointerException e )
            {
                Log.e( DEBUG_TAG, "getView : NullPointerException : cacher image" );
                e.printStackTrace();
            }
        } // End if cacher != null

        return rowView;
    } // End getView
} // End QuickSelectArrayAdapter
// EOF