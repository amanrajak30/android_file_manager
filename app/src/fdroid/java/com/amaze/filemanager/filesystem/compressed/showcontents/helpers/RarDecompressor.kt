

package com.amaze.filemanager.filesystem.compressed.showcontents.helpers

import android.content.Context
import com.amaze.filemanager.asynchronous.asynctasks.compress.UnknownCompressedFileHelperCallable
import com.amaze.filemanager.filesystem.compressed.showcontents.Decompressor

class RarDecompressor(context: Context) : Decompressor(context) {
    override fun changePath(
        path: String,
        addGoBackItem: Boolean,
    ) = UnknownCompressedFileHelperCallable(filePath, addGoBackItem)

    override fun realRelativeDirectory(dir: String): String {
        return ""
    }
}
