/*
 * Copyright (C) 2016 Álinson Santos Xavier <isoron@gmail.com>
 *
 * This file is part of Loop Habit Tracker.
 *
 * Loop Habit Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Loop Habit Tracker is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.isoron.uhabits.receivers;

import android.content.*;
import android.net.*;
import android.support.annotation.*;

import org.isoron.uhabits.*;
import org.isoron.uhabits.sync.*;

import static android.content.Context.*;

public class ConnectivityReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(@Nullable Context context, @Nullable Intent intent)
    {
        if (context == null) return;
        if (intent == null) return;

        AppComponent component =
            ((HabitsApplication) context.getApplicationContext()).getComponent();

        NetworkInfo networkInfo =
            ((ConnectivityManager) context.getSystemService(
                CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        boolean isConnected =
            (networkInfo != null && networkInfo.isConnectedOrConnecting());

        SyncManager syncManager = component.getSyncManager();
        syncManager.onNetworkStatusChanged(isConnected);
    }
}
