
package com.amaze.filemanager.test

import android.os.Environment
import com.amaze.filemanager.database.TabHandler
import com.amaze.filemanager.database.models.explorer.Tab
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements

@Implements(TabHandler::class)
class ShadowTabHandler {
    companion object {
        /**
         * Implements [TabHandler.getInstance]
         */
        @JvmStatic @Implementation
        fun getInstance(): TabHandler {
            val retval = mockk<TabHandler>()
            val home = Environment.getExternalStorageDirectory().absolutePath
            every { retval.addTab(any()) } returns Completable.fromCallable { true }
            every { retval.update(any()) } returns Unit
            every { retval.findTab(1) } returns Tab(1, home, home)
            every { retval.findTab(2) } returns Tab(2, home, home)
            every { retval.allTabs } returns emptyArray()
            return retval
        }
    }
}
